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
package sqlite.feature.contentprovider.kripton35;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ContentProviderController;

import com.abubusoft.kripton.common.DateUtils;

import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import base.BaseAndroidTest;
import sqlite.feature.contentprovider.kripton35.entities.PersonTable;
import sqlite.feature.contentprovider.kripton35.persistence.BindPersonContentProvider;


/**
 * The Class TestContentProviderInsert.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestContentProviderInsert extends BaseAndroidTest {

	/**
	 * Setup content provider.
	 */
	@Before
	public void setupContentProvider() {
		ProviderInfo info = new ProviderInfo();
		info.authority = BindPersonContentProvider.AUTHORITY;

		ContentProviderController<BindPersonContentProvider> controller = Robolectric.buildContentProvider(BindPersonContentProvider.class);
		controller.create(info);
	}

	/**
	 * Test run insert.
	 */
	@Test
	public void testRunInsert() {
		insertRows(10);
		
	}

	/**
	 * Insert rows.
	 *
	 * @param rows the rows
	 */
	private void insertRows(int rows) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PersonTable.COLUMN_BIRTH_CITY, "New York");
		contentValues.put(PersonTable.COLUMN_BIRTH_DAY, DateUtils.write(new Date()));

		for (int i = 0; i < rows; i++) {
			Uri uri = BindPersonContentProvider.URI_PERSON_INSERT_BEAN;//  Uri.parse(BindPersonContentProvider.URI + "/persons");
			Uri resultURI = getApplicationContext().getContentResolver().insert(uri, contentValues);
			assertTrue(Long.parseLong(resultURI.toString().replace("content://sqlite.feature.contentprovider.kripton35/persons/",""))>0);
		}				
		
	}


}
