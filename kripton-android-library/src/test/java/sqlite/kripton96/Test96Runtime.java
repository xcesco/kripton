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
package sqlite.kripton96;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.kripton93.BindBean93DataSource;
import sqlite.kripton96.BindBean96DataSource.Batch;

// TODO: Auto-generated Javadoc
/**
 * The Class Test96Runtime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class Test96Runtime extends BaseAndroidTest {

	/**
	 * Test run sqlite.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public void testRunSqlite() throws IOException, InstantiationException, IllegalAccessException {
		BindBean96DataSource dataSource = BindBean96DataSource.getInstance();
		// dataSource.openWritableDatabase();

		final Bean96 bean = new Bean96();
		bean.name = "all";

		dataSource.execute(new BindBean96DataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindBean96DaoFactory daoFactory) {
				Bean96DaoImpl dao = daoFactory.getBean96Dao();

				dao.insert(bean);

				Bean96 result = dao.selectByBean("all");
				assertTrue(result != null);

				return TransactionResult.COMMIT;
			}
		});

		dataSource.executeBatch(new Batch<Integer>() {

			@Override
			public Integer onExecute(BindBean96DaoFactory daoFactory) {
				Bean96DaoImpl dao = daoFactory.getBean96Dao();

				dao.insert(bean);

				return (int) bean.id;
			}
		});
	}

}
