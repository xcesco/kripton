package sqlite.feature.contentprovider.case1;

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
}
