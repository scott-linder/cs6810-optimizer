package cfg;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import iloc.IlocFrame;
import iloc.IlocProgram;
import parser.IlocParser;
import parser.ParseException;

public class BasicBlockTest {

	@Test
	public void testFindBasicBlocks() throws ParseException {
		String ilocSource = ".frame main, 0\n" + "nop\n" + ".L1: nop\n" + "nop\n" + ".L2: nop\n" + "nop\n" + "nop\n";
		InputStream ilocFile = new ByteArrayInputStream(ilocSource.getBytes(StandardCharsets.UTF_8));
		IlocParser parser = new IlocParser(ilocFile);
		IlocProgram program = parser.program();
		ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		assertThat(blocks.size(), is(3));
		List<Integer> sizes = new ArrayList<>();
		blocks.iterator().forEachRemaining((BasicBlock b) -> sizes.add(b.instructions.size()));
		Collections.sort(sizes);
		List<Integer> expected = Arrays.asList(1, 2, 3);
		assertThat(sizes, is(expected));
	}

	@Test
	public void testConstructCFG() throws ParseException {
		String ilocSource = ".frame main, 0\n" + "nop\n" + ".L1: nop\n" + "cbr %vr1 -> .L1\n" + ".L2: nop\n" + "nop\n"
				+ "cbr %vr1 -> .L2\n";
		InputStream ilocFile = new ByteArrayInputStream(ilocSource.getBytes(StandardCharsets.UTF_8));
		IlocParser parser = new IlocParser(ilocFile);
		IlocProgram program = parser.program();
		ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		BasicBlock.constructCFG(blocks);
		assertThat(blocks.get(0).predecessors.size(), is(0));
		assertThat(blocks.get(0).successors.size(), is(1));
		assertThat(blocks.get(1).predecessors.size(), is(2));
		assertThat(blocks.get(1).successors.size(), is(2));
		assertThat(blocks.get(2).predecessors.size(), is(2));
		assertThat(blocks.get(2).successors.size(), is(1));
	}

	@Test
	public void testConstructDT() throws ParseException {
		String ilocSource = ".frame main, 0\n" + "nop\n" + ".L1: nop\n" + "cbr %vr1 -> .L1\n" + ".L2: nop\n" + "nop\n"
				+ "cbr %vr1 -> .L2\n";
		InputStream ilocFile = new ByteArrayInputStream(ilocSource.getBytes(StandardCharsets.UTF_8));
		IlocParser parser = new IlocParser(ilocFile);
		IlocProgram program = parser.program();
		ArrayList<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		BasicBlock.constructCFG(blocks);
		BasicBlock.constructDT(blocks);
		assertThat(blocks.get(0).parent, is((BasicBlock) null));
		assertThat(blocks.get(0).children, is(Arrays.asList(blocks.get(1))));
		assertThat(blocks.get(1).parent, is((blocks.get(0))));
		assertThat(blocks.get(1).children, is(Arrays.asList(blocks.get(2))));
		assertThat(blocks.get(2).parent, is((blocks.get(1))));
		assertThat(blocks.get(2).children, is(Arrays.asList()));

	}

}
