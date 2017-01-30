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
package sqlite.paginatedResult;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.Logger;

import com.abubusoft.kripton.android.sqlite.PaginatedResult;

import base.BaseAndroidTest;


/**
 * @author xcesco
 *
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestPaginatedResultRuntime extends BaseAndroidTest {

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		try (BindPersonDataSource dataSource=BindPersonDataSource.open();  PersonDAOImpl dao = dataSource.getPersonDAO())
		{
			//dataSource.execute(transaction);
			for (int i=0;i<100;i++)
			{
				dao.insertOne("name"+i, "surname"+i, "birthCity"+i, new Date());
			}
			
			PaginatedResult<Person> result = dao.selectPagedStatic2(10);
			
			int i=0;
			
			List<Person> list = result.execute();
			while (result.hasRows())
			{
				Logger.info("---------------");
				Logger.info("\tPage "+i);
				Logger.info("---------------");
				for (Person item:list)
				{
					Logger.info(item.toString());
				}
				
				result.nextPage();
				list = result.execute();
			}
		}
		
	}

}
