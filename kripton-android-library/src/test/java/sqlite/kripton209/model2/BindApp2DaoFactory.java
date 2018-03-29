package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for App2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see App2DataSource
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
public interface BindApp2DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DeviceDao
   */
  DeviceDaoImpl getDeviceDao();

  /**
   *
   * retrieve dao UserDao
   */
  UserDaoImpl getUserDao();

  /**
   *
   * retrieve dao UserDeviceDao
   */
  UserDeviceDaoImpl getUserDeviceDao();
}
