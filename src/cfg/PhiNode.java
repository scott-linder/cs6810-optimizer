package cfg;

import java.util.ArrayList;

import iloc.VirtualRegisterOperand;

public class PhiNode {
	public VirtualRegisterOperand target;
	public ArrayList<VirtualRegisterOperand> operands = new ArrayList<>();

	public PhiNode(VirtualRegisterOperand target, int n) {
		this.target = target;
		for (int i = 1; i <= n; i++) {
			VirtualRegisterOperand operand = target.copy();
			operand.setSSAId(i);
			this.operands.add(operand);
		}
	}
}
