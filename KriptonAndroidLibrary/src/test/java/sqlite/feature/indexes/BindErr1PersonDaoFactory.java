package sqlite.feature.indexes;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Err1PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Err1PersonDataSource
 * @see Err1PersonDAO
 * @see Err1PersonDAOImpl
 * @see Err1Person
 */
public interface BindErr1PersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Err1PersonDAO
   */
  Err1PersonDAOImpl getErr1PersonDAO();
}
