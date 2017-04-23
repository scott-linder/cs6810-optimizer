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
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import codegen.BasicBlock;
import iloc.IlocFrame;
import iloc.IlocProgram;
import parser.IlocParser;
import parser.ParseException;

public class BasicBlockTest {
	private ArrayList<IlocFrame> framesList;
	private ArrayList<ArrayList<BasicBlock>> blocksList;
	BasicBlock a0;
	BasicBlock a1;
	BasicBlock a2;
	BasicBlock a3;
	BasicBlock b0;
	BasicBlock b1;
	BasicBlock b2;
	BasicBlock b3;
	BasicBlock b4;

	private void nameBlocks() {
		a0 = blocksList.get(0).get(0);
		a1 = blocksList.get(0).get(1);
		a2 = blocksList.get(0).get(2);
		a3 = blocksList.get(0).get(3);
		b0 = blocksList.get(1).get(0);
		b1 = blocksList.get(1).get(1);
		b2 = blocksList.get(1).get(2);
		b3 = blocksList.get(1).get(3);
		b4 = blocksList.get(1).get(4);
	}

	@Before
	public void parseProgram() throws ParseException {
		// open test file
		byte[] ilocSource = null;
		try {
			ilocSource = Files.readAllBytes(Paths.get("tests/gcd.il"));
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
		// check number of blocks
		Iterator<Integer> expectedNumberOfBlocks = Arrays.asList(3, 4).iterator();
		blocksList.iterator().forEachRemaining(
				(ArrayList<BasicBlock> bs) -> assertThat(bs.size(), is(expectedNumberOfBlocks.next())));
	}

	@Test
	public void testSizeOfEachBasicBlock() {
		// check size of each block
		Iterator<Integer> expectedSizeOfEachBlock = Arrays.asList(25, 27, 2, 5, 3, 9, 2).iterator();
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

		// frame a blocks
		assertThat(new HashSet<>(a0.predecessors), is(new HashSet<>()));
		assertThat(new HashSet<>(a0.successors), is(new HashSet<>(Arrays.asList(a1, a2))));
		assertThat(new HashSet<>(a1.predecessors), is(new HashSet<>(Arrays.asList(a0, a1))));
		assertThat(new HashSet<>(a1.successors), is(new HashSet<>(Arrays.asList(a1, a2))));
		assertThat(new HashSet<>(a2.predecessors), is(new HashSet<>(Arrays.asList(a0, a1))));
		assertThat(new HashSet<>(a2.successors), is(new HashSet<>(Arrays.asList(a3))));
		assertThat(new HashSet<>(a3.predecessors), is(new HashSet<>(Arrays.asList(a2))));
		assertThat(new HashSet<>(a3.successors), is(new HashSet<>()));

		// frame b blocks
		assertThat(new HashSet<>(b0.predecessors), is(new HashSet<>()));
		assertThat(new HashSet<>(b0.successors), is(new HashSet<>(Arrays.asList(b1, b2))));
		assertThat(new HashSet<>(b1.predecessors), is(new HashSet<>(Arrays.asList(b0))));
		assertThat(new HashSet<>(b1.successors), is(new HashSet<>(Arrays.asList(b3))));
		assertThat(new HashSet<>(b2.predecessors), is(new HashSet<>(Arrays.asList(b0))));
		assertThat(new HashSet<>(b2.successors), is(new HashSet<>(Arrays.asList(b3))));
		assertThat(new HashSet<>(b3.predecessors), is(new HashSet<>(Arrays.asList(b1, b2))));
		assertThat(new HashSet<>(b3.successors), is(new HashSet<>(Arrays.asList(b4))));
		assertThat(new HashSet<>(b4.predecessors), is(new HashSet<>(Arrays.asList(b3))));
		assertThat(new HashSet<>(b4.successors), is(new HashSet<>()));
	}

	@Test
	public void testConstructDT() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.constructDT(bs);
		}

		nameBlocks();

		// frame a blocks
		assertThat(a0.parent, is((BasicBlock) null));
		assertThat(a0.children, is(Arrays.asList(a1, a2)));
		assertThat(a1.parent, is((a0)));
		assertThat(a1.children, is(Arrays.asList()));
		assertThat(a2.parent, is((a0)));
		assertThat(a2.children, is(Arrays.asList(a3)));
		assertThat(a3.parent, is((a2)));

		// frame b blocks
		assertThat(b0.parent, is((BasicBlock) null));
		assertThat(b0.children, is(Arrays.asList(b1, b2, b3)));
		assertThat(b1.parent, is((b0)));
		assertThat(b1.children, is(Arrays.asList()));
		assertThat(b2.parent, is((b0)));
		assertThat(b2.children, is(Arrays.asList()));
		assertThat(b3.parent, is((b0)));
		assertThat(b3.children, is(Arrays.asList(b4)));
		assertThat(b4.parent, is((b3)));
		assertThat(b4.children, is(Arrays.asList()));
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

		// frame b blocks
		assertThat(b0.df, is(new HashSet<>()));
		assertThat(b1.df, is(new HashSet<>(Arrays.asList(b3))));
		assertThat(b2.df, is(new HashSet<>(Arrays.asList(b3))));
		assertThat(b3.df, is(new HashSet<>()));
		assertThat(b4.df, is(new HashSet<>()));
	}

	@Test
	public void testAnalyzeLiveness() {
		for (ArrayList<BasicBlock> bs : blocksList) {
			BasicBlock.constructCFG(bs);
			BasicBlock.analyzeLiveness(bs);
		}

		nameBlocks();

		/* TODO: testing */
		assertThat(true, is(false));
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

		/* TODO: testing */
		assertThat(true, is(false));
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

		/* TODO: testing */
		assertThat(true, is(false));
	}
}
