package iloc;

/**
 * <p>
 * Title: TesteqInstruction.java
 * </p>
 *
 * <p>
 * Description: An instruction to test equality
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
public class TesteqInstruction extends TestInstruction {

	/**
	 * Create a testeq instruction
	 * 
	 * @param source
	 *            the source register
	 * @param dest
	 *            the destination register
	 */
	public TesteqInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "testeq";
	}
}
