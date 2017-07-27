package sqlite.feature.contentprovider.kripton35.persistence;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonDataSource
 * @see PersonDAO
 * @see PersonDAOImpl
 * @see sqlite.feature.contentprovider.kripton35.entities.Person
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonDAO
   */
  PersonDAOImpl getPersonDAO();
}
