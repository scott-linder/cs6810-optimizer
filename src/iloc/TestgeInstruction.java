package iloc;

/**
 * <p>Title: TestgeInstruction.java</p>
 *
 * <p>Description: A test greater than or equal instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestgeInstruction extends TestInstruction {
	
  /**
   * Create a test greater than or equal instruction
   * @param source the register to test
   * @param dest the result register
   */
  public TestgeInstruction(VirtualRegisterOperand source,
                           VirtualRegisterOperand dest) {
    this.source = source;
    this.dest = dest;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return "testge";
  }
}
