package iloc;

/**
 * <p>Title: LabelOperand.java</p>
 *
 * <p>Description: An iloc label</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class LabelOperand extends ImmediateOperand {

  private String label; // the label name

  /**
   * Create a label
   * @param label the label name
   */
  public LabelOperand(String label) {
    this.label = label;
  }

  /**
   * Return the label name
   * @return String
   */
  public String getLabel() {
    return label;
  }

  /**
   * Return the label name
   */
  public String toString() {
    return label;
  }
}
