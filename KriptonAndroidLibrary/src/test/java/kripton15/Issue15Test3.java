/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
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
package kripton15;

import org.junit.Before;

import all.IssueBaseTest;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue15Test3 extends IssueBaseTest<Bean3> {

	@Before
	public void setup()
	{
		beanInput=new Bean3();
		
		beanInput.map.put(new Bean0("key1", 12), new Bean0("value1", 23));
		beanInput.map.put(new Bean0("key2", 23), new Bean0("value2", 34));
		beanInput.map.put(new Bean0("key3", 34), new Bean0("value3", 45));
		
		
	}

}
