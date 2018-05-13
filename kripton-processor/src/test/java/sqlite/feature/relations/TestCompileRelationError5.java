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
package sqlite.feature.relations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;

import sqlite.AbstractBindSQLiteProcessorTest;

import sqlite.feature.relations.error5.Article;
import sqlite.feature.relations.error5.Channel;
import sqlite.feature.relations.error5.DateAdapter;
import sqlite.feature.relations.error5.Entity;
import sqlite.feature.relations.error5.Image;
import sqlite.feature.relations.error5.RSSFeed;

import sqlite.feature.relations.error5.DaoArticle;
import sqlite.feature.relations.error5.DaoBase;
import sqlite.feature.relations.error5.DaoChannel;
import sqlite.feature.relations.error5.RssDataSource;

/**
 * The Class TestCompileRX.
 */
@RunWith(JUnit4.class)
public class TestCompileRelationError5 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	@Test
	public void testCompile() throws Throwable {
		this.expectedException(InvalidDefinition.class, "InvalidDefinition: In class 'sqlite.feature.relations.error5.Channel', property 'articles' has invalid definition: @BindSqlRelation#foreignKey need to specify a valid foreign key to entity 'sqlite.feature.relations.error5.Article'");
		buildDataSourceProcessorTest(Article.class, Channel.class, DateAdapter.class, Entity.class, Image.class,
				RSSFeed.class, DaoArticle.class, DaoBase.class, DaoChannel.class, RssDataSource.class);
	}

}
