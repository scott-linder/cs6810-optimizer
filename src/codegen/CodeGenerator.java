/**
 * 
 */
package codegen;

import java.io.FileReader;
import java.util.HashSet;

import cfg.BasicBlock;
import parser.IlocParser;
import parser.ParseException;

import iloc.IlocProgram;


/**
 * @author carr
 *
 */
public final class CodeGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws java.io.FileNotFoundException,
    	java.io.IOException {
		
        if (args.length == 0) {
      	   System.err.println("No file name specified");
             System.err.println("Command Syntax: optimizer <file>");
             System.exit(-1);
        }
        
    	String fileName = new String(args[args.length - 1]);

    	FileReader ilocFile = new FileReader(fileName);
                
    	IlocParser parser = new IlocParser(ilocFile);

        //
        // parse the input file and return the intermediate
        //

        IlocProgram prog;
        
        try {
          prog = IlocParser.program();
          HashSet<BasicBlock> blocks = BasicBlock.findBasicBlocks(prog.program);
        }
        catch (ParseException e) {
          System.err.println("Invalid iloc file "+ fileName+": "+e.getMessage());
        }

        System.out.println("Parsed.");
        
       
	}

}