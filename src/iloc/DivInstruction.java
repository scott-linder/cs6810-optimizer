package iloc;

/**
 * <p>Title: DivInstruction.java</p>
 *
 * <p>Description: An iloc divide instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class DivInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a divide instruction.
   * @param source1 the left source virtual register operand
   * @param source2 the right source virtual register operand
   * @param dest the destination virtual register operand
   */
  public DivInstruction(VirtualRegisterOperand source1,
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
    return "div";
  }

}
