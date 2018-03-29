package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao PKDao
   */
  PKDaoImpl getPKDao();
}
