package iloc;

/**
 * <p>Title: TestneInstruction.java</p>
 *
 * <p>Description: A test not equal instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class TestneInstruction extends TestInstruction {
	
  /**
   * Create a test not equal instruction
   * @param source the register to test
   * @param dest the result register
   */
  public TestneInstruction(VirtualRegisterOperand source,
                           VirtualRegisterOperand dest) {
    this.source = source;
    this.dest = dest;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   */
  public String getOpcode() {
    return "testne";
  }
}
