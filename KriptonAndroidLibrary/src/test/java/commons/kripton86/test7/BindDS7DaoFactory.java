package commons.kripton86.test7;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DS7DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DS7DataSource
 * @see Dao7
 * @see Dao7Impl
 * @see Bean7
 */
public interface BindDS7DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Dao7
   */
  Dao7Impl getDao7();
}
