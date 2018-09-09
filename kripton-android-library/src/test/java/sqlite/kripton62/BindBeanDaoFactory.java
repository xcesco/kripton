package sqlite.kripton62;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for BeanDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see BeanDataSource
 * @see BeanDao
 * @see BeanDaoImpl
 * @see Bean
 */
public interface BindBeanDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao BeanDao.
   *
   * @return dao implementation
   */
  BeanDaoImpl getBeanDao();
}
