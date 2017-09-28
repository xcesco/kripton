package sqlite.feature.foreignkeyaction.err2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ArtistDao.class, AlbumDao.class}, fileName = "artist.db", generateSchema=true)
public interface ArtistErr2DataSource {

}
