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
import com.abubusoft.kripton.processor.kripton58.array.Bean01;
import com.abubusoft.kripton.processor.kripton58.array.Bean01Inner;
import com.abubusoft.kripton.processor.kripton58.array.DaoBean01;
import com.abubusoft.kripton.processor.kripton58.array.Dummy01DataSource;
import com.abubusoft.kripton.processor.kripton58.list.Bean04Bean;
import com.abubusoft.kripton.processor.kripton58.list.Bean04Dao;
import com.abubusoft.kripton.processor.kripton58.list.Bean04DataSource;
import com.abubusoft.kripton.processor.kripton58.list.Bean04Inner;
import com.abubusoft.kripton.processor.kripton58.list.Integer03Bean;
import com.abubusoft.kripton.processor.kripton58.list.Integer03Dao;
import com.abubusoft.kripton.processor.kripton58.list.Integer03DataSource;
import com.abubusoft.kripton.processor.kripton58.list.Short05Bean;
import com.abubusoft.kripton.processor.kripton58.list.Short05Dao;
import com.abubusoft.kripton.processor.kripton58.list.Short05DataSource;
import com.abubusoft.kripton.processor.kripton58.list.String02Bean;
import com.abubusoft.kripton.processor.kripton58.list.String02Dao;
import com.abubusoft.kripton.processor.kripton58.list.String02DataSource;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton58 extends BaseProcessorTest {

	/**
	 * Test bean with array fields
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test01ArrayFields() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Dummy01DataSource.class, DaoBean01.class, Bean01.class, Bean01Inner.class, BaseDao.class);
	}	
	
	/**
	 * Test bean with string list
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test02StringListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(String02DataSource.class, String02Dao.class, String02Bean.class, BaseDao.class);
	}
	
	/**
	 * Test bean with integer list
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test03IntegerListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Integer03DataSource.class, Integer03Dao.class, Integer03Bean.class, BaseDao.class);
	}	

	
	/**
	 * Test bean with list of another bean
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test04InnerBeanListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Bean04DataSource.class, Bean04Dao.class, Bean04Bean.class, Bean04Inner.class,BaseDao.class);
	}
	
	/**
	 * Test bean with list of Short
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test05ShortListFieldType() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Short05DataSource.class, Short05Dao.class, Short05Bean.class, BaseDao.class);
	}

}
