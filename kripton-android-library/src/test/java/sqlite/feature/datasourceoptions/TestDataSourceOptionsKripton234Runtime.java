/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.datasourceoptions;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.DatabaseLifecycleHandler;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;
import sqlite.feature.datasourceoptions.kripton234.BindAppWithConfigDataSource;

/**
 * The Class TestKripton234Runtime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestDataSourceOptionsKripton234Runtime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite1() throws IOException, InstantiationException, IllegalAccessException {
		BindAppWithConfigDataSource ds=BindAppWithConfigDataSource.getInstance();
				
	}
	
	/**
	 * Test run sqlite 2.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite2() throws IOException, InstantiationException, IllegalAccessException {
		DataSourceOptions.Builder optionsBuilder=DataSourceOptions.builder();
				
		optionsBuilder.inMemory(false);
		optionsBuilder.databaseLifecycleHandler(new DatabaseLifecycleHandler() {
			
			@Override
			public void onUpdate(SQLiteDatabase database, int oldVersion, int newVersion, boolean upgrade) {
				log("databaseLifecycleHandler - onUpdate");
				
			}
			
			@Override
			public void onCreate(SQLiteDatabase database) {
				log("databaseLifecycleHandler - onCreate "+database.getVersion());
				
			}
			
			@Override
			public void onConfigure(SQLiteDatabase database) {
				log("databaseLifecycleHandler - onConfigure");
				
			}
		});
		optionsBuilder.addUpdateTask(1, new SQLiteUpdateTask() {
			
			@Override
			public void execute(SQLiteDatabase database, int previousVersion, int currentVersion) {
				log("aaaaaaaaa");
				
			}
		});
		
		BindAppWithConfigDataSource ds=BindAppWithConfigDataSource.build(optionsBuilder.build());
	}

	
	/**
	 * Test run sqlite error.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqliteError() throws InstantiationException, IllegalAccessException {
		this.expectedException(KriptonRuntimeException.class);
		BindAppWithConfigDataSource ds=BindAppWithConfigDataSource.getInstance();
		
		BindAppWithConfigDataSource.build(DataSourceOptions.builder().build());
	}

}
