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
package sqlite.feature.typeadapter.kripton180.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.typeadapter.kripton180.Address;
import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterAddress;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBirthDay;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFirstName;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLastName;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;
import sqlite.feature.typeadapter.kripton180.bean.insertselect.EmployeeBeanInsertSelectDao;
import sqlite.feature.typeadapter.kripton180.bean.insertselect.Kripton180BeanInsertSelectDataSource;
import sqlite.feature.typeadapter.kripton180.raw.insertselect.EmployeeRawInsertSelectDao;
import sqlite.feature.typeadapter.kripton180.raw.insertselect.Kripton180RawInsertSelectDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class TestKripton180Bean.
 */
@RunWith(JUnit4.class)
public class TestKripton180Bean extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test compile.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompile() throws Throwable {
		buildDataSourceProcessorTest(Address.class, Employee.class, EmployeeBeanDao.class, Kripton180BeanDataSource.class, TypeAdapterAddress.class, TypeAdapterBirthDay.class, TypeAdapterFirstName.class,
				TypeAdapterBoolean.class, TypeAdapterByte.class,  TypeAdapterShort.class, TypeAdapterChar.class, TypeAdapterDouble.class, TypeAdapterFloat.class, TypeAdapterInteger.class, TypeAdapterLong.class,
				TypeAdapterString.class, TypeAdapterByteArray.class, TypeAdapterLastName.class);
	}
	
	/**
	 * Test bean insert select.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testBeanInsertSelect() throws Throwable {
		buildDataSourceProcessorTest(Address.class, Employee.class, EmployeeBeanInsertSelectDao.class, Kripton180BeanInsertSelectDataSource.class, TypeAdapterAddress.class, TypeAdapterBirthDay.class, TypeAdapterFirstName.class,
				TypeAdapterBoolean.class, TypeAdapterByte.class,  TypeAdapterShort.class, TypeAdapterChar.class, TypeAdapterDouble.class, TypeAdapterFloat.class, TypeAdapterInteger.class, TypeAdapterLong.class,
				TypeAdapterString.class, TypeAdapterByteArray.class, TypeAdapterLastName.class);
	}
	
	/**
	 * Test raw insert select.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testRawInsertSelect() throws Throwable {
		buildDataSourceProcessorTest(Address.class, Employee.class, EmployeeRawInsertSelectDao.class, Kripton180RawInsertSelectDataSource.class, TypeAdapterAddress.class, TypeAdapterBirthDay.class, TypeAdapterFirstName.class,
				TypeAdapterBoolean.class, TypeAdapterByte.class,  TypeAdapterShort.class, TypeAdapterChar.class, TypeAdapterDouble.class, TypeAdapterFloat.class, TypeAdapterInteger.class, TypeAdapterLong.class,
				TypeAdapterString.class, TypeAdapterByteArray.class, TypeAdapterLastName.class);
	}

}
