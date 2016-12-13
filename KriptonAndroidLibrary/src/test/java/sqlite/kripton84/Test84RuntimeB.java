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

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.kripton84.BindBean84BDataSource.Transaction;

/**
 * @author xcesco
 *
 */
public class Test84RuntimeB extends BaseAndroidTest {

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(Bean84BTable.class.getName() != null);
		Assert.assertNotNull(Bean84BDaoImpl.class.getName() != null);
		
		BindBean84BDataSource dataSource = BindBean84BDataSource.instance();

		dataSource.execute(new Transaction() {

			@Override
			public boolean onExecute(BindBean84BDaoFactory daoFactory) {
				Bean84BDaoImpl dao = daoFactory.getBean84BDao();

				Bean84B2 innerBean = new Bean84B2();
				innerBean.columnString = "test01";

				Bean84B bean = new Bean84B();
				bean.columnBean = innerBean;
				dao.insert(bean);

				Bean84B bean2 = dao.selectById(bean.id);
				assertTrue(bean.equals(bean2));

				Bean84B bean3 = dao.selectByBean(innerBean);
				assertTrue(bean.equals(bean3));

				return true;
			}

			@Override
			public void onError(Throwable e) {

			}
		});

	}

}
