package iloc;

import java.util.ArrayList;

public class IlocFrame {
	private FramePseudoOp frameInstruction;

	public IlocFrame(IlocInstruction frameInstruction) {
		this.frameInstruction = (FramePseudoOp) frameInstruction;
	}

	public static ArrayList<IlocFrame> findFrames(IlocProgram program) {
		ArrayList<IlocFrame> frames = new ArrayList<>();
		// We assume the parser always adds a frame as the first instruction,
		// even if it is not present in the source.
		frames.add(new IlocFrame(program.head));
		for (IlocInstruction i = program.head.getNext(); i != null; i = i.getNext()) {
			if (i instanceof FramePseudoOp) {
				frames.add(new IlocFrame(i));
			}
		}
		return frames;
	}

	public IlocInstruction head() {
		return frameInstruction.nextInFrame();
	}
}
