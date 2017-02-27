package iloc;

/**
 * <p>Title: TestgtInstruction.java</p>
 *
 * <p>Description: A test greater than instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class TestgtInstruction extends TestInstruction {
	
  /**
   * Create a test greater than instruction
   * @param source the source register to test
   * @param dest the result register
   */
  public TestgtInstruction(VirtualRegisterOperand source,
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
    return "testgt";
  }
}
