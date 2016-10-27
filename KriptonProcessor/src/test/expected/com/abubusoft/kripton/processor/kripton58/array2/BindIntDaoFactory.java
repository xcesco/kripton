package com.abubusoft.kripton.processor.kripton58.array2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for IntDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntDataSource
 * @see IntDao
 * @see BindIntDao
 * @see IntBean
 */
public interface BindIntDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao IntDao
   */
  BindIntDao getIntDao();
}
