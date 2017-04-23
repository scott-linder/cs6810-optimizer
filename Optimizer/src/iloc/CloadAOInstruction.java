/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class CloadAOInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a char load address register plus offset register instruction
	 * 
	 * @param source1
	 *            the base address register
	 * @param source2
	 *            the offset register
	 * @param dest
	 *            the destination register
	 */
	public CloadAOInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2,
			VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "cloadAO";
	}

}
