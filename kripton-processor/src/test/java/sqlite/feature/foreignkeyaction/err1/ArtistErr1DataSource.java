package sqlite.feature.foreignkeyaction.err1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ArtistDao.class, AlbumDao.class}, fileName = "artist.db", schema=true)
public interface ArtistErr1DataSource {

}
