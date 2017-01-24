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
package sqlite.quickstart;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.quickstart.model.Address;
import sqlite.quickstart.model.Comment;
import sqlite.quickstart.model.Company;
import sqlite.quickstart.model.Geo;
import sqlite.quickstart.model.Post;
import sqlite.quickstart.model.Todo;
import sqlite.quickstart.model.User;
import sqlite.quickstart.persistence.CommentDao;
import sqlite.quickstart.persistence.PostDao;
import sqlite.quickstart.persistence.QuickStartDataSource;
import sqlite.quickstart.persistence.TodoDao;
import sqlite.quickstart.persistence.UserDao;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@RunWith(JUnit4.class)
public class TestQuickstart extends AbstractBindSQLiteProcessorTest {

	/**
	 * No DAO definition with @BindDaoDefinition annotation was found for class
	 * Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(QuickStartDataSource.class, CommentDao.class, PostDao.class , TodoDao.class, UserDao.class, Address.class, Comment.class, Company.class, Geo.class, Post.class, Todo.class, User.class);
	}

}
