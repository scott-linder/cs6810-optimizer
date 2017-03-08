package iloc;

/**
 * <p>
 * Title: IwriteInstruction.java
 * </p>
 *
 * <p>
 * Description: An integer write instruction
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
public class IwriteInstruction extends OneAddressIlocInstruction {

	/**
	 * Create an integer write instruction.
	 * 
	 * @param source
	 *            the register containing the value to write
	 */
	public IwriteInstruction(VirtualRegisterOperand source) {
		this.source = source;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "iwrite";
	}
}
