package cfg;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import iloc.IlocFrame;
import iloc.IlocProgram;
import parser.IlocParser;
import parser.ParseException;

public class BasicBlockTest {

	private IlocProgram program;

	@Before
	public void parseProgram() throws ParseException {
		String ilocSource = ".frame main, 0\n" + "nop\n" + ".L1: nop\n" + "cbr %vr1 -> .L1\n" + ".L2: nop\n" + "nop\n"
				+ "cbr %vr1 -> .L2\n";
		InputStream ilocFile = new ByteArrayInputStream(ilocSource.getBytes(StandardCharsets.UTF_8));
		IlocParser parser = new IlocParser(ilocFile);
		program = parser.program();
	}

	@Test
	public void testFindBasicBlocks() {
		ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		assertThat(blocks.size(), is(3));
		List<Integer> sizes = new ArrayList<>();
		blocks.iterator().forEachRemaining((BasicBlock b) -> sizes.add(b.instructions.size()));
		Collections.sort(sizes);
		List<Integer> expected = Arrays.asList(1, 2, 3);
		assertThat(sizes, is(expected));
	}

	@Test
	public void testConstructCFG() {
		ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		BasicBlock.constructCFG(blocks);
		BasicBlock b0 = blocks.get(0);
		BasicBlock b1 = blocks.get(1);
		BasicBlock b2 = blocks.get(2);
		assertThat(new HashSet<>(b0.predecessors), is(new HashSet<>()));
		assertThat(new HashSet<>(b0.successors), is(new HashSet<>(Arrays.asList(b1))));
		assertThat(new HashSet<>(b1.predecessors), is(new HashSet<>(Arrays.asList(b0, b1))));
		assertThat(new HashSet<>(b1.successors), is(new HashSet<>(Arrays.asList(b1, b2))));
		assertThat(new HashSet<>(b2.predecessors), is(new HashSet<>(Arrays.asList(b1, b2))));
		assertThat(new HashSet<>(b2.successors), is(new HashSet<>(Arrays.asList(b2))));
	}

	@Test
	public void testConstructDT() {
		ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		BasicBlock.constructCFG(blocks);
		BasicBlock.constructDT(blocks);
		BasicBlock b0 = blocks.get(0);
		BasicBlock b1 = blocks.get(1);
		BasicBlock b2 = blocks.get(2);
		assertThat(b0.parent, is((BasicBlock) null));
		assertThat(b0.children, is(Arrays.asList(b1)));
		assertThat(b1.parent, is((b0)));
		assertThat(b1.children, is(Arrays.asList(b2)));
		assertThat(b2.parent, is((b1)));
		assertThat(b2.children, is(Arrays.asList()));
	}

}
