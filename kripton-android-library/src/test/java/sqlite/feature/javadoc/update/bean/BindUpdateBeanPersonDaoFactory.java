package sqlite.feature.javadoc.update.bean;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for UpdateBeanPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see UpdateBeanPersonDataSource
 * @see UpdateBeanPersonDao
 * @see UpdateBeanPersonDaoImpl
 * @see Person
 */
public interface BindUpdateBeanPersonDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao UpdateBeanPersonDao
   */
  UpdateBeanPersonDaoImpl getUpdateBeanPersonDao();
}
