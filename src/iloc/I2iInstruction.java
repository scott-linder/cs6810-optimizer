package iloc;

/**
 * <p>
 * Title: I2iInstruction
 * </p>
 *
 * <p>
 * Description: A integer move operation
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
public class I2iInstruction extends CopyInstruction {

	/**
	 * Create an integer move instruction
	 * 
	 * @param source
	 *            the source register
	 * @param dest
	 *            the destination register
	 */
	public I2iInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "i2i";
	}

}
