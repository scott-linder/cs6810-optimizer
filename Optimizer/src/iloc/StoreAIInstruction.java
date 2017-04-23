package iloc;

/**
 * <p>
 * Title: StoreAIInstruction.java
 * </p>
 *
 * <p>
 * Description: An integer store address plus immediate instruction
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
public class StoreAIInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create an integer store address plus immediate instruction
	 * 
	 * @param source1
	 *            the register with the value to be stored
	 * @param source2
	 *            the immediate offset
	 * @param dest
	 *            the base address register
	 */
	public StoreAIInstruction(VirtualRegisterOperand source1, ImmediateOperand source2, VirtualRegisterOperand dest) {
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
		return "storeAI";
	}

	/**
	 * Print the instruction to System.out
	 */
	public void emit() {
		if (label != null)
			System.out.print(label + ":");

		System.out.println(
				"\t" + getOpcode() + " " + source1.toString() + " => " + source2.toString() + " " + dest.toString());
	}

}
