package iloc;

import java.util.*;

/**
 * <p>
 * Title: DataSection.java
 * </p>
 *
 * <p>
 * Description: The data section in an iloc program.
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
 * @author Steven M. Carr
 * @version 1.0
 */
public class DataSection {
	Vector pseudoOps; // the data declaration in an iloc program

	/**
	 * Create a data section
	 * 
	 * @param pseudoOps
	 *            the data section pseudo-ops in an iloc program
	 */
	public DataSection(Vector pseudoOps) {
		this.pseudoOps = pseudoOps;
	}

}
