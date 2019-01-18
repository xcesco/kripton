package sqlite.feature.livedata.paginated.case2;

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
 * @see DaoGroup
 * @see DaoGroupImpl
 * @see Group
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoPerson.
   *
   * @return dao implementation
   */
  DaoPersonImpl getDaoPerson();

  /**
   * Retrieve dao DaoGroup.
   *
   * @return dao implementation
   */
  DaoGroupImpl getDaoGroup();
}
