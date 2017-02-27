package iloc;

/**
 * <p>Title: FloadInstruction.java</p>
 *
 * <p>Description: An iloc floating-point load instruction. </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FloadInstruction extends TwoAddressIlocInstruction {
	
  /**
   * Create a floating-point load instruction
   * 
   * @param source the address register
   * @param dest the destination register
   */
  public FloadInstruction(VirtualRegisterOperand source,
                          VirtualRegisterOperand dest) {
    this.source = source;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "fload";
  }
}
