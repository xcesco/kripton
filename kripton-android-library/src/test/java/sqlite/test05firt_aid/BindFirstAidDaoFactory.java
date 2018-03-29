package sqlite.test05firt_aid;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for FirstAidDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FirstAidDataSource
 * @see FirstAidDao
 * @see FirstAidDaoImpl
 * @see FirstAid
 */
public interface BindFirstAidDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao FirstAidDao
   */
  FirstAidDaoImpl getFirstAidDao();
}
