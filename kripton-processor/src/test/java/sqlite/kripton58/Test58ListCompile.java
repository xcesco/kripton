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
import sqlite.kripton58.list.BeanBean;
import sqlite.kripton58.list.BeanDao;
import sqlite.kripton58.list.BeanDataSource;
import sqlite.kripton58.list.ByteBean;
import sqlite.kripton58.list.ByteDao;
import sqlite.kripton58.list.ByteDataSource;
import sqlite.kripton58.list.CharBean;
import sqlite.kripton58.list.CharDao;
import sqlite.kripton58.list.CharDataSource;
import sqlite.kripton58.list.DoubleBean;
import sqlite.kripton58.list.DoubleDao;
import sqlite.kripton58.list.DoubleDataSource;
import sqlite.kripton58.list.FloatBean;
import sqlite.kripton58.list.FloatDao;
import sqlite.kripton58.list.FloatDataSource;
import sqlite.kripton58.list.IntegerBean;
import sqlite.kripton58.list.IntegerDao;
import sqlite.kripton58.list.IntegerDataSource;
import sqlite.kripton58.list.LongBean;
import sqlite.kripton58.list.LongDao;
import sqlite.kripton58.list.LongDataSource;
import sqlite.kripton58.list.ShortBean;
import sqlite.kripton58.list.ShortDao;
import sqlite.kripton58.list.ShortDataSource;
import sqlite.kripton58.list.StringBean;
import sqlite.kripton58.list.StringDao;
import sqlite.kripton58.list.StringDataSource;


/**
 * The Class Test58ListCompile.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@RunWith(JUnit4.class)
public class Test58ListCompile extends AbstractBindSQLiteProcessorTest {

	/**
	 * Test bean with list of Byte.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test1ByteListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ByteDataSource.class, ByteDao.class, ByteBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Char.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test2CharListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(CharDataSource.class, CharDao.class, CharBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Short.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test3ShortListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ShortDataSource.class, ShortDao.class, ShortBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Integer.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test4IntegerListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(IntegerDataSource.class, IntegerDao.class, IntegerBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Long.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test5LongListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(LongDataSource.class, LongDao.class, LongBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Float.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test6FloatListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(FloatDataSource.class, FloatDao.class, FloatBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Double.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test7DoubleListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(DoubleDataSource.class, DoubleDao.class, DoubleBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of String.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test8StringListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(StringDataSource.class, StringDao.class, StringBean.class, BaseDao.class, BeanInner.class);
	}

	/**
	 * Test bean with list of Bean.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void test9BeanListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanDataSource.class, BeanDao.class, BeanBean.class, BaseDao.class, BeanInner.class);
	}
}
