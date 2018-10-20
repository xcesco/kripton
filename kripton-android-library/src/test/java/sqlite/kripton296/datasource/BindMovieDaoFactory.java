package sqlite.kripton296.datasource;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import java.util.concurrent.Future;

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

  /**
   * Executes method {@link clearDb}
   *
   * @return <code>true</code> if transaction was done succefull.
   */
  boolean clearDb();

  /**
   * Executes method {@link clearDb} in async mode
   *
   * @return a <code>Future</code> with true if transaction was done succefull.
   */
  Future<Boolean> clearDbAsync();
}
