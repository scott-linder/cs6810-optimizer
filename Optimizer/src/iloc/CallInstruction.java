package iloc;

import java.util.Vector;
import java.util.Hashtable;

/**
 * <p>
 * Title: CallInstruction.java
 * </p>
 *
 * <p>
 * Description: An iloc call instruction.
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
public class CallInstruction extends InvocationInstruction {

	/**
	 * Create a call instruction object.
	 * 
	 * @param name
	 *            the name of the function to be called
	 * @param parameters
	 *            a Vector of operands to the call instruction.
	 */
	public CallInstruction(LabelOperand name, Vector parameters) {
		operands = new Vector();
		operands.add(name);
		for (int i = 0; i < parameters.size(); i++) {
			Operand parameter = (Operand) parameters.elementAt(i);
			operands.add(parameter);
		}
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "call";
	}

	/**
	 * Execute a call instruction.
	 */
}
