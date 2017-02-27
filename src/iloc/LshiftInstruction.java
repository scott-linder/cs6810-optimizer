package iloc;

/**
 * <p>Title: LShiftInstruction.java</p>
 *
 * <p>Description: A left shift instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class LshiftInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a left shift instruction.
   * @param source1 the register to shift
   * @param source2 the register shift value
   * @param dest the destination register
   */
  public LshiftInstruction(VirtualRegisterOperand source1,
                           VirtualRegisterOperand source2,
                           VirtualRegisterOperand dest) {
    this.source1 = source1;
    this.source2 = source2;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "lshift";
  }
}
