package sqlite.feature.jql.err2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Err2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Err2DataSource
 * @see DaoErr2
 * @see DaoErr2Impl
 * @see BeanErr2
 */
public interface BindErr2DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoErr2
   */
  DaoErr2Impl getDaoErr2();
}
