package iloc;

/**
 * <p>
 * Title: ConstantOperand.java
 * </p>
 *
 * <p>
 * Description: A constant value operand in an iloc instruction.
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
public class ConstantOperand extends ImmediateOperand {

	private int value; // the value of the constant

	/**
	 * Create a constant operand
	 * 
	 * @param value
	 *            the operand value
	 */
	public ConstantOperand(int value) {
		this.value = value;
	}

	/**
	 * Copy a constant operand.
	 * 
	 * @return ConstantOperand
	 */
	public ConstantOperand copy() {
		return new ConstantOperand(value);
	}

	/**
	 * Returnt the value of the constant
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Return the String representation of the constant.
	 */
	public String toString() {
		return Integer.toString(value);
	}
}
