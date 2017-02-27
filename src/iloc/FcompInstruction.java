package iloc;


/**
 * <p>Title: FcompInstruction.java</p>
 *
 * <p>Description: An iloc floating-point compare instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FcompInstruction extends ThreeAddressIlocInstruction {

  /**
   * Create a fp compare instruction
   * @param source1 first source register
   * @param source2 second source register
   * @param dest destination register
   */
  public FcompInstruction(VirtualRegisterOperand source1,
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
    return "fcomp";
  }

}
