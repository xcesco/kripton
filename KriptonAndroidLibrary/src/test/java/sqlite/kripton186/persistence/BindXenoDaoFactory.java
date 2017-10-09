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
 * @see sqlite.kripton186.model.PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see sqlite.kripton186.model.PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see sqlite.kripton186.model.Country
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
