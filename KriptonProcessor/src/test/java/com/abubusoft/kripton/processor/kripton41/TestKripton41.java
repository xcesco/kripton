package com.abubusoft.kripton.processor.kripton41;

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
public class TestKripton41 extends BaseProcessorTest {

	@Test(expected=AssertionError.class)
	public void testSelectError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBeanSelectERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testSelectOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy02DataSource.class, DaoBeanSelectOK.class, Bean01.class, BaseDao.class);
	}	

	@Test(expected=AssertionError.class)
	public void testInsertError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy03DataSource.class, DaoBeanInsertERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testInsertOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy04DataSource.class, DaoBeanInsertOK.class, Bean01.class, BaseDao.class);
	}	
	
	@Test(expected=AssertionError.class)
	public void testUpdateError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy05DataSource.class, DaoBeanUpdateERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testUpdateOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy06DataSource.class, DaoBeanUpdateOK.class, Bean01.class, BaseDao.class);
	}	

	@Test(expected=AssertionError.class)
	public void testDeleteError() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy07DataSource.class, DaoBeanDeleteERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testDeleteOK() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy08DataSource.class, DaoBeanDeleteOK.class, Bean01.class, BaseDao.class);
	}	

}
