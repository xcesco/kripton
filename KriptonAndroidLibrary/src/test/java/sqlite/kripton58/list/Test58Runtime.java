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
package sqlite.kripton58.list;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.robolectric.util.Logger;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.kripton58.list.BindLongDataSource.Transaction;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class Test58Runtime extends BaseAndroidTest {

	@Test
	public void testRunSqlite1() {
		BindLongDataSource dataSource = BindLongDataSource.instance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindLongDaoFactory daoFactory) {
				LongDaoImpl dao = daoFactory.getLongDao();

				ArrayList<Long> value = new ArrayList<Long>();
				value.add(45L);

				LongBean bean = new LongBean();
				bean.value = value;

				dao.insert(bean);

				// retrieve same value
				List<LongBean> result = dao.selectList(value);
				assertTrue(result.size() == 1);

				return TransactionResult.COMMIT;
			}
			
		});
	}

}
