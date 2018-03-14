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
package sqlite.feature.livedata;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.livedata.persistence.BindAppDaoFactory;
import sqlite.feature.livedata.persistence.BindAppDataSource;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TestLiveDataRuntime extends BaseAndroidTest {

	@Test
	public void testRun() {
		BindAppDataSource ds=BindAppDataSource.build(DataSourceOptions.builder().inMemory(true).build());
		
		ds.execute(new BindAppDataSource.Transaction() {
			
			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {

				daoFactory.getDaoPerson().select("Manero");
				return TransactionResult.COMMIT;
			}
		});
	}
	
}
