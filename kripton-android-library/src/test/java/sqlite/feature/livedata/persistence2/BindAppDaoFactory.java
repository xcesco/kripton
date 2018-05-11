package sqlite.feature.livedata.persistence2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see Person
 * @see DaoCity
 * @see DaoCityImpl
 * @see City
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoPerson
   */
  DaoPersonImpl getDaoPerson();

  /**
   *
   * retrieve dao DaoCity
   */
  DaoCityImpl getDaoCity();
}
