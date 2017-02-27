package iloc;

/**
 * <p>Title: FdivInstruction.java</p>
 *
 * <p>Description: An iloc floating-point divide instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FdivInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create an fp divide instruction
   * 
   * @param source1 first source register
   * @param source2 second source register
   * @param dest destination register
   */
  public FdivInstruction(VirtualRegisterOperand source1,
                         VirtualRegisterOperand source2,
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
    return "fdiv";
  }

}
