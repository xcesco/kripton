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
 * @see Employee
 */
public interface BindKripton180RawInsertSelectDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao EmployeeRawInsertSelectDao.
   *
   * @return dao implementation
   */
  EmployeeRawInsertSelectDaoImpl getEmployeeRawInsertSelectDao();
}
