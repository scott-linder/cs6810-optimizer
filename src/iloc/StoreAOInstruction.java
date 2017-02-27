package iloc;

/**
 * <p>Title: StoreAOInstruction.java</p>
 *
 * <p>Description: An integer store address plus register instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class StoreAOInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create an integer store address plus register instruction
   * @param source1 the register with the value to be stored
   * @param source2 the offset register
   * @param dest the base address register
   */
  public StoreAOInstruction(VirtualRegisterOperand source1,
                            VirtualRegisterOperand source2,
                            VirtualRegisterOperand dest) {
    this.source1 = source1;
    this.source2 = source2;
    this.dest = dest;
  }


  /**
   * Return the iloc opcode
   *
   * @return String
   */
  public String getOpcode() {
    return "storeAO";
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
