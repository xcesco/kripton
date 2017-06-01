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
package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

import bind.kripton109.animations.math.Vector3;

@BindType
public class TranslationFrame extends KeyFrame {

	public static TranslationFrame build(long duration) {
		return build(0f, 0f, 0f, duration);
	}

	public static TranslationFrame build(float x, float y, float z, long duration) {
		TranslationFrame frame = new TranslationFrame();

		frame.translation.setCoords(x, y, z);
		frame.duration = duration;

		return frame;
	}

	@Bind
	public Vector3 translation = new Vector3();

	public TranslationFrame() {
	}

}
