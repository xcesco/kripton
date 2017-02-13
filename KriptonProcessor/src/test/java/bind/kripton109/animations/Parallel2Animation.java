/**
 * 
 */
package bind.kripton109.animations;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;

public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> {

	static final int NUMBER_OF_ANIMATIONS = 2;

	@Bind("frame1")
	public ArrayList<K1> frames1;

	public Parallel2Animation() {
		frames1 = new ArrayList<>();
	}

	public int add1(K1 frame) {
		// se non abbiamo un nome, lo aggiungiamo di default
		if (frame.name == null) {
			frame.name = "keyframe1-" + frames.size();
		}
		frames1.add(frame);

		return frames1.size() - 1;
	}

	@Override
	public long duration() {
		long maxDuration = 0;

		{
			// frame
			long duration = 0;
			int n = frames.size();
			for (int i = 0; i < n; i++) {
				duration += frames.get(i).duration;
			}

			maxDuration = Math.max(maxDuration, (long) (duration * rate));
		}

		{
			// frame 1
			long duration = 0;
			int n = frames1.size();
			for (int i = 0; i < n; i++) {
				duration += frames1.get(i).duration;
			}

			maxDuration = Math.max(maxDuration, (long) (duration * rate));
		}

		return maxDuration;
	}

	public K1 getFrame1(int index) {
		return frames1.get(index);
	}

	public void setAnimation(Animation<K0> value) {
		frames = value.frames;
	}

	public void setAnimation1(Animation<K1> value) {
		frames1 = value.frames;
	}

}
