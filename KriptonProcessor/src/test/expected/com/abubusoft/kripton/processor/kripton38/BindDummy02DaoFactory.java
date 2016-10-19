package com.abubusoft.kripton.processor.kripton38;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy02DataSource
 * @see DaoBean02
 * @see BindDaoBean02
 * @see Bean02
 */
public interface BindDummy02DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean02
   */
  BindDaoBean02 getDaoBean02();
}
