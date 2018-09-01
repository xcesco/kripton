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
package sqlite.feature.typeadapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.typeadapter.bitmap.err2.AppDataSource;
import sqlite.feature.typeadapter.bitmap.err2.DaoPerson;
import sqlite.feature.typeadapter.bitmap.err2.Person;

/**
 * The Class TestBitmapTypeAdapter.
 */
@RunWith(JUnit4.class)
public class TestBitmapTypeAdapterErr2 extends AbstractBindSQLiteProcessorTest {
	
	/**
	 * Test compile.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompile() throws Throwable {		
		this.expectedException(UnsupportedFieldTypeException.class, "Unsupported type 'android.graphics.Atlas' is used");
		buildDataSourceProcessorTest(AppDataSource.class, DaoPerson.class, Person.class);
	}

}
