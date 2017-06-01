/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.kripton58;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton58.array.BeanBean;
import sqlite.kripton58.array.BeanDao;
import sqlite.kripton58.array.BeanDataSource;
import sqlite.kripton58.array.ByteBean;
import sqlite.kripton58.array.ByteDao;
import sqlite.kripton58.array.ByteDataSource;
import sqlite.kripton58.array.CharBean;
import sqlite.kripton58.array.CharDao;
import sqlite.kripton58.array.CharDataSource;
import sqlite.kripton58.array.DoubleBean;
import sqlite.kripton58.array.DoubleDao;
import sqlite.kripton58.array.DoubleDataSource;
import sqlite.kripton58.array.FloatBean;
import sqlite.kripton58.array.FloatDao;
import sqlite.kripton58.array.FloatDataSource;
import sqlite.kripton58.array.IntBean;
import sqlite.kripton58.array.IntDao;
import sqlite.kripton58.array.IntDataSource;
import sqlite.kripton58.array.LongBean;
import sqlite.kripton58.array.LongDao;
import sqlite.kripton58.array.LongDataSource;
import sqlite.kripton58.array.ShortBean;
import sqlite.kripton58.array.ShortDao;
import sqlite.kripton58.array.ShortDataSource;
import sqlite.kripton58.array.StringBean;
import sqlite.kripton58.array.StringDao;
import sqlite.kripton58.array.StringDataSource;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@RunWith(JUnit4.class)
public class Test58ArrayCompile extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test bean with list of Byte
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test1ByteArrayFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ByteDataSource.class, ByteDao.class, ByteBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(CharDataSource.class, CharDao.class, CharBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(ShortDataSource.class, ShortDao.class, ShortBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(IntDataSource.class, IntDao.class, IntBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(LongDataSource.class, LongDao.class, LongBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(FloatDataSource.class, FloatDao.class, FloatBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(DoubleDataSource.class, DoubleDao.class, DoubleBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(StringDataSource.class, StringDao.class, StringBean.class, BaseDao.class, BeanInner.class);
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
		buildDataSourceProcessorTest(BeanDataSource.class, BeanDao.class, BeanBean.class, BaseDao.class, BeanInner.class);
	}
}
