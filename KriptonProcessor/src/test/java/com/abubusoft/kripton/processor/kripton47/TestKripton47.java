package com.abubusoft.kripton.processor.kripton47;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;

@RunWith(JUnit4.class)
public class TestKripton47 extends BaseProcessorTest {

	/**
	 * No @BindType is put in bean definition
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void test() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(AppPreferences.class, UserAccessToken.class);
	}
	
	/**
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSecurity() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(SecurityPreferences.class, DeviceAccessToken.class, UserAccessToken.class);
	}
	


}
