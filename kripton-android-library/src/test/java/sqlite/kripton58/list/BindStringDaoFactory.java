package sqlite.kripton58.list;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for StringDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see StringDataSource
 * @see StringDao
 * @see StringDaoImpl
 * @see StringBean
 */
public interface BindStringDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao StringDao
   */
  StringDaoImpl getStringDao();
}
