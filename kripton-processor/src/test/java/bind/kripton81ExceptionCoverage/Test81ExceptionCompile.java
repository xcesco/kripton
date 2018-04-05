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
package bind.kripton81ExceptionCoverage;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.exceptions.InvalidForeignKeyTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementFound;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;
import com.abubusoft.kripton.processor.exceptions.UnknownPropertyInJQLException;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;

import sqlite.AbstractBindSQLiteProcessorTest;

// TODO: Auto-generated Javadoc
/**
 * The Class Test81ExceptionCompile.
 */
public class Test81ExceptionCompile extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test error bind type.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindType() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildBindProcessorTest(BindTypeError.class);
	}

	/**
	 * Test error bind table.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindTable() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(BindTableError.class);
	}

	/**
	 * Test error bind data source.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindDataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(NoDaoElementFound.class);
		buildDataSourceProcessorTest(ErrorDataSource.class);
	}

	/**
	 * Test error bind data source 2.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindDataSource2() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidNameException.class);
		buildDataSourceProcessorTest(Error2DataSource.class, Error2_1Dao.class, BeanError2_1.class);
	}

	/**
	 * Test error bind data source 3.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindDataSource3() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyNotFoundException.class);
		buildDataSourceProcessorTest(Error3DataSource.class, Error3Dao.class, Error3Bean.class);
	}

	/**
	 * Test error bind data source 4.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindDataSource4() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Error4DataSource.class, Error4Dao.class, Error4Bean.class);
	}

	/**
	 * Test error bind shared preference.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindSharedPreference() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildSharedPreferencesProcessorTest(Error5Bean.class);
	}

	/**
	 * Test error bind type setter.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorBindTypeSetter() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyVisibilityException.class);
		buildBindProcessorTest(Error6Bean.class);
	}

	/**
	 * Test error 7 data source.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError7DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidForeignKeyTypeException.class);
		buildDataSourceProcessorTest(Error7_1Bean.class, Error7Bean.class, Error7Dao.class, Error7_1Dao.class, Error7DataSource.class);
	}

	/**
	 * Test 8 data source.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test8DataSource() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Bean8.class, Bean8Dao.class, Bean8DataSource.class);
	}

	/**
	 * Test error 8 data source.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError8DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Error8.class, Error8Dao.class, Error8Database.class);
	}

	/**
	 * Test error 9 data source.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError9DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidNameException.class);
		buildDataSourceProcessorTest(Bean8.class, Bean8Dao.class, Bean9Database.class);
	}

	/**
	 * Test error 10 data source.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError10DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Bean8.class, Bean8Dao.class, Bean10DataSource.class);
	}

	/**
	 * Test error 12.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError12() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class);
		buildBindProcessorTest(Error12Bean.class);
	}

	/**
	 * Test error 13.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError13() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class);
		buildSharedPreferencesProcessorTest(Error13Bean.class);
	}

	/**
	 * Test error 14.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError14() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class);
		buildDataSourceProcessorTest(Error14Bean.class);
	}

	/**
	 * Test error 15.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testError15() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Error15Bean.class);
	}

	/**
	 * Test error PK 1.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErrorPK1() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(ErrorPK1Dao.class, ErrorPK1Bean.class, ErrorPK1DataSource.class);
	}

	/**
	 * UnsupportedFieldTypeException test sql.Date that is not supported by
	 * default
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErr9() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(UnsupportedFieldTypeException.class);
		buildDataSourceProcessorTest(Error9Dao.class, Error9Bean.class, Error9DataSource.class);
	}

	/**
	 * UnsupportedFieldTypeException test sql.Date that is not supported by
	 * default
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErr9_1() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(UnsupportedFieldTypeException.class);
		buildBindProcessorTest(Error9Bean.class);
	}

	/**
	 * IncompatibleAttributesInAnnotationException: value and excludedFields are
	 * incompatible.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErr10() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildDataSourceProcessorTest(Error10Dao.class, Error10Bean.class, Error10DataSource.class);
	}

	/**
	 * UnknownPropertyInJQLException in select(value).
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErr11() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(UnknownPropertyInJQLException.class);
		buildDataSourceProcessorTest(Error11Dao.class, Error11Bean.class, Error11DataSource.class);
	}

	/**
	 * UnknownPropertyInJQLException: in select(excludedFields).
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testErr16() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(UnknownPropertyInJQLException.class);
		buildDataSourceProcessorTest(Error16Dao.class, Error16Bean.class, Error16DataSource.class);
	}

	/**
	 * Test PK.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testPK() throws IOException, InstantiationException, IllegalAccessException {
		// this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(PKDao.class, PKBean.class, PKDataSource.class);
	}
}
