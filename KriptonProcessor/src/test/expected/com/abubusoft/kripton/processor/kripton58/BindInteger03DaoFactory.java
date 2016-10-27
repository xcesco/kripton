package com.abubusoft.kripton.processor.kripton58;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import com.abubusoft.kripton.processor.kripton58.list.Integer03Bean;
import com.abubusoft.kripton.processor.kripton58.list.Integer03Dao;
import com.abubusoft.kripton.processor.kripton58.list.Integer03DataSource;

/**
 * <p>
 * Represents dao factory interface for Integer03DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Integer03DataSource
 * @see Integer03Dao
 * @see BindInteger03Dao
 * @see Integer03Bean
 */
public interface BindInteger03DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao Integer03Dao
   */
  BindInteger03Dao getInteger03Dao();
}
