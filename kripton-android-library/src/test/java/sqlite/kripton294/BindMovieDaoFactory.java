package sqlite.kripton294;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for MovieDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see MovieDataSource
 * @see DirectorDao
 * @see DirectorDaoImpl
 * @see Director
 * @see MovieDao
 * @see MovieDaoImpl
 * @see Movie
 */
public interface BindMovieDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DirectorDao.
   *
   * @return dao implementation
   */
  DirectorDaoImpl getDirectorDao();

  /**
   * Retrieve dao MovieDao.
   *
   * @return dao implementation
   */
  MovieDaoImpl getMovieDao();
}
