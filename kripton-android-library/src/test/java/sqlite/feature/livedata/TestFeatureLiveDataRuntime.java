/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.livedata;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.util.ContentProviderController;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import base.BaseAndroidTest;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedata.data.PersonTable;
import sqlite.feature.livedata.persistence1.BindApp1ContentProvider;
import sqlite.feature.livedata.persistence1.BindApp1DataSource;
//import sqlite.feature.livedata.persistence1.BindApp1ContentProvider;
//import sqlite.feature.livedata.persistence1.BindApp1DataSource;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TestFeatureLiveDataRuntime extends BaseAndroidTest {

	@Before
	public void setupContentProvider() {
		ProviderInfo info = new ProviderInfo();
		info.authority = BindApp1ContentProvider.AUTHORITY;

		ContentProviderController<BindApp1ContentProvider> controller = Robolectric.buildContentProvider(BindApp1ContentProvider.class);
		controller.create(info);
	}

	@Test
	public void testRunInsert() {
		insertRows(10);
		
	}

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
	

	LiveData<List<Person>> liveData;
	
	@Test
	public void testRunJQL1() throws InterruptedException {
		liveData = BindApp1DataSource.instance().getDaoPerson1().selectAll();
		
		liveData.observeForever(new Observer<List<Person>>() {
			
			@Override
			public void onChanged(List<Person> t) {
				log(">> observed changes for person. Now thera are %s", t.size());
				
			}
		});
		
		insertRows(10);	
		
		//Thread.sleep(2000);
	}

}