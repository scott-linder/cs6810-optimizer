package iloc;

/**
 * <p>
 * Title: NoAddressInstruction.java
 * </p>
 *
 * <p>
 * Description: An abstract class for instructions w/o operands
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
public abstract class NoAddressIlocInstruction extends IlocInstruction {

	/**
	 * Print the instruction to System.out
	 */
	public void emit() {
		if (label != null)
			System.out.print(label + ":");

		System.out.println("\t" + getOpcode());
	}

	/**
	 * Return false, no operands
	 * 
	 * @param operand
	 * @return boolean
	 */
	public boolean operandIsLValue(Operand operand) {
		return false;
	}

	/**
	 * Return false, no operands
	 * 
	 * @param operand
	 * @return boolean
	 */
	public boolean operandIsRValue(Operand operand) {
		return false;
	}
}
