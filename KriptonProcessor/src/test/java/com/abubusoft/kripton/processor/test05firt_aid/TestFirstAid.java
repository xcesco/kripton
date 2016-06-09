package com.abubusoft.kripton.processor.test05firt_aid;

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
public class TestFirstAid extends BaseProcessorTest {

	/**
	 *  id: Long id
	 * 
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException {
		buildTest(FirstAidDatabase.class, FirstAidDao.class, FirstAid.class);
	}	

}
