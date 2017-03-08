package iloc;

/**
 * <p>
 * Title: NopInstruction.java
 * </p>
 *
 * <p>
 * Description: A nop instruction
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class NopInstruction extends NoAddressIlocInstruction {

	/**
	 * Create a nop instruction
	 *
	 */
	public NopInstruction() {
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "nop";
	}

}
