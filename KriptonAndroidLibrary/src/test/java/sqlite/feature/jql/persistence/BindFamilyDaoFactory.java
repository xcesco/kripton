package sqlite.feature.jql.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for FamilyDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FamilyDataSource
 * @see DaoChild
 * @see DaoChildImpl
 * @see Child
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindFamilyDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoChild
   */
  DaoChildImpl getDaoChild();

  /**
   *
   * retrieve dao DaoPerson
   */
  DaoPersonImpl getDaoPerson();
}
