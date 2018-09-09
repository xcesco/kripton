package sqlite.feature.globaltypeadapters.model1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;
import sqlite.feature.globaltypeadapters.DaoPersonImpl;

/**
 * <p>
 * Represents dao factory interface for Person1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person1DataSource
 * @see sqlite.feature.globaltypeadapters.DaoPerson
 * @see DaoPersonImpl
 * @see Person
 */
public interface BindPerson1DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoPerson.
   *
   * @return dao implementation
   */
  DaoPersonImpl getDaoPerson();
}
