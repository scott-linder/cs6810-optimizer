package iloc;

/**
 * <p>Title: SubIInstruction.javar</p>
 *
 * <p>Description: A subtract immediate instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class SubIInstruction extends ThreeAddressIlocInstruction {
	
  /**
   * Create a subtract immediate instruction
   * 
   * @param source1 the source register
   * @param source2 the immediate value
   * @param dest the destination register
   */
  public SubIInstruction(VirtualRegisterOperand source1,
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
    return "subI";
  }
}
