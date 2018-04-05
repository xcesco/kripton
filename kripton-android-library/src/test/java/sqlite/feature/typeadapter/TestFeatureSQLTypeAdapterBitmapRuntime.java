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
package sqlite.feature.typeadapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import base.BaseAndroidTest;
import sqlite.feature.typeadapter.bitmap.BindAppDaoFactory;
import sqlite.feature.typeadapter.bitmap.BindAppDataSource;
import sqlite.feature.typeadapter.bitmap.BindAppDataSource.Transaction;
import sqlite.feature.typeadapter.bitmap.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class TestFeatureSQLTypeAdapterBitmapRuntime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestFeatureSQLTypeAdapterBitmapRuntime extends BaseAndroidTest {

	/**
	 * Test select.
	 */
	@Test
	public void testSelect() {

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		
		final Bitmap bitmap = BitmapFactory.decodeFile("/src/test/resources/imgs/img.png", options);

		BindAppDataSource ds = BindAppDataSource.instance();

		ds.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				Person bean = new Person();
				bean.image = bitmap;
				daoFactory.getDaoPerson().insert(bean);

				List<Person> list = daoFactory.getDaoPerson().list();
				
				for (Person person:list) {
					// use					
					saveImage((new File("src/test/resources/imgs/img0.png")).getAbsolutePath(), person.image);
				}
				return TransactionResult.COMMIT;
			}
		});

	}

	/**
	 * Save image.
	 *
	 * @param fileName the file name
	 * @param bmp the bmp
	 */
	private void saveImage(String fileName, Bitmap bmp) {
		FileOutputStream out = null;
		try {
		    out = new FileOutputStream(fileName);
		    bmp.compress(Bitmap.CompressFormat.PNG, 90, out); // bmp is your Bitmap instance
		    // PNG is a lossless format, the compression factor (100) is ignored
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (out != null) {
		            out.close();
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}

}
