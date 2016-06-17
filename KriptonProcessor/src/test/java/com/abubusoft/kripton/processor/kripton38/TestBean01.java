package com.abubusoft.kripton.processor.kripton38;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestBean01 extends BaseProcessorTest {

	/**
	 *  id: Long id
	 * 
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException {
		buildTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, BaseDao.class);
	}	

	/**
	 *  id: long id
	 * 
	 * @throws IOException
	 */
	@Test
	public void test02() throws IOException {
		buildTest(Dummy02DataSource.class, DaoBean02.class, Bean02.class, BaseDao.class);
	}
	
	/**
	 *  @BindColumn(ColumnType.PRIMARY_KEY) on Long id;
	 * 
	 * @throws IOException
	 */
	@Test
	public void test03() throws IOException {
		buildTest(Dummy03DataSource.class, DaoBean03.class, Bean03.class, BaseDao.class);
	}
	

	/**
	 *  No DAO definition with @BindDaoDefinition annotation was found for class Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test04() throws IOException {
		buildTest(Dummy04DataSource.class, DaoBean04.class, Bean04.class, BaseDao.class);
	}
	
	/**
	 *  No DAO definition with @BindDaoDefinition annotation was found for class Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 */
	@Test
	public void test05() throws IOException {
		buildTest(Dummy05DataSource.class, DaoBean05.class, Bean05.class, BaseDao.class, BeanType.class);
	}
	
	@Test(expected=AssertionError.class)
	public void test06() throws IOException {
		buildTest(Dummy06DataSource.class, DaoBean06.class, Bean06.class, BaseDao.class, BeanType.class);
	}

}
