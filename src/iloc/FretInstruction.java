package iloc;

/**
 * <p>
 * Title: FretInstruction.java
 * </p>
 *
 * <p>
 * Description: A floating-point return instruction
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
public class FretInstruction extends OneAddressIlocInstruction {

	/**
	 * Create a floating-point return instruction
	 * 
	 * @param source
	 *            the register containing the return value.
	 */
	public FretInstruction(Operand source) {
		this.source = source;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "fret";
	}

}
