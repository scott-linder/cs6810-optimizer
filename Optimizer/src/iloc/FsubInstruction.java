package iloc;

/**
 * <p>
 * Title: FsubInstruction.java
 * </p>
 *
 * <p>
 * Description: A floating-point subtract instruction.
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
public class FsubInstruction extends ThreeAddressIlocInstruction {

	/**
	 * create a floating-point subtract instruction
	 * 
	 * @param source1
	 *            the first source register
	 * @param source2
	 *            the second source register
	 * @param dest
	 *            the destination register
	 */
	public FsubInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2,
			VirtualRegisterOperand dest) {
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
		return "fsub";
	}

}
