/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package bind.kripton75ByteArray;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime75 extends AbstractBaseTest {


	@Test
	public void testRun() throws Exception
	{
		Assert.assertNotNull(new Bean75BindMap());
		
		Bean75 bean=new Bean75();
			
		bean.valueByteArray=new Byte[2];
		bean.valueByteArray[0]='1';
		bean.valueByteArray[1]='2';
				
		bean.valueByteTypeArray="hello world2!".getBytes();
		
		check(bean);
	}

	
}
