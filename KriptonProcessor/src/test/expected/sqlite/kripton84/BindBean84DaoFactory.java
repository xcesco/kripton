package sqlite.kripton84;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean84DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean84DataSource
 * @see Bean84Dao
 * @see Bean84DaoImpl
 * @see Bean84
 */
public interface BindBean84DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean84Dao
   */
  Bean84DaoImpl getBean84Dao();
}
