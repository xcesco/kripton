package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy08DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy08DataSource
 * @see DaoBeanDeleteOK
 * @see DaoBeanDeleteOKImpl
 * @see Bean01
 */
public interface BindDummy08DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanDeleteOK
   */
  DaoBeanDeleteOKImpl getDaoBeanDeleteOK();
}
