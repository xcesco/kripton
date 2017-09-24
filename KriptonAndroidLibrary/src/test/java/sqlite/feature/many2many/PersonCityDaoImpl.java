package sqlite.feature.many2many;

import com.abubusoft.kripton.android.sqlite.AbstractDao;

/**
 * <p>
 * DAO implementation for entity <code>Person2City</code>, based on interface <code>PersonCityDao</code>
 * </p>
 *
 *  @see Person2City
 *  @see PersonCityDao
 *  @see Person2CityTable
 */
public class PersonCityDaoImpl extends AbstractDao implements PersonCityDao {
  public PersonCityDaoImpl(BindPersonCirtyDataSource dataSet) {
    super(dataSet);
  }
}
