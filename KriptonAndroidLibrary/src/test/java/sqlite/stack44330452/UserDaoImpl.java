package sqlite.stack44330452;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.database.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>UserDao</code>
 * </p>
 *
 *  @see User
 *  @see UserDao
 *  @see UserTable
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
  public UserDaoImpl(BindPetUserDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id FROM user</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> loadUser() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id FROM user");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<User> resultList=new LinkedList<User>();
      User resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");

        do
         {
          resultBean=new User();

          resultBean.id=cursor.getLong(index0);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
