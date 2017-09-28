package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Kripton180RawInsertSelectDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180RawInsertSelectDataSource
 * @see EmployeeRawInsertSelectDao
 * @see EmployeeRawInsertSelectDaoImpl
 * @see sqlite.feature.typeadapter.kripton180.Employee
 */
public interface BindKripton180RawInsertSelectDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao EmployeeRawInsertSelectDao
   */
  EmployeeRawInsertSelectDaoImpl getEmployeeRawInsertSelectDao();
}
