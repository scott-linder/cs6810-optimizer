package iloc;

import java.util.Vector;

/**
 * <p>
 * Title: IlocProgram.java
 * </p>
 *
 * <p>
 * Description: Storage for an iloc program
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
public class IlocProgram {
	public DataSection dataSection; // the data section of the code
	public IlocInstruction head; // the iloc instructions

	public static int maxVirtualReg = 1; // the current maximum virtual register
											// used
											// in the program

	/**
	 * Create an iloc program.
	 * 
	 * @param dataSection
	 *            the data section of the program
	 * @param instructions
	 *            the iloc instructions.
	 */
	public IlocProgram(DataSection dataSection, Vector<IlocInstruction> instructions) {
		this.dataSection = dataSection;
		this.head = instructions.get(0);
		for (int i = 0; i < instructions.size() - 1; i++)
			instructions.get(i).setNext(instructions.get(i + 1));
		for (int i = 1; i < instructions.size(); i++)
			instructions.get(i).setPrev(instructions.get(i - 1));
	}

	/**
	 * Set the max virtual register value in the program
	 * 
	 * @param virtualReg
	 *            a virtual reg id
	 */
	public static void setMaxVirtualReg(int virtualReg) {
		if (virtualReg > maxVirtualReg)
			maxVirtualReg = virtualReg;
	}

	/**
	 * Return the size of the iloc program
	 * 
	 * @return int
	 */
	public int getProgramSize() {
		return head.size();
	}
}
