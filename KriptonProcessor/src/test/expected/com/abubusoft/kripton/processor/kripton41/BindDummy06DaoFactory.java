package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy06DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy06DataSource
 * @see DaoBeanUpdateOK
 * @see BindDaoBeanUpdateOK
 * @see Bean01
 */
public interface BindDummy06DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanUpdateOK
   */
  BindDaoBeanUpdateOK getDaoBeanUpdateOK();
}
