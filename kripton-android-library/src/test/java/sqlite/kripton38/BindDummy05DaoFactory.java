package sqlite.kripton38;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy05DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy05DataSource
 * @see DaoBean05
 * @see DaoBean05Impl
 * @see Bean05
 */
public interface BindDummy05DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean05
   */
  DaoBean05Impl getDaoBean05();
}
