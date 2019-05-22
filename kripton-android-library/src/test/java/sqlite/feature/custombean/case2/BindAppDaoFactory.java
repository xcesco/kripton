package sqlite.feature.custombean.case2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see BookDao
 * @see BookDaoImpl
 * @see Book
 * @see LoanDao
 * @see LoanDaoImpl
 * @see Loan
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao BookDao.
   *
   * @return dao implementation
   */
  BookDaoImpl getBookDao();

  /**
   * Retrieve dao LoanDao.
   *
   * @return dao implementation
   */
  LoanDaoImpl getLoanDao();

  /**
   * Retrieve dao UserDao.
   *
   * @return dao implementation
   */
  UserDaoImpl getUserDao();
}
