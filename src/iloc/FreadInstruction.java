package iloc;

/**
 * <p>Title: FreadInstruction.java</p>
 *
 * <p>Description: An iloc floating-point read instruction</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FreadInstruction extends OneAddressReadIlocInstruction {
	
  /**
   * Create a floating-point read instruction
   * @param source The register containing the address where the read value is to be stored
   */
  public FreadInstruction(VirtualRegisterOperand source) {
    this.source = source;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
    */
  public String getOpcode() {
    return "fread";
  }

}
