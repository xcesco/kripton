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
package sqlite.feature.livedata;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.robolectric.Robolectric;
import org.robolectric.util.ContentProviderController;

import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import base.BaseAndroidTest;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedata.data.PersonTable;
import sqlite.feature.livedata.persistence1.BindApp1ContentProvider;
import sqlite.feature.livedata.persistence1.BindApp1DataSource;

/**
 * The Class TestFeatureLiveDataRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestFeatureLiveDataRuntime extends BaseAndroidTest {
	
	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();


	/**
	 * Setup content provider.
	 */
	@Before
	public void setupContentProvider() {
		ProviderInfo info = new ProviderInfo();
		info.authority = BindApp1ContentProvider.AUTHORITY;

		ContentProviderController<BindApp1ContentProvider> controller = Robolectric.buildContentProvider(BindApp1ContentProvider.class);
		controller.create(info);
	}


	/**
	 * Insert rows.
	 *
	 * @param rows the rows
	 */
	private void insertRows(int rows) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PersonTable.COLUMN_NAME, "Tonj");
		contentValues.put(PersonTable.COLUMN_SURNAME, "Manero");

		for (int i = 0; i < rows; i++) {
			Uri uri = BindApp1ContentProvider.URI_PERSON_INSERT;//  Uri.parse(BindPersonContentProvider.URI + "/persons");
			Uri resultURI = getApplicationContext().getContentResolver().insert(uri, contentValues);
			assertTrue(Long.parseLong(resultURI.toString().replace(BindApp1ContentProvider.URI_PERSON_INSERT+"/",""))>0);
		}				
		
	}
	

	/**
	 * Test run JQL 1.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@Test
	public void testRunJQL1() throws InterruptedException {
		LiveData<List<Person>> liveData = BindApp1DataSource.getInstance().getDaoPerson1().selectAll();
		
		liveData.observeForever(new Observer<List<Person>>() {
			
			@Override
			public void onChanged(List<Person> t) {
				log(">> observed changes for person. Now thera are %s", t.size());
				
			}
		});
		
		// https://www.adityathakker.com/android-content-observer-react-on-content-change/
		MyObserver myObserver = new MyObserver(new Handler());		
		getApplicationContext().getContentResolver().registerContentObserver(BindApp1ContentProvider.URI_PERSON_SELECT_ALL, true, myObserver);
		
		
		insertRows(10);
		
		
		Thread.sleep(2000);
	}
	
	public class MyObserver extends ContentObserver {
		public MyObserver(Handler handler) {
	        	super(handler);
	    	}

		@Override
		public void onChange(boolean selfChange) {
			this.onChange(selfChange,null);
		}

		@Override
		public void onChange(boolean selfChange, Uri uri) {
			//Write your code here
			//Whatever is written here will be 
			//executed whenever a change is made
			log("--> observed changes for person. Now thera are %s", uri.toString());
		}

	}

}
