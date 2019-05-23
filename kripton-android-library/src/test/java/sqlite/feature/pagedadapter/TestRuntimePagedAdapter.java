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
package sqlite.feature.pagedadapter;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;
import com.abubusoft.kripton.android.livedata.PagedLiveData;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.view.View;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import base.BaseAndroidTest;
import sqlite.feature.pagedadapter.KriptonRecyclerViewAdapter.ViewBuffer;

/**
 * The Class TestRuntimeMany2Many.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRuntimePagedAdapter extends BaseAndroidTest {

	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();

	public class DevRecyclerViewAdapter extends KriptonRecyclerViewAdapter<Person, DevViewHolder> {

		public DevRecyclerViewAdapter(PagedLiveData<List<T>> pagedResult, int viewPageSize,
				CustomDiffCallback<T> diff) {
			super(pagedResult, viewPageSize, diff);
		}

		public class DevViewHolder extends KriptonRecyclerViewAdapter.KriptonViewHolder<Person> {

			public DevViewHolder(View itemView) {
				super(itemView);
				// TODO Auto-generated constructor stub
			}

		}
	}

	/**
	 * Test many 2 many.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Test
	public void testPagedAdapter() throws InterruptedException {
		BindAppDataSource dataSource = BindAppDataSource.getInstance();

		final int RECYCLER_VIEW_PAGE_SIZE = 20;
		final int DB_PAGE = RECYCLER_VIEW_PAGE_SIZE * 3;

		final int TOTAL_COUNT = 1000;

		dataSource.execute(daoFactory -> {
			DaoPersonImpl daoPerson = daoFactory.getDaoPerson();

			Person person;
			for (int i = 0; i < TOTAL_COUNT; i++) {
				person = new Person();
				person.name = "name" + i;
				person.surname = "surname" + i;
				daoPerson.insert(person);
			}

			return TransactionResult.COMMIT;
		});

		PagedLiveData<List<Person>> pagedResult = dataSource.getDaoPerson().selectAll();
		KriptonRecyclerViewAdapter<Person> pagetAdapter = new KriptonRecyclerViewAdapter(pagedResult, viewPageSize, diff)<>(pagedResult, 20, new PersonDiffCallback());

		pagetAdapter.getResult().observeForever(lista -> {
			log("observable " + lista.size());

		});

		for (int i = 0; i < TOTAL_COUNT; i++) {
			Person item = pagetAdapter.getPosition(i);
			log("getPosition(%s)=%s", i, item.toString());
		}

		// --- go back
	/*	log("-------------------------");
		for (int i = TOTAL_COUNT - 1; i >= 0; i--) {
			Person item = pagetAdapter.getPosition(i);
			log("getPosition(%s)=%s", i, item.toString());
		}*/

		/*
		 * MediatorLiveData<Boolean> a; a.
		 */

		dataSource.execute(daoFactory -> {
			DaoPersonImpl daoPerson = daoFactory.getDaoPerson();

			Person person;
			for (int i = TOTAL_COUNT; i < TOTAL_COUNT + 10; i++) {
				person = new Person();
				person.name = "name" + i;
				person.surname = "surname" + i;
				daoPerson.insert(person);
			}

			return TransactionResult.COMMIT;
		});

		// ora cambiamo uno
		/*dataSource.execute(daoFactory -> {
			DaoPersonImpl daoPerson = daoFactory.getDaoPerson();

			Person person;
			person = daoPerson.selectById(10);
			person.name = person.name + "--nuovoa";
			daoPerson.update(person);

			person = daoPerson.selectById(50);
			person.name = person.name + "--nuovoa";
			daoPerson.update(person);

			return TransactionResult.COMMIT;
		});*/

		KriptonLibrary.getExecutorService().awaitTermination(10, TimeUnit.SECONDS);

	}

}
