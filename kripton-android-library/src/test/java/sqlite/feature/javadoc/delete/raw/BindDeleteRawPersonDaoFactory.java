package sqlite.feature.javadoc.delete.raw;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DeleteRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DeleteRawPersonDataSource
 * @see DeleteRawPersonDao
 * @see DeleteRawPersonDaoImpl
 * @see Person
 */
public interface BindDeleteRawPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DeleteRawPersonDao.
   *
   * @return dao implementation
   */
  DeleteRawPersonDaoImpl getDeleteRawPersonDao();
}
