package iloc;

/**
 * <p>Title: FloadAIInstruction.java</p>
 *
 * <p>Description: An iloc floating-point load address + immediate instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FloadAIInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a floating point address+immediate instruction
   * @param source1 the base address register
   * @param source2 the immediate value
   * @param dest the destination register.
   */
  public FloadAIInstruction(VirtualRegisterOperand source1,
                            ImmediateOperand source2,
                            VirtualRegisterOperand dest) {
    this.source1 = source1;
    this.source2 = source2;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode.
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "floadAI";
  }

}
