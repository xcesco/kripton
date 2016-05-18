package com.abubusoft.kripton.processor.test03;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.BaseProcessorTest;

public class TestBean02 extends BaseProcessorTest  {

	@Test
	public void testSelectCursor() throws IOException
	{
		buildTest(Dummy01Database.class, DaoBean02.class, Bean01.class);
	}
}
