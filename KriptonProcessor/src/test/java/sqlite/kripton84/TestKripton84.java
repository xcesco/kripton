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
package sqlite.kripton84;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import com.abubusoft.kripton.android.KriptonLibrary;

import base.BaseProcessorTest;

/**
 * @author xcesco
 *
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestKripton84 extends BaseProcessorTest {
	
	@Before
	public void setup()
	{
		ShadowLog.stream = System.out;
		KriptonLibrary.init(RuntimeEnvironment.application);
	}
	
	@Test
	public void testCompileSqlite() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(Bean84DataSource.class, Bean84Dao.class, Bean84.class, Enum84Type.class);
	}
	
	@Test
	public void testRunSqlite() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(Bean84Table.class.getName()!=null);
		Assert.assertNotNull(Bean84DaoImpl.class.getName()!=null);
//		BindBeanDataSource dataSource=BindBeanDataSource.instance();
//		dataSource.openWritableDatabase();
//		
//		dataSource.execute(new Transaction() {
//			
//			@Override
//			public boolean onExecute(BindBeanDaoFactory daoFactory) {
//				BeanDaoImpl dao = daoFactory.getBeanDao();
//				
//				Bean84 bean=new Bean84();
//				bean.valueString ="hello";
//				bean.valueMapStringBean =new HashMap<>();
//				bean.valueMapStringBean.put("key1", new Bean84());
//				bean.valueSetString=new HashSet<String>();
//				bean.valueSetString.add("hello");
//				
//				dao.insert(bean);
//				List<Bean84> list=dao.selectList(bean.id);
//				Assert.assertEquals("not list ", 1, list.size());
//											
//				Assert.assertEquals("not map", 1, list.size());
//				
//				Assert.assertEquals("not set", 1, list.get(0).valueSetString.size());
//				
//				return true;
//			}
//			
//			@Override
//			public void onError(Throwable e) {
//				
//			}
//		});
				
	}

	
}
