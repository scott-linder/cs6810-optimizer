package cfg;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import iloc.IlocFrame;
import iloc.IlocInstruction;
import iloc.LabelOperand;
import iloc.OneAddressIlocInstruction;
import iloc.Operand;
import iloc.ThreeAddressIlocInstruction;
import iloc.TwoAddressIlocInstruction;
import iloc.VirtualRegisterOperand;

public class BasicBlock {
	public ArrayList<IlocInstruction> instructions = new ArrayList<>();

	// CFG
	public ArrayList<BasicBlock> predecessors = new ArrayList<>();
	public ArrayList<BasicBlock> successors = new ArrayList<>();

	// DT
	public BasicBlock parent = null;
	public ArrayList<BasicBlock> children = new ArrayList<>();

	// Liveness Analysis
	public BitSet generated = new BitSet();
	public BitSet preserved = new BitSet();
	public BitSet in = new BitSet();
	public BitSet out = new BitSet();
	public boolean visited = false;

	private void addEdge(BasicBlock other) {
		successors.add(other);
		other.predecessors.add(this);
	}

	private void setParent(BasicBlock other) {
		parent = other;
		parent.children.add(this);
	}

	private boolean isGenerated(VirtualRegisterOperand o) {
		return generated.get(o.getRegisterId());
	}

	private void generates(VirtualRegisterOperand o) {
		generated.set(o.getRegisterId());
	}

	private boolean isPreserved(VirtualRegisterOperand o) {
		return !preserved.get(o.getRegisterId());
	}

	private void preserves(VirtualRegisterOperand o) {
		preserved.set(o.getRegisterId());
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
		// Ensure only 1 exit block exists
		BasicBlock exit = new BasicBlock();
		for (BasicBlock block : blocks) {
			if (block.successors.isEmpty())
				block.addEdge(exit);
		}
		blocks.add(exit);
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

	public static void analyzeLiveness(ArrayList<BasicBlock> blocks) {
		for (BasicBlock b : blocks) {
			for (IlocInstruction i : b.instructions) {
				if (i instanceof ThreeAddressIlocInstruction) {
					ThreeAddressIlocInstruction instruction = (ThreeAddressIlocInstruction) i;

					Operand[] sources = { instruction.getLeftOperand(), instruction.getRightOperand() };
					Operand destination = instruction.getDestination();

					for (Operand source : sources) {
						if (source instanceof VirtualRegisterOperand) {
							VirtualRegisterOperand rval = (VirtualRegisterOperand) source;

							if (b.isPreserved(rval)) {
								b.generates(rval);
							}
						}
					}

					if (destination instanceof VirtualRegisterOperand) {
						VirtualRegisterOperand lval = (VirtualRegisterOperand) destination;

						b.preserves(lval);
					}

				} else if (i instanceof TwoAddressIlocInstruction) {
					TwoAddressIlocInstruction instruction = (TwoAddressIlocInstruction) i;

					Operand source = instruction.getSource();
					Operand destination = instruction.getDestination();

					if (source instanceof VirtualRegisterOperand) {
						VirtualRegisterOperand rval = (VirtualRegisterOperand) source;

						if (b.isPreserved(rval)) {
							b.generates(rval);
						}
					}

					if (destination instanceof VirtualRegisterOperand) {
						VirtualRegisterOperand lval = (VirtualRegisterOperand) destination;

						b.preserves(lval);
					}

				} else if (i instanceof OneAddressIlocInstruction) {
					OneAddressIlocInstruction instruction = (OneAddressIlocInstruction) i;

					Operand source = instruction.getOperand();

					if (source instanceof VirtualRegisterOperand) {
						VirtualRegisterOperand rval = (VirtualRegisterOperand) source;

						if (b.isPreserved(rval)) {
							b.generates(rval);
						}
					}
				}
			}
		}

		boolean changed;
		do {
			for (BasicBlock b : blocks) {
				b.visited = false;
			}
			changed = propagate(blocks.get(0), blocks);
		} while (changed);

		// DEBUG
		for (BasicBlock b : blocks) {
			System.out.println(blocks.indexOf(b) + ":: in: " + b.in + ", gen: " + b.generated + ", prsv: " + b.preserved
					+ ", out: " + b.out);
		}
	}

	private static boolean propagate(BasicBlock b, ArrayList<BasicBlock> blocks) {
		b.visited = true;

		boolean changed = false;
		for (BasicBlock successor : b.successors) {
			if (!successor.visited) {
				changed = propagate(successor, blocks);
			}

			BitSet old = b.out;
			b.out.or(successor.in);
			if (!old.equals(b.out))
				changed = true;
		}

		BitSet old = b.in;
		BitSet out = (BitSet) b.out.clone();
		BitSet gen = (BitSet) b.generated.clone();
		out.andNot(b.preserved);
		gen.or(out);
		b.in = gen;
		if (!old.equals(b.in))
			changed = true;

		return changed;
	}
}
