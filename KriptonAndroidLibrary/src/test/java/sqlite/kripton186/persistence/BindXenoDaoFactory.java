package sqlite.kripton186.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for XenoDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see Country
 */
public interface BindXenoDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PhoneDao
   */
  PhoneDaoImpl getPhoneDao();

  /**
   *
   * retrieve dao PrefixConfigDao
   */
  PrefixConfigDaoImpl getPrefixConfigDao();

  /**
   *
   * retrieve dao CountryDao
   */
  CountryDaoImpl getCountryDao();
}
