package iloc;

import java.util.*;

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
	public ArrayList<IlocInstruction> instructions; // the iloc instructions

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
		this.instructions = new ArrayList<>(instructions);
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
		return instructions.size();
	}
}
