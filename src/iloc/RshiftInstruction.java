package iloc;

/**
 * <p>Title: RShiftInstruction.java</p>
 *
 * <p>Description: A right shift instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class RshiftInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a right shift instruction
   * @param source1 the register to shift
   * @param source2 the register w/ the shift value
   * @param dest the destination register
   */
  public RshiftInstruction(VirtualRegisterOperand source1,
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
   */
  public String getOpcode() {
    return "rshift";
  }
}
