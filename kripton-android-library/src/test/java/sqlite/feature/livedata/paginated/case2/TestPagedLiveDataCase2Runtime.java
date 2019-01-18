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
package sqlite.feature.livedata.paginated.case2;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;
import com.abubusoft.kripton.android.executor.KriptonTaskExecutor;
import com.abubusoft.kripton.android.livedata.PagedLiveData;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.One;

import base.BaseAndroidTest;
import org.junit.Assert;


/**
 * The Class TestFeatureLiveDataRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestPagedLiveDataCase2Runtime extends BaseAndroidTest {

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
		int[] checkPageNumbers={0, 0, 1, 1, 2, 1, 4};
		int[] checkTotalCount={0, 100, 100, 101, 101, 101, 101};
		int[] checkPageSize={20, 20 , 20, 20, 20, 20, 15};
		One<Integer> checkCounter=new One<>(0);
		
		BindAppDataSource ds = BindAppDataSource.getInstance();// .build(DataSourceOptions.builder().inMemory(false).build());

		log("Main thread" + KriptonTaskExecutor.getInstance().isMainThread());
		
		Group group = new Group();
		group.name="DummyGorup";
		
		// insert elements
		ds.execute(tx -> {			
			ds.getDaoGroup().insert(group);
			return TransactionResult.COMMIT;
		});

		// check 0
		PagedLiveData<List<GroupedPerson>> liveData = ds.getDaoPerson().selectAll();
		liveData.observeForever(t -> {						
			log("=========================> Check: %s -- Page %s -- pageSize %s -- Total %s", checkCounter.value0, liveData.getPageNumber(), liveData.getPageSize(), liveData.getTotalElements());
			
			Assert.assertEquals("Wrong page number", checkPageNumbers[checkCounter.value0] , liveData.getPageNumber());
			Assert.assertEquals("Wrong page size",checkPageSize[checkCounter.value0] , liveData.getPageSize());
			Assert.assertEquals("Wrong total count",checkTotalCount[checkCounter.value0] , liveData.getTotalElements());
			checkCounter.value0++;
		});

		// check 1
		ds.execute(daoFactory -> {
			for (int i = 0; i < 100; i++) {
				Person person = new Person();
				person.name = "Manero" + i;
				person.surname = "Tonj" + i;
				person.groupId=group.id;
				daoFactory.getDaoPerson().insert(person);
			}
			return TransactionResult.COMMIT;
		});

		// check 2
		liveData.nextPage();

		// check 3
		ds.execute(daoFactory -> {
			Person person = new Person();
			person.name = "Manero";
			person.surname = "Tonj";
			person.groupId=group.id;
			daoFactory.getDaoPerson().insert(person);
			return TransactionResult.COMMIT;
		});
		
		// none
		liveData.setPage(1);
				
		// check 4
		liveData.nextPage();
		
		// check 5
		liveData.previousPage();
		
		// check 6
		liveData.setPageSize(20);
		
		// check 7
		liveData.createPageRequestBuilder().page(4).pageSize(15).apply();
		
		Thread.sleep(1000);

	}

}
