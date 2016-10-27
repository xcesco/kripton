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
package com.abubusoft.kripton.processor.kripton58;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.kripton58.list.BeanBean;
import com.abubusoft.kripton.processor.kripton58.list.BeanDao;
import com.abubusoft.kripton.processor.kripton58.list.BeanDataSource;
import com.abubusoft.kripton.processor.kripton58.list.ByteBean;
import com.abubusoft.kripton.processor.kripton58.list.ByteDao;
import com.abubusoft.kripton.processor.kripton58.list.ByteDataSource;
import com.abubusoft.kripton.processor.kripton58.list.CharBean;
import com.abubusoft.kripton.processor.kripton58.list.CharDao;
import com.abubusoft.kripton.processor.kripton58.list.CharDataSource;
import com.abubusoft.kripton.processor.kripton58.list.DoubleBean;
import com.abubusoft.kripton.processor.kripton58.list.DoubleDao;
import com.abubusoft.kripton.processor.kripton58.list.DoubleDataSource;
import com.abubusoft.kripton.processor.kripton58.list.FloatBean;
import com.abubusoft.kripton.processor.kripton58.list.FloatDao;
import com.abubusoft.kripton.processor.kripton58.list.FloatDataSource;
import com.abubusoft.kripton.processor.kripton58.list.IntegerBean;
import com.abubusoft.kripton.processor.kripton58.list.IntegerDao;
import com.abubusoft.kripton.processor.kripton58.list.IntegerDataSource;
import com.abubusoft.kripton.processor.kripton58.list.LongBean;
import com.abubusoft.kripton.processor.kripton58.list.LongDao;
import com.abubusoft.kripton.processor.kripton58.list.LongDataSource;
import com.abubusoft.kripton.processor.kripton58.list.ShortBean;
import com.abubusoft.kripton.processor.kripton58.list.ShortDao;
import com.abubusoft.kripton.processor.kripton58.list.ShortDataSource;
import com.abubusoft.kripton.processor.kripton58.list.StringBean;
import com.abubusoft.kripton.processor.kripton58.list.StringDao;
import com.abubusoft.kripton.processor.kripton58.list.StringDataSource;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton58List extends BaseProcessorTest {

	/**
	 * Test bean with list of Byte
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test1ByteListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ByteDataSource.class, ByteDao.class, ByteBean.class, BaseDao.class);
	}
	
	/**
	 * Test bean with list of Char
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test2CharListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(CharDataSource.class, CharDao.class, CharBean.class, BaseDao.class);
	}

	
	/**
	 * Test bean with list of Short
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test3ShortListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ShortDataSource.class, ShortDao.class, ShortBean.class, BaseDao.class);
	}
	
	/**
	 * Test bean with list of Integer
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test4IntegerListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(IntegerDataSource.class, IntegerDao.class, IntegerBean.class, BaseDao.class);
	}
	
	/**
	 * Test bean with list of Long
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test5LongListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(LongDataSource.class, LongDao.class, LongBean.class, BaseDao.class);
	}
	
	/**
	 * Test bean with list of Float
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test6FloatListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(FloatDataSource.class, FloatDao.class, FloatBean.class, BaseDao.class);
	}
	
	/**
	 * Test bean with list of Double
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test7DoubleListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(DoubleDataSource.class, DoubleDao.class, DoubleBean.class, BaseDao.class);
	}	

	/**
	 * Test bean with list of String
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test8StringListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(StringDataSource.class, StringDao.class, StringBean.class, BaseDao.class);
	}	
	
	/**
	 * Test bean with list of Bean
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test9BeanListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanDataSource.class, BeanDao.class, BeanBean.class, BaseDao.class);
	}	
}
