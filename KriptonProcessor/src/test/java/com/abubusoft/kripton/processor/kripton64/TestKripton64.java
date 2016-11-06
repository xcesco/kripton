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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.Logger;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.kripton64.BindBeanDataSource.Transaction;

/**
 * @author xcesco
 *
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestKripton64 extends BaseProcessorTest {
	
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
		
		dataSource.execute(new Transaction() {
			
			@Override
			public boolean onExecute(BindBeanDaoFactory daoFactory) {
				BeanDaoImpl dao = daoFactory.getBeanDao();
				
				Bean bean=new Bean();
				bean.valueString ="hello";
				bean.valueMapStringBean =new HashMap<>();
				bean.valueMapStringBean.put("key1", new Bean());
				
				dao.insert(bean);
				List<Bean> list=dao.selectList(bean.id);
				Assert.assertEquals("not ", 1, list.size());
				
				return true;
			}
			
			@Override
			public void onError(Throwable e) {
				
			}
		});
				
	}

	@Test
	public void testJson() throws IOException, InstantiationException, IllegalAccessException, MappingException, WriterException, ReaderException {
		Bean bean=new Bean();
		
		bean.valueString ="hello";
		bean.valueMapStringBean =new HashMap<>();
		bean.valueMapStringBean.put("key1", new Bean());
		
		BinderJsonWriter writer=BinderFactory.getJsonWriter();
		
		String buffer=writer.writeMap(bean.valueMapStringBean);
		
		BinderJsonReader reader=BinderFactory.getJsonReader();
		HashMap<String, Bean> map = reader.readMap(new HashMap<String, Bean>(), String.class, Bean.class, buffer);
		
		String buffer2=writer.writeMap(map);
		
		Assert.assertEquals(buffer, buffer2);				
	}

	@Test
	public void testSharedFields() throws IOException, InstantiationException, IllegalAccessException {
		BindBeanSharedPreferences shared = BindBeanSharedPreferences.instance();
		
		shared.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				Log.i("TAG", "change "+key);			
			}
		});
		
		shared.edit()		
		.putValueBool(true)
		.putValueBoolType(true)
		.putValueBigDecimal(new BigDecimal(10))
		.putValueBigInteger(BigInteger.valueOf(20))
		.putValueByte((byte)34)
		.putValueByteType((byte)34)
		.putValueString("hello")
		.commit();
		
		Assert.assertEquals(true, shared.valueBool());
		Assert.assertEquals(true, shared.valueBoolType());
		Assert.assertEquals(new BigDecimal(10), shared.valueBigDecimal());
		Assert.assertEquals(BigInteger.valueOf(20), shared.valueBigInteger());
		Assert.assertTrue((byte)34==shared.valueByte());
		Assert.assertEquals((byte)34, shared.valueByteType());
		Assert.assertEquals("hello", shared.valueString());
	}
	
}
