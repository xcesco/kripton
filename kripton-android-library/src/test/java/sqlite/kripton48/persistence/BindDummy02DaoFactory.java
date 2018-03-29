package sqlite.kripton48.persistence;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy02DataSource
 * @see DaoBean02
 * @see DaoBean02Impl
 * @see Bean02
 */
public interface BindDummy02DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean02
   */
  DaoBean02Impl getDaoBean02();
}
