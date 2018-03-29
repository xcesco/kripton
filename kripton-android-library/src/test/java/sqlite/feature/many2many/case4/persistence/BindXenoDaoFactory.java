package sqlite.feature.many2many.case4.persistence;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
 * @see Person2PhoneDao
 * @see Person2PhoneDaoImpl
 * @see PersonPhoneNumber
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
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

  /**
   *
   * retrieve dao Person2PhoneDao
   */
  Person2PhoneDaoImpl getPerson2PhoneDao();

  /**
   *
   * retrieve dao PersonDao
   */
  PersonDaoImpl getPersonDao();
}
