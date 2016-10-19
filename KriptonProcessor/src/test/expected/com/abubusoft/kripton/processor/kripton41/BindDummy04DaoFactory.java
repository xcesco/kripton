package com.abubusoft.kripton.processor.kripton41;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy04DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy04DataSource
 * @see DaoBeanInsertOK
 * @see BindDaoBeanInsertOK
 * @see Bean01
 */
public interface BindDummy04DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBeanInsertOK
   */
  BindDaoBeanInsertOK getDaoBeanInsertOK();
}
