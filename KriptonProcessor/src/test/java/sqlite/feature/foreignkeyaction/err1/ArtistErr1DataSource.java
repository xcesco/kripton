package sqlite.feature.foreignkeyaction.err1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ArtistDao.class, AlbumDao.class}, fileName = "artist.db", generateSchema=true)
public interface ArtistErr1DataSource {

}
