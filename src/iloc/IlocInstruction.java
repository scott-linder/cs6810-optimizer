package iloc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

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
	private boolean dead = false;

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
		return branchDestinations() != null;
	}

	public boolean isUnconditionalBranch() {
		return false;
	}

	public ArrayList<LabelOperand> branchDestinations() {
		return null;
	}

	public IlocInstruction getPrev() {
		return prev;
	}

	public void setPrev(IlocInstruction prev) {
		this.prev = prev;
	}

	public IlocInstruction prevInFrame() {
		if (prev == null || prev instanceof FramePseudoOp)
			return null;
		return prev;
	}

	public IlocInstruction getNext() {
		return next;
	}

	public void setNext(IlocInstruction next) {
		this.next = next;
	}

	public IlocInstruction nextInFrame() {
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

	public boolean isDead() {
		return dead;
	}

	public boolean hasSideEffects() {
		return this instanceof CallInstruction || this instanceof F2iInstruction || this instanceof FloadInstruction
				|| this instanceof I2fInstruction || this instanceof I2iInstruction || this instanceof IcallInstruction
				|| this instanceof IreadInstruction || this instanceof IretInstruction
				|| this instanceof IwriteInstruction || this instanceof JumpIInstruction
				|| this instanceof LoadInstruction || this instanceof RetInstruction || this instanceof StoreInstruction
				|| this instanceof SwriteInstruction;
	}

	public void kill() {
		this.dead = true;
	}

	public boolean remove() {
		if (this.dead) {
			if (null != this.prev) {
				this.next.prev = this.prev;
			}

			if (null != this.next) {
				this.prev.next = this.next;
			}

			return true;
		} else
			return false;
	}

	/*
	 * Registers which this instruction reads.
	 */
	public ArrayList<VirtualRegisterOperand> registerSources() {
		ArrayList<VirtualRegisterOperand> operands = new ArrayList<>();
		for (Operand o : sources())
			if (o instanceof VirtualRegisterOperand)
				operands.add((VirtualRegisterOperand) o);
		return operands;
	}

	/*
	 * Register which this instruction writes.
	 */
	public VirtualRegisterOperand registerDestination() {
		Operand d = destination();
		if (d != null && d instanceof VirtualRegisterOperand)
			return (VirtualRegisterOperand) d;
		return null;
	}

	/*
	 * Operands which this instruction reads.
	 */
	public ArrayList<Operand> sources() {
		ArrayList<Operand> operands = new ArrayList<>();
		if (this instanceof ThreeAddressIlocInstruction) {
			ThreeAddressIlocInstruction i = (ThreeAddressIlocInstruction) this;
			operands.addAll(Arrays.asList(i.getLeftOperand(), i.getRightOperand()));
		} else if (this instanceof TwoAddressIlocInstruction) {
			TwoAddressIlocInstruction i = (TwoAddressIlocInstruction) this;
			operands.add(i.getSource());
			if (this instanceof StoreInstruction) {
				operands.add(i.getDestination());
			}
		} else if (this instanceof OneAddressIlocInstruction) {
			OneAddressIlocInstruction i = (OneAddressIlocInstruction) this;
			operands.add(i.getOperand());
		} else if (this instanceof IcallInstruction) {
			IcallInstruction i = (IcallInstruction) this;
			Vector ops = i.getOperands();
			for (int j = 1; j < ops.size(); j++) {
				operands.add((Operand) ops.get(j));
			}
		}
		return operands;
	}

	/*
	 * Registers which this instruction writes.
	 */
	public Operand destination() {
		Operand dest = null;
		if (this instanceof ThreeAddressIlocInstruction) {
			ThreeAddressIlocInstruction i = (ThreeAddressIlocInstruction) this;
			dest = i.getDestination();
		} else if (this instanceof TwoAddressIlocInstruction) {
			TwoAddressIlocInstruction i = (TwoAddressIlocInstruction) this;
			if (!(this instanceof StoreInstruction)) {
				dest = i.getDestination();
			}
		} else if (this instanceof IcallInstruction) {
			IcallInstruction i = (IcallInstruction) this;
			dest = i.getReturnRegister();
		}
		return dest;
	}

	public String expr() {
		String ret = getOpcode() + " ";
		for (Operand o : sources())
			ret += o.toString() + " ";
		return ret;
	}
}
