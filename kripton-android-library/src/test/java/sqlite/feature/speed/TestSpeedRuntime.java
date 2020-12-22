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
package sqlite.feature.speed;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.One;

import base.BaseAndroidTest;
import sqlite.feature.speed.model.Person;
import sqlite.feature.speed.persistence.BindPersonDaoFactory;
import sqlite.feature.speed.persistence.BindPersonDataSource;
import sqlite.feature.speed.persistence.BindPersonDataSource.Transaction;
import sqlite.feature.speed.persistence.PersonDaoImpl;


/**
 * The Class TestSpeedRuntime.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSpeedRuntime extends BaseAndroidTest {

	/**
	 * Test single.
	 */
	@Test
	public void testSingle() {
		final One<Long> start = new One<>();
		final One<Long> end = new One<>();

		final int COUNTER = 200000;

		final BindPersonDataSource ds = BindPersonDataSource.getInstance();

		ds.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindPersonDaoFactory daoFactory) {
				start.value0 = System.currentTimeMillis();
				PersonDaoImpl dao = daoFactory.getPersonDao();
				for (int i = 0; i < COUNTER; i++) {
					Person bean = new Person();
					bean.name = "name" + i;
					bean.surname = "surname" + i;
					// Object bean = new
					dao.insert(bean);
				}

				end.value0 = System.currentTimeMillis();
				return TransactionResult.COMMIT;
			}
		});

		// 3100
		System.out.println("Esecuzione terminata in " + (end.value0 - start.value0) + " ms");

	}

	/**
	 * Test transaction.
	 */
	@Test
	public void testTransaction() {
		final One<Long> start = new One<>();
		final One<Long> end = new One<>();
		final One<Integer> index = new One<>();

		final int COUNTER = 200;

		final BindPersonDataSource ds = BindPersonDataSource.getInstance();

		ds.openWritableDatabase();
		
		start.value0 = System.currentTimeMillis();

		for (int i = 0; i < COUNTER; i++) {

			index.value0 = i;

			ds.execute(new Transaction() {
				
				@Override
				public TransactionResult onExecute(BindPersonDaoFactory daoFactory) {
					PersonDaoImpl dao = daoFactory.getPersonDao();

					Person bean = new Person();
					bean.name = "name" + index.value0;
					bean.surname = "surname" + index.value0;
					// Object bean = new
					dao.insert(bean);

					return TransactionResult.COMMIT;

				}
			});
		}

		end.value0 = System.currentTimeMillis();
		
		ds.close();

		// 3100
		System.out.println("Esecuzione terminata in " + (end.value0 - start.value0) + " ms");

	}
	
	
	/**
	 * Test insert batch.
	 */
	@Test
	public void testInsertBatch() {
		final int ITEM_COUNTER=10;
		
		final One<Long> start = new One<>();
		final One<Long> end = new One<>();
		final One<Integer> index = new One<>();

		final int COUNTER = 10;

		final BindPersonDataSource ds = BindPersonDataSource.getInstance();

		ds.openWritableDatabase();
		
		final List<Person> list=new ArrayList<Person>();
		for (int i=0; i<ITEM_COUNTER;i++) {
			Person bean = new Person();
			bean.name = "name" + index.value0;
			bean.surname = "surname" + index.value0;
			
			list.add(bean);
		}
		
		start.value0 = System.currentTimeMillis();

		for (int i = 0; i < COUNTER; i++) {

			index.value0 = i;

			ds.execute(new Transaction() {
				
				@Override
				public TransactionResult onExecute(BindPersonDaoFactory daoFactory) {
					PersonDaoImpl dao = daoFactory.getPersonDao();
					
					for (int i=0;i<list.size();i++) {					
					dao.insert(list.get(i));
					}

					return TransactionResult.COMMIT;

				}
			});
		}

		end.value0 = System.currentTimeMillis();
		
		ds.close();

		// 3100
		System.out.println("Average time to insert "+ITEM_COUNTER+" items: " + ((end.value0 - start.value0)*1.0/COUNTER) + " ms");

	}
		
}
