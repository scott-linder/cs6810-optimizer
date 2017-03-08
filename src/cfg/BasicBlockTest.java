package cfg;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
		HashSet<BasicBlock> blocks = BasicBlock.findBasicBlocks(IlocFrame.findFrames(program).get(0));
		assertThat(blocks.size(), is(3));
		List<Integer> sizes = new ArrayList<>();
		blocks.iterator().forEachRemaining((BasicBlock b) -> sizes.add(b.instructions.size()));
		Collections.sort(sizes);
		List<Integer> expected = Arrays.asList(1, 2, 3);
		assertThat(sizes, is(expected));
	}

}
