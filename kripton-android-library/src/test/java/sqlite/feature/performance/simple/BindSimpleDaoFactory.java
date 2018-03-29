package sqlite.feature.performance.simple;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SimpleDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SimpleDataSource
 * @see SimpleAddressDao
 * @see SimpleAddressDaoImpl
 * @see SimpleAddressItem
 */
public interface BindSimpleDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao SimpleAddressDao
   */
  SimpleAddressDaoImpl getSimpleAddressDao();
}
