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
import sqlite.feature.childselect.error4.Article;
import sqlite.feature.childselect.error4.Channel;
import sqlite.feature.childselect.error4.DaoArticle;
import sqlite.feature.childselect.error4.DaoBase;
import sqlite.feature.childselect.error4.DaoChannel;
import sqlite.feature.childselect.error4.DateAdapter;
import sqlite.feature.childselect.error4.Entity;
import sqlite.feature.childselect.error4.Image;
import sqlite.feature.childselect.error4.RSSFeed;
import sqlite.feature.childselect.error4.RssDataSource;

/**
 * The Class TestCompileRX.
 */
@RunWith(JUnit4.class)
public class TestChildSelectError4 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	@Test
	public void testCompile() throws Throwable {
		this.expectedException(InvalidMethodSignException.class, "In class 'DaoChannel', method 'selectAll' has an invalid signature:  method 'sqlite.feature.childselect.error4.DaoArticle#selectAll', referred by @BindSqlChildSelect annotation, can have only one parameter");
		buildDataSourceProcessorTest(Article.class, Channel.class, DateAdapter.class, Entity.class, Image.class,
				RSSFeed.class, DaoArticle.class, DaoBase.class, DaoChannel.class, RssDataSource.class);
	}

}
