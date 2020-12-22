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
package bind.feature.generichierarchy;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import bind.AbstractBaseTest;


/**
 * The Class TestRuntimeHierarchy.
 */
public class TestRuntimeHierarchy extends AbstractBaseTest {

	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception {
		Channel channel=new Channel();
		channel.uid="25";
		channel.imageSize=22;
		channel.name="dyumm";
		
		ChannelListResponse bean=new ChannelListResponse();
		bean.list=new ArrayList<>();
		bean.map=new HashMap<>();
		
		bean.bean=channel;
		bean.list.add(channel);
		bean.map.put("ca", channel);
		
		check(bean);
	}

}
