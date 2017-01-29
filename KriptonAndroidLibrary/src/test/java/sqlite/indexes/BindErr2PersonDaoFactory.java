package sqlite.indexes;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Err2PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Err2PersonDataSource
 * @see Err2PersonDAO
 * @see Err2PersonDAOImpl
 * @see Err2Person
 */
public interface BindErr2PersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Err2PersonDAO
   */
  Err2PersonDAOImpl getErr2PersonDAO();
}
