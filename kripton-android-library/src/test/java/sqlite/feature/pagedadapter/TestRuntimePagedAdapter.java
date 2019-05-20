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
import java.util.concurrent.atomic.AtomicBoolean;
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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import base.BaseAndroidTest;

/**
 * The Class TestRuntimeMany2Many.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRuntimePagedAdapter extends BaseAndroidTest {
	final ListUpdateCallback ca = new ListUpdateCallback() {

		@Override
		public void onInserted(int position, int count) {
			log("@@@@@ onInserted " + position + " count " + count);
		}

		@Override
		public void onRemoved(int position, int count) {
			log("@@@@@ onRemoved " + position + " count " + count);
		}

		@Override
		public void onMoved(int fromPosition, int toPosition) {
			log("@@@@@ onMoved " + fromPosition + " to " + toPosition);
		}

		@Override
		public void onChanged(int position, int count, Object payload) {
			log("@@@@@ onChanged  " + position + " count " + count);
		}

	};

	public class PageAdapterEmulator<T> {
		public PageAdapterEmulator(PagedLiveData<List<T>> pagedResult, int viewPageSize, CustomDiffCallback<T> diff) {
			viewBuffer = new ViewBuffer<T>(pagedResult, viewPageSize, diff);
		}

		public int getSize() {
			return viewBuffer.getSize();
		}

		public T getPosition(int position) {
			return viewBuffer.get(position);
		}

		ViewBuffer<T> viewBuffer;

		public LiveData<List<T>> getResult() {
			return viewBuffer.getResult();
		}

	}

	public class ViewBuffer<E> {
		private PagedLiveData<List<E>> pagedResult;
		private LiveData<List<E>> result;

		public LiveData<List<E>> getResult() {
			return result;
		}

		private List<E> buffer;
		private int startPosition;
		private int size;
		private int databaseSize;
		private ReentrantLock lock = new ReentrantLock();
		private boolean loading = false;

		public ViewBuffer(PagedLiveData<List<E>> pagedResult, int viewPageSize, CustomDiffCallback<E> diff) {
			this.pagedResult = pagedResult;
			this.databaseSize = viewPageSize * 3;

			this.result = Transformations.map(pagedResult, result -> {
				lock.lock();
				loading = false;
				if (startPosition == pagedResult.getOffset()) {
					System.out.println("==$$$ Value changes at " + pagedResult.getOffset());
				} else {
					System.out.println("==$$$ Load at " + pagedResult.getOffset());
				}

				diff.setOldList(this.buffer);
				diff.setIncomingList(result);
				DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diff);
				diffResult.dispatchUpdatesTo(ca);

				startPosition = pagedResult.getOffset();
				size = pagedResult.getPageSize();
				buffer = result;

				lock.unlock();
				return result;
			});

			this.pagedResult.createPageRequestBuilder().pageSize(databaseSize).apply();
		}

		public int getSize() {
			return pagedResult.getTotalElements();
		}

		public E get(int index) {
			System.out.println(String.format("Real Index: %s, Index: %s, StartPosition: %s, Size: %s",
					index - startPosition, index, startPosition, size));

			return loadAround(index);
		}

		public E loadAround(int position) {
			if (position >= startPosition + (size * 2 / 3)) {
				if (!loading) {
					lock.lock();
					loading = true;
					System.out.println("==> Load " + (position - size / 3));
					pagedResult.createPageRequestBuilder().offset(position - size / 3).apply();
					lock.unlock();
				}
				return buffer.get(position - startPosition);
			} else if ((position - startPosition) <= 0) {
				int loadPos = (startPosition - (size / 3));
				if (loadPos < 0) {
					System.out.println("==> No-Load " + loadPos);
				} else {
					if (!loading) {
						System.out.println("==> Load " + loadPos);
						lock.lock();
						loading = true;
						pagedResult.createPageRequestBuilder().offset(loadPos).apply();
						lock.unlock();
					}

				}
				return buffer.get(position - startPosition);
			} else if (position - startPosition >= 0 && position - startPosition < buffer.size()) {
				return buffer.get(position - startPosition);
			} else {
				return null;
			}
		}
	}

	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();

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
		PageAdapterEmulator<Person> pagetAdapter = new PageAdapterEmulator<>(pagedResult, 20, new PersonDiffCallback());

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