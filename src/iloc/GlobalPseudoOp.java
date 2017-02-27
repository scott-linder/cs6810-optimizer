package iloc;

/**
 * <p>Title: GlobalPseudoOp.java</p>
 *
 * <p>Description: An iloc pseudo op for declaring a global label</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class GlobalPseudoOp extends PseudoOpInstruction {
  String name; // name of the global data
  int size; // the size of the data
   int align; // the byte alignment

  public GlobalPseudoOp(String name,
                        int size,
                        int align) {
    this.name = name;
    this.size = size;
    this.align = align;
  }

  /**
   * Return the iloc opcode
   *
   * @return String
   * @todo Implement this middleEnd.IlocInstruction method
   */
  public String getOpcode() {
    return ".global";
  }

  /**
   * Print the iloc instruction to System.out
   */
  public void emit() {
    if (label != null)
      System.out.print(label+":");

    System.out.println("\t"+getOpcode()+" "+name+" "+size+" "+align);
  }

  /**
   * Get the global data name
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * Return the size of the global data
   * @return int
   */
  public int getSize() {
    return size;
  }

  /**
   * Return the byte alignment
   * @return int
   */
  public int getAlign() {
    return align;
  }

}
