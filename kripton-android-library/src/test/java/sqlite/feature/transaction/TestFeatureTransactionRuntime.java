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
package sqlite.feature.transaction;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;

import base.BaseAndroidTest;

/**
 * The Class TestFeatureSQLTypeAdapterRuntime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestFeatureTransactionRuntime extends BaseAndroidTest {

	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();
	
	/**
	 * Test select.
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	@Test
	public void testSelect() throws InterruptedException, ExecutionException {
		BindAppDataSource ds = BindAppDataSource.getInstance();
		Future<Boolean> result = ds.executeAsync("tonj");
		
		log("Result : "+result.get());
		 
		List<Person> list = ds.executeBatch(daoFactory -> {
			return daoFactory.getDaoPerson().select("tonj");
		});
		
		assertEquals(1, list.size());
		
		Thread.sleep(1000);

	}

}
