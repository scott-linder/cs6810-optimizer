package iloc;

import java.util.ArrayList;

/**
 * <p>
 * Title: JumpIInstruction.java
 * </p>
 *
 * <p>
 * Description: A jump immediate iloc instruction
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
public class JumpIInstruction extends OneAddressIlocInstruction {

	/**
	 * Create a jump immmediate instruciton
	 * 
	 * @param source
	 *            the jump target
	 */
	public JumpIInstruction(ImmediateOperand source) {
		this.source = source;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "jumpI";
	}

	@Override
	public ArrayList<LabelOperand> branchDestinations() {
		ArrayList<LabelOperand> destinations = new ArrayList<>();
		destinations.add((LabelOperand) source);
		return destinations;
	}

	@Override
	public boolean isUnconditionalBranch() {
		return true;
	}

}
