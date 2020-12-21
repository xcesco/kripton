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
package sqlite.kripton93;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.database.sqlite.SQLiteConstraintException;
import base.BaseAndroidTest;


/**
 * The Class Test93Runtime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class Test93Runtime extends BaseAndroidTest {

	/**
	 * Test run insert default.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunInsertDefault() throws IOException, InstantiationException, IllegalAccessException {
		BindBean93DataSource dataSource = BindBean93DataSource.getInstance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindBean93DaoFactory daoFactory) {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();

				dao.insertDefault(bean);
				assertTrue(dao.selectAll().size() == 1);
				return TransactionResult.ROLLBACK;
			}
		});
	}

	/**
	 * Test run insert abort.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunInsertAbort() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedKriptonRuntimeExceptionWithCause(SQLiteConstraintException.class);
		BindBean93DataSource dataSource = BindBean93DataSource.getInstance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindBean93DaoFactory daoFactory) {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertAbort(bean);
				assertTrue(dao.selectAll().size() == 1);
				return TransactionResult.ROLLBACK;
			}
		});
	}

	/**
	 * Test run insert fail.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunInsertFail() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedKriptonRuntimeExceptionWithCause(SQLiteConstraintException.class);
		BindBean93DataSource dataSource = BindBean93DataSource.getInstance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindBean93DaoFactory daoFactory) {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertFail(bean);
				assertTrue(dao.selectAll().size() == 1);
				return TransactionResult.ROLLBACK;
			}
		});
	}

	/**
	 * Test run insert ignore.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunInsertIgnore() throws IOException, InstantiationException, IllegalAccessException {
		BindBean93DataSource dataSource = BindBean93DataSource.getInstance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindBean93DaoFactory daoFactory) {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertIgnore(bean);
				assertTrue(dao.selectAll().size() == 1);
				return TransactionResult.ROLLBACK;
			}
		});
	}

	/**
	 * Test run insert replace.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunInsertReplace() throws IOException, InstantiationException, IllegalAccessException {
		BindBean93DataSource dataSource = BindBean93DataSource.getInstance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindBean93DaoFactory daoFactory) {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertReplace(bean);
				assertTrue(dao.selectAll().size() == 1);
				return TransactionResult.ROLLBACK;
			}
		});
	}

	/**
	 * Test run insert rollback.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunInsertRollback() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedKriptonRuntimeExceptionWithCause(SQLiteConstraintException.class);
		BindBean93DataSource dataSource = BindBean93DataSource.getInstance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindBean93DaoFactory daoFactory) {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertRollback(bean);
				assertTrue(dao.selectAll().size() == 0);
				return TransactionResult.ROLLBACK;
			}
		});
	}
}
