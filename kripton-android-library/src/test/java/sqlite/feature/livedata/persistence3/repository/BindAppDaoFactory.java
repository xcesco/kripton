package sqlite.feature.livedata.persistence3.repository;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import sqlite.feature.livedata.persistence3.dao.DaoPersonImpl;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see sqlite.feature.livedata.persistence3.dao.DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoPerson
   */
  DaoPersonImpl getDaoPerson();
}
