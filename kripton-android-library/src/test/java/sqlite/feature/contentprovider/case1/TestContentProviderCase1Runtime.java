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
package sqlite.feature.contentprovider.case1;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ContentProviderController;

import com.abubusoft.kripton.android.executor.KriptonInstantTaskExecutorRule;
import com.abubusoft.kripton.common.DateUtils;

import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import base.BaseAndroidTest;

/**
 * The Class TestContentProviderRuntime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestContentProviderCase1Runtime extends BaseAndroidTest {
	
	@Rule
	public TestRule rule = new KriptonInstantTaskExecutorRule();

	/**
	 * Setup content provider.
	 */
	@Before
	public void setupContentProvider() {
		ProviderInfo info = new ProviderInfo();
		info.authority = BindArtistsContentProvider.AUTHORITY;

		ContentProviderController<BindArtistsContentProvider> controller = Robolectric.buildContentProvider(BindArtistsContentProvider.class);
		controller.create(info);
	}

	/**
	 * Insert rows.
	 *
	 * @param rows the rows
	 */
	private void insertRows(int rows) {
		ContentValues contentValues = new ContentValues();
						

		for (int i = 0; i < rows; i++) {
			Uri uri = BindArtistsContentProvider.URI_ARTIST_INSERT;
			contentValues.put(ArtistTable.COLUMN_NAME, "Tonj Manero"+i);
			Uri resultURI = getApplicationContext().getContentResolver().insert(uri, contentValues);
			assertTrue(Long.parseLong(resultURI.toString().replace(BindArtistsContentProvider.URI_ARTIST_INSERT+"/", "")) > 0);
		}
	}

	/**
	 * Test run select after insert.
	 */
	@Test
	public void testRunSelectAfterInsert() {
		BindArtistsDataSource.getInstance().getArtistDao().selectAll().observeForever(artists -> {			
			log("**** There are %s artits in database!", artists.size());							
		});
		
		int rows = 10;		
		insertRows(rows);
	}


}
