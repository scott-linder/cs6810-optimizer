/**
 * 
 */
package codegen;

import java.io.FileReader;
import java.util.ArrayList;

import cfg.BasicBlock;
import iloc.IlocFrame;
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

		if (args.length == 0) {
			System.err.println("No file name specified");
			System.err.println("Command Syntax: optimizer <file>");
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

		for (IlocFrame frame : IlocFrame.findFrames(program)) {
			ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(frame);
			BasicBlock.constructCFG(blocks);
			BasicBlock.constructDT(blocks);
			BasicBlock.analyzeLiveness(blocks);
		}

	}

}
