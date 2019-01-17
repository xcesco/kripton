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
package sqlite.feature.foreignkey;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import base.BaseAndroidTest;
import sqlite.feature.foreignkey.BindDummyDataSource.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Class TestForeignKeyARuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestForeignKeyARuntime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite1() throws IOException, InstantiationException, IllegalAccessException {				
		BindDummyDataSource dataSource = BindDummyDataSource.getInstance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindDummyDaoFactory daoFactory) {
				DaoBeanA_1Impl dao = daoFactory.getDaoBeanA_1();

				BeanA_2 beanParent = new BeanA_2();
				beanParent.valueString2 = "parent";
				daoFactory.getDaoBeanA_2().insert(beanParent);

				BeanA_1 bean = new BeanA_1();
				bean.valueString = "hello";
				bean.beanA2Id = beanParent.id;

				dao.insert(bean);
				assertEquals(1, bean.id);
				List<BeanA_1> list = dao.selectById(bean.id);

				Assert.assertEquals("not one ", 1, list.size());
				// Assert.assertEquals("not equals", true,
				// list.get(0).equals(bean));

				return TransactionResult.COMMIT;
			}

		});

	}

	/**
	 * Test run sqlite 2.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite2() throws IOException, InstantiationException, IllegalAccessException {

		BindDummyDataSource dataSource = BindDummyDataSource.getInstance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindDummyDaoFactory daoFactory) {
				DaoBeanA_1Impl dao = daoFactory.getDaoBeanA_1();

				BeanA_2 beanParent = new BeanA_2();
				beanParent.valueString2 = "parent";
				daoFactory.getDaoBeanA_2().insert(beanParent);

				BeanA_1 bean = new BeanA_1();
				bean.valueString = "hello";
				bean.beanA2Id = beanParent.id;

				dao.insert(bean);
				assertEquals(1, bean.id);
				// List<BeanA_1> list = dao.selectById(bean.id);

				// Assert.assertEquals("not one ", 1, list.size());
				// Assert.assertEquals("not equals", true,
				// list.get(0).equals(bean));

				return TransactionResult.COMMIT;
			}
		
		});

	}

	/**
	 * Test run sqlite 3.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite3() throws IOException, InstantiationException, IllegalAccessException {
		
		BindDummyDataSource dataSource = BindDummyDataSource.getInstance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindDummyDaoFactory daoFactory) {
				DaoBeanA_1Impl dao = daoFactory.getDaoBeanA_1();

				BeanA_2 beanParent = new BeanA_2();
				beanParent.valueString2 = "parent";
				daoFactory.getDaoBeanA_2().insert(beanParent);

				BeanA_1 bean = new BeanA_1();
				bean.valueString = "hello";
				bean.beanA2Id = beanParent.id;

				dao.insert(bean);
				assertEquals(1, bean.id);
				// List<BeanA_1> list = dao.selectById(bean.id);

				// Assert.assertEquals("not one ", 1, list.size());
				// Assert.assertEquals("not equals", true,
				// list.get(0).equals(bean));

				return TransactionResult.COMMIT;
			}
			
		});

	}

	/**
	 * Test run sqlite 4.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite4() throws IOException, InstantiationException, IllegalAccessException {
		BindDummyDataSource dataSource = BindDummyDataSource.getInstance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindDummyDaoFactory daoFactory) {
				DaoBeanA_1Impl dao = daoFactory.getDaoBeanA_1();

				BeanA_2 beanParent = new BeanA_2();
				beanParent.valueString2 = "parent";
				daoFactory.getDaoBeanA_2().insert(beanParent);

				BeanA_1 bean = new BeanA_1();
				bean.valueString = "hello";
				bean.beanA2Id = beanParent.id;

				dao.insert(bean);
				assertEquals(1, bean.id);
				// List<BeanA_1> list = dao.selectById(bean.id);

				// Assert.assertEquals("not one ", 1, list.size());
				// Assert.assertEquals("not equals", true,
				// list.get(0).equals(bean));

				return TransactionResult.COMMIT;
			}
			
		});

	}

}
