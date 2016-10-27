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
import com.abubusoft.kripton.processor.kripton58.array2.ByteBean;
import com.abubusoft.kripton.processor.kripton58.array2.ByteDao;
import com.abubusoft.kripton.processor.kripton58.array2.ByteDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.BeanBean;
import com.abubusoft.kripton.processor.kripton58.array2.BeanDao;
import com.abubusoft.kripton.processor.kripton58.array2.BeanDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.CharBean;
import com.abubusoft.kripton.processor.kripton58.array2.CharDao;
import com.abubusoft.kripton.processor.kripton58.array2.CharDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.DoubleBean;
import com.abubusoft.kripton.processor.kripton58.array2.DoubleDao;
import com.abubusoft.kripton.processor.kripton58.array2.DoubleDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.FloatBean;
import com.abubusoft.kripton.processor.kripton58.array2.FloatDao;
import com.abubusoft.kripton.processor.kripton58.array2.FloatDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.IntBean;
import com.abubusoft.kripton.processor.kripton58.array2.IntDao;
import com.abubusoft.kripton.processor.kripton58.array2.IntDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.LongBean;
import com.abubusoft.kripton.processor.kripton58.array2.LongDao;
import com.abubusoft.kripton.processor.kripton58.array2.LongDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.ShortBean;
import com.abubusoft.kripton.processor.kripton58.array2.ShortDao;
import com.abubusoft.kripton.processor.kripton58.array2.ShortDataSource;
import com.abubusoft.kripton.processor.kripton58.array2.StringBean;
import com.abubusoft.kripton.processor.kripton58.array2.StringDao;
import com.abubusoft.kripton.processor.kripton58.array2.StringDataSource;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton58Array2 extends BaseProcessorTest {

	/**
	 * Test bean with list of Byte
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test1ByteArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test2CharArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test3ShortArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test4IntegerArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(IntDataSource.class, IntDao.class, IntBean.class, BaseDao.class);
	}

	/**
	 * Test bean with list of Long
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test5LongArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test6FloatArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test7DoubleArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test8StringArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
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
	public void test9BeanArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanDataSource.class, BeanDao.class, BeanBean.class, BaseDao.class);
	}
}
