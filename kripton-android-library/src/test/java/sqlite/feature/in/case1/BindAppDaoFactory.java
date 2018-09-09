package sqlite.feature.in.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoCity
 * @see DaoCityImpl
 * @see City
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoCity.
   *
   * @return dao implementation
   */
  DaoCityImpl getDaoCity();
}
