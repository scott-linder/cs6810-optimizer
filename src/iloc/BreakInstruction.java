/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class BreakInstruction extends NoAddressIlocInstruction {

	int PC;
	
	public BreakInstruction(int PC,int sourceLine){
		this.PC = PC;
		lineNumber = sourceLine;
	}
	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "break";
	}

}
