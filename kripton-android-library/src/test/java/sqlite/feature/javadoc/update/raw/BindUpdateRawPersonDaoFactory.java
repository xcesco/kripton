package sqlite.feature.javadoc.update.raw;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for UpdateRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see UpdateRawPersonDataSource
 * @see UpdateRawPersonDao
 * @see UpdateRawPersonDaoImpl
 * @see Person
 */
public interface BindUpdateRawPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao UpdateRawPersonDao
   */
  UpdateRawPersonDaoImpl getUpdateRawPersonDao();
}
