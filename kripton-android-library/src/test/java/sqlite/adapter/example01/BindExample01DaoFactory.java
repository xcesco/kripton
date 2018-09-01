package sqlite.adapter.example01;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Example01DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Example01DataSource
 * @see PersonDAO
 * @see PersonDAOImpl
 * @see Person
 */
public interface BindExample01DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonDAO
   */
  PersonDAOImpl getPersonDAO();
}
