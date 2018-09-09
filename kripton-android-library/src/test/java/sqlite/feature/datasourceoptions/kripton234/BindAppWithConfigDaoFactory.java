package sqlite.feature.datasourceoptions.kripton234;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppWithConfigDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppWithConfigDataSource
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindAppWithConfigDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoPerson.
   *
   * @return dao implementation
   */
  DaoPersonImpl getDaoPerson();
}
