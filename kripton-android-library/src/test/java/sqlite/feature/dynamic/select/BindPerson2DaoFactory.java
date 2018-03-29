package sqlite.feature.dynamic.select;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person2DataSource
 * @see PersonDAO2
 * @see PersonDAO2Impl
 * @see Person
 */
public interface BindPerson2DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonDAO2
   */
  PersonDAO2Impl getPersonDAO2();
}
