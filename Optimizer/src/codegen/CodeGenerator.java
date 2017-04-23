/**
 * 
 */
package codegen;

import java.io.FileReader;
import java.util.ArrayList;

import iloc.IlocFrame;
import iloc.IlocInstruction;
import iloc.IlocProgram;
import parser.IlocParser;
import parser.ParseException;

/**
 * @author carr
 *
 */
public final class CodeGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws java.io.FileNotFoundException, java.io.IOException {

		if (args.length != 2 || !(args[0].equalsIgnoreCase("-A") || args[0].equalsIgnoreCase("-B"))) {
			System.err.println("Usage: optimizer <-A|-B> <file>");
			System.exit(-1);
		}

		String fileName = new String(args[args.length - 1]);
		FileReader ilocFile = new FileReader(fileName);
		IlocParser parser = new IlocParser(ilocFile);
		IlocProgram program = null;

		try {
			program = parser.program();
		} catch (ParseException e) {
			System.err.println("Invalid iloc file " + fileName + ": " + e.getMessage());
		}

		if (args[0].equalsIgnoreCase("-A")) {
			for (IlocFrame frame : IlocFrame.findFrames(program)) {
				ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(frame);
				BasicBlock.constructCFG(blocks);
				BasicBlock.constructDT(blocks);
				BasicBlock.analyzeLiveness(blocks);
				BasicBlock.computeDF(blocks);
				BasicBlock.insertPhiNodes(blocks);
				BasicBlock.optSSA(blocks);
				BasicBlock.unSSA(blocks);
				BasicBlock.analyzeLiveness(blocks);
				BasicBlock.removeDeadCode(blocks);
			}
		} else /* -B */ {
			for (IlocFrame frame : IlocFrame.findFrames(program)) {
				ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(frame);
				for (BasicBlock b : blocks)
					BasicBlock.localValueNumbering(b);
				BasicBlock.constructCFG(blocks);
				BasicBlock.analyzeLiveness(blocks);
				BasicBlock.removeDeadCode(blocks);
			}
		}

		System.out.println(".data");
		for (Object i : program.dataSection.pseudoOps)
			((IlocInstruction) i).emit();
		System.out.println(".text");
		for (IlocInstruction i = program.head; i != null; i = i.getNext())
			i.emit();
	}

}
