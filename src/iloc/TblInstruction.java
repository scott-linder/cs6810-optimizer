/**
 * 
 */
package iloc;

/**
 * @author carr
 *
 */
public class TblInstruction extends TwoAddressIlocInstruction {

	public TblInstruction(Operand reg,
						  Operand label) {
		this.source = reg;
		this.dest = label;
	}
	
	public void emit() {
		if (label != null)
			System.out.print(label+":");

		System.out.println("\t"+getOpcode()+" "+source.toString()+" "+
				dest.toString());
	}
	/* (non-Javadoc)
	 * @see iloc.IlocInstruction#getOpcode()
	 */
	@Override
	public String getOpcode() {

		return "tbl";
	}
}
