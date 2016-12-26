package commons.benchmark.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for BenchmarkDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see BenchmarkDataSource
 * @see UserDao
 * @see UserDaoImpl
 * @see commons.benchmark.model.User
 */
public interface BindBenchmarkDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao UserDao
   */
  UserDaoImpl getUserDao();
}
