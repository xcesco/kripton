package commons.kripton86.test5;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DS5DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DS5DataSource
 * @see Dao5
 * @see Dao5Impl
 * @see Bean5
 */
public interface BindDS5DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Dao5
   */
  Dao5Impl getDao5();
}
