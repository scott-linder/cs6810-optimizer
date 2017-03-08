package iloc;

/**
 * <p>
 * Title: IlocInstruction.java
 * </p>
 *
 * <p>
 * Description: An abstract class for all iloc instructions.
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
public abstract class IlocInstruction {
	protected String label = null; // the instruction label
	protected int instId; // the instruction id
	protected int lineNumber = -1;
	private IlocInstruction prev = null;
	private IlocInstruction next = null;

	/**
	 * Return the iloc opcode.
	 * 
	 * @return String
	 */
	public abstract String getOpcode();

	/**
	 * Print the instruction to System.out
	 *
	 */
	public abstract void emit();

	/**
	 * Return the label
	 * 
	 * @return String
	 */
	public String getLabel() {
		return label;
	}

	public boolean hasLabel() {
		return label != null;
	}

	/**
	 * Set the instruction label
	 * 
	 * @param label
	 *            the label for this instruction
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Return the instruction id.
	 * 
	 * @return int
	 */
	public int getInstructionId() {
		return instId;
	}

	/**
	 * Set the instruction id
	 * 
	 * @param id
	 *            the instruction id value
	 */
	public void setInstructionId(int id) {
		instId = id;
	}

	public void setSourceLine(int line) {
		lineNumber = line;
	}

	public int getSourceLine() {
		return lineNumber;
	}

	public boolean isBranch() {
		return false;
	}

	public IlocInstruction getPrev() {
		return prev;
	}

	public void setPrev(IlocInstruction prev) {
		this.prev = prev;
	}

	public IlocInstruction getNext() {
		return next;
	}

	public void setNext(IlocInstruction next) {
		this.next = next;
	}

	public IlocInstruction nextInFrame() {
		IlocInstruction next = getNext();
		if (next == null || next instanceof FramePseudoOp)
			return null;
		return next;
	}

	public void delete() {
		if (this.prev != null)
			this.prev.next = this.next;
		if (this.next != null)
			this.next.prev = this.prev;
	}

	public int size() {
		int size = 0;
		for (IlocInstruction i = this; i != null; i = i.getNext())
			size++;
		return size;
	}

	public int frameSize() {
		int size = 0;
		for (IlocInstruction i = this; i != null; i = i.nextInFrame())
			size++;
		return size;
	}

}
