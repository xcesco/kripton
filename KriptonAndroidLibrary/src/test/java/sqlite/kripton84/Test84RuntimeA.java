/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton84;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.kripton84.BindBean84ADataSource.Transaction;


/**
 * @author xcesco
 *
 */
public class Test84RuntimeA extends BaseAndroidTest {

	@Test
	public void testRunSqlite() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(Bean84ATable.class.getName() != null);
		Assert.assertNotNull(Bean84ADaoImpl.class.getName() != null);
		BindBean84ADataSource dataSource = BindBean84ADataSource.instance();
		dataSource.openWritableDatabase();

		dataSource.execute(new Transaction() {

			@Override
			public boolean onExecute(BindBean84ADaoFactory daoFactory) {
				Bean84ADaoImpl dao = daoFactory.getBean84ADao();

				Bean84A bean = new Bean84A();
				bean.valueString = "hello";

				dao.insertAll(bean);
				List<Bean84A> list = dao.selectById(bean.id);
				Assert.assertEquals("not list ", 1, list.size());

				Assert.assertEquals("not map", 1, list.size());

				// Assert.assertEquals("not set", 1,
				// list.get(0).valueSetString.size());

				return true;
			}

			@Override
			public void onError(Throwable e) {

			}
		});

	}

}
