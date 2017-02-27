/**
 * 
 */
package iloc;


/**
 * @author carr
 *
 */
public class C2iInstruction extends CopyInstruction {


	  /**
	   * Create an char to int conversion instruction.
	   * @param source the source fp virtual register
	   * @param dest the destination integer virtual register.
	   */
	  public C2iInstruction(VirtualRegisterOperand source,
	                        VirtualRegisterOperand dest) {
	    this.source = source;
	    this.dest = dest;
	  }
	  
	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "c2i";
	}

}
