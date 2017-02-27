package iloc;

/**
 * <p>Title: IreadInstruction.java</p>
 *
 * <p>Description: An integer read instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class IreadInstruction extends OneAddressReadIlocInstruction {
	
  /**
   * Create an integer read instruction.
   * 
   * @param source the address where to store the value.
   */
  public IreadInstruction(VirtualRegisterOperand source) {
    this.source = source;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this iloc.IlocInstruction method
   */
  public String getOpcode() {
    return "iread";
  }

}
