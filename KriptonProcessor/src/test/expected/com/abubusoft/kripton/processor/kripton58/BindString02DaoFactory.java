package com.abubusoft.kripton.processor.kripton58;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for String02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see String02DataSource
 * @see String02Dao
 * @see BindString02Dao
 * @see String02Bean
 */
public interface BindString02DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao String02Dao
   */
  BindString02Dao getString02Dao();
}
