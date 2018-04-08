package sqlite.kripton64;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean64DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean64DataSource
 * @see Bean64Dao
 * @see Bean64DaoImpl
 * @see Bean64
 */
public interface BindBean64DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean64Dao
   */
  Bean64DaoImpl getBean64Dao();
}
