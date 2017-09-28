package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Kripton180BeanInsertSelectDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180BeanInsertSelectDataSource
 * @see EmployeeBeanInsertSelectDao
 * @see EmployeeBeanInsertSelectDaoImpl
 * @see sqlite.feature.typeadapter.kripton180.Employee
 */
public interface BindKripton180BeanInsertSelectDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao EmployeeBeanInsertSelectDao
   */
  EmployeeBeanInsertSelectDaoImpl getEmployeeBeanInsertSelectDao();
}
