package iloc;

/**
 * <p>Title: NotInstruction.java</p>
 *
 * <p>Description: A not instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class NotInstruction extends TwoAddressIlocInstruction {
	
  /**
   * Create a not instruction.
   * 
   * @param source the source register
   * @param dest the destination register
   */
  public NotInstruction(VirtualRegisterOperand source,
                        VirtualRegisterOperand dest) {
    this.source = source;
    this.dest = dest;
  }

  /**
   * Return the iloc opcode.
   *
   * @return String
   */
  public String getOpcode() {
    return "not";
  }

}
