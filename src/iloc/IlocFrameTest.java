package iloc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import parser.IlocParser;
import parser.ParseException;

public class IlocFrameTest {

	@Test
	public void testFindFrames() throws ParseException {
		String ilocSource = ".frame main, 0\n" + "nop\n" + ".frame foo, 0\n" + "nop\n" + "nop\n" + ".frame bar, 0\n"
				+ "nop\n" + "nop\n" + "nop\n";
		InputStream ilocFile = new ByteArrayInputStream(ilocSource.getBytes(StandardCharsets.UTF_8));
		IlocParser parser = new IlocParser(ilocFile);
		IlocProgram program = parser.program();
		ArrayList<IlocFrame> frames = IlocFrame.findFrames(program);
		assertThat(frames.size(), is(3));
		List<Integer> sizes = new ArrayList<>();
		frames.iterator().forEachRemaining((IlocFrame f) -> sizes.add(f.instructions.size()));
		Collections.sort(sizes);
		List<Integer> expected = Arrays.asList(1, 2, 3);
		assertThat(sizes, is(expected));
	}

}
