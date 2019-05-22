package sqlite.git20.immutable;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for MovieDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see MovieDataSource
 * @see MovieDao
 * @see MovieDaoImpl
 * @see Movie
 */
public interface BindMovieDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao MovieDao.
   *
   * @return dao implementation
   */
  MovieDaoImpl getMovieDao();
}
