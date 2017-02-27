package iloc;

import java.util.Hashtable;

/**
 * <p>Title: AddIInstruction.java</p>
 *
 * <p>Description: An object for an add immediate instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */


public class AddIInstruction extends ThreeAddressIlocInstruction {
	
	/**
	 * Create an addI instruction.
	 * 
	 * @param VirtualRegisterOperand left operand
	 * @param ImmediateOperand right operand
	 * @param VirtualRegisterOperand destination operand
	 */
  public AddIInstruction(VirtualRegisterOperand source1,
                         ImmediateOperand source2,
                         VirtualRegisterOperand dest) {
    this.source1 = source1;
    this.source2 = source2;
    this.dest = dest;
  }

  /**
   * Return the instruction opcode.
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "addI";
  }

}
