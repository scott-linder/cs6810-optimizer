package iloc;

import java.util.ArrayList;

/**
 * <p>
 * Title: CbrInstruction.java
 * </p>
 *
 * <p>
 * Description: A conditional branch instruction
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Michigan Technolgoical University
 * </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class CbrInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create a conditional branch instruction
	 * 
	 * @param source1
	 *            the register to test
	 * @param dest
	 *            the branch target
	 */
	public CbrInstruction(VirtualRegisterOperand source1, LabelOperand dest) {
		this.source = source1;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "cbr";
	}

	@Override
	public ArrayList<LabelOperand> branchDestinations() {
		ArrayList<LabelOperand> destinations = new ArrayList<>();
		destinations.add((LabelOperand) dest);
		return destinations;
	}

}
