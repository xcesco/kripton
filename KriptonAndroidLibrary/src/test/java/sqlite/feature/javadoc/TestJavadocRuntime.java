/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.javadoc;

import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.feature.javadoc.update.bean.BindPersonDataSource;
import sqlite.feature.javadoc.update.bean.PersonDaoImpl;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestJavadocRuntime extends BaseAndroidTest {

	@Test
	public void testRunSqlite1() {	
		try (BindPersonDataSource dataSource=BindPersonDataSource.open()) {
			Person bean=new Person();
			bean.setName("Tonj");
			bean.setSurname("Manero");
			bean.setStudent(true);
			
			PersonDaoImpl dao = dataSource.getPersonDao();
			
			dao.updateAllBeans(bean);
			
			dao.updateBean(bean);
		}
		
		
	}

}
