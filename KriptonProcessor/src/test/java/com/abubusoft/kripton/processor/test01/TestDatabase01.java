package com.abubusoft.kripton.processor.test01;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.sqlite.exceptions.*;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestDatabase01 extends BaseProcessorTest {

	/**
	 * No @BindType is put in bean definition
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test01() throws IOException {
		buildTest(Dummy01Database.class, Bean01.class);
	}

	/**
	 * No @BindType is put in bean definition
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test02() throws IOException {
		buildTest(Dummy02Database.class, Bean01.class, Bean02.class);

	}

	/**
	 * No database schema with @BindDatabaseSchema was found
	 * 
	 * @throws IOException
	 */
	@Test
	public void test03() throws IOException {
		buildTest(Dummy03Database.class, Bean01.class, Bean02.class);
	}

	/**
	 * Class com.abubusoft.kripton.processor.test01.Bean04, used in Dummy04DatabaseSchema DatabaseSchemaDefinition, has no property!
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test04() throws IOException {
		buildTest(Dummy04Database.class, Bean04.class);
	}

	/**
	 * No database schema with @BindDatabaseSchema was found
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test05() throws IOException {
		buildTest(Dummy05Database.class, Bean04.class);		
	}

	@Test(expected = AssertionError.class)
	public void test06() throws IOException {
		buildTest(Dummy06Database.class, Bean06.class);	
	}

	/**
	 * No primary key
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test07() throws IOException {
		buildTest(Dummy07Database.class, Bean07.class);					
	}

	/**
	 * No primary key
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test08() throws IOException {
		buildTest(Dummy08Database.class, Bean08.class);		
	}

	/**
	 * Primary key Long
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test09() throws IOException {
		buildTest(Dummy09Database.class, Bean09.class);		
	}

	/**
	 * Too many primary keys
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test10() throws IOException {
		buildTest(Dummy10Database.class, Bean10.class);		
	}
	
	/**
	 * Too many primary keys
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test11() throws IOException {
		buildTest(Dummy11Error.class, Bean10.class);		
	}
	
	/**
	 * Twice database definitino
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test12() throws IOException {
		buildTest(Dummy12ADatabase.class, Dummy12BDatabase.class,Bean12.class);		
	}

}
