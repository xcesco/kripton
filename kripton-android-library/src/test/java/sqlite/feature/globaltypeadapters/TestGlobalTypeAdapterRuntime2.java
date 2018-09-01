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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.graphics.Bitmap;
import base.BaseAndroidTest;
import sqlite.feature.globaltypeadapters.bitmap.BindAppDaoFactory;
import sqlite.feature.globaltypeadapters.bitmap.BindAppDataSource;
import sqlite.feature.globaltypeadapters.bitmap.BindAppDataSource.Transaction;
import sqlite.feature.globaltypeadapters.bitmap.Person;

/**
 * The Class TestGlobalTypeAdapterRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestGlobalTypeAdapterRuntime2 extends BaseAndroidTest {

	/**
	 * Run.
	 */
	@Test
	public void run() {
		BindAppDataSource ds=BindAppDataSource.getInstance();
		
		ds.execute(new Transaction() {			
			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				Person bean=new Person();
				bean.image =Bitmap.createBitmap(12,12, Bitmap.Config.ARGB_8888);	
				daoFactory.getDaoPerson().insert(bean);
				
				List<Person> list=daoFactory.getDaoPerson().list();
				Assert.assertTrue(list.get(0).image!=null);
				
				return TransactionResult.COMMIT;
			}
		});
				
	}

}
