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
package sqlite.kripton63;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.yaml.snakeyaml.reader.ReaderException;

import base.BaseProcessorTest;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestKripton63 extends BaseProcessorTest {

	@Test
	public void testJson() throws IOException, InstantiationException, IllegalAccessException, ReaderException {
//		Bean63 bean=new Bean63();
//		
//		bean.value="hell";
//		bean.valueMapStringByte=new HashMap<>();
//		bean.valueMapStringByte.put("hello", (byte) 24);
//		bean.valueMapStringByte.put("hello2", (byte) 224);
//		
//		BinderJsonWriter writer=KriptonBinder.getJsonWriter();
//		
//		String buffer=writer.writeMap(bean.valueMapStringByte);
//		
//		BinderJsonReader reader=KriptonBinder.getJsonReader();
//		HashMap<String, Byte> map = reader.readMap(new HashMap<String, Byte>(), String.class, Byte.class, buffer);
//		
//		String buffer2=writer.writeMap(map);
//		
//		Assert.assertEquals(buffer, buffer2);				
	}
	
	@Test
	public void testSqlite() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(BeanDataSource.class, BeanDao.class, Bean63.class, EnumType.class);
	}

	@Test
	public void testSharedFields() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(Bean63.class, EnumType.class);
	}
	
}
