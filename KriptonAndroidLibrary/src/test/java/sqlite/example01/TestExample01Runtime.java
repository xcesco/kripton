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
package sqlite.example01;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.android.Logger;

import base.BaseAndroidTest;
import sqlite.example01.BindDummy01DataSource.Transaction;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestExample01Runtime extends BaseAndroidTest {

	@Test
	public void testRunSqlite1() throws IOException, InstantiationException, IllegalAccessException {
		BindDummy01DataSource dataSource = BindDummy01DataSource.instance();

		DaoChannelImpl dao=dataSource.getDaoChannel();
		
		dataSource.execute(new Transaction() {

			@Override
			public boolean onExecute(BindDummy01DaoFactory daoFactory) {
				DaoChannelImpl dao = daoFactory.getDaoChannel();

				{
					long result = dao.insertRaw1("test", 52);
					assertTrue(result == 52);
				}

				assertTrue(dao.insertRaw2("test2", 23) == true);

				{
					// insert use max id value + 1
					Channel bean = new Channel();
					bean.setName("test3");

					assertTrue(dao.insertBean1(bean) == 53);

					Logger.info("%s", bean.id);
				}

				{
					assertTrue(dao.selectAll().size() == 3);
				}
				return true;
			}

			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub

			}
		});
	}

}
