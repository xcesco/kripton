package sqlite.feature.typeadapter.kripton180.bean;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Kripton180BeanDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180BeanDataSource
 * @see EmployeeBeanDao
 * @see EmployeeBeanDaoImpl
 * @see Employee
 */
public interface BindKripton180BeanDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao EmployeeBeanDao.
   *
   * @return dao implementation
   */
  EmployeeBeanDaoImpl getEmployeeBeanDao();
}
