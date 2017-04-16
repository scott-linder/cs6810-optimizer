package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import codegen.BasicBlock;
import codegen.PhiNode;
import iloc.IlocFrame;
import iloc.IlocInstruction;
import iloc.IlocProgram;
import iloc.VirtualRegisterOperand;
import parser.IlocParser;
import parser.ParseException;

public class Fib {
	private ArrayList<IlocFrame> framesList;
	private ArrayList<ArrayList<BasicBlock>> blocksList;
	BasicBlock a0, a1, a2, a3;

	private void nameBlocks() {
		a0 = blocksList.get(0).get(0);
		a1 = blocksList.get(0).get(1);
		a2 = blocksList.get(0).get(2);
		a3 = blocksList.get(0).get(3);
	}

	@Before
	public void parseProgram() throws ParseException {
		// open test file
		byte[] ilocSource = null;
		try {
			ilocSource = Files.readAllBytes(Paths.get("tests/fib.il"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// parse input file
		InputStream inputStream = new ByteArrayInputStream(ilocSource);
		IlocParser parser = new IlocParser(inputStream);
		IlocProgram ilocProgram = parser.program();
		framesList = IlocFrame.findFrames(ilocProgram);

		// list of frames with list of blocks
		blocksList = new ArrayList<>();
		framesList.iterator().forEachRemaining((IlocFrame f) -> blocksList.add(BasicBlock.findBasicBlocks(f)));
	}

	@Test
	public void testNumberOfBasicBlocks() {
		Iterator<Integer> expectedNumberOfBlocks = Arrays.asList(3).iterator();
		blocksList.iterator().forEachRemaining(
				(ArrayList<BasicBlock> bs) -> assertThat(bs.size(), is(expectedNumberOfBlocks.next())));
	}

	@Test
	public void testSizeOfEachBasicBlock() {
		Iterator<Integer> expectedSizeOfEachBlock = Arrays.asList(12, 13, 2, 0).iterator();
		for (ArrayList<BasicBlock> bs : blocksList) {
			for (BasicBlock b : bs) {
				assertThat(b.instructions.size(), is(expectedSizeOfEachBlock.next()));
			}
		}
	}

	@Test
	public void testConstructCFG() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
		}

		nameBlocks();

		assertThat(new HashSet<>(a0.predecessors), is(new HashSet<>()));
		assertThat(new HashSet<>(a0.successors), is(new HashSet<>(Arrays.asList(a1, a2))));

		assertThat(new HashSet<>(a1.predecessors), is(new HashSet<>(Arrays.asList(a0, a1))));
		assertThat(new HashSet<>(a1.successors), is(new HashSet<>(Arrays.asList(a1, a2))));

		assertThat(new HashSet<>(a2.predecessors), is(new HashSet<>(Arrays.asList(a0, a1))));
		assertThat(new HashSet<>(a2.successors), is(new HashSet<>(Arrays.asList(a3))));

		assertThat(new HashSet<>(a3.predecessors), is(new HashSet<>(Arrays.asList(a2))));
		assertThat(new HashSet<>(a3.successors), is(new HashSet<>()));
	}

	@Test
	public void testConstructDT() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.constructDT(bs);
		}

		nameBlocks();

		assertThat(a0.parent, is((BasicBlock) null));
		assertThat(a0.children, is(Arrays.asList(a1, a2)));

		assertThat(a1.parent, is((a0)));
		assertThat(a1.children, is(Arrays.asList()));

		assertThat(a2.parent, is((a0)));
		assertThat(a2.children, is(Arrays.asList(a3)));

