/**
 * 
 */
package iloc;

import java.util.ArrayList;

/**
 * @author carr
 *
 */
public abstract class TwoDestinationBranchInstruction extends IlocInstruction {

	Operand source;
	Operand dest1;
	Operand dest2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see iloc.IlocInstruction#emit()
	 */
	@Override
	/**
	 * Print the instruction to System.out
	 */
	public void emit() {
		if (label != null)
			System.out.print(label + ":");

		System.out.println(
				"\t" + getOpcode() + " " + source.toString() + " => " + dest1.toString() + " " + dest2.toString());
	}

	public Operand getSource() {
		return source;
	}

	public Operand getDestination1() {
		return dest1;
	}

	public Operand getDestination2() {
		return dest2;
	}

	@Override
	public ArrayList<LabelOperand> branchDestinations() {
		ArrayList<LabelOperand> destinations = new ArrayList<>();
		destinations.add((LabelOperand) dest1);
		destinations.add((LabelOperand) dest2);
		return destinations;
	}

	/**
	 * Return true if the given operand is a destination
	 * 
	 * @param operand
	 *            the operand to test
	 * @return boolean
	 */
	public boolean operandIsLValue(Operand operand) {
		return (operand == dest1 || operand == dest2);
	}

	/**
	 * Return true if the given operand is the source operand
	 * 
	 * @param operand
	 *            the operand to test
	 * @return boolean
	 */
	public boolean operandIsRValue(Operand operand) {
		return (operand == source);
	}

}
