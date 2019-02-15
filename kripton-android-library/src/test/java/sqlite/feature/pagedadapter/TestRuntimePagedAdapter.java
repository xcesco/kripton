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

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;
import com.abubusoft.kripton.android.livedata.PagedLiveData;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import base.BaseAndroidTest;

/**
 * The Class TestRuntimeMany2Many.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRuntimePagedAdapter extends BaseAndroidTest implements LifecycleOwner {
	
	
	public class PageAdapterEmulator<T> {
		public PageAdapterEmulator(PagedLiveData<List<T>> pagedResult, int viewPageSize) {			
			//viewBuffer=new ViewBuffer<List<T>>(pagedResult);
			viewBuffer=new ViewBuffer<T>(pagedResult, viewPageSize);
		}
		
		public int getSize() { return viewBuffer.getSize(); }
		
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

		public ViewBuffer(PagedLiveData<List<E>> pagedResult, int viewPageSize) {
			this.pagedResult=pagedResult;
			this.databaseSize=viewPageSize*3;
									
			this.result=Transformations.map(pagedResult, result -> {				
				startPosition=pagedResult.getOffset();
				size=pagedResult.getPageSize();
				buffer=result;
				
				return result;
			});			
			
			this.pagedResult.createPageRequestBuilder().pageSize(databaseSize).apply();
		}
						
		public int getSize() { return pagedResult.getTotalElements(); }
		
		public E get(int index) {
			System.out.println(String.format("Real Index: %s, Index: %s, StartPosition: %s, Size: %s", index - startPosition, index, startPosition, size));
			loadAround(index);
			return buffer.get(index-startPosition);
		}
		
		public void loadAround(int position) {
			if (position>=startPosition+(size*2/3)) {
				pagedResult.createPageRequestBuilder().offset(position-size/3).apply();
			} else if (startPosition>0 && position<=startPosition-(size/3)+1) {
				pagedResult.createPageRequestBuilder().offset(startPosition-size/3).apply();
			}
		}
	}
	
	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();


	/**
	 * Test many 2 many.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
	public void testPagedAdapter() throws InterruptedException {
		BindAppDataSource dataSource=BindAppDataSource.getInstance();
		
		final int RECYCLER_VIEW_PAGE_SIZE=10;
		final int DB_PAGE=RECYCLER_VIEW_PAGE_SIZE*3;
		
		final int TOTAL_COUNT=10000;
		
		dataSource.execute(daoFactory -> {
			DaoPersonImpl daoPerson = daoFactory.getDaoPerson();
			
			Person person;
			for (int i=0; i<TOTAL_COUNT;i++) {
				person=new Person();
				person.name="name"+i;
				person.surname="surname"+i;
				daoPerson.insert(person);
			}
			
			return TransactionResult.COMMIT;
		});
		
		PagedLiveData<List<Person>> pagedResult = dataSource.getDaoPerson().selectAll();
		PageAdapterEmulator<Person> pagetAdapter=new PageAdapterEmulator<>(pagedResult, 20);
		
		pagetAdapter.getResult().observeForever(lista -> {
			log("observable "+lista.size());
		});
		
		for (int i=0; i<TOTAL_COUNT; i++) {			
			Person item = pagetAdapter.getPosition(i);
			log("getPosition(%s)=%s",i, item.toString());
		}
		
		//--- go back
		log("-------------------------");
		for (int i=TOTAL_COUNT-1; i>=0; i--) {			
			Person item = pagetAdapter.getPosition(i);
			log("getPosition(%s)=%s",i, item.toString());
		}
		
		/*MediatorLiveData<Boolean> a;
		a.*/

	}

	@Override
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

}
