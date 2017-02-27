package iloc;

/**
 * <p>Title: CreadInstruction.java</p>
 *
 * <p>Description: A character read iloc instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class CreadInstruction extends OneAddressReadIlocInstruction {

  /**
   * Create a character read instruction.
   * @param source the register containing the destination address.
   */
  public CreadInstruction(VirtualRegisterOperand source) {
    this.source = source;
  }

  /**
   * Return the iloc opcode.
   *
   * @return String
   * @todo Implement this iloc.IlocInstruction method
   */
  public String getOpcode() {
    return "cread";
  }

}
