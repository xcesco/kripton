package sqlite.feature.typeadapter;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * DAO implementation for entity <code>Contact</code>, based on interface <code>ContactDao</code>
 * </p>
 *
 *  @see Contact
 *  @see ContactDao
 *  @see ContactTable
 */
public class ContactDaoImpl extends AbstractDao implements ContactDao {
  public ContactDaoImpl(BindContactDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLRaw1(String password, Date birthDay, ContactType type, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (birthDay!=null) {
      contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      contentValues.putNull("birth_day");
    }
    contentValues.put("id", id);

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=? and type=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }
}
