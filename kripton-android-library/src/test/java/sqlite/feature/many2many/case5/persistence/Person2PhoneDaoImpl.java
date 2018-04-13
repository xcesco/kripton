package sqlite.feature.many2many.case5.persistence;

import com.abubusoft.kripton.android.sqlite.Dao;

/**
 * <p>
 * DAO implementation for entity <code>PersonPhoneNumber</code>, based on interface <code>GeneratedPerson2PhoneDao</code>
 * </p>
 *
 *  @see PersonPhoneNumber
 *  @see GeneratedPerson2PhoneDao
 *  @see PersonPhoneNumberTable
 */
public class Person2PhoneDaoImpl extends Dao implements GeneratedPerson2PhoneDao {
  public Person2PhoneDaoImpl(BindXenoDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  public static void clearCompiledStatements() {
  }
}