		assertThat(a3.parent, is((a2)));
		assertThat(a3.children, is(Arrays.asList()));
	}

	@Test
	public void testComputeDF() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.constructDT(bs);
			BasicBlock.computeDF(bs);
		}

		nameBlocks();

		// frame a blocks
		assertThat(a0.df, is(new HashSet<>()));
		assertThat(a1.df, is(new HashSet<>(Arrays.asList(a1, a2))));
		assertThat(a2.df, is(new HashSet<>()));
		assertThat(a3.df, is(new HashSet<>()));
	}

	@Test
	public void testAnalyzeLiveness() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.analyzeLiveness(bs);
		}

		nameBlocks();

		BitSet none = new BitSet();

		BitSet all = new BitSet();
		all.set(3, 14 + 1);

		BitSet four_to_seven = new BitSet();
		four_to_seven.set(4, 7 + 1);

		BitSet three_nine = new BitSet();
		three_nine.set(3);
		three_nine.set(9);

		BitSet three_thirteen_fourteen = new BitSet();
		three_thirteen_fourteen.set(3);
		three_thirteen_fourteen.set(13);
		three_thirteen_fourteen.set(14);

		assertThat(a0.in, is(none));
		assertThat(a0.generated, is(none));
		BitSet preserved = (BitSet) all.clone();
		preserved.andNot(three_thirteen_fourteen);
		assertThat(a0.preserved, is(preserved));
		assertThat(a0.out, is(four_to_seven));

		assertThat(a1.in, is(four_to_seven));
		assertThat(a1.generated, is(four_to_seven));
		preserved = (BitSet) all.clone();
		preserved.andNot(three_nine);
		assertThat(a1.preserved, is(preserved));
		assertThat(a1.out, is(four_to_seven));

		assertThat(a2.in, is(none));
		assertThat(a2.generated, is(none));
		preserved = (BitSet) all.clone();
		preserved.andNot(all);
		assertThat(a2.preserved, is(preserved));
		assertThat(a2.out, is(none));

		assertThat(a3.in, is(none));
		assertThat(a3.generated, is(none));
		preserved = (BitSet) all.clone();
		preserved.andNot(all);
		assertThat(a3.preserved, is(preserved));
		assertThat(a3.out, is(none));

	}

	@Test
	public void testInsertPhiNodes() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.constructDT(bs);
			BasicBlock.computeDF(bs);
			BasicBlock.analyzeLiveness(bs);
			BasicBlock.insertPhiNodes(bs);
		}

		nameBlocks();

		assertThat(a0.phiNodes.size(), is(0));
		assertThat(a1.phiNodes.size(), is(4));
		assertThat(a2.phiNodes.size(), is(0));
		assertThat(a3.phiNodes.size(), is(0));

		HashSet<String> phiTargets = new HashSet<>();
		for (PhiNode pn : a1.phiNodes) {
			phiTargets.add(pn.target.toString());
		}
		assertThat(phiTargets, is(new HashSet<>(Arrays.asList("%vr4", "%vr5", "%vr6", "%vr7"))));

		for (PhiNode pn : a1.phiNodes)
			assertThat(pn.operands.size(), is(2));
	}

	private static ArrayList<Integer> ssaIds(BasicBlock b, Integer j) {
		ArrayList<Integer> ids = new ArrayList<>();
		IlocInstruction i = b.instructions.get(j);
		for (VirtualRegisterOperand vr : i.registerOperands())
			ids.add(vr.getSSAId());
		if (i.registerDestination() != null)
			ids.add(i.registerDestination().getSSAId());
		return ids;
	}

	@Test
	public void testOptRename() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.constructDT(bs);
			BasicBlock.computeDF(bs);
			BasicBlock.analyzeLiveness(bs);
			BasicBlock.insertPhiNodes(bs);
			BasicBlock.optSSA(bs);
		}

		nameBlocks();

		assertThat(ssaIds(a0, 0), is(new ArrayList<>(Arrays.asList(1))));
		assertThat(ssaIds(a0, 1), is(new ArrayList<>(Arrays.asList(1, 1))));
		assertThat(ssaIds(a0, 2), is(new ArrayList<>(Arrays.asList(1))));
		assertThat(ssaIds(a0, 3), is(new ArrayList<>(Arrays.asList(1, 1))));
		assertThat(a0.instructions.get(4).isDead(), is(true));
		assertThat(ssaIds(a0, 5), is(new ArrayList<>(Arrays.asList(1, 1))));
		assertThat(a0.instructions.get(6).isDead(), is(true));
		assertThat(ssaIds(a0, 7), is(new ArrayList<>(Arrays.asList(1, 1))));
		assertThat(ssaIds(a0, 8), is(new ArrayList<>(Arrays.asList(1))));
		assertThat(ssaIds(a0, 9), is(new ArrayList<>(Arrays.asList(1, 1, 1))));
		assertThat(ssaIds(a0, 10), is(new ArrayList<>(Arrays.asList(1, 1))));
		assertThat(ssaIds(a0, 11), is(new ArrayList<>(Arrays.asList(1))));

	}
}
