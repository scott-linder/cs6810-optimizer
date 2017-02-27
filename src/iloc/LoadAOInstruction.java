package iloc;

/**
 * <p>Title: LoadAOInstruction.java</p>
 *
 * <p>Description: A load address register plus offset register instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class LoadAOInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a load address register plus offset register instruction
   * @param source1 the base address register
   * @param source2 the offset register
   * @param dest the destination register
   */
  public LoadAOInstruction(VirtualRegisterOperand source1,
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
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "loadAO";
  }

}
