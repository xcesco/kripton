package com.abubusoft.kripton.processor.kripton50;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;

@RunWith(JUnit4.class)
public class TestKripton50 extends BaseProcessorTest {

	
	@Test
	public void testSharedPreferenceName() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(App.class, UserAccessToken.class);
	}

}
