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
package sqlite.git20.immutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.One;

import base.BaseAndroidTest;
import sqlite.git20.immutable.BindMovieDataSource.Transaction;

/**
 * The Class Test209Model1Runtime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestGit20ImmutableRuntime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 */
	@Test
	public void testRun() {
		One<List<Count>> r=new One<>();
		BindMovieDataSource dataSource=BindMovieDataSource.getInstance();
		
		dataSource.execute(new Transaction() {
			
			@Override
			public TransactionResult onExecute(BindMovieDaoFactory daoFactory) {
				List<Count> result = daoFactory.getMovieDao().findCountByTitle();
				
				r.value0=result;				
				return TransactionResult.COMMIT;
			}
		});
		
		assertTrue(r.value0!=null);
		assertTrue(r.value0.size()==1);
		assertEquals(r.value0.get(0).getTitle(),"title");
		assertEquals(r.value0.get(0).getCount(),1);
		
	}

}
