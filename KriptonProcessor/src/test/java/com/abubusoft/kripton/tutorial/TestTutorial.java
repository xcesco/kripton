package com.abubusoft.kripton.tutorial;

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
import com.abubusoft.kripton.tutorial.BindSecurityDataSource.Transaction;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestTutorial extends BaseProcessorTest {

	@Test
	public void testSQLite() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(SecurityDataSource.class, DaoUser.class, User.class);
	}	
	
	@Test
	public void test() throws InstantiationException, IllegalAccessException, IOException
	{
		buildSharedPreferencesProcessorTest(SecurityPreferences.class, User.class);
		
		final User bean=new User();
		
		BindSecurityPreferences security=BindSecurityPreferences.instance();
		security.edit().putUser(bean).commit();
	}


}
