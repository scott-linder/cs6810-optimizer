package iloc;

/**
 * <p>
 * Title: StringPseudoOpInstruction.java
 * </p>
 *
 * <p>
 * Description: A string pseudo op to declare a character string value
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Michigan Technological University
 * </p>
 *
 * @author Steve Carr
 * @version 1.0
 */
public class StringPseudoOp extends PseudoOpInstruction {

	String name; // the name of the string value
	String val; // the string itself

	/**
	 * Create a string pseudo-op
	 * 
	 * @param name
	 *            the name of the string
	 * @param val
	 *            the string itslef
	 */
	public StringPseudoOp(String name, String val) {
		this.name = name;
		this.val = val;
	}

	/**
	 * Return the iloc opcode
	 *
	 * @return String
	 */
	public String getOpcode() {
		return ".string";
	}

	/**
	 * Print the instruction to System.out
	 */
	public void emit() {
		if (label != null)
			System.out.print(label + ":");

		System.out.println("\t" + getOpcode() + " " + name + " \"" + val + "\"");
	}

	/**
	 * Return the string name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the string value
	 * 
	 * @return String
	 */
	public String getValue() {
		return val;
	}

}
