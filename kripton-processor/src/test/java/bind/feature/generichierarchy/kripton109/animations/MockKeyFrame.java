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

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * The Class MockKeyFrame.
 */
@BindType
public class MockKeyFrame extends KeyFrame {
		
	/** The val. */
	@Bind
	public int val;
	
	/**
	 * Costruisce frame.
	 *
	 * @param name the name
	 * @param value the value
	 * @param duration the duration
	 * @return the mock key frame
	 */
	public static MockKeyFrame build(String name, int value, long duration)
	{
		MockKeyFrame ret=new MockKeyFrame();
		
		ret=new MockKeyFrame();
		ret.name=name;
		ret.val=value;
		ret.duration=duration;
		
		return ret;
	}

}
