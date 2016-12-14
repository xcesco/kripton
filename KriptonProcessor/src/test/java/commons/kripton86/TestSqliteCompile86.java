package commons.kripton86;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.DaoDefinitionWithoutAnnotatedMethodException;
import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.MethodWithoutSupportedAnnotationException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotFoundException;
import com.abubusoft.kripton.processor.exceptions.SQLPrimaryKeyNotValidTypeException;
import com.abubusoft.kripton.processor.exceptions.TooManySQLPrimaryKeyFoundException;

import commons.kripton86.test1.Bean1;
import commons.kripton86.test1.DS1DataSource;
import commons.kripton86.test1.Dao1;
import commons.kripton86.test2.Bean2;
import commons.kripton86.test2.DS2DataSource;
import commons.kripton86.test2.Dao2;
import commons.kripton86.test4.Bean4;
import commons.kripton86.test4.DS4DataSource;
import commons.kripton86.test4.Dao4;
import commons.kripton86.test5.Bean5;
import commons.kripton86.test5.DS5DataSource;
import commons.kripton86.test5.Dao5;
import commons.kripton86.test7.Bean7;
import commons.kripton86.test7.DS7DataSource;
import commons.kripton86.test7.Dao7;
import commons.kripton86.test8.Bean8;
import commons.kripton86.test8.DS8DataSource;
import commons.kripton86.test8.Dao8;
import sqlite.AbstractBindSQLiteProcessorTest;

public class TestSqliteCompile86 extends AbstractBindSQLiteProcessorTest {

	@Test
	public void test1Compile() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(DaoDefinitionWithoutAnnotatedMethodException.class);
		buildDataSourceProcessorTest(DS1DataSource.class, Dao1.class, Bean1.class);
	}
	
	@Test
	public void test2Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(MethodWithoutSupportedAnnotationException.class);
		buildDataSourceProcessorTest(DS2DataSource.class, Dao2.class, Bean2.class);
	}
	
	@Test
	public void test4Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(SQLPrimaryKeyNotFoundException.class);
		buildDataSourceProcessorTest(DS4DataSource.class, Dao4.class, Bean4.class);
	}
	
	@Test
	public void test5Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(SQLPrimaryKeyNotValidTypeException.class);
		buildDataSourceProcessorTest(DS5DataSource.class, Dao5.class, Bean5.class);
	}
	
	@Test
	public void test7Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(TooManySQLPrimaryKeyFoundException.class);
		buildDataSourceProcessorTest(DS7DataSource.class, Dao7.class, Bean7.class);
	}

	@Test
	public void test8Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(InvalidMethodSignException.class);
		buildDataSourceProcessorTest(DS8DataSource.class, Dao8.class, Bean8.class);
	}
}
