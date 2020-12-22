/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.datasourceoptions.livedata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.abubusoft.kripton.android.sqlite.SQLitePopulator;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.database.sqlite.SQLiteDatabase;
import sqlite.feature.datasourceoptions.livedata.BindAppWithConfigDataSource.Transaction;


/**
 * The Class PersonPopulator.
 */
public class PersonPopulator implements SQLitePopulator {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLitePopulator#execute()
	 */
	@Override
	public void execute() {
		BindAppWithConfigDataSource ds=BindAppWithConfigDataSource.getInstance();
		
		Future<Boolean> result = ds.executeAsync(new Transaction() {
			
			@Override
			public TransactionResult onExecute(BindAppWithConfigDaoFactory daoFactory) {
				Person bean=new Person();
				bean.name="Tets";
				
				daoFactory.getDaoPerson().insert(bean);
				
				return TransactionResult.COMMIT;
			}
		});
//		
//		try {
//		//	boolean a=result.get();
//			//System.out.println("ccaaaa"+a);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

}
