/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class Cmp_neInstruction extends ThreeAddressIlocInstruction {

	/**
	 * Create a compare not equal instruction.
	 * 
	 * @param source1
	 *            the left source register
	 * @param source2
	 *            the right source register
	 * @param dest
	 *            the destination register
	 */
	public Cmp_neInstruction(VirtualRegisterOperand source1, VirtualRegisterOperand source2,
			VirtualRegisterOperand dest) {
		this.source1 = source1;
		this.source2 = source2;
		this.dest = dest;
	}

	/**
	 * Return the iloc opcode.
	 *
	 * @return String
	 * @todo Implement this middleEnd.IlocInstruction method
	 */
	public String getOpcode() {
		return "cmp_NE";
	}

}
