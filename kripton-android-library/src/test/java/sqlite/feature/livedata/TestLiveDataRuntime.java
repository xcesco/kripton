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

import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import base.BaseAndroidTest;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedata.persistence.BindAppDaoFactory;
import sqlite.feature.livedata.persistence.BindAppDataSource;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TestLiveDataRuntime extends BaseAndroidTest {

	@Test
	public void testRun() throws InterruptedException {
		BindAppDataSource ds=BindAppDataSource.instance();// .build(DataSourceOptions.builder().inMemory(false).build());
		
		
		LiveData<List<Person>> liveData = ds.getDaoPerson().select("Manero");		
		liveData.observeForever(new Observer<List<Person>>() {
			
			@Override
			public void onChanged(List<Person> t) {
				System.out.println("*********** "+t.size());				
			}
		});
		
		
		Thread.sleep(1);
		ds.execute(new BindAppDataSource.Transaction() {
			
			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				Person person=new Person();
				person.name="Manero";
				person.surname="Tonj";
				daoFactory.getDaoPerson().insert(person);
				return TransactionResult.COMMIT;
			}
		});
	}
	
}
