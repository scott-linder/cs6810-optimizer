package iloc;

/**
 * <p>Title: Operand.java</p>
 *
 * <p>Description: An abstract class for iloc operands</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public abstract class Operand {

  protected int operandType; // the data type of the operand

  public static final int INTEGER_TYPE = 0; // an integer
  public static final int FLOAT_TYPE = 1; // a floating-point
  public static final int CHARACTER_TYPE = 2; // a character

  /**
   * Set the operand data type
   * @param type a data type
   */
  protected void setType(int type) {
    operandType = type;
  }

  /**
   * Return the operand data type
   * @return int
   */
  public int getType() {
    return operandType;
  }
}
