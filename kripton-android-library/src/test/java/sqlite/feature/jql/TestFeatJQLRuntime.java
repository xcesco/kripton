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
package sqlite.feature.jql;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.android.orm.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.jql.entities.Child;
import sqlite.feature.jql.entities.Person;
import sqlite.feature.jql.persistence.BindFamilyDaoFactory;
import sqlite.feature.jql.persistence.BindFamilyDataSource;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TestFeatJQLRuntime extends BaseAndroidTest {

	@Test
	public void testJQL1() {
		BindFamilyDataSource dataSource = BindFamilyDataSource.instance();

		// transaction to insert elements
		dataSource.execute(new BindFamilyDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindFamilyDaoFactory daoFactory) {
				// TODO Auto-generated method stub
				Person person = new Person();
				person.name = "Tonj Manero";
				daoFactory.getDaoPerson().insertBean(person);

				Child child = new Child();
				child.name = "Luna";
				child.parentId = person.id;

				daoFactory.getDaoChild().insertBean(child);
				daoFactory.getDaoChild().insertBean(child);

				List<Child> list = daoFactory.getDaoChild().selectByParent(person.id);

				assertTrue(2 == list.size());

				for (int i = 0; i < list.size(); i++) {
					assertTrue(list.get(i).name.equals("Luna"));
				}

				return TransactionResult.COMMIT;
			}
		});

	}

}