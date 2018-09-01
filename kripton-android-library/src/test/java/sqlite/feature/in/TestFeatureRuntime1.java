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
package sqlite.feature.in;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.in.case1.City;
import sqlite.feature.in.case1.BindAppDaoFactory;
import sqlite.feature.in.case1.BindAppDataSource;
import sqlite.feature.in.case1.BindAppDataSource.Transaction;
import sqlite.feature.in.case1.DaoCityImpl;

public class TestFeatureRuntime1 extends BaseAndroidTest {

	@Test
	public void testRuntime() {
		BindAppDataSource ds=BindAppDataSource.getInstance();
		ds.execute(new Transaction() {
			
			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				DaoCityImpl dao = daoFactory.getDaoCity();
				
				long id1;
				long id2;
				
				
				City bean1=new City();
				bean1.name="city1";				
				id1=dao.insert(bean1);
				
				City bean2=new City();
				bean2.name="city2";				
				id2=dao.insert(bean2);
				
				ArrayList<Long> params=new ArrayList<Long>();
				params.add(id1);
				params.add(id2);
				
				assertTrue(dao.selectAll2(params).size()==2);
				
				return TransactionResult.COMMIT;
			}
		});
	}

}
