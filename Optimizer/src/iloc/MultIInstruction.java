package iloc;

/**
 * <p>
 * Title: MultIInstruction.java
 * </p>
 *
 * <p>
 * Description: An integer multiply immediate instruction
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
public class MultIInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Creat an integer multiply immediate instruction
	 * 
	 * @param source1
	 *            the left source register
	 * @param source2
	 *            the immediate operand
	 * @param dest
	 *            the destination register
	 */
	public MultIInstruction(VirtualRegisterOperand source1, ImmediateOperand source2, VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "multI";
	}
}
