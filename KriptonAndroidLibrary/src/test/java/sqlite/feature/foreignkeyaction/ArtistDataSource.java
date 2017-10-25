package sqlite.feature.foreignkeyaction;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet={ArtistDao.class, AlbumDao.class, TrackDao.class}, fileName = "artist.db", schema=true)
public interface ArtistDataSource {

}
