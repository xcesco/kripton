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
package sqlite.feature.pkstring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.pkstring.relations.err2.Album;
import sqlite.feature.pkstring.relations.err2.AppDataSource;
import sqlite.feature.pkstring.relations.err2.DaoAlbum;
import sqlite.feature.pkstring.relations.err2.DaoBase;
import sqlite.feature.pkstring.relations.err2.DaoSong;
import sqlite.feature.pkstring.relations.err2.Song;


/**
 * The Class TestCompileRX.
 */
@RunWith(JUnit4.class)
public class TestCompilePkStringRelationErr2 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompile() throws Throwable {		
		this.expectedException(InvalidDefinition.class, "In class 'sqlite.feature.pkstring.relations.err2.Album', property 'songs' has invalid definition: sqlite.feature.pkstring.relations.err2.Song#albumId is a foreign key to sqlite.feature.pkstring.relations.err2.Album#id: they have to be same type");
		buildDataSourceProcessorTest(Album.class, DaoAlbum.class, DaoSong.class, DaoBase.class, AppDataSource.class, Song.class);
	}

}
