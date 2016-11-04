/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.kripton64;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowContextImpl;
import org.robolectric.shadows.ShadowLog;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.processor.BaseProcessorTest;

import android.app.Application;
import android.content.Context;

/**
 * @author xcesco
 *
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestKripton64 extends BaseProcessorTest {

	@Test
	public void testJson() throws IOException, InstantiationException, IllegalAccessException, MappingException, WriterException, ReaderException {
		Bean bean=new Bean();
		
		bean.value="hell";
		bean.valueMapStringByte=new HashMap<>();
		bean.valueMapStringByte.put("hello", (byte) 24);
		bean.valueMapStringByte.put("hello2", (byte) 224);
		
		BinderJsonWriter writer=BinderFactory.getJsonWriter();
		
		String buffer=writer.writeMap(bean.valueMapStringByte);
		
		BinderJsonReader reader=BinderFactory.getJsonReader();
		HashMap<String, Byte> map = reader.readMap(new HashMap<String, Byte>(), String.class, Byte.class, buffer);
		
		String buffer2=writer.writeMap(map);
		
		Assert.assertEquals(buffer, buffer2);				
	}
	
	
	@Before
	public void setup()
	{
		ShadowLog.stream = System.out;
		KriptonLibrary.init(RuntimeEnvironment.application);
	}
	
	@Test
	public void testSqlite() throws IOException, InstantiationException, IllegalAccessException {
		
		
		//Context context=Robolectric.
		BindBeanDataSource dataSource=BindBeanDataSource.instance();
		dataSource.openWritableDatabase();
		
		BindBeanDao dao = dataSource.getBeanDao();
		
		Bean bean=new Bean();
		bean.value="hello";
		bean.valueMapEnumByte=new HashMap<>();
		bean.valueMapEnumByte.put(EnumType.VALUE_1, (byte) 34);
		
		dao.insert(bean);
		List<Bean> list=dao.selectList(bean.id);
		Assert.assertEquals("not ", 1, list.size());
		
		
		dataSource.close();
				
	}

	@Test
	public void testSharedFields() throws IOException, InstantiationException, IllegalAccessException {
	}
	
}
