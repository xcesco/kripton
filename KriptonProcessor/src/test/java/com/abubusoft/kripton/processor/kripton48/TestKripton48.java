package com.abubusoft.kripton.processor.kripton48;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.kripton48.entities.Bean01;
import com.abubusoft.kripton.processor.kripton48.entities.Bean02;
import com.abubusoft.kripton.processor.kripton48.persistence.BaseDao;
import com.abubusoft.kripton.processor.kripton48.persistence.DaoBean01;
import com.abubusoft.kripton.processor.kripton48.persistence.DaoBean02;
import com.abubusoft.kripton.processor.kripton48.persistence.Dummy01DataSource;
import com.abubusoft.kripton.processor.kripton48.persistence.Dummy02DataSource;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton48 extends BaseProcessorTest {

	/**
	 *  id: Long id
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, BaseDao.class,Dummy02DataSource.class, DaoBean02.class, Bean02.class);
	}	


}
