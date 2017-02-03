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
package sqlite.kripton93;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.database.sqlite.SQLiteConstraintException;
import base.BaseAndroidTest;

/**
 * @author xcesco
 *
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class Test93Runtime extends BaseAndroidTest {

	@Test
	public void testRunInsertDefault() throws IOException, InstantiationException, IllegalAccessException {
		BindBean93DataSource dataSource = BindBean93DataSource.instance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindBean93DaoFactory daoFactory) throws Throwable {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				assertTrue(dao.selectAll().size() == 1);
				return true;
			}
		});
	}

	@Test
	public void testRunInsertAbort() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedKriptonRuntimeExceptionWithCause(SQLiteConstraintException.class);
		BindBean93DataSource dataSource = BindBean93DataSource.instance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindBean93DaoFactory daoFactory) throws Throwable {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertAbort(bean);
				assertTrue(dao.selectAll().size() == 1);
				return true;
			}
		});
	}

	@Test
	public void testRunInsertFail() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedKriptonRuntimeExceptionWithCause(SQLiteConstraintException.class);
		BindBean93DataSource dataSource = BindBean93DataSource.instance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindBean93DaoFactory daoFactory) throws Throwable {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertFail(bean);
				assertTrue(dao.selectAll().size() == 1);
				return true;
			}
		});
	}

	@Test
	public void testRunInsertIgnore() throws IOException, InstantiationException, IllegalAccessException {
		BindBean93DataSource dataSource = BindBean93DataSource.instance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindBean93DaoFactory daoFactory) throws Throwable {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertIgnore(bean);
				assertTrue(dao.selectAll().size() == 1);
				return true;
			}
		});
	}

	@Test
	public void testRunInsertReplace() throws IOException, InstantiationException, IllegalAccessException {
		BindBean93DataSource dataSource = BindBean93DataSource.instance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindBean93DaoFactory daoFactory) throws Throwable {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertReplace(bean);
				assertTrue(dao.selectAll().size() == 1);
				return true;
			}
		});
	}

	@Test
	public void testRunInsertRollback() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedKriptonRuntimeExceptionWithCause(SQLiteConstraintException.class);
		BindBean93DataSource dataSource = BindBean93DataSource.instance();
		final Bean93 bean = new Bean93();
		bean.name = "all";

		dataSource.execute(new BindBean93DataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindBean93DaoFactory daoFactory) throws Throwable {
				Bean93DaoImpl dao = daoFactory.getBean93Dao();
				dao.insertDefault(bean);
				dao.insertRollback(bean);
				assertTrue(dao.selectAll().size() == 0);
				return false;
			}
		});
	}
}
