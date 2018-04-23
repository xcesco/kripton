package sqlite.kripton93;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean93DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean93DataSource
 * @see Bean93Dao
 * @see Bean93DaoImpl
 * @see Bean93
 */
public interface BindBean93DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean93Dao
   */
  Bean93DaoImpl getBean93Dao();
}
