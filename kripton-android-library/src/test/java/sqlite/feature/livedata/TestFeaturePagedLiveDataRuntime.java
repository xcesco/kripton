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
package sqlite.feature.livedata;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;
import com.abubusoft.kripton.android.executor.KriptonTaskExecutor;
import com.abubusoft.kripton.android.livedata.PagedLiveData;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.arch.lifecycle.Observer;
import base.BaseAndroidTest;
import sqlite.feature.livedata.paginated.BindAppDaoFactory;
import sqlite.feature.livedata.paginated.BindAppDataSource;
import sqlite.feature.livedata.paginated.Person;
import sqlite.feature.livedata.paginated.BindAppDataSource.Transaction;

/**
 * The Class TestFeatureLiveDataRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestFeaturePagedLiveDataRuntime extends BaseAndroidTest {

	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();

	/**
	 * Test run.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Test
	public void testRun() throws InterruptedException {
		BindAppDataSource ds = BindAppDataSource.getInstance();// .build(DataSourceOptions.builder().inMemory(false).build());

		log("Main thread" + KriptonTaskExecutor.getInstance().isMainThread());

		final PagedLiveData<List<Person>> liveData = ds.getDaoPerson().selectPaged("Manero");
		liveData.observeForever(new Observer<List<Person>>() {

			@Override
			public void onChanged(List<Person> t) {
				log("--> Page %s -- size %s", liveData.getPage(), t.size());
			}
		});

		ds.execute(new BindAppDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				for (int i = 0; i < 100; i++) {
					Person person = new Person();
					person.name = "Manero" + i;
					person.surname = "Tonj" + i;
					daoFactory.getDaoPerson().insert(person);
				}
				return TransactionResult.COMMIT;
			}
		});

		liveData.nextPage();

		ds.execute(new BindAppDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				Person person = new Person();
				person.name = "Manero";
				person.surname = "Tonj";
				daoFactory.getDaoPerson().insert(person);
				return TransactionResult.COMMIT;
			}
		});
		
		liveData.createPageRequestBuilder().pageSize(41).page(2).apply();


		Thread.sleep(1000);

	}

}
