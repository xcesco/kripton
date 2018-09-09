package sqlite.kotlin.hierarchy;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonDataSource
 * @see DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoPerson.
   *
   * @return dao implementation
   */
  DaoPersonImpl getDaoPerson();
}
