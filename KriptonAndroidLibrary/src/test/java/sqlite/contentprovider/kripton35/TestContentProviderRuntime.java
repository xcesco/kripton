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
package sqlite.contentprovider.kripton35;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowContentResolver;
import org.robolectric.util.ContentProviderController;

import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import base.BaseAndroidTest;
import sqlite.contentprovider.kripton35.entities.Person;
import sqlite.contentprovider.kripton35.entities.PersonTable;
import sqlite.contentprovider.kripton35.persistence.BindPersonContentProvider;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestContentProviderRuntime extends BaseAndroidTest {

	@Before
	public void setupContentProvider() {
//		BindPersonContentProvider mProvider = new BindPersonContentProvider();
//		ShadowContentResolver.registerProvider(BindPersonContentProvider.AUTHORITY, mProvider);
//		
		ProviderInfo info=new ProviderInfo();
		info.authority=BindPersonContentProvider.AUTHORITY;
		
		ContentProviderController<BindPersonContentProvider> controller=Robolectric.buildContentProvider(BindPersonContentProvider.class);
		controller.create(info);
	}

	@Test
	public void testRunInsert() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(PersonTable.COLUMN_BIRTH_CITY, "New York");
		contentValues.put(PersonTable.COLUMN_BIRTH_DAY, DateUtils.write(new Date()));

		for (int i = 0; i < 10; i++) {
			Uri uri = Uri.parse(BindPersonContentProvider.URI + "/" + BindPersonContentProvider.PATH_PERSON_1);
			Uri resultURI = getApplicationContext().getContentResolver().insert(uri, contentValues);
			assertTrue(resultURI.toString().equals("content://sqlite.contentprovider.kripton35/persons/" + (i + 1)));
		}
		
		contentValues.put(PersonTable.COLUMN_PARENT_ID, 1);
		for (int i = 10; i < 20; i++) {
			Uri uri = Uri.parse(BindPersonContentProvider.URI + "/persons/1/children");
			Uri resultURI = getApplicationContext().getContentResolver().insert(uri, contentValues);
			log(resultURI.toString());
			assertTrue(resultURI.toString().equals("content://sqlite.contentprovider.kripton35/persons/1/children/" + (i + 1)));
		}
	}

	/**
	 * <p>
	 * This test fails because we try to insert ID column that is not specified
	 * between column that can be inserted with this operation.
	 */
	@Test(expected = KriptonRuntimeException.class)
	public void testFail1Insert() {
		ContentValues contentValues = new ContentValues();
				
		contentValues.put(PersonTable.COLUMN_ID, 25);
		contentValues.put(PersonTable.COLUMN_BIRTH_CITY, "New York");
		contentValues.put(PersonTable.COLUMN_BIRTH_DAY, DateUtils.write(new Date()));

		Uri uri = Uri.parse(BindPersonContentProvider.URI + "/" + BindPersonContentProvider.PATH_PERSON_1);
		getApplicationContext().getContentResolver().insert(uri, contentValues);
	}
	
	/**
	 * <p>
	 * This test fails because we try to insert PARENT_ID column that is excluded in @BindSqlInsert
	 */
	@Test(expected = KriptonRuntimeException.class)
	public void testFail2Insert() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(PersonTable.COLUMN_PARENT_ID, 1);		
		contentValues.put(PersonTable.COLUMN_BIRTH_CITY, "New York");
		contentValues.put(PersonTable.COLUMN_BIRTH_DAY, DateUtils.write(new Date()));

		Uri uri = Uri.parse(BindPersonContentProvider.URI + "/" + BindPersonContentProvider.PATH_PERSON_1);
		getApplicationContext().getContentResolver().insert(uri, contentValues);
	}

}
