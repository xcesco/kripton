package com.abubusoft.kripton.processor.kripton58.list;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for IntegerDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see IntegerDataSource
 * @see IntegerDao
 * @see IntegerDaoImpl
 * @see IntegerBean
 */
public interface BindIntegerDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao IntegerDao
   */
  IntegerDaoImpl getIntegerDao();
}
