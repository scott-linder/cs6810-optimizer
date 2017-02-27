package iloc;

/**
 * <p>Title: FloatPseudoOp.java</p>
 *
 * <p>Description: A floating point value declaration.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FloatPseudoOp extends PseudoOpInstruction {

  String name;  // The name of the value
  float val; // The floating-point value

  /**
   * Create a floating-point value declaration.
   * 
   * @param name The name of the value
   * @param val The actual floating-point value
   */
  public FloatPseudoOp(String name,
                       float val) {
    this.name = name;
    this.val = val;
  }

  /**
   * Return the iloc opcode.
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return ".float";
  }

  /**
   * Print the iloc instruction to System.out
   */
  public void emit() {
    if (label != null)
      System.out.print(label+":");

    System.out.println("\t"+getOpcode()+" "+name+" "+val);
  }

  /**
   * Return the name of the value
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * Return the floating-point value.
   * @return float
   */
  public float getValue() {
    return val;
  }
}
