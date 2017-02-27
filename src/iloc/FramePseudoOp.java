package iloc;

import java.util.*;

/**
 * <p>Title: FramePseudoOp.java</p>
 *
 * <p>Description: An iloc declaration of a procedure stack frame.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class FramePseudoOp extends PseudoOpInstruction {
  String name; // the function name
  int localSize; // the size of the stack frame 
  Vector parameters; // the formal register parameters to this function

  /**
   * Create a function stack frame declaration.
   * @param name the name of the function
   * @param localSize the size of the stack frame
   * @param parameters the formal register parameters to the function
   */
  public FramePseudoOp(String name,
                       int localSize,
                       Vector parameters) {
    this.name = name;
    this.localSize = localSize;
    this.parameters = parameters;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return ".frame";
  }

  /**
   * Print the pseudo op to System.out
   */
  public void emit() {
    if (label != null)
      System.out.print(label+":");

    System.out.print("\t"+getOpcode()+" "+name+" "+localSize);

    for (int i = 0; i < parameters.size(); i++) {
      VirtualRegisterOperand reg = (VirtualRegisterOperand)parameters.elementAt(i);
      System.out.print(" "+reg.toString());
    }

    System.out.println("");
  }

  /**
   * Return the function name.
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * Return the local stack size
   * @return int
   */
  public int getLocalSize() {
    return localSize;
  }

  /**
   * Return the formal register parameters of the function
   * @return Vector
   */
  public Vector getParameters() {
    return parameters;
  }

}
