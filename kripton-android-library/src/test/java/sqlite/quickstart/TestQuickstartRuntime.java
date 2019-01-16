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
package sqlite.quickstart;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.quickstart.model.Comment;
import sqlite.quickstart.model.Post;
import sqlite.quickstart.model.User;
import sqlite.quickstart.persistence.BindQuickStartDaoFactory;
import sqlite.quickstart.persistence.BindQuickStartDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class TestQuickstartRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestQuickstartRuntime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 */
	@Test
	public void testRunSqlite1() {
		BindQuickStartDataSource dataSource = BindQuickStartDataSource.getInstance();
		
		dataSource.execute(new BindQuickStartDataSource.Transaction() {
			
			@Override
			public TransactionResult onExecute(BindQuickStartDaoFactory daoFactory) {
				User user=new User();
				user.id=1;
				user.name="user";
				user.username="username";
				
				daoFactory.getUserDao().insert(user);
				
				Post post=new Post();
				post.id=2;
				post.title="post";
				post.userId=user.id;
				daoFactory.getPostDao().insert(post);
				
				Comment comment=new Comment();
				comment.id=3;
				comment.postId=post.id;
				daoFactory.getCommentDao().insert(comment);
				return TransactionResult.COMMIT;
			}
		});
	}

}
