package sqlite.feature.foreignkeyaction.err2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ArtistDao.class, AlbumDao.class}, fileName = "artist.db", schema=true)
public interface ArtistErr2DataSource {

}
