package sqlite.feature.typeadapter;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for ContactDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ContactDataSource
 * @see ContactDao
 * @see ContactDaoImpl
 * @see Contact
 */
public interface BindContactDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao ContactDao.
   *
   * @return dao implementation
   */
  ContactDaoImpl getContactDao();
}
