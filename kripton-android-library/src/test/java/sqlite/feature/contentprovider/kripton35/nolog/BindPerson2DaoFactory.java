package sqlite.feature.contentprovider.kripton35.nolog;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person2DataSource
 * @see Person2DAO
 * @see Person2DAOImpl
 * @see Person
 * @see City2DAO
 * @see City2DAOImpl
 * @see City
 */
public interface BindPerson2DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Person2DAO
   */
  Person2DAOImpl getPerson2DAO();

  /**
   *
   * retrieve dao City2DAO
   */
  City2DAOImpl getCity2DAO();
}
