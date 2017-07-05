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
package sqlite.featJQL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.featJQL.entities.Bean;
import sqlite.featJQL.entities.Child;
import sqlite.featJQL.entities.Person;
import sqlite.featJQL.persistence.DaoBean;
import sqlite.featJQL.persistence.DaoChild;
import sqlite.featJQL.persistence.DaoPerson;
import sqlite.featJQL.persistence.FamilyDataSource;

@RunWith(JUnit4.class)
public class TestJQL extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile01() throws Throwable {
		buildDataSourceProcessorTest(FamilyDataSource.class, DaoChild.class, DaoPerson.class, Person.class, Child.class, Bean.class, DaoBean.class);
	}

}
