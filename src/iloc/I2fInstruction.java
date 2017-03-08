package iloc;

/**
 * <p>
 * Title: I2fInstruction.java
 * </p>
 *
 * <p>
 * Description: Convert an integer to a floating-point value
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
public class I2fInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create an int-to-float instruction.
	 * 
	 * @param source
	 *            the source register
	 * @param dest
	 *            the destination register
	 */
	public I2fInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "i2f";
	}
}
