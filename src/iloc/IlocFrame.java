package iloc;

import java.util.ArrayList;

public class IlocFrame {
	public FramePseudoOp frameInstruction;
	public ArrayList<IlocInstruction> instructions = new ArrayList<>();
	
	public IlocFrame(FramePseudoOp frameInstruction) {
		this.frameInstruction = frameInstruction;
	}
	
	public static ArrayList<IlocFrame> findFrames(IlocProgram program) {
		ArrayList<IlocFrame> frames = new ArrayList<>();
		// We assume the parser always adds a frame as the first instruction,
		// even if it is not present in the source.
		IlocFrame frame = new IlocFrame((FramePseudoOp) program.instructions.get(0));
		for (int i = 1; i < program.instructions.size(); i++) {
			IlocInstruction instruction = program.instructions.get(i);
			if (instruction instanceof FramePseudoOp) {
				frames.add(frame);
				frame = new IlocFrame((FramePseudoOp) instruction);
			} else {
				frame.instructions.add(instruction);
			}
		}
		frames.add(frame);
		return frames;
	}
}
