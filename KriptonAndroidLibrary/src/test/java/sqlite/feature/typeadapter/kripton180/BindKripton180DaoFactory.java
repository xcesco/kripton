package sqlite.feature.typeadapter.kripton180;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Kripton180DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180DataSource
 * @see EmployeeDao
 * @see EmployeeDaoImpl
 * @see Employee
 */
public interface BindKripton180DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao EmployeeDao
   */
  EmployeeDaoImpl getEmployeeDao();
}
