package cfg;

import java.util.ArrayList;

import iloc.IlocFrame;
import iloc.IlocInstruction;
import iloc.LabelOperand;

public class BasicBlock {
	IlocFrame frame;
	ArrayList<IlocInstruction> instructions = new ArrayList<>();
	ArrayList<BasicBlock> predecessors = new ArrayList<>();
	ArrayList<BasicBlock> successors = new ArrayList<>();

	public void addEdge(BasicBlock other) {
		successors.add(other);
		other.predecessors.add(this);
	}

	public IlocInstruction last() {
		return instructions.get(instructions.size() - 1);
	}

	public static BasicBlock findBasicBlockWithLabel(ArrayList<BasicBlock> blocks, String label) {
		for (BasicBlock block : blocks) {
			for (IlocInstruction i : block.instructions)
				if (i.getLabel() != null && i.getLabel().equals(label))
					return block;
		}
		return null;
	}

	public static BasicBlock findBasicBlockWithInstruction(ArrayList<BasicBlock> blocks, IlocInstruction instruction) {
		for (BasicBlock block : blocks) {
			for (IlocInstruction i : block.instructions)
				if (i == instruction)
					return block;
		}
		return null;
	}

	public static ArrayList<BasicBlock> findBasicBlocks(IlocFrame frame) {
		IlocInstruction i = frame.head();
		ArrayList<IlocInstruction> leaders = new ArrayList<>();
		leaders.add(i);
		for (i = i.nextInFrame(); i != null; i = i.nextInFrame()) {
			if (i.hasLabel() || (i.prevInFrame() != null && i.prevInFrame().isBranch())) {
				leaders.add(i);
			}
		}
		ArrayList<BasicBlock> blocks = new ArrayList<>();
		for (IlocInstruction leader : leaders) {
			i = leader;
			BasicBlock block = new BasicBlock();
			block.instructions.add(i);
			for (i = i.nextInFrame(); i != null && !leaders.contains(i); i = i.nextInFrame()) {
				block.instructions.add(i);
			}
			blocks.add(block);
		}
		return blocks;
	}

	public static void constructCFG(ArrayList<BasicBlock> blocks) {
		for (BasicBlock block : blocks) {
			if (block.last().isBranch())
				for (LabelOperand target : block.last().branchDestinations())
					block.addEdge(findBasicBlockWithLabel(blocks, target.getLabel()));
			if (!block.last().isUnconditionalBranch()) {
				IlocInstruction next = block.last().nextInFrame();
				if (next != null)
					block.addEdge(findBasicBlockWithInstruction(blocks, next));
			}
		}
	}
}
