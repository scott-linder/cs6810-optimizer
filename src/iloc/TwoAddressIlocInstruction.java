package iloc;

/**
 * <p>Title: TwoAddressIlocInstruction.javar</p>
 *
 * <p>Description: An abstract class for two address instructions</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public abstract class TwoAddressIlocInstruction extends IlocInstruction {
  Operand source; // the source operand
  Operand dest; // the destination operand

  /**
   * Print the instruction to System.out
   */
  public void emit() {
    if (label != null)
      System.out.print(label+":");

    System.out.println("\t"+getOpcode()+" "+source.toString()+" "+
                       " => "+dest.toString());
  }

  /**
   * Return the source operand
   * @return Operand
   */
  public Operand getSource() {
    return source;
  }

  /**
   * Return the destination operand
   * @return Operand
   */
  public Operand getDestination() {
    return dest;
  }

  /**
   * Return true if the given operand is the destination operand
   * @param operand the operand to test
   * @return boolean
   */
  public boolean operandIsLValue(Operand operand) {
    return operand == dest;
  }

  /**
   * Return true if the given operand is the source operand
   * @param operand the operand to test
   * @return boolean
   */
  public boolean operandIsRValue(Operand operand) {
    return operand == source;
  }
}
