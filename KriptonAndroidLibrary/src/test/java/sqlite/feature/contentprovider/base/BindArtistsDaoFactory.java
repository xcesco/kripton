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
 */
public interface BindArtistsDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao ArtistDao
   */
  ArtistDaoImpl getArtistDao();
}
