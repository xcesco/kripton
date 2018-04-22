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
import sqlite.feature.relations.error2.Album;
import sqlite.feature.relations.error2.AppDataSource;
import sqlite.feature.relations.error2.DaoAlbum;
import sqlite.feature.relations.error2.DaoSong;
import sqlite.feature.relations.error2.Song;

/**
 * The Class TestCompileRelationErr1.
 */
@RunWith(JUnit4.class)
public class TestCompileRelationError2 extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	@Test
	public void testCompileError() throws Throwable {
		this.expectedException(InvalidDefinition.class, "property 'songs' has invalid definition: type java.lang.Long is not a valid entity to use in a relation");
		buildDataSourceProcessorTest(Album.class, DaoAlbum.class, DaoSong.class, AppDataSource.class, Song.class);
	}

}
