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
package sqlite.feature.pkstring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.pkstring.relations.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TestFeatureSQLTypeAdapterRuntime.
 *
 * @author xcesco
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestPkStringRelationRuntime extends BaseAndroidTest {

	int ITERACTIONS = 10;

	/**
	 * Test select.
	 */
	@Test
	public void testSelect() {
		BindAppDataSource ds = BindAppDataSource.getInstance();

		ds.execute(new BindAppDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				for (int i = 0; i < ITERACTIONS; i++) {
					Album album = new Album();
					album.id = "album" + i;
					album.name = "album" + i;
					daoFactory.getDaoAlbum().insert(album);

					for (int j = 0; j < i; j++) {
						Song song = new Song();
						song.albumId = album.id;
						song.name = "song" +i+"_" +j;
						daoFactory.getDaoSong().insert(song);
					}

				}
				return TransactionResult.COMMIT;
			}

		});

		ds.execute(new BindAppDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				List<Album> result = daoFactory.getDaoAlbum().selectAlbums();

				for (int i = 0; i < ITERACTIONS; i++) {
					assertThat(result.get(i).getSongs().size(), is(i));
				}

				return TransactionResult.COMMIT;
			}
		});

	}

}
