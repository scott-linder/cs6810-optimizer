package iloc;

/**
 * <p>Title: SwriteInstruction.java</p>
 *
 * <p>Description: A string write instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class SwriteInstruction extends OneAddressIlocInstruction {
	
  /**
   * Create a string write instruction
   * @param source the start address of the string
   */
  public SwriteInstruction(VirtualRegisterOperand source) {
    this.source = source;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this iloc.IlocInstruction method
   */
  public String getOpcode() {
    return "swrite";
  }
}
