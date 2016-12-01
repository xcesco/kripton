package kripton64;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

import sqlite.kripton64.Bean;
import sqlite.kripton64.BeanDao;
import sqlite.kripton64.BeanDataSource;

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
   *
   * retrieve dao BeanDao
   */
  BeanDaoImpl getBeanDao();
}
