/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.kripton64;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.kripton64.BindBean64ADataSource.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Class Test64ARuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class Test64ARuntime extends BaseAndroidTest {

	/**
	 * Test run sqlite.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite() throws IOException, InstantiationException, IllegalAccessException {
		BindBean64ADataSource dataSource = BindBean64ADataSource.getInstance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindBean64ADaoFactory daoFactory) {
				Bean64ADaoImpl dao = daoFactory.getBean64ADao();

				Bean64A bean = new Bean64A();
				bean.valueString = "hello";
				bean.valueMapStringBean = new HashMap<>();
				bean.valueMapStringBean.put("key1", new Bean64A());
				bean.valueSetString = new HashSet<String>();
				bean.valueSetString.add("hello");

				dao.insert(bean);
				List<Bean64A> list = dao.selectList(bean.id);
				Assert.assertEquals("not list ", 1, list.size());

				Assert.assertEquals("not map", 1, list.size());

				Assert.assertEquals("not set", 1, list.get(0).valueSetString.size());

				return TransactionResult.COMMIT;
			}
			
		});

	}

}
