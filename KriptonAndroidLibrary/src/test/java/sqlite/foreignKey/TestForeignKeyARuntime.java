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
package sqlite.foreignKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.foreignKey.BindDummyDataSource.Transaction;

/**
 * @author xcesco
 *
 */
public class TestForeignKeyARuntime extends BaseAndroidTest {

	@Test(expected=AssertionError.class)
	public void testRunSqlite1() throws IOException, InstantiationException, IllegalAccessException {
		BindDummyDataSource dataSource = BindDummyDataSource.instance();

		dataSource.execute(new Transaction() {

			@Override
			public boolean onExecute(BindDummyDaoFactory daoFactory) {
				DaoBeanA_1Impl dao = daoFactory.getDaoBeanA_1();
				
				BeanA_2 beanParent = new BeanA_2();
				beanParent.valueString2="parent";
				//daoFactory.getDaoBeanA_2().insert(beanParent);

				BeanA_1 bean = new BeanA_1();
				bean.valueString="hello";				
				bean.beanA2Id=beanParent.id;
				
				dao.insert(bean);
				assertNotEquals(-1, bean.id);
				List<BeanA_1> list = dao.selectById(bean.id);
				
				//Assert.assertEquals("not one ", 1, list.size());
				//Assert.assertEquals("not equals", true, list.get(0).equals(bean));

				return true;
			}

			@Override
			public void onError(Throwable e) {

			}
		});

	}

	

}
