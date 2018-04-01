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
package sqlite.feature.performance;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.performance.simple.BindSimpleDaoFactory;
import sqlite.feature.performance.simple.BindSimpleDataSource;
import sqlite.feature.performance.simple.SimpleAddressDaoImpl;
import sqlite.feature.performance.simple.SimpleAddressItem;

/**
 * @author xcesco
 *
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestPerformanceRuntime extends BaseAndroidTest {

	private static final int LOOP_COUNT = 25000;

	@Test
	public void testRunSave() {

		// delete all
		final BindSimpleDataSource dataSource = BindSimpleDataSource.instance();

		dataSource.execute(new BindSimpleDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindSimpleDaoFactory daoFactory) {
				
				daoFactory.getSimpleAddressDao().deleteAll();

				Collection<SimpleAddressItem> simpleAddressItems = Generator.getKriptonSimpleAddresses(SimpleAddressItem.class, LOOP_COUNT);

				long insertDuration;
				long insertOptimizedDuration;

				// normal INSERT
				{
					log("insert NORMAL start");
					long startTime = System.currentTimeMillis();

					for (SimpleAddressItem simpleAddressItem : simpleAddressItems) {
						daoFactory.getSimpleAddressDao().insert(simpleAddressItem);
					}

					insertDuration = System.currentTimeMillis() - startTime;
					log("insert duration: %s", insertDuration);
				}
				// end

				daoFactory.getSimpleAddressDao().deleteAll();

				// optimized INSERT
				{
					log("insert OPTIZED start");
					long startTime = System.currentTimeMillis();

					for (SimpleAddressItem simpleAddressItem : simpleAddressItems) {
						daoFactory.getSimpleAddressDao().insert(simpleAddressItem);
					}

					insertOptimizedDuration = System.currentTimeMillis() - startTime;
					log("insert OPTIMIZED duration: %s", insertOptimizedDuration);
				}
				// end
				return TransactionResult.COMMIT;
			}

		});

	}
}
