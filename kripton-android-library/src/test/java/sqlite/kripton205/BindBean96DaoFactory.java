package sqlite.kripton205;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean96DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean96DataSource
 * @see Bean205Dao
 * @see Bean205DaoImpl
 * @see Bean205
 */
public interface BindBean96DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean205Dao
   */
  Bean205DaoImpl getBean205Dao();
}
