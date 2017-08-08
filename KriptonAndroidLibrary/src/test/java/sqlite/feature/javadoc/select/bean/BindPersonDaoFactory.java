package sqlite.feature.javadoc.select.bean;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonDataSource
 * @see PersonDao
 * @see PersonDaoImpl
 * @see sqlite.feature.javadoc.Person
 */
public interface BindPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao PersonDao
   */
  PersonDaoImpl getPersonDao();
}
