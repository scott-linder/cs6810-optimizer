package iloc;

import java.util.*;

/**
 * <p>
 * Title: FcallInstruction.java
 * </p>
 *
 * <p>
 * Description: An floating-point iloc function call.
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Steven M. Carr
 * </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FcallInstruction extends InvocationInstruction {

	private VirtualRegisterOperand returnRegister; // The return register for
													// the function call

	/**
	 * Create a fp function call
	 * 
	 * @param name
	 *            the function label
	 * @param parameters
	 *            the parameters to the function call
	 * @param returnReg
	 *            the return virtual register
	 */
	public FcallInstruction(LabelOperand name, Vector parameters, VirtualRegisterOperand returnReg) {
		operands = new Vector();
		operands.add(name);
		for (int i = 0; i < parameters.size(); i++) {
			Operand parameter = (Operand) parameters.elementAt(i);
			operands.add(parameter);
		}
		returnRegister = returnReg;
	}

	/**
	 * Return the return register
	 * 
	 * @return VirtualRegisterOperand
	 */
	public VirtualRegisterOperand getReturnRegister() {
		return returnRegister;
	}

	/**
	 * Return the iloc opcode.
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "fcall";
	}

	/**
	 * Emit the instruction-specific portion of this instruction.
	 */
	protected void emitInstSpecific() {
		System.out.println("\t => " + returnRegister.toString());
	}
}
