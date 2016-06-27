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
	public void testSelectError() throws IOException {
		buildTest(Dummy01DataSource.class, DaoBeanSelectERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testSelectOK() throws IOException {
		buildTest(Dummy02DataSource.class, DaoBeanSelectOK.class, Bean01.class, BaseDao.class);
	}	

	@Test(expected=AssertionError.class)
	public void testInsertError() throws IOException {
		buildTest(Dummy03DataSource.class, DaoBeanInsertERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testInsertOK() throws IOException {
		buildTest(Dummy04DataSource.class, DaoBeanInsertOK.class, Bean01.class, BaseDao.class);
	}	
	
	@Test(expected=AssertionError.class)
	public void testUpdateError() throws IOException {
		buildTest(Dummy05DataSource.class, DaoBeanUpdateERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testUpdateOK() throws IOException {
		buildTest(Dummy06DataSource.class, DaoBeanUpdateOK.class, Bean01.class, BaseDao.class);
	}	

	@Test(expected=AssertionError.class)
	public void testDeleteError() throws IOException {
		buildTest(Dummy07DataSource.class, DaoBeanDeleteERR.class, Bean01.class, BaseDao.class);
	}	
	
	@Test
	public void testDeleteOK() throws IOException {
		buildTest(Dummy08DataSource.class, DaoBeanDeleteOK.class, Bean01.class, BaseDao.class);
	}	

}
