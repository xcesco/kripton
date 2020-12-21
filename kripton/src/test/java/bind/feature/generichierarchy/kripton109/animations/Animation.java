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
package bind.feature.generichierarchy.kripton109.animations;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindXml;


/**
 * The Class Animation.
 *
 * @param <K> the key type
 */
public class Animation<K extends KeyFrame> {

	/** The frames. */
	@Bind(order = 4)
	@BindXml(elementTag = "frame")
	public ArrayList<K> frames;

	/** The loop. */
	@Bind(order = 1)
	protected boolean loop = false;

	/** The name. */
	@Bind(order = 0)
	protected String name;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** The rate. */
	@Bind(order = 3)
	protected float rate = 1.0f;

	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * Instantiates a new animation.
	 */
	public Animation() {
		frames = new ArrayList<K>();
		rate = 1.0f;
		loop = false;
	}

	/**
	 * Instantiates a new animation.
	 *
	 * @param values the values
	 */
	public Animation(ArrayList<K> values) {
		frames = values;
		rate = 1.0f;
		loop = false;
	}

	/**
	 * Adds the.
	 *
	 * @param frame the frame
	 * @return the int
	 */
	public int add(K frame) {
		// se non abbiamo un nome, lo aggiungiamo di default
		if (frame.name == null) {
			frame.name = "keyframe" + frames.size();
		}
		frames.add(frame);

		return frames.size() - 1;
	}

	/**
	 * Duration.
	 *
	 * @return the long
	 */
	public long duration() {
		long duration = 0;
		int n = frames.size();
		for (int i = 0; i < n; i++) {
			duration += frames.get(i).duration;
		}

		return (long) (duration * rate);
	}

	/**
	 * Gets the frame.
	 *
	 * @param index the index
	 * @return the frame
	 */
	public K getFrame(int index) {
		return frames.get(index);
	}

	/**
	 * Checks if is loop.
	 *
	 * @return true, if is loop
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * Sets the interval.
	 *
	 * @param firstFrame the first frame
	 * @param secondFrame the second frame
	 */
	public void setInterval(K firstFrame, K secondFrame) {
		frames.clear();
		add(firstFrame);
		add(secondFrame);
	}

	/**
	 * Sets the loop.
	 *
	 * @param value the new loop
	 */
	public void setLoop(boolean value) {
		loop = value;
	}

	/**
	 * Sets the rate.
	 *
	 * @param value the new rate
	 */
	public void setRate(float value) {
		rate = value;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return frames.size();
	}

}
