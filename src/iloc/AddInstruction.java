package iloc;

import java.util.Vector;
import java.util.Hashtable;

/**
 * <p>
 * Title: AddInstruction.java
 * </p>
 *
 * <p>
 * Description: An object for an add instruction
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class AddInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create an add instruction.
	 * 
	 * @param source1
	 *            the left operand
	 * @param source2
	 *            the right operand
	 * @param dest
	 *            the destination operand
	 */
	public AddInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2, VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode for an add
	 * 
	 * @return String
	 */
	public String getOpcode() {
		return "add";
	}

}
