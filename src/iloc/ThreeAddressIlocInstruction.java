package iloc;

/**
 * <p>Title: ThreeAddressIlocInstruction.java</p>
 *
 * <p>Description: An abstract class for three address instructions</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public abstract class ThreeAddressIlocInstruction extends IlocInstruction {
  Operand source1; // the first source operand
  Operand source2; // the second source operand
  Operand dest; // the destination operand

  /**
   * Print the instruction to System.out
   */
  public void emit() {
    if (label != null)
      System.out.print(label+":");

    System.out.println("\t"+getOpcode()+" "+source1.toString()+" "+
                       source2.toString()+" => "+dest.toString());
  }

  /**
   * Return the first source operand
   * @return Operand
   */
  public Operand getLeftOperand() {
    return source1;
  }

  /**
   * Return the second source operand
   * @return Operand
   */
  public Operand getRightOperand() {
    return source2;
  }

  /**
   * Return the destination
   * @return Operand
   */
  public Operand getDestination() {
    return dest;
  }

  /**
   * Return true if the given operand is the destination
   * @param operand the operand to test
   * @return boolean
   */
  public boolean operandIsLValue(Operand operand) {
    return operand == dest;
  }

  /**
   * Return true if the given operand is one of the source operands
   * @param operand the operand to test
   * @return boolean
   */
  public boolean operandIsRValue(Operand operand) {
    return (operand == source1 || operand == source2);
  }
}
