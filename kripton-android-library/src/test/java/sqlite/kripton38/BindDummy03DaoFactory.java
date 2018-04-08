package sqlite.kripton38;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy03DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy03DataSource
 * @see DaoBean03
 * @see DaoBean03Impl
 * @see Bean03
 */
public interface BindDummy03DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean03
   */
  DaoBean03Impl getDaoBean03();
}
