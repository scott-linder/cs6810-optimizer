package cfg;

import java.util.ArrayList;
import java.util.HashSet;

import iloc.IlocFrame;
import iloc.IlocInstruction;

public class BasicBlock {
	IlocFrame frame;
	ArrayList<IlocInstruction> instructions = new ArrayList<>();
	ArrayList<BasicBlock> ancestors = new ArrayList<>();
	ArrayList<BasicBlock> predecessors = new ArrayList<>();

	public static HashSet<BasicBlock> findBasicBlocks(IlocFrame frame) {
		IlocInstruction i = frame.head();
		HashSet<IlocInstruction> leaders = new HashSet<>();
		leaders.add(i);
		for (i = i.nextInFrame(); i != null; i = i.nextInFrame()) {
			if (i.hasLabel() || i.isBranch()) {
				leaders.add(i);
			}
		}
		HashSet<BasicBlock> blocks = new HashSet<>();
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
}
