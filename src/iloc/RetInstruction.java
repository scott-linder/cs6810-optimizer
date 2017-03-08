package iloc;

/**
 * <p>
 * Title: RetInstruction.java
 * </p>
 *
 * <p>
 * Description: A function return instruction w/o a return value
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
public class RetInstruction extends NoAddressIlocInstruction {

	/**
	 * Create a return instruction
	 *
	 */
	public RetInstruction() {
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "ret";
	}
}
