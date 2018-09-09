package sqlite.feature.javadoc.select.bean;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for SelectBeanPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SelectBeanPersonDataSource
 * @see SelectBeanPersonDao
 * @see SelectBeanPersonDaoImpl
 * @see Person
 */
public interface BindSelectBeanPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao SelectBeanPersonDao.
   *
   * @return dao implementation
   */
  SelectBeanPersonDaoImpl getSelectBeanPersonDao();
}
