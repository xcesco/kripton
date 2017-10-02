package sqlite.feature.contentprovider.base;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ArtistsDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ArtistsDataSource
 * @see ArtistDao
 * @see ArtistDaoImpl
 * @see Artist
 * @see AlbumDao
 * @see AlbumDaoImpl
 * @see Album
 */
public interface BindArtistsDaoFactory extends BindDaoFactory {
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
}
