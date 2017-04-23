package iloc;

import java.util.Vector;

/**
 * <p>
 * Title: VariableAddressIlocInsruction.java
 * </p>
 *
 * <p>
 * Description: An abstract class for instruction with a variable number of
 * operands
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
public abstract class VariableAddressIlocInstruction extends IlocInstruction {
	Vector operands; // the operands to the instruction

	/**
	 * Print the instruction to System.out
	 */
	public void emit() {
		if (label != null)
			System.out.print(label + ":");

		System.out.print("\t" + getOpcode());
		for (int i = 0; i < operands.size(); i++) {
			Operand operand = (Operand) operands.elementAt(i);
			System.out.print(" " + operand.toString());
		}
		emitInstSpecific();
	}

	/**
	 * Return the operand to the instruction
	 * 
	 * @return Vector
	 */
	public Vector getOperands() {
		return operands;
	}

	/**
	 * Return true if the given operand is in the operand list
	 * 
	 * @param operand
	 *            the operand to test
	 * @return boolean
	 */
	public boolean operandIsRValue(Operand operand) {
		return operands.contains(operand);
	}

	/**
	 * Return false, no destination (overridden if it has one)
	 * 
	 * @param operand
	 *            the operand to test
	 * @return boolean
	 */
	public boolean operandIsLValue(Operand operand) {
		return false;
	}

	/**
	 * Print the instruction specific ending to the instruction.
	 *
	 */
	protected void emitInstSpecific() {
		System.out.println("");
	}
}
