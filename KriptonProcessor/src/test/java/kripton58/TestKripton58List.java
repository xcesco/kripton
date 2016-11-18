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
package kripton58;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import base.BaseProcessorTest;
import kripton58.list.BeanBean;
import kripton58.list.BeanDao;
import kripton58.list.BeanDataSource;
import kripton58.list.ByteBean;
import kripton58.list.ByteDao;
import kripton58.list.ByteDataSource;
import kripton58.list.CharBean;
import kripton58.list.CharDao;
import kripton58.list.CharDataSource;
import kripton58.list.DoubleBean;
import kripton58.list.DoubleDao;
import kripton58.list.DoubleDataSource;
import kripton58.list.FloatBean;
import kripton58.list.FloatDao;
import kripton58.list.FloatDataSource;
import kripton58.list.IntegerBean;
import kripton58.list.IntegerDao;
import kripton58.list.IntegerDataSource;
import kripton58.list.LongBean;
import kripton58.list.LongDao;
import kripton58.list.LongDataSource;
import kripton58.list.ShortBean;
import kripton58.list.ShortDao;
import kripton58.list.ShortDataSource;
import kripton58.list.StringBean;
import kripton58.list.StringDao;
import kripton58.list.StringDataSource;

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
