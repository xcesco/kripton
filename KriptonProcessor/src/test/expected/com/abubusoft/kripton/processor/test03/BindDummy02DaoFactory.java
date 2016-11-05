package com.abubusoft.kripton.processor.test03;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy02DataSource
 * @see com.abubusoft.kripton.processor.test03.DaoBean02
 * @see com.abubusoft.kripton.processor.test03.DaoBean02$Impl
 * @see Bean01
 */
public interface BindDummy02DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean02
   */
  com.abubusoft.kripton.processor.test03.DaoBean02 getDaoBean02();
}
