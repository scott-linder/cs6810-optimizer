package iloc;

/**
 * <p>
 * Title: RshiftIInstruction.java
 * </p>
 *
 * <p>
 * Description: A right shift immediate instruction
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
public class RshiftIInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a right shift immediate instruction
	 * 
	 * @param source1
	 *            the source register
	 * @param source2
	 *            the immediate value
	 * @param dest
	 *            the destination register
	 */
	public RshiftIInstruction(VirtualRegisterOperand source1, ImmediateOperand source2, VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc operand
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "rshiftI";
	}
}
