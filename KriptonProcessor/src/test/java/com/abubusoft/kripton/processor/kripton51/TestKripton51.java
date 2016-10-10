package com.abubusoft.kripton.processor.kripton51;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.kripton51.entities.MessageEntity;
import com.abubusoft.kripton.processor.kripton51.entities.OwnerType;
import com.abubusoft.kripton.processor.kripton51.internal.MessageType;
import com.abubusoft.kripton.processor.kripton51.persistence.DaoMessage;
import com.abubusoft.kripton.processor.kripton51.persistence.WhisperDataSource;

@RunWith(JUnit4.class)
public class TestKripton51 extends BaseProcessorTest {

	@Test
	public void testDatabase() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(WhisperDataSource.class, MessageEntity.class, OwnerType.class, DaoMessage.class, MessageType.class);		
	}

}
