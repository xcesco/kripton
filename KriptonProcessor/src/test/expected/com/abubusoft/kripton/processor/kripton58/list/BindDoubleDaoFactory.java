package com.abubusoft.kripton.processor.kripton58.list;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DoubleDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DoubleDataSource
 * @see DoubleDao
 * @see BindDoubleDao
 * @see DoubleBean
 */
public interface BindDoubleDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DoubleDao
   */
  BindDoubleDao getDoubleDao();
}
