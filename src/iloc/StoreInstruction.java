package iloc;

/**
 * <p>
 * Title: StoreInstruction.java
 * </p>
 *
 * <p>
 * Description: An integer store instruction
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
public class StoreInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create an integer store instruction
	 * 
	 * @param source
	 *            the register with the value to store
	 * @param dest
	 *            the address register
	 */
	public StoreInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "store";
	}

}
