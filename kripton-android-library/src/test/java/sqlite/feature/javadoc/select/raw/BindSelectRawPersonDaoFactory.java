package sqlite.feature.javadoc.select.raw;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SelectRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SelectRawPersonDataSource
 * @see SelectRawPersonDao
 * @see SelectRawPersonDaoImpl
 * @see Person
 */
public interface BindSelectRawPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao SelectRawPersonDao
   */
  SelectRawPersonDaoImpl getSelectRawPersonDao();
}
