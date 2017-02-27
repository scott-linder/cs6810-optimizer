/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class CstoreAOInstruction extends ThreeAddressIlocInstruction {

	  /**
	   * Create an char store address plus register instruction
	   * @param source1 the register with the value to be stored
	   * @param source2 the offset register
	   * @param dest the base address register
	   */
	  public CstoreAOInstruction(VirtualRegisterOperand source1,
	                            VirtualRegisterOperand source2,
	                            VirtualRegisterOperand dest) {
	    this.source1 = source1;
	    this.source2 = source2;
	    this.dest = dest;
	  }

	  /**
	   * Print the instruction to System.out
	   */
	  public void emit() {
	    if (label != null)
	      System.out.print(label+":");

	    System.out.println("\t"+getOpcode()+" "+source1.toString()+" => "+
	                       source2.toString()+" "+dest.toString());
	  }
	  
	  /* (non-Javadoc)
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "cstoreAO";
	}

}
