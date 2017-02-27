package iloc;

/**
 * <p>Title: LoadInstruction.java</p>
 *
 * <p>Description: A load instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class LoadInstruction extends TwoAddressIlocInstruction {
	
  /**
   * Create a load instruction
   * @param source the load address register
   * @param dest the destination register
   */
  public LoadInstruction(VirtualRegisterOperand source,
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
    return "load";
  }

}
