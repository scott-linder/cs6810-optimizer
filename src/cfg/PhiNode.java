package cfg;

import java.util.ArrayList;

import iloc.VirtualRegisterOperand;

public class PhiNode {
	public VirtualRegisterOperand target;
	public ArrayList<VirtualRegisterOperand> operands = new ArrayList<>();

	public PhiNode(int target, int n) {
		this.target = new VirtualRegisterOperand(target);
		for (int i = 1; i <= n; i++) {
			VirtualRegisterOperand operand = this.target.copy();
			this.operands.add(operand);
		}
	}
}
