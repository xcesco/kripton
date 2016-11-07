package com.abubusoft.kripton.processor.kripton58.array2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for FloatDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FloatDataSource
 * @see FloatDao
 * @see FloatDaoImpl
 * @see FloatBean
 */
public interface BindFloatDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao FloatDao
   */
  FloatDaoImpl getFloatDao();
}
