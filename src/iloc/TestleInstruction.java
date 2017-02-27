package iloc;

/**
 * <p>Title: TestleInstruction.java</p>
 *
 * <p>Description: A test less than or equal instruction </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class TestleInstruction extends TestInstruction {
	
  /**
   * Create a test less than or equal instruction
   * @param source the source register to test
   * @param dest the destination register
   */
  public TestleInstruction(VirtualRegisterOperand source,
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
    return "testle";
  }
}
