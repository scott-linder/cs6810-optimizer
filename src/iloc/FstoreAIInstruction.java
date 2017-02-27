package iloc;

/**
 * <p>Title: FstoreAIInstruciton.java</p>
 *
 * <p>Description: A floating-point load address + immediate instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FstoreAIInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a floating-point load address + immediate instruction
   * @param source1 the register with the value to be stored
   * @param source2 the immediate offset
   * @param dest the base address register
   */
  public FstoreAIInstruction(VirtualRegisterOperand source1,
                             ImmediateOperand source2,
                             VirtualRegisterOperand dest) {
    this.source1 = source1;
    this.source2 = source2;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "fstoreAI";
  }

  /**
   * Print the iloc instruction to System.out
   */
  public void emit() {
    if (label != null)
      System.out.print(label+":");

    System.out.println("\t"+getOpcode()+" "+source1.toString()+" => "+
                       source2.toString()+" "+dest.toString());
  }

}
