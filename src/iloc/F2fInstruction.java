package iloc;

/**
 * <p>Title: F2fInstruction.java</p>
 *
 * <p>Description: A floating-point copy instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class F2fInstruction extends CopyInstruction {
	
  /**
   * Create a fp copy instruction
   * @param source the source virtual register
   * @param dest the destination virtual register
   */
  public F2fInstruction(VirtualRegisterOperand source,
                        VirtualRegisterOperand dest) {
    this.source = source;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode
   *
   * @return String
    */
  public String getOpcode() {
    return "f2f";
  }

}
