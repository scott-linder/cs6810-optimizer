package cfg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import iloc.IlocFrame;
import iloc.IlocInstruction;
import iloc.LabelOperand;

public class BasicBlock {
	ArrayList<IlocInstruction> instructions = new ArrayList<>();

	// CFG
	ArrayList<BasicBlock> predecessors = new ArrayList<>();
	ArrayList<BasicBlock> successors = new ArrayList<>();

	// DT
	BasicBlock parent = null;
	ArrayList<BasicBlock> children = new ArrayList<>();

	private void addEdge(BasicBlock other) {
		successors.add(other);
		other.predecessors.add(this);
	}

	private void setParent(BasicBlock other) {
		parent = other;
		parent.children.add(this);
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

	public static void constructDT(ArrayList<BasicBlock> blocks) {
		// Key dominates every block in set
		HashMap<BasicBlock, HashSet<BasicBlock>> dom = new HashMap<>();
		// Set containing only the entry block
		BasicBlock entry = blocks.get(0);
		HashSet<BasicBlock> entrySet = new HashSet<>();
		entrySet.add(entry);
		// Set containing all blocks except the entry
		HashSet<BasicBlock> nonEntrySet = new HashSet<>(blocks);
		nonEntrySet.remove(entry);

		dom.put(entry, entrySet);
		for (BasicBlock block : nonEntrySet) {
			dom.put(block, new HashSet<>(blocks));
		}

		boolean changed;
		do {
			changed = false;
			for (BasicBlock block : nonEntrySet) {
				HashSet<BasicBlock> newSet = new HashSet<>();
				newSet.add(block);
				if (block.predecessors != null && block.predecessors.size() > 0) {
					HashSet<BasicBlock> intersect = new HashSet<>();
					intersect.addAll(dom.get(block.predecessors.get(0)));
					for (BasicBlock pred : block.predecessors)
						intersect.retainAll(dom.get(pred));
					newSet.addAll(intersect);
				}
				if (!dom.get(block).equals(newSet)) {
					dom.put(block, newSet);
					changed = true;
				}
			}
		} while (changed);

		for (BasicBlock block : blocks) {
			HashSet<BasicBlock> strictDominators = dom.get(block);
			strictDominators.remove(block);
			Optional<BasicBlock> idom = strictDominators.stream().max(Comparator.comparing(b -> dom.get(b).size()));
			if (idom.isPresent())
				block.setParent(idom.get());
		}
	}
}
