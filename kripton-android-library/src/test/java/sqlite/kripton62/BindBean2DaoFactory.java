package sqlite.kripton62;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean2DataSource
 * @see BeanDao2
 * @see BeanDao2Impl
 * @see Bean2
 */
public interface BindBean2DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao BeanDao2.
   *
   * @return dao implementation
   */
  BeanDao2Impl getBeanDao2();
}
