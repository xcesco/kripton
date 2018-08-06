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
package sqlite.feature.pkstring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.SqlModificationType;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import sqlite.feature.pkstring.rx.BindUserDaoFactory;
import sqlite.feature.pkstring.rx.BindUserDataSource;
import sqlite.feature.pkstring.rx.BindUserDataSource.Transaction;
import sqlite.feature.pkstring.rx.User;


/**
 * The Class TestPkString1Runtime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestPkStringRxRuntime extends BaseAndroidTest {
	
	@Test
	public void testRun() {
		final BindUserDataSource dataSource=BindUserDataSource.getInstance();
		dataSource.getUserSubject().map(new Function<SQLiteEvent, User>() {
			@Override
			public User apply(SQLiteEvent t) throws Exception {
			    User createdUser = null;
			    if (t.getOperationType() == SqlModificationType.INSERT) {
			        String id = t.getLastInsertedUid();

			        createdUser = dataSource.getUserDao().getUser(id);

			    }
			    return createdUser;

			}
		}).subscribe(new Consumer<User>() {

			@Override
			public void accept(User user) throws Exception {
				Logger.info("update %s - %s", user.getId(), user.getUserName());
				
			}
		});
		
		final User user=new User("usss");
		dataSource.execute(new Transaction() {
			@Override
			public TransactionResult onExecute(BindUserDaoFactory daoFactory) {
			    dataSource.getUserDao().insertUser(user);

			    return TransactionResult.COMMIT;
			}
		});
				
	}

}
