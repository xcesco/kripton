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
package sqlite.feature.custombean;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.arch.lifecycle.Observer;
import base.BaseAndroidTest;
import sqlite.feature.custombean.case1.BindAppDaoFactory;
import sqlite.feature.custombean.case1.BindAppDataSource;
import sqlite.feature.custombean.case1.BindAppDataSource.Transaction;
import sqlite.feature.custombean.case1.Book;
import sqlite.feature.custombean.case1.Loan;
import sqlite.feature.custombean.case1.LoanWithUserAndBook;
import sqlite.feature.custombean.case1.User;

/**
 * The Class TestContentProviderRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestCustomBeanRuntime extends BaseAndroidTest {

	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();

	public int counter = 1;

	@Test
	public void testCompile() throws InterruptedException {
		BindAppDataSource dataSource = BindAppDataSource.getInstance();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				User user = createUser(daoFactory);

				Book book = createBook(daoFactory);

				insertLoan(daoFactory, user, book);
				// insertLoan(daoFactory, user, book);
				return TransactionResult.COMMIT;
			}

			private void insertLoan(BindAppDaoFactory daoFactory, User user, Book book) {
				Loan loan = new Loan();
				loan.id = UUID.randomUUID().toString();
				loan.bookId = book.id;
				loan.userId = user.id;
				loan.startTime = new Date();
				daoFactory.getLoanDao().insertLoan(loan);
			}

			private Book createBook(BindAppDaoFactory daoFactory) {
				Book book = new Book();
				book.id = UUID.randomUUID().toString();
				book.title = "Never Ending Story #" + (counter++);
				daoFactory.getBookDao().insertBook(book);

				return book;
			}

			private User createUser(BindAppDaoFactory daoFactory) {
				User user = new User();
				user.id = UUID.randomUUID().toString();
				user.age = 24;
				user.name = "Fox";
				user.lastName = "Mulder";
				daoFactory.getUserDao().insertUser(user);

				return user;
			}
		});

		dataSource.getLoanDao().findAllWithUserAndBook().observeForever(new Observer<List<LoanWithUserAndBook>>() {

			@Override
			public void onChanged(List<LoanWithUserAndBook> t) {
				assertTrue(t.size() == 1);
				LoanWithUserAndBook bean = t.get(0);
				log("Found %s elements", t.size());
				assertTrue(bean.bookTitle != null);
				assertTrue(bean.userName != null);
				assertTrue(bean.startTime != null);
				assertTrue(bean.id != null);
			}
		});

		KriptonLibrary.getExecutorService().awaitTermination(4, TimeUnit.SECONDS);
	}

}
