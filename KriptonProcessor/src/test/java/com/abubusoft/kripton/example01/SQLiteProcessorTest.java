package com.abubusoft.kripton.example01;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;

@RunWith(JUnit4.class)
public class SQLiteProcessorTest extends BaseProcessorTest {

	/**
	 * No @BindType is put in bean definition
	 * 
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void test01() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoChannel.class, DaoChannelMessage.class, Channel.class, ChannelMessage.class);
	}
}
