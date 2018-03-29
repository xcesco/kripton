package sqlite.kripton58.array2;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ShortDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ShortDataSource
 * @see ShortDao
 * @see ShortDaoImpl
 * @see ShortBean
 */
public interface BindShortDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao ShortDao
   */
  ShortDaoImpl getShortDao();
}
