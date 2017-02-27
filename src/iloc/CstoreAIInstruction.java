/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class CstoreAIInstruction extends ThreeAddressIlocInstruction {

	  /**
	   * Create an char store address plus immediate instruction
	   * @param source1 the register with the value to be stored
	   * @param source2 the immediate offset
	   * @param dest the base address register
	   */
	  public CstoreAIInstruction(VirtualRegisterOperand source1,
	                            ImmediateOperand source2,
	                            VirtualRegisterOperand dest) {
	    this.source1 = source1;
	    this.source2 = source2;
	    this.dest = dest;
	  }
	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {
		return "cstoreAI";
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

}
