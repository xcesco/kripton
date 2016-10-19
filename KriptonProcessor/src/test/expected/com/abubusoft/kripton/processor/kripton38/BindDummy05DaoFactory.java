package com.abubusoft.kripton.processor.kripton38;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy05DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy05DataSource
 * @see DaoBean05
 * @see BindDaoBean05
 * @see Bean05
 */
public interface BindDummy05DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean05
   */
  BindDaoBean05 getDaoBean05();
}
