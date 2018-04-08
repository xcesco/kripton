package sqlite.stack44330452;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for PetUserDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PetUserDataSource
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 * @see PetDao
 * @see PetDaoImpl
 * @see Pet
 */
public interface BindPetUserDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao UserDao
   */
  UserDaoImpl getUserDao();

  /**
   *
   * retrieve dao PetDao
   */
  PetDaoImpl getPetDao();
}
