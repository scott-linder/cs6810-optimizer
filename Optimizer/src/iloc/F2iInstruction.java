package iloc;

/**
 * <p>
 * Title: F2iInstruction.java
 * </p>
 *
 * <p>
 * Description: An iloc instruction to convert an integer into a floating-point
 * value.
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
public class F2iInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create an fp to int conversion instruction.
	 * 
	 * @param source
	 *            the source fp virtual register
	 * @param dest
	 *            the destination integer virtual register.
	 */
	public F2iInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode.
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "f2i";
	}
}
