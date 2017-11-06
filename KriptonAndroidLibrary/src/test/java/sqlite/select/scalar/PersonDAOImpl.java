package sqlite.select.scalar;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.database.KriptonContentValues;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.Date;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see sqlite.select.Person
 *  @see PersonDAO
 *  @see sqlite.select.PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT type_name FROM person ORDER BY type_name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>type_name</dt><dd>is associated to bean's property <strong>typeName</strong></dd>
   * </dl>
   *
   * @return single value extracted by query.
   */
  @Override
  public String selectAll() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT type_name FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";

    // build where condition
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY type_name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

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
      String result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getString(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT birth_day FROM person ORDER BY type_name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return single value extracted by query.
   */
  @Override
  public Date selectAll2() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT birth_day FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";

    // build where condition
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY type_name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

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
      Date result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=DateUtils.read(cursor.getString(0));
      }
      return result;
    }
  }
}
