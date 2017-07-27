package sqlite.feature.dynamic.kripton121;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person1DataSource
 * @see Person1DAO
 * @see Person1DAOImpl
 * @see sqlite.feature.dynamic.Person
 */
public interface BindPerson1DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Person1DAO
   */
  Person1DAOImpl getPerson1DAO();
}
