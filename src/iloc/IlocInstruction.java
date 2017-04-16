package iloc;

import java.util.ArrayList;
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
		if (!hasSideEffects())
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
	public ArrayList<VirtualRegisterOperand> registerOperands() {
		ArrayList<VirtualRegisterOperand> operands = new ArrayList<>();
		for (Operand o : operands())
			if (o instanceof VirtualRegisterOperand)
				operands.add((VirtualRegisterOperand) o);
		return operands;
	}

	/*
	 * Operands which this instruction reads.
	 */
	public ArrayList<Operand> operands() {
		ArrayList<Operand> operands = new ArrayList<>();
		if (this instanceof ThreeAddressIlocInstruction) {
			ThreeAddressIlocInstruction instruction = (ThreeAddressIlocInstruction) this;

			Operand[] sources = { instruction.getLeftOperand(), instruction.getRightOperand() };

			for (Operand source : sources) {
				operands.add(source);
			}
		} else if (this instanceof TwoAddressIlocInstruction) {
			TwoAddressIlocInstruction instruction = (TwoAddressIlocInstruction) this;

			Operand source = instruction.getSource();

			operands.add(source);
		} else if (this instanceof OneAddressIlocInstruction) {
			OneAddressIlocInstruction instruction = (OneAddressIlocInstruction) this;

			Operand source = instruction.getOperand();

			operands.add(source);
		} else if (this instanceof IcallInstruction) {
			IcallInstruction instruction = (IcallInstruction) this;

			Vector operands_ = instruction.getOperands();
			// GROSS
			Operand[] sources = (Operand[]) operands_.subList(1, operands_.size())
					.toArray(new Operand[operands_.size() - 1]);

			for (Operand source : sources) {
				operands.add(source);
			}
		}
		return operands;
	}

	/*
	 * Registers which this instruction writes.
	 */
	public VirtualRegisterOperand registerDestination() {
		VirtualRegisterOperand dest = null;
		if (this instanceof I2iInstruction)
			return null;
		if (this instanceof ThreeAddressIlocInstruction) {
			ThreeAddressIlocInstruction instruction = (ThreeAddressIlocInstruction) this;

			Operand destination = instruction.getDestination();

			if (destination instanceof VirtualRegisterOperand) {
				dest = (VirtualRegisterOperand) destination;
			}
		} else if (this instanceof TwoAddressIlocInstruction) {
			TwoAddressIlocInstruction instruction = (TwoAddressIlocInstruction) this;

			Operand destination = instruction.getDestination();

			if (!(this instanceof StoreInstruction)) {
				if (destination instanceof VirtualRegisterOperand) {
					dest = (VirtualRegisterOperand) destination;
				}
			}
		} else if (this instanceof IcallInstruction) {
			IcallInstruction instruction = (IcallInstruction) this;

			Operand destination = instruction.getReturnRegister();

			if (destination instanceof VirtualRegisterOperand) {
				dest = (VirtualRegisterOperand) destination;
			}
		}
		return dest;
	}

	public String expr() {
		String ret = getOpcode() + " ";
		for (Operand o : operands())
			ret += o.toString() + " ";
		return ret;
	}
}
