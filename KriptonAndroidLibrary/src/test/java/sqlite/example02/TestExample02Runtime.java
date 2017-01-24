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
package sqlite.example02;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;

import android.database.Cursor;
import base.BaseAndroidTest;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestExample02Runtime extends BaseAndroidTest {

	@Test
	public void testRunSqlite1() throws IOException, InstantiationException, IllegalAccessException {
		BindPersonDataSource instance=BindPersonDataSource.instance();
		
		
		instance.execute(new BindPersonDataSource.SimpleTransaction() {
			
			@Override
			public boolean onExecute(BindPersonDaoFactory daoFactory) throws Throwable {
				PersonDAOImpl dao=daoFactory.getPersonDAO();
				
				dao.insertOne("name1", "surname1", "city1", new Date());
				dao.insertOne("name2", "surname2", "city2", new Date());															
				
				return true;
			}
		});
		
		// open database
		instance.openReadOnlyDatabase();
		
		Set<Person> list=instance.getPersonDAO().selectAll("name");
		instance.getPersonDAO().selectBeanListener(new OnReadBeanListener<Person>() {
			
			@Override
			public void onRead(Person bean, int row, int rowCount) {
				// work with 
				
			}
		});
		
		instance.getPersonDAO().deleteOne("hellp", "aa");
		
		instance.getPersonDAO().selectCursorListener(new OnReadCursorListener() {
			
			@Override
			public void onRead(Cursor cursor) {
				// work directly with cursor
				
			}
		});
		
		// close database
		instance.close();
	}

}
