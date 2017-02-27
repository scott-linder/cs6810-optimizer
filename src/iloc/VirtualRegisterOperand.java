package iloc;

/**
 * <p>Title: VirtualRegisterOperand.java</p>
 *
 * <p>Description: A virtual register</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class VirtualRegisterOperand extends Operand {

  public static final int FP_REG = 0; // the frame pointer
  public static final int SP_REG = 1; // the stack pointer
  public static final int INT_RET_REG = 2; // an integer return register
  public static final int FLOAT_RET_REG = 3; // a floating-point return register
  public static final int FREE_REG = 4; // a free register

  private int registerId; // the register id

  /**
   * Create a virtual register
   * @param registerId the register id
   */
  public VirtualRegisterOperand(int registerId) {
    this.registerId = registerId;
  }

  /**
   * Copy this virtual register
   * @return VirtualRegisterOperand
   */
  public VirtualRegisterOperand copy() {
    return new VirtualRegisterOperand(registerId);
  }

  /**
   * Return the register id
   * @return int
   */
  public int getRegisterId() {
    return registerId;
  }

  /**
   * return the string representation of the virtual register
   * 
   * @return String
   */
  public String toString() {
    return "%vr"+registerId;
  }
}
