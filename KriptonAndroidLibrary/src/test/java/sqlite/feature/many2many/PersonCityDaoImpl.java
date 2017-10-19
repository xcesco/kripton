package sqlite.feature.many2many;

import com.abubusoft.kripton.android.sqlite.AbstractDao;

/**
 * <p>
 * DAO implementation for entity <code>PersonCity</code>, based on interface <code>PersonCityDao</code>
 * </p>
 *
 *  @see PersonCity
 *  @see PersonCityDao
 *  @see PersonCityTable
 */
public class PersonCityDaoImpl extends AbstractDao implements PersonCityDao {
  public PersonCityDaoImpl(BindPersonCirtyDataSource dataSet) {
    super(dataSet);
  }
}
