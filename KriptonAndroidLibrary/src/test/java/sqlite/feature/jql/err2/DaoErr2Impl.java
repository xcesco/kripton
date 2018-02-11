package sqlite.feature.jql.err2;

import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

/**
 * <p>
 * DAO implementation for entity <code>BeanErr2</code>, based on interface <code>DaoErr2</code>
 * </p>
 *
 *  @see BeanErr2
 *  @see DaoErr2
 *  @see BeanErr2Table
 */
public class DaoErr2Impl extends AbstractDao implements DaoErr2 {
  private static SQLiteStatement updateStudentsPreparedStatement0;

  public DaoErr2Impl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean_err2 SET name=:name</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   * @param firstName
   * 	is used as updated field <strong>name</strong>
   *
   * @return number of updated records
   */
  @Override
  public int updateStudents(String firstName) {
    if (updateStudentsPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean_err2 SET name=?";
      updateStudentsPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateStudentsPreparedStatement0);
    if (firstName!=null) {
      _contentValues.put("name", firstName);
    } else {
      _contentValues.putNull("name");
    }


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean_err2 SET name=:name");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateStudentsPreparedStatement0, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    if (updateStudentsPreparedStatement0!=null) {
      updateStudentsPreparedStatement0.close();
      updateStudentsPreparedStatement0=null;
    }
  }
}
