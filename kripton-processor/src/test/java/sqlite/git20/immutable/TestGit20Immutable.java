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
package sqlite.git20.immutable;

import java.io.IOException;

import org.junit.Test;

import sqlite.AbstractBindSQLiteProcessorTest;

/**
 * The Class Test209_2.
 *
 * @author xcesco
 * 
 * https://stackoverflow.com/questions/45160625/how-to-implement-a-many-to-many-relations-with-android-room-persistence-library
 */
public class TestGit20Immutable extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {		
		buildDataSourceProcessorTest(
				Count.class,
				Movie.class,			
				MovieDao.class,
				MovieDataSource.class
				);
	}

}
