package iloc;

/**
 * <p>
 * Title: OneAddressInstruction.java
 * </p>
 *
 * <p>
 * Description: An abstract class for instructions with one operand
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
public abstract class OneAddressIlocInstruction extends IlocInstruction {
	Operand source; // the operand

	/**
	 * Return the iloc opcode
	 */
	public abstract String getOpcode();

	/**
	 * Print the instruction to System.out
	 */
	public void emit() {
		if (label != null)
			System.out.print(label + ":");

		System.out.println("\t" + getOpcode() + " " + source.toString());
	}

	/**
	 * Return the source operand.
	 * 
	 * @return Operand
	 */
	public Operand getOperand() {
		return source;
	}

	/**
	 * Return false, since no operands modified
	 * 
	 * @param operand
	 * @return boolean
	 */
	public boolean operandIsLValue(Operand operand) {
		return false;
	}

	/**
	 * Return true if parameter is the source operand
	 * 
	 * @param operand
	 *            operand to check
	 * @return boolean
	 */
	public boolean operandIsRValue(Operand operand) {
		return operand == source;
	}
}
