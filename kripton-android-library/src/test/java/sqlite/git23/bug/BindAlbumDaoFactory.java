package sqlite.git23.bug;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AlbumDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AlbumDataSource
 * @see AlbumDao
 * @see AlbumDaoImpl
 * @see Album
 */
public interface BindAlbumDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao AlbumDao.
   *
   * @return dao implementation
   */
  AlbumDaoImpl getAlbumDao();
}
