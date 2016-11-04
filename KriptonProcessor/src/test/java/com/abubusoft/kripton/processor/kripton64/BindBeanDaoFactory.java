package com.abubusoft.kripton.processor.kripton64;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for BeanDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see BeanDataSource
 * @see BeanDao
 * @see BindBeanDao
 * @see Bean63
 */
public interface BindBeanDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao BeanDao
   */
  BindBeanDao getBeanDao();
}
