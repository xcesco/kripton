package sqlite.feature.join.model;

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
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 * @see LoanDao
 * @see LoanDaoImpl
 * @see Loan
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao BookDao.
   *
   * @return dao implementation
   */
  BookDaoImpl getBookDao();

  /**
   * Retrieve dao UserDao.
   *
   * @return dao implementation
   */
  UserDaoImpl getUserDao();

  /**
   * Retrieve dao LoanDao.
   *
   * @return dao implementation
   */
  LoanDaoImpl getLoanDao();
}
