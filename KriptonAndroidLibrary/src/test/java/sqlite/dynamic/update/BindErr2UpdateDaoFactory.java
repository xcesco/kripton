package sqlite.dynamic.update;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Err2UpdateDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Err2UpdateDataSource
 * @see Err2UpdateDAO
 * @see Err2UpdateDAOImpl
 * @see sqlite.dynamic.Person
 */
public interface BindErr2UpdateDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Err2UpdateDAO
   */
  Err2UpdateDAOImpl getErr2UpdateDAO();
}
