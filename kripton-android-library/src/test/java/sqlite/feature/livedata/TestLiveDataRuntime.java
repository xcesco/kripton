/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import base.BaseAndroidTest;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedata.persistence0.BindApp0DaoFactory;
import sqlite.feature.livedata.persistence0.BindApp0DataSource;

/**
 * The Class TestLiveDataRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestLiveDataRuntime extends BaseAndroidTest {

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
		BindApp0DataSource ds = BindApp0DataSource.getInstance();// .build(DataSourceOptions.builder().inMemory(false).build());

		System.out.println("aa" + KriptonTaskExecutor.getInstance().isMainThread());

		LiveData<List<Person>> liveData = ds.getDaoPerson0().select("Manero");
		liveData.observeForever(new Observer<List<Person>>() {

			@Override
			public void onChanged(List<Person> t) {
				System.out.println("*********** " + t.size());
			}
		});

		ds.execute(new BindApp0DataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindApp0DaoFactory daoFactory) {
				Person person = new Person();
				person.name = "Manero";
				person.surname = "Tonj";
				daoFactory.getDaoPerson0().insert(person);
				return TransactionResult.COMMIT;
			}
		});

		ds.execute(new BindApp0DataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindApp0DaoFactory daoFactory) {
				Person person = new Person();
				person.name = "Manero";
				person.surname = "Tonj";
				daoFactory.getDaoPerson0().insert(person);
				return TransactionResult.COMMIT;
			}
		});

		Thread.sleep(1000);
	}

}
