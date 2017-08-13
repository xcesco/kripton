package sqlite.feature.javadoc.select.raw;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SelectRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SelectRawPersonDataSource
 * @see SelectRawPersonDao
 * @see SelectRawPersonDaoImpl
 * @see sqlite.feature.javadoc.Person
 */
public interface BindSelectRawPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao SelectRawPersonDao
   */
  SelectRawPersonDaoImpl getSelectRawPersonDao();
}
