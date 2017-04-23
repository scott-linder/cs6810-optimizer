package iloc;

/**
 * <p>
 * Title: TestltInstruction.java
 * </p>
 *
 * <p>
 * Description: A test less than instruction
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
public class TestltInstruction extends TestInstruction {

	/**
	 * Create a test less than instruction
	 * 
	 * @param source
	 *            the register to test
	 * @param dest
	 *            the result register
	 */
	public TestltInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "testlt";
	}
}
