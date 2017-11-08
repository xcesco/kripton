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

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.DatabaseLifecycleHandler;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;
import sqlite.feature.schema.version2.BindSchoolDataSource;
import sqlite.feature.schema.version2.DaoProfessorImpl;
import sqlite.feature.schema.version2.DaoStudentImpl;
import sqlite.feature.schema.version2.Professor;
import sqlite.feature.schema.version2.Student;

/**
 * @author xcesco
 *
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaRuntime extends BaseAndroidTest {

	@Test
	public void testRun() {
		
		BindSchoolDataSource.build(DataSourceOptions.builder().databaseLifecycleHandler(new DatabaseLifecycleHandler() {
			
			@Override
			public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion, boolean upgrade) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCreate(SQLiteDatabase database) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onConfigure(SQLiteDatabase database) {
				// TODO Auto-generated method stub
				
			}
		}).build());
		
		try (BindSchoolDataSource dataSource = BindSchoolDataSource.open(); DaoProfessorImpl dao = dataSource.getDaoProfessor()) {
			// dataSource.execute(transaction);
			for (int i = 0; i < 10; i++) {
				Professor professor = new Professor();
				professor.name = String.format("professor%03d", i);
				professor.surname="surname"+i;
				professor.birthDate = new Date();
				dao.insert(professor);
			}

		}
		
		try (BindSchoolDataSource dataSource = BindSchoolDataSource.open(); DaoStudentImpl dao = dataSource.getDaoStudent()) {
			// dataSource.execute(transaction);
			for (int i = 0; i < 100; i++) {
				Student student = new Student();
				student.name = String.format("professor%03d", i);
				student.location=String.format("location%03d", i);				
				dao.insert(student);
			}

		}

	}

}
