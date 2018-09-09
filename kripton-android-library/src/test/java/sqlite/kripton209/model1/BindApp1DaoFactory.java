package sqlite.kripton209.model1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for App1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see App1DataSource
 * @see DeviceDao
 * @see DeviceDaoImpl
 * @see Device
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 * @see UserDeviceDao
 * @see UserDeviceDaoImpl
 * @see UserDevice
 */
public interface BindApp1DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DeviceDao.
   *
   * @return dao implementation
   */
  DeviceDaoImpl getDeviceDao();

  /**
   * Retrieve dao UserDao.
   *
   * @return dao implementation
   */
  UserDaoImpl getUserDao();

  /**
   * Retrieve dao UserDeviceDao.
   *
   * @return dao implementation
   */
  UserDeviceDaoImpl getUserDeviceDao();
}
