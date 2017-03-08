package iloc;

/**
 * <p>
 * Title: FstoreInstruction.java
 * </p>
 *
 * <p>
 * Description: A floating point store instruction.
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
 * @author Steve Carr
 * @version 1.0
 */
public class FstoreInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create a floating point store instruction
	 * 
	 * @param source
	 *            the register with the value to be stored
	 * @param dest
	 *            the address register
	 */
	public FstoreInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "fstore";
	}
}
