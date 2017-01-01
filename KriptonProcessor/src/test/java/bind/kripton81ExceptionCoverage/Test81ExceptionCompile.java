package bind.kripton81ExceptionCoverage;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.InvalidDefinition;
import com.abubusoft.kripton.processor.exceptions.InvalidForeignKeyTypeException;
import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;
import com.abubusoft.kripton.processor.exceptions.InvalidNameException;
import com.abubusoft.kripton.processor.exceptions.NoDaoElementsFound;
import com.abubusoft.kripton.processor.exceptions.PropertyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;

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
}
