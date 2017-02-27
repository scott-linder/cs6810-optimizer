package iloc;

/**
 * <p>Title: IretInstruction.java</p>
 *
 * <p>Description: An integer return instruction.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class IretInstruction extends OneAddressIlocInstruction {
	
  /**
   * Create an integer return instruction.
   * 
   * @param source the return register
   */
  public IretInstruction(Operand source) {
    this.source = source;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "iret";
  }

}
