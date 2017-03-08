package iloc;

/**
 * <p>
 * Title: DivIInstruction
 * </p>
 *
 * <p>
 * Description: An iloc divide immediate instruction
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
public class DivIInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a divide immediate instruction.
	 * 
	 * @param source1
	 *            the source virtual register operand
	 * @param source2
	 *            the immediate operand
	 * @param dest
	 *            the destination virtual register operand
	 */
	public DivIInstruction(VirtualRegisterOperand source1, ImmediateOperand source2, VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode.
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "divI";
	}

}
