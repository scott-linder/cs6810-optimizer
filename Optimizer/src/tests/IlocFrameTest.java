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
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import iloc.IlocFrame;
import iloc.IlocProgram;
import parser.IlocParser;
import parser.ParseException;

public class IlocFrameTest {
	private ArrayList<IlocFrame> framesList;

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
	}

	@Test
	public void testNumberOfFrames() {
		// check number of frames
		assertThat(framesList.size(), is(2));
	}

	@Test
	public void testSizeOfFrames() {
		// check size of each frame
		List<Integer> sizes = new ArrayList<>();
		framesList.iterator().forEachRemaining((IlocFrame f) -> sizes.add(f.head().frameSize()));
		Collections.sort(sizes);
		List<Integer> expected = Arrays.asList(19, 54);
		assertThat(sizes, is(expected));
	}
}
