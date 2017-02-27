package iloc;

/**
 * <p>Title: TextSection.java</p>
 *
 * <p>Description: A text section pseudo op</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: Michigan Technological University</p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class TextSection extends PseudoOpInstruction {
	
	/**
	 * Create a text section pseudo op
	 *
	 */
    public TextSection() {
    }

    /**
     * Return the iloc opcode
     *
     * @return String
     */
    public String getOpcode() {
        return ".text";
    }

    /**
     * Print the instruction to System.out
     */
    public void emit() {
      if (label != null)
        System.out.print(label+":");

      System.out.println("\t"+getOpcode());
    }
}
