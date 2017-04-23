package iloc;

import java.util.ArrayList;

/**
 * <p>
 * Title: JumpInstruction.java
 * </p>
 *
 * <p>
 * Description: An iloc jump instruction
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
public class JumpInstruction extends OneAddressIlocInstruction {

	/**
	 * Create a jump instruction
	 * 
	 * @param source
	 *            the target address register
	 */
	public JumpInstruction(VirtualRegisterOperand source) {
		this.source = source;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "jump";
	}

	@Override
	public ArrayList<LabelOperand> branchDestinations() {
		ArrayList<LabelOperand> destinations = new ArrayList<>();
		return destinations;
	}

	@Override
	public boolean isUnconditionalBranch() {
		return true;
	}

}
