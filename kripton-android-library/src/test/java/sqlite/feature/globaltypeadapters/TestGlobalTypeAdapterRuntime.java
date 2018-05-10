/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.globaltypeadapters;

import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.globaltypeadapters.model1.BindPerson1DaoFactory;
import sqlite.feature.globaltypeadapters.model1.BindPerson1DataSource;
import sqlite.feature.globaltypeadapters.model1.BindPerson1DataSource.Transaction;

// TODO: Auto-generated Javadoc
/**
 * The Class TestGlobalTypeAdapterRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestGlobalTypeAdapterRuntime extends BaseAndroidTest {

	/**
	 * Run.
	 */
	@Test
	public void run() {
		BindPerson1DataSource ds=BindPerson1DataSource.getInstance();
		
		ds.execute(new Transaction() {			
			@Override
			public TransactionResult onExecute(BindPerson1DaoFactory daoFactory) {
				Person bean=new Person();
				bean.birthDay=new Date(0);				
				daoFactory.getDaoPerson().insert(bean);
				
				List<Person> list=daoFactory.getDaoPerson().selectAll();
				Assert.assertTrue(list.get(0).birthDay.getTime()==2);
				
				return TransactionResult.COMMIT;
			}
		});
				
	}

}
