package com.abubusoft.kripton.processor.kripton33;

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
public class TestKripton33 extends BaseProcessorTest {

	/**
	 * test on select 1
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test01() throws IOException {
		buildTest(Channel01DataSource.class, DaoChannel01.class, Channel.class);
	}	
	
	/**
	 * test on select 2
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test02() throws IOException {
		buildTest(Channel02DataSource.class, DaoChannel02.class, Channel.class);
	}
	
	/**
	 * test on update raw
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test03() throws IOException {
		buildTest(Channel03DataSource.class, DaoChannel03.class, Channel.class);
	}
	
	/**
	 * test on delete raw
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test04() throws IOException {
		buildTest(Channel04DataSource.class, DaoChannel04.class, Channel.class);
	}
	
	/**
	 * test on update bean
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test05() throws IOException {
		buildTest(Channel05DataSource.class, DaoChannel05.class, Channel.class);
	}
	
	/**
	 * test on delete bean
	 * @throws IOException
	 */
	@Test(expected=AssertionError.class)
	public void test06() throws IOException {
		buildTest(Channel06DataSource.class, DaoChannel06.class, Channel.class);
	}

}
