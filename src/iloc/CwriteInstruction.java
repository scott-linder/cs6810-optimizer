package iloc;

/**
 * <p>
 * Title: CwriteInstruction.java
 * </p>
 *
 * <p>
 * Description: An iloc character write instruction.
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
public class CwriteInstruction extends OneAddressIlocInstruction {

	/**
	 * Create a character write instruction.
	 * 
	 * @param source
	 *            the register containing the character to be written
	 */
	public CwriteInstruction(VirtualRegisterOperand source) {
		this.source = source;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this iloc.IlocInstruction method
	 */
	public String getOpcode() {
		return "cwrite";
	}

}
