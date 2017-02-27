package iloc;

/**
 * <p>Title: SubInstruction.java</p>
 *
 * <p>Description: A subtract instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class SubInstruction extends ThreeAddressIlocInstruction {
  public SubInstruction(VirtualRegisterOperand source1,
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
    return "sub";
  }
}
