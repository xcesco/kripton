package sqlite.feat.dynamic.update;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonUpdateDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonUpdateDataSource
 * @see PersonUpdateDAO
 * @see PersonUpdateDAOImpl
 * @see sqlite.feat.dynamic.Person
 */
public interface BindPersonUpdateDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonUpdateDAO
   */
  PersonUpdateDAOImpl getPersonUpdateDAO();
}
