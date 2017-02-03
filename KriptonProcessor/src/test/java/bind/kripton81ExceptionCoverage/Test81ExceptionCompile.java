package bind.kripton81ExceptionCoverage;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.exceptions.InvalidForeignKeyTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementsFound;
import com.abubusoft.kripton.processor.exceptions.PropertyInAnnotationNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;

import sqlite.AbstractBindSQLiteProcessorTest;

public class Test81ExceptionCompile extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testErrorBindType() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildBindProcessorTest(BindTypeError.class);
	}

	@Test
	public void testErrorBindTable() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(BindTableError.class);
	}

	@Test
	public void testErrorBindDataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(NoDaoElementsFound.class);
		buildDataSourceProcessorTest(ErrorDataSource.class);
	}

	@Test
	public void testErrorBindDataSource2() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidNameException.class);
		buildDataSourceProcessorTest(Error2DataSource.class, Error2_1Dao.class, BeanError2_1.class);
	}

	@Test
	public void testErrorBindDataSource3() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyNotFoundException.class);
		buildDataSourceProcessorTest(Error3DataSource.class, Error3Dao.class, Error3Bean.class);
	}

	@Test
	public void testErrorBindDataSource4() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Error4DataSource.class, Error4Dao.class, Error4Bean.class);
	}

	@Test
	public void testErrorBindSharedPreference() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildSharedPreferencesProcessorTest(Error5Bean.class);
	}

	@Test
	public void testErrorBindTypeSetter() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyVisibilityException.class);
		buildBindProcessorTest(Error6Bean.class);
	}

	@Test
	public void testError7DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidForeignKeyTypeException.class);
		buildDataSourceProcessorTest(Error7_1Bean.class, Error7Bean.class, Error7Dao.class, Error7_1Dao.class, Error7DataSource.class);
	}

	@Test
	public void test8DataSource() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Bean8.class, Bean8Dao.class, Bean8DataSource.class);
	}

	@Test
	public void testError8DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Error8.class, Error8Dao.class, Error8Database.class);
	}

	@Test
	public void testError9DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidNameException.class);
		buildDataSourceProcessorTest(Bean8.class, Bean8Dao.class, Bean9Database.class);
	}

	@Test
	public void testError10DataSource() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Bean8.class, Bean8Dao.class, Bean10DataSource.class);
	}

	@Test
	public void testError12() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class);
		buildBindProcessorTest(Error12Bean.class);
	}

	@Test
	public void testError13() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class);
		buildSharedPreferencesProcessorTest(Error13Bean.class);
	}

	@Test
	public void testError14() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidDefinition.class);
		buildDataSourceProcessorTest(Error14Bean.class);
	}

	@Test
	public void testError15() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildDataSourceProcessorTest(Error15Bean.class);
	}

	@Test
	public void testErrorPK1() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(ErrorPK1Dao.class, ErrorPK1Bean.class, ErrorPK1DataSource.class);
	}

	/**
	 * UnsupportedFieldTypeException test sql.Date that is not supported by
	 * default
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
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
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErr9_1() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(UnsupportedFieldTypeException.class);
		buildBindProcessorTest(Error9Bean.class);
	}

	/**
	 * IncompatibleAttributesInAnnotationException: value and excludedFields are
	 * incompatible
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErr10() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildDataSourceProcessorTest(Error10Dao.class, Error10Bean.class, Error10DataSource.class);
	}

	/**
	 * PropertyNotFoundException in select(value)
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErr11() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyInAnnotationNotFoundException.class);
		buildDataSourceProcessorTest(Error11Dao.class, Error11Bean.class, Error11DataSource.class);
	}

	/**
	 * PropertyNotFoundException in select(excludedFields)
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErr16() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(PropertyInAnnotationNotFoundException.class);
		buildDataSourceProcessorTest(Error16Dao.class, Error16Bean.class, Error16DataSource.class);
	}

	@Test
	public void testPK() throws IOException, InstantiationException, IllegalAccessException {
		// this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(PKDao.class, PKBean.class, PKDataSource.class);
	}
}
