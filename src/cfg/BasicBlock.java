package cfg;

import java.util.ArrayList;
import java.util.HashSet;

import iloc.IlocFrame;
import iloc.IlocInstruction;

public class BasicBlock {
	ArrayList<IlocInstruction> instructions = new ArrayList<>();
	
	public static HashSet<BasicBlock> findBasicBlocks(IlocFrame frame) {
		ArrayList<IlocInstruction> instructions = frame.instructions;
		// hash of indices of leader instructions in `program`
		HashSet<Integer> leaders = new HashSet<>();
		leaders.add(0);
		for (int i = 1; i < instructions.size(); i++) {
			if (instructions.get(i).hasLabel() || instructions.get(i - 1).isBranch()) {
				leaders.add(i);
			}
		}
		HashSet<BasicBlock> blocks = new HashSet<>();
		for (Integer leader : leaders) {
			int i = leader;
			BasicBlock block = new BasicBlock();
			block.instructions.add(instructions.get(i++));
			for (; i < instructions.size() && !leaders.contains(i); i++) {
				block.instructions.add(instructions.get(i));
			}
			blocks.add(block);
		}
		return blocks;
	}
}
