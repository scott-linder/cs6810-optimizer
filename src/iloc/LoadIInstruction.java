package iloc;

/**
 * <p>Title: LoadIInstruction.java</p>
 *
 * <p>Description: A load immediate instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class LoadIInstruction extends CopyInstruction {
	
  /**
   * Create a load immediate instruction.
   * 
   * @param source the immediate value
   * @param dest the destination register
   */
  public LoadIInstruction(ImmediateOperand source,
                          VirtualRegisterOperand dest) {
    this.source = source;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode.
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "loadI";
  }

}
