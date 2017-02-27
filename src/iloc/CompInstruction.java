package iloc;

/**
 * <p>Title: CompInstruction.java</p>
 *
 * <p>Description: A compare instruction in iloc. </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class CompInstruction extends ThreeAddressIlocInstruction {

  public static final int EQUAL = 0;     // compare equal result
  public static final int LESS_THAN = 1; // comare less than result
  public static final int GREATER_THAN = 2; // compare greater than result

  /**
   * Create a compare instruction.
   * 
   * @param source1 the left source register
   * @param source2 the right source register
   * @param dest the destination register
   */
  public CompInstruction(VirtualRegisterOperand source1,
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
	  return "comp";
  }
}
