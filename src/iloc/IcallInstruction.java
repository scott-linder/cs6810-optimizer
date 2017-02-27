package iloc;

import java.util.*;

/**
 * <p>Title: IcallInstruction.java</p>
 *
 * <p>Description: An function call with an integer return value.</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class IcallInstruction extends InvocationInstruction {
  private VirtualRegisterOperand returnRegister; // the return register id

  /**
   * Create an integer function call.
   * @param name the name of the function
   * @param parameters the actual parameters
   * @param returnReg the return register id
   */
  public IcallInstruction(LabelOperand name,
                         Vector parameters,
                         VirtualRegisterOperand returnReg) {
    operands = new Vector();
    operands.add(name);
    for (int i = 0; i < parameters.size(); i++) {
      Operand parameter = (Operand)parameters.elementAt(i);
      operands.add(parameter);
    }
    returnRegister = returnReg;
 }

  /**
   * Return the return register id
   * @return VirtualRegisterOperand
   */
  public VirtualRegisterOperand getReturnRegister() {
    return returnRegister;
  }

  /**
   * Return the iloc opcode.
   *
   * @return String
   */
  public String getOpcode() {
    return "icall";
  }
  

  /**
   * Emit the return register portion of the function call instruction.
   */
  protected void emitInstSpecific() {
    System.out.println("\t => "+returnRegister.toString());
  }
}
