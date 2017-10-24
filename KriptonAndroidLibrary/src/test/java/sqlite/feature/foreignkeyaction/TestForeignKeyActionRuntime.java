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
package sqlite.feature.foreignkeyaction;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.foreignkeyaction.BindArtistDataSource.Transaction;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TestForeignKeyActionRuntime extends BaseAndroidTest {

	@Test
	public void testRun()  {
		BindArtistDataSource dataSource=BindArtistDataSource.instance();
		
		dataSource.execute(new Transaction() {
			
			@Override
			public TransactionResult onExecute(BindArtistDaoFactory daoFactory) {
				ArtistDaoImpl daoArtist = daoFactory.getArtistDao();
				AlbumDaoImpl daoAlbum = daoFactory.getAlbumDao();
				
				Artist bean=new Artist();
				bean.name="Tonj Manero";
				
				daoArtist.insert(bean);
				
				Album album=new Album();
				album.name="First album";
				album.artistId=bean.id;
				
				daoAlbum.insert(album);
				Assert.assertTrue(daoArtist.selectAll().size()==1);
				
				daoArtist.deleteById(bean.id);
				Assert.assertTrue(daoArtist.selectAll().size()==0);
				
				return TransactionResult.COMMIT;
			}
			
			@Override
			public void onError(Throwable e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
