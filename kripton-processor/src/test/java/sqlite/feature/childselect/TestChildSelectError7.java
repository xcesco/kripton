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
package sqlite.feature.childselect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.childselect.error7.Article;
import sqlite.feature.childselect.error7.Channel;
import sqlite.feature.childselect.error7.DaoArticle;
import sqlite.feature.childselect.error7.DaoBase;
import sqlite.feature.childselect.error7.DaoChannel;
import sqlite.feature.childselect.error7.DateAdapter;
import sqlite.feature.childselect.error7.Entity;
import sqlite.feature.childselect.error7.Image;
import sqlite.feature.childselect.error7.RSSFeed;
import sqlite.feature.childselect.error7.RssDataSource;

/**
 * The Class TestCompileRX.
 */
@RunWith(JUnit4.class)
public class TestChildSelectError7 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	@Test
	public void testCompile() throws Throwable {
		this.expectedException(InvalidMethodSignException.class, "In class 'DaoChannel', method 'selectAll' has an invalid signature:  property 'Channel#a' does not exits (referred by annotation @BindSqlChildSelect(field='a', method='a'))");
		buildDataSourceProcessorTest(Article.class, Channel.class, DateAdapter.class, Entity.class, Image.class,
				RSSFeed.class, DaoArticle.class, DaoBase.class, DaoChannel.class, RssDataSource.class);
	}

}
