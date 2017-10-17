package sqlite.feature.many2many.case4.persistence;

import com.abubusoft.kripton.android.sqlite.AbstractDao;

/**
 * <p>
 * DAO implementation for entity <code>PersonPhoneNumber</code>, based on interface <code>Person2PhoneDao</code>
 * </p>
 *
 *  @see PersonPhoneNumber
 *  @see Person2PhoneDao
 *  @see PersonPhoneNumberTable
 */
public class Person2PhoneDaoImpl extends AbstractDao implements Person2PhoneDao {
  public Person2PhoneDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
  }
}
