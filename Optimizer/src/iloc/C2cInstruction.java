/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class C2cInstruction extends CopyInstruction {

	/**
	 * Create a char copy instruction
	 * 
	 * @param source
	 *            the source virtual register
	 * @param dest
	 *            the destination virtual register
	 */
	public C2cInstruction(VirtualRegisterOperand source, VirtualRegisterOperand dest) {
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
		return "c2c";
	}

}
