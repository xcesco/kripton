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
package bind.kripton110;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton110.model.stage1.Friend;
import bind.kripton110.model.stage1.Image;
import bind.kripton110.model.stage1.Name;
import bind.kripton110.model.stage1.Response;
import bind.kripton110.model.stage1.User;

public class TestCompile110 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Friend.class, Image.class, Name.class, Response.class, User.class, bind.kripton110.model.stage2.Name.class,bind.kripton110.model.stage2.Native.class,bind.kripton110.model.stage2.Nld.class, bind.kripton110.model.stage2.Pap.class);
	}


}
