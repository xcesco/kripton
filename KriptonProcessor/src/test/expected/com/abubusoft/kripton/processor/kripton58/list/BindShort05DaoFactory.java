package com.abubusoft.kripton.processor.kripton58.list;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Short05DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Short05DataSource
 * @see Short05Dao
 * @see BindShort05Dao
 * @see Short05Bean
 */
public interface BindShort05DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Short05Dao
   */
  BindShort05Dao getShort05Dao();
}
