package sqlite.feature.javadoc.select.bean;

import com.abubusoft.kripton.android.orm.BindDaoFactory;

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
   *
   * retrieve dao SelectBeanPersonDao
   */
  SelectBeanPersonDaoImpl getSelectBeanPersonDao();
}
