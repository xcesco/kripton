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
package sqlite.feature.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.paginatedresult.model.Person;
import sqlite.feature.schema.version2.DaoBase;
import sqlite.feature.schema.version2.DaoProfessor;
import sqlite.feature.schema.version2.DaoSeminar;
import sqlite.feature.schema.version2.DaoSeminar2Student;
import sqlite.feature.schema.version2.DaoStudent;
import sqlite.feature.schema.version2.Entity;
import sqlite.feature.schema.version2.Professor;
import sqlite.feature.schema.version2.SchoolDataSource;
import sqlite.feature.schema.version2.Seminar;
import sqlite.feature.schema.version2.Seminar2Student;
import sqlite.feature.schema.version2.Student;


/**
 * The Class TestSchema.
 */
@RunWith(JUnit4.class)
public class TestSchema extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile version 2.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompileVersion2() throws Throwable {
		buildDataSourceProcessorTest(DaoBase.class, DaoProfessor.class, DaoSeminar.class, DaoSeminar2Student.class, DaoStudent.class, Entity.class, Person.class, Professor.class,
				SchoolDataSource.class, Seminar2Student.class, Student.class, Seminar.class);
	}

}
