package bind.kripton81exceptioncoverage;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PKDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PKDataSource
 * @see PKDao
 * @see PKDaoImpl
 * @see PKBean
 */
public interface BindPKDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PKDao.
   *
   * @return dao implementation
   */
  PKDaoImpl getPKDao();
}
