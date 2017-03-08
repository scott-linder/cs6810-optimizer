package iloc;

import java.util.Vector;
import java.util.Hashtable;

/**
 * <p>
 * Title: AndInstruction.java
 * </p>
 *
 * <p>
 * Description: An and iloc instruction.
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Michigan Technological University
 * </p>
 *
 * @author Steven M. Carr
 * @version 1.0
 */
public class AndInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create an and instruction.
	 * 
	 * @param source1
	 *            left operand
	 * @param source2
	 *            right operand
	 * @param dest
	 *            destination operand
	 */
	public AndInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2, VirtualRegisterOperand dest) {
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
		return "and";
	}

}
