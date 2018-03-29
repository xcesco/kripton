package sqlite.feature.typeadapter;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao ContactDao
   */
  ContactDaoImpl getContactDao();
}
