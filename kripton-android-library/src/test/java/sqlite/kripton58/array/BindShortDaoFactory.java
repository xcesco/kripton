package sqlite.kripton58.array;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

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
   * Retrieve dao ShortDao.
   *
   * @return dao implementation
   */
  ShortDaoImpl getShortDao();
}
