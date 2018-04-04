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
   *
   * retrieve dao ArtistDao
   */
  ArtistDaoImpl getArtistDao();

  /**
   *
   * retrieve dao AlbumDao
   */
  AlbumDaoImpl getAlbumDao();

  /**
   *
   * retrieve dao TrackDao
   */
  TrackDaoImpl getTrackDao();
}
