package sqlite.feature.foreignkeyaction;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ArtistDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ArtistDataSource
 * @see ArtistDao
 * @see ArtistDaoImpl
 * @see Artist
 * @see AlbumDao
 * @see AlbumDaoImpl
 * @see Album
 * @see TrackDao
 * @see TrackDaoImpl
 * @see Track
 */
public interface BindArtistDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao ArtistDao.
   *
   * @return dao implementation
   */
  ArtistDaoImpl getArtistDao();

  /**
   * Retrieve dao AlbumDao.
   *
   * @return dao implementation
   */
  AlbumDaoImpl getAlbumDao();

  /**
   * Retrieve dao TrackDao.
   *
   * @return dao implementation
   */
  TrackDaoImpl getTrackDao();
}
