package iloc;

/**
 * <p>
 * Title: LshiftIInstruction.java
 * </p>
 *
 * <p>
 * Description: A left shift immediate instruction
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
public class LshiftIInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a left shift immediate instruction.
	 * 
	 * @param source1
	 *            the register to shift
	 * @param source2
	 *            the immediate value to shift by
	 * @param dest
	 *            the destination register.
	 */
	public LshiftIInstruction(VirtualRegisterOperand source1, ImmediateOperand source2, VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode.
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "lshiftI";
	}

}
