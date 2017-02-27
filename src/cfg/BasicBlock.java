package cfg;

import java.util.ArrayList;
import java.util.HashSet;

import iloc.IlocInstruction;

public class BasicBlock {
	ArrayList<IlocInstruction> instructions = new ArrayList<>();
	
	public static HashSet<BasicBlock> findBasicBlocks(ArrayList<IlocInstruction> program) {
		// hash of indices of leader instructions in `program`
		HashSet<Integer> leaders = new HashSet<>();
		leaders.add(0);
		for (int i = 1; i < program.size(); i++) {
			if (program.get(i).hasLabel() || program.get(i - 1).isBranch()) {
				leaders.add(i);
			}
		}
		HashSet<BasicBlock> blocks = new HashSet<>();
		for (Integer leader : leaders) {
			int i = leader;
			BasicBlock block = new BasicBlock();
			block.instructions.add(program.get(i++));
			for (; i < program.size() && !leaders.contains(i); i++) {
				block.instructions.add(program.get(i));
			}
			blocks.add(block);
		}
		return blocks;
	}
}
