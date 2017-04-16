package codegen;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

import iloc.ConstantOperand;
import iloc.IlocFrame;
import iloc.IlocInstruction;
import iloc.ImmediateOperand;
import iloc.LabelOperand;
import iloc.LoadIInstruction;
import iloc.VirtualRegisterOperand;

public class BasicBlock {
	public ArrayList<PhiNode> phiNodes = new ArrayList<>();
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

	// DF
	public HashSet<BasicBlock> df = new HashSet<>();

	// LVN
	public static int nextValueNumber;

	private void addEdge(BasicBlock other) {
		successors.add(other);
		other.predecessors.add(this);
	}

	private void setParent(BasicBlock other) {
		parent = other;
		parent.children.add(this);
	}

	private boolean dominates(BasicBlock other) {
		if (this == other)
			return true;
		for (BasicBlock child : children) {
			if (child.dominates(other))
				return true;
		}
		return false;
	}

	private boolean strictlyDominates(BasicBlock other) {
		return dominates(other) && this != other;
	}

	private boolean isGenerated(VirtualRegisterOperand o) {
		return generated.get(o.getRegisterId());
	}

	private boolean isGenerated(int o) {
		return generated.get(o);
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

	/*
	 * Instructions do not know what block they are in, so we must explicitly
	 * remove them when they die.
	 */
	public void removeDeadInstructions() {
		instructions.removeIf(i -> i.isDead());
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
			b.generated = new BitSet();
			b.preserved = new BitSet();
			b.in = new BitSet();
			b.out = new BitSet();

			for (IlocInstruction i : b.instructions) {
				for (VirtualRegisterOperand rval : i.registerSources()) {
					if (b.isPreserved(rval)) {
						b.generates(rval);
					}
				}
				VirtualRegisterOperand lval = i.registerDestination();
				if (lval != null) {
					b.preserves(lval);
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

	public static void removeDeadCode(ArrayList<BasicBlock> blocks) {
		for (BasicBlock b : blocks) {
			BitSet live = (BitSet) b.out.clone();
			for (int j = b.instructions.size() - 1; j >= 0; j--) {
				IlocInstruction i = b.instructions.get(j);
				if (i.registerDestination() != null) {
					int id = i.registerDestination().getRegisterId();
					if (!live.get(id)) {
						i.kill();
					} else {
						live.clear(id);
					}
				}
				if (!i.isDead()) {
					for (VirtualRegisterOperand rval : i.registerSources()) {
						live.set(rval.getRegisterId());
					}
				} else {
					i.remove();
				}
			}
			b.removeDeadInstructions();
		}
	}

	public static void computeDF(ArrayList<BasicBlock> blocks) {
		computeDFRecursive(blocks.get(0));
	}

	private static void computeDFRecursive(BasicBlock block) {
		// Post-order
		if (block == null)
			return;
		for (BasicBlock child : block.children)
			computeDFRecursive(child);
		// Actual iteration
		for (BasicBlock child : block.children) {
			for (BasicBlock childsDf : child.df) {
				if (!block.strictlyDominates(childsDf)) {
					block.df.add(childsDf);
				}
			}
		}
		for (BasicBlock successor : block.successors) {
			if (!block.strictlyDominates(successor)) {
				block.df.add(successor);
			}
		}
	}

	private static HashSet<BasicBlock> getSv(int vr, ArrayList<BasicBlock> blocks) {
		HashSet<BasicBlock> sv = new HashSet<>();
		for (BasicBlock block : blocks) {
			if (block.isGenerated(vr)) {
				sv.add(block);
			}
		}
		sv.add(blocks.get(0));
		return sv;
	}

	private static HashSet<BasicBlock> computeDFPlus(HashSet<BasicBlock> sv) {
		Queue<BasicBlock> work = new LinkedList<>();
		HashSet<BasicBlock> dfPlus = new HashSet<>();
		work.addAll(sv);
		while (!work.isEmpty()) {
			BasicBlock b = work.remove();
			for (BasicBlock c : b.df) {
				if (!dfPlus.contains(c)) {
					dfPlus.add(c);
					work.add(c);
				}
			}
		}
		return dfPlus;
	}

	private static BitSet variables(ArrayList<BasicBlock> blocks) {
		BitSet vars = new BitSet();
		for (BasicBlock block : blocks) {
			vars.or(block.out);
		}
		return vars;
	}

	public static void insertPhiNodes(ArrayList<BasicBlock> blocks) {
		BitSet vars = variables(blocks);
		for (int t = vars.nextSetBit(0); t >= 0; t = vars.nextSetBit(t + 1)) {
			HashSet<BasicBlock> sv = getSv(t, blocks);
			HashSet<BasicBlock> dfplus = computeDFPlus(sv);
			for (BasicBlock block : dfplus) {
				if (block.in.get(t)) {
					int n = block.predecessors.size();
					block.phiNodes.add(new PhiNode(t, n));
				}
			}
		}
	}

	private static boolean availTarget(ArrayList<HashSet<String>> avails, HashMap<String, Stack<Integer>> nameStacks,
			IlocInstruction i) {
		for (int j = avails.size() - 1; j >= 0; j--)
			if (avails.get(j).contains(i.expr()))
				return true;
		// add expr since we did not find it
		avails.get(avails.size() - 1).add(i.expr());
		return false;
	}

	public static void optSSA(ArrayList<BasicBlock> blocks) {
		// Block structured table of available expressions
		ArrayList<HashSet<String>> avails = new ArrayList<>();
		// Namestacks
		HashMap<String, Stack<Integer>> nameStacks = new HashMap<>();
		HashSet<String> variables = new HashSet<>();
		for (BasicBlock block : blocks) {
			for (IlocInstruction i : block.instructions) {
				for (VirtualRegisterOperand vr : i.registerSources()) {
					variables.add(vr.toString());
				}
				if (i.registerDestination() != null) {
					variables.add(i.registerDestination().toString());
				}
			}
		}
		for (String vr : variables) {
			nameStacks.put(vr, new Stack<>());
			// hack to avoid needing to know what registers the frame defines;
			// we simply assume all variables have a definition at the entry to
			// the frame
			newName(nameStacks, vr);
		}
		optRename(blocks.get(0), nameStacks, avails);
	}

	private static void newName(HashMap<String, Stack<Integer>> nameStacks, String vr) {
		if (nameStacks.get(vr).isEmpty())
			nameStacks.get(vr).push(0);
		else
			nameStacks.get(vr).push(nameStacks.get(vr).peek() + 1);
	}

	public static void optRename(BasicBlock block, HashMap<String, Stack<Integer>> nameStacks,
			ArrayList<HashSet<String>> avails) {

		for (PhiNode phi : block.phiNodes) {
			newName(nameStacks, phi.target.toString());
		}

		// StartBlock
		avails.add(new HashSet<>());

		for (IlocInstruction i : block.instructions) {
			for (VirtualRegisterOperand t : i.registerSources()) {
				// replace T by top of name stack
				t.setSSAId(nameStacks.get(t.toString()).peek());
			}
			if (i.registerDestination() != null) {
				if (!i.hasSideEffects() && availTarget(avails, nameStacks, i)) {
					Stack<Integer> ns = nameStacks.get(i.registerDestination().toString());
					ns.push(ns.peek());
					i.kill();
				} else {
					newName(nameStacks, i.registerDestination().toString());
				}
			}
		}

		for (int i = 0; i < block.successors.size(); i++) {
			for (PhiNode phi : block.phiNodes) {
				phi.operands.get(i).setSSAId(nameStacks.get(phi.operands.get(i).toString()).peek());
			}
		}

		for (BasicBlock c : block.children) {
			optRename(c, nameStacks, avails);
		}

		for (int j = block.instructions.size() - 1; j >= 0; j--) {
			IlocInstruction i = block.instructions.get(j);
			if (i.registerDestination() != null) {
				int x = nameStacks.get(i.registerDestination().toString()).pop();
				if (i.isDead()) {
					i.remove();
				} else {
					i.registerDestination().setSSAId(x);
				}
			}
		}

		block.removeDeadInstructions();

		for (PhiNode phi : block.phiNodes) {
			phi.target.setSSAId(nameStacks.get(phi.target.toString()).pop());
		}

		// EndBlock
		avails.remove(avails.size() - 1);
	}

	/*
	 * Convert SSA back to normal form by dropping all subscripts.
	 */
	public static void unSSA(ArrayList<BasicBlock> blocks) {
		for (BasicBlock b : blocks) {
			for (IlocInstruction i : b.instructions) {
				for (VirtualRegisterOperand vr : i.registerSources())
					vr.setSSAId(-1);
				if (i.registerDestination() != null)
					i.registerDestination().setSSAId(-1);
			}
		}
	}

	private static int valnum(HashMap<String, Integer> symbolTable, String name) {
		if (symbolTable.containsKey(name))
			return symbolTable.get(name);
		symbolTable.put(name, nextValueNumber);
		return nextValueNumber++;
	}

	private static void setvalnum(HashMap<String, Integer> symbolTable, String name, int valueNumber) {
		symbolTable.put(name, valueNumber);
	}

	public static void localValueNumbering(BasicBlock b) {
		// This is static as a convenience, so valnum behaves as in the notes
		nextValueNumber = 0;
		// maps ValueNumber to Value
		HashMap<Integer, Integer> constantTable = new HashMap<>();
		// maps Name (%vrX for registers, Y for constants) to ValueNumber (TODO:
		// needs subsumes/subsumed)
		HashMap<String, Integer> symbolTable = new HashMap<>();
		// maps "<r1,op,r2>" to virtual register number (just X for %vrX)
		HashMap<String, Integer> exprTable = new HashMap<>();

		for (IlocInstruction i : b.instructions) {
			// We only care about instructions which write to a register
			VirtualRegisterOperand lval = i.registerDestination();
			if (lval == null)
				continue;

			if (i instanceof LoadIInstruction) {
				ImmediateOperand rvalI = (ImmediateOperand) ((LoadIInstruction) i).getSource();
				if (rvalI instanceof ConstantOperand) {
					int value = ((ConstantOperand) rvalI).getValue();
					int valueNumber = valnum(symbolTable, Integer.toString(value));
					String expr = String.format("<%d,iLDI,-1>", valueNumber);
					if (exprTable.containsKey(expr)) {
						i.kill();
						i.remove();
					} else {
						setvalnum(symbolTable, lval.toString(), valueNumber);
						constantTable.put(valueNumber, value);
						exprTable.put(expr, lval.getRegisterId());
					}
				}
			}
		}
		b.removeDeadInstructions();
	}
}
