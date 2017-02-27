/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class CloadAIInstruction extends ThreeAddressIlocInstruction {

	  /**
	   * Create a char load address+immediate instruction
	   * @param source1 the base address register
	   * @param source2 the immediate offset
	   * @param dest the destination register
	   */
	  public CloadAIInstruction(VirtualRegisterOperand source1,
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
		return "cloadAI";
	}
}
