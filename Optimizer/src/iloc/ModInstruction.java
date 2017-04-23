package iloc;

/**
 * <p>
 * Title: ModInstruction.java
 * </p>
 *
 * <p>
 * Description: A modulo instruction.
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Michigan Technological Univeristy
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ModInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a modulo instruction.
	 * 
	 * @param source1
	 *            the left source register
	 * @param source2
	 *            the right source register
	 * @param dest
	 *            the destination register
	 */
	public ModInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2, VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 */
	public String getOpcode() {
		return "mod";
	}
}
