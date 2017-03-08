package iloc;

/**
 * <p>
 * Title: FwriteInstruction.java
 * </p>
 *
 * <p>
 * Description: A floating-point write instruction.
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
public class FwriteInstruction extends OneAddressIlocInstruction {

	/**
	 * Create a loating-point write instruction
	 * 
	 * @param source
	 *            the value to write
	 */
	public FwriteInstruction(VirtualRegisterOperand source) {
		this.source = source;
	}

	/**
	 * Return the iloc opcode.
	 *
	 * @return String
	 * @todo Implement this iloc.IlocInstruction method
	 */
	public String getOpcode() {
		return "fwrite";
	}
}
