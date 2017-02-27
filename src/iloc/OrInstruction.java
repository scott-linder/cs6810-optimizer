package iloc;

/**
 * <p>Title: OrInstruction.java</p>
 *
 * <p>Description: A bitwise-or instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class OrInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a bitwise-or instruction.
   * 
   * @param source1 the first source register
   * @param source2 the second source register
   * @param dest the destination register
   */
  public OrInstruction(VirtualRegisterOperand source1,
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
    return "or";
  }
}
