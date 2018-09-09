package sqlite.feature.javadoc.delete.bean;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for DeleteBeanPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see DeleteBeanPersonDataSource
 * @see DeleteBeanPersonDao
 * @see DeleteBeanPersonDaoImpl
 * @see Person
 */
public interface BindDeleteBeanPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DeleteBeanPersonDao.
   *
   * @return dao implementation
   */
  DeleteBeanPersonDaoImpl getDeleteBeanPersonDao();
}
