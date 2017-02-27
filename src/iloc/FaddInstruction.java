package iloc;

/**
 * <p>Title: FaddInstruction.java</p>
 *
 * <p>Description: An iloc floating-point add instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FaddInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create an fp add instruction
   * @param source1 a source virtual register
   * @param source2 a source virtual register
   * @param dest a destination virtual register
   */
  public FaddInstruction(VirtualRegisterOperand source1,
                         VirtualRegisterOperand source2,
                         VirtualRegisterOperand dest) {
    this.source1 = source1;
    this.source2 = source2;
    this.dest = dest;
  }

  /**
   * Return the iloc opcode
   */
  public String getOpcode() {
    return "fadd";
  }
}
