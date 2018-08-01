/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package bind.feature.generichierarchy.kripton109.animations;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindTypeVariables;

// TODO: Auto-generated Javadoc
/**
 * The Class Parallel2Animation.
 *
 * @param <K0> the generic type
 * @param <K1> the generic type
 */
public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> {

	/** The Constant NUMBER_OF_ANIMATIONS. */
	static final int NUMBER_OF_ANIMATIONS = 2;

	/** The frames 1. */
	@Bind("frame1")
	public ArrayList<K1> frames1;

	/**
	 * Instantiates a new parallel 2 animation.
	 */
	public Parallel2Animation() {
		frames1 = new ArrayList<>();
	}

	/**
	 * Adds the 1.
	 *
	 * @param frame the frame
	 * @return the int
	 */
	public int add1(K1 frame) {
		// se non abbiamo un nome, lo aggiungiamo di default
		if (frame.name == null) {
			frame.name = "keyframe1-" + frames.size();
		}
		frames1.add(frame);

		return frames1.size() - 1;
	}

	/* (non-Javadoc)
	 * @see bind.feature.generichierarchy.kripton109.animations.Animation#duration()
	 */
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

	/**
	 * Gets the frame 1.
	 *
	 * @param index the index
	 * @return the frame 1
	 */
	public K1 getFrame1(int index) {
		return frames1.get(index);
	}

	/**
	 * Sets the animation.
	 *
	 * @param value the new animation
	 */
	public void setAnimation(Animation<K0> value) {
		frames = value.frames;
	}

	/**
	 * Sets the animation 1.
	 *
	 * @param value the new animation 1
	 */
	public void setAnimation1(Animation<K1> value) {
		frames1 = value.frames;
	}

}
