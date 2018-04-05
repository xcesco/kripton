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
package sqlite.feature.schema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.SQLiteSchemaVerifierHelper;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTestDatabase;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestSchemaUpdater.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaUpdater extends BaseAndroidTest {

	/**
	 * Test update with helper.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test
	public void testUpdateWithHelper() throws FileNotFoundException {
		final FileInputStream stream = new FileInputStream("schemas/school_schema_2.sql");

		SQLiteUpdateTestDatabase.builder(1, new FileInputStream("schemas/school_schema_1.sql"))

				.addVersionUpdateTask(2, new SQLiteUpdateTask() {

					@Override
					public void execute(SQLiteDatabase database, int previousVersion, int currentVersion) {
						SQLiteSchemaVerifierHelper.renameAllTablesWithPrefix(database, "tmp_");
						SQLiteSchemaVerifierHelper.executeSQL(database, stream);
						SQLiteSchemaVerifierHelper.dropTablesWithPrefix(database, "tmp_");

					}
				}).build().updateAndVerify(2, new FileInputStream("schemas/school_schema_2.sql"));

		log("finish");
	}

	/**
	 * Test update with file.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test
	public void testUpdateWithFile() throws FileNotFoundException {
		SQLiteUpdateTestDatabase.builder(1, new FileInputStream("schemas/school_schema_1.sql"))
				.addVersionUpdateTask(2, new FileInputStream("schemas/school_update_1_2.sql")).build()
				.updateAndVerify(2, new FileInputStream("schemas/school_schema_2.sql"));

		log("finish");

	}
}
