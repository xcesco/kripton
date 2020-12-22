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
package sqlite.feature.typeadapter.update.err2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.typeadapter.Contact;
import sqlite.feature.typeadapter.ContactType;
import sqlite.feature.typeadapter.DateAdapterType;
import sqlite.feature.typeadapter.EnumAdapterType;
import sqlite.feature.typeadapter.PasswordAdapterType;


/**
 * The Class TestTypeAdapterUpdateErr2.
 */
@RunWith(JUnit4.class)
public class TestTypeAdapterUpdateErr2 extends AbstractBindSQLiteProcessorTest {
	
	/**
	 * Test error with adapter on updated fields.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testErrorWithAdapterOnUpdatedFields() throws Throwable {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(Contact.class, ErrContactDao.class, EnumAdapterType.class, ContactType.class, ErrContactDataSource.class, DateAdapterType.class, PasswordAdapterType.class);
	}

}
