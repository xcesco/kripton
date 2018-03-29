package sqlite.kripton41;

import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.orm.Dao;
import com.abubusoft.kripton.android.orm.KriptonContentValues;
import com.abubusoft.kripton.android.orm.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.orm.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanInsertOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanInsertOK
 *  @see Bean01Table
 */
public class DaoBeanInsertOKImpl extends Dao implements DaoBeanInsertOK {
  private static SQLiteStatement insertDistancePreparedStatement0;

  public DaoBeanInsertOKImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean01 (id, value) VALUES (${id}, ${value})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column value <strong>id</strong>
   * @param value
   * 	is binded to column value <strong>value</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertDistance(long id, Double value) {
    if (insertDistancePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean01 (id, value) VALUES (?, ?)";
      insertDistancePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertDistancePreparedStatement0);

    _contentValues.put("id", id);
    _contentValues.put("value", value);

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO bean01 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertDistancePreparedStatement0, _contentValues);
    return result!=-1;
  }

  public static void clearCompiledStatements() {
    if (insertDistancePreparedStatement0!=null) {
      insertDistancePreparedStatement0.close();
      insertDistancePreparedStatement0=null;
    }
  }
}
