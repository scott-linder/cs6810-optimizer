package iloc;

/**
 * <p>
 * Title: LoadAIInstruction.java
 * </p>
 *
 * <p>
 * Description: A load address+immediate instruction
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
public class LoadAIInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a load address+immediate instruction
	 * 
	 * @param source1
	 *            the base address register
	 * @param source2
	 *            the immediate offset
	 * @param dest
	 *            the destination register
	 */
	public LoadAIInstruction(VirtualRegisterOperand source1, ImmediateOperand source2, VirtualRegisterOperand dest) {
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
		return "loadAI";
	}

}
