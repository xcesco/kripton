package com.abubusoft.kripton.processor.kripton58;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Bean04DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean04DataSource
 * @see Bean04Dao
 * @see BindBean04Dao
 * @see Bean04Bean
 */
public interface BindBean04DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Bean04Dao
   */
  BindBean04Dao getBean04Dao();
}
