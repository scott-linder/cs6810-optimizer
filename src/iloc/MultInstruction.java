package iloc;

/**
 * <p>
 * Title: MultInstruction.java
 * </p>
 *
 * <p>
 * Description: An integer multiply instruction
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
public class MultInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create an integer multiply instruction
	 * 
	 * @param source1
	 *            the left source register
	 * @param source2
	 *            the right source register
	 * @param dest
	 *            the destination register
	 */
	public MultInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2,
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
		return "mult";
	}
}
