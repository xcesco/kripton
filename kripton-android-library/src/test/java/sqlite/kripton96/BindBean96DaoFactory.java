package sqlite.kripton96;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean96DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean96DataSource
 * @see Bean96Dao
 * @see Bean96DaoImpl
 * @see Bean96
 */
public interface BindBean96DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao Bean96Dao.
   *
   * @return dao implementation
   */
  Bean96DaoImpl getBean96Dao();
}
