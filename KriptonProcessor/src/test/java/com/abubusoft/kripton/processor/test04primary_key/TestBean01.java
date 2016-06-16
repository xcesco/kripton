package com.abubusoft.kripton.processor.test04primary_key;

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
		buildTest(Dummy01Database.class, DaoBean01.class, Bean01.class, BaseDao.class);
	}	

	/**
	 *  id: long id
	 * 
	 * @throws IOException
	 */
	@Test
	public void test02() throws IOException {
		buildTest(Dummy02Database.class, DaoBean02.class, Bean02.class, BaseDao.class);
	}
	
	/**
	 *  @BindColumn(ColumnType.PRIMARY_KEY) on Long id;
	 * 
	 * @throws IOException
	 */
	@Test
	public void test03() throws IOException {
		buildTest(Dummy03Database.class, DaoBean03.class, Bean03.class, BaseDao.class);
	}
	

	/**
	 *  No DAO definition with @BindDaoDefinition annotation was found for class Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test04() throws IOException {
		buildTest(Dummy04Database.class, DaoBean04.class, Bean04.class, BaseDao.class);
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

}
