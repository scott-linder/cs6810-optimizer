/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class CstoreInstruction extends TwoAddressIlocInstruction {

	/**
	 * Create a char store instruction
	 * 
	 * @param source
	 *            the register with the value to store
	 * @param dest
	 *            the address register
	 */
	public CstoreInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
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
		return "cstore";
	}

}
