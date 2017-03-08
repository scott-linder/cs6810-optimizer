/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class CloadInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create a char load instruction
	 * 
	 * @param source
	 *            the address register
	 * @param dest
	 *            the destination register
	 */
	public CloadInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
		this.source = source;
		this.dest = dest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "cload";
	}

}
