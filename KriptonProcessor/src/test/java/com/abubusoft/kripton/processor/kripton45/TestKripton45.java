package com.abubusoft.kripton.processor.kripton45;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;

@RunWith(JUnit4.class)
public class TestKripton45 extends BaseProcessorTest {

	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(AppPreferences.class);
	}

}
