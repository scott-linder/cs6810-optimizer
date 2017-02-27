/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class Cbr_ltInstruction extends TwoDestinationBranchInstruction {

	/**
	 * Create a conditonal branch less than instruction
	 * 
	 * @param source1 the register to test
	 * @param dest the branch target.
	 */
	public Cbr_ltInstruction(VirtualRegisterOperand source,
			LabelOperand dest1,
			LabelOperand dest2) {
		this.source = source;
		this.dest1 = dest1;
		this.dest2 = dest2;
	}

	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "cbr_LT";
	}

}
