package iloc;

import java.util.ArrayList;

/**
 * <p>
 * Title: CbrneInstruction.java
 * </p>
 *
 * <p>
 * Description: A condition branch not equal instruction.
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
 * @author Steve Carr
 * @version 1.0
 */
public class CbrneInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create a conditonal branch not equal instruction
	 * 
	 * @param source1
	 *            the register to test
	 * @param dest
	 *            the branch target.
	 */
	public CbrneInstruction(VirtualRegisterOperand source1, LabelOperand dest) {
		this.source = source1;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return "cbrne";
	}

	@Override
	public ArrayList<LabelOperand> branchDestinations() {
		ArrayList<LabelOperand> destinations = new ArrayList<>();
		destinations.add((LabelOperand) dest);
		return destinations;
	}

}
