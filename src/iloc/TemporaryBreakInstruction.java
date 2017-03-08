/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class TemporaryBreakInstruction extends NoAddressIlocInstruction {

	int PC;
	Integer returnAddress;

	/**
	 * Create a new temporary breakpoint instruction. Note that the return
	 * address is a frame from the returnAddressStack in the interpreter. This
	 * uniquely identifies the invocation in which the temporary breakpoint is
	 * to execute. This supports a step return or step over command.
	 * 
	 * @param PC
	 *            The PC where the tbreak occurs
	 * @param sourceLine
	 *            the source line in the program
	 * @param returnAddress
	 *            the invocation in which the tbreak occurs
	 */
	public TemporaryBreakInstruction(int PC, int sourceLine, Integer returnAddress) {
		this.PC = PC;
		lineNumber = sourceLine;
		this.returnAddress = returnAddress;
	}

	public TemporaryBreakInstruction(int PC, int sourceLine) {
		this(PC, sourceLine, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "tbreak";
	}
}
