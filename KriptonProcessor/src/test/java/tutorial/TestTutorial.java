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
package tutorial;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import base.BaseProcessorTest;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestTutorial extends BaseProcessorTest {

	@Test
	public void testSQLite() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(SecurityDataSource.class, DaoUser.class, User.class);
	}	
	
	@Test
	public void test() throws InstantiationException, IllegalAccessException, IOException
	{
		buildSharedPreferencesProcessorTest(SecurityPreferences.class, User.class);
		
		final User bean=new User();
		
	//	BindSecurityPreferences security=BindSecurityPreferences.instance();
//		security.edit().putUser(bean).commit();
	}


}
