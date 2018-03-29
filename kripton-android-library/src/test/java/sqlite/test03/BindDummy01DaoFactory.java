package sqlite.test03;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy01DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy01DataSource
 * @see DaoBean01
 * @see DaoBean01Impl
 * @see Bean01
 */
public interface BindDummy01DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean01
   */
  DaoBean01Impl getDaoBean01();
}
