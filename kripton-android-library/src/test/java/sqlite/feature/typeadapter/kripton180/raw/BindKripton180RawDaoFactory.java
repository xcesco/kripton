package sqlite.feature.typeadapter.kripton180.raw;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Kripton180RawDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Kripton180RawDataSource
 * @see EmployeeRawDao
 * @see EmployeeRawDaoImpl
 * @see Employee
 */
public interface BindKripton180RawDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao EmployeeRawDao
   */
  EmployeeRawDaoImpl getEmployeeRawDao();
}
