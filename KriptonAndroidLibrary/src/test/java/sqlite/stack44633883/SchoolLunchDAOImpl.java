package sqlite.stack44633883;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>SchoolLunch</code>, based on interface <code>SchoolLunchDAO</code>
 * </p>
 *
 *  @see SchoolLunch
 *  @see SchoolLunchDAO
 *  @see SchoolLunchTable
 */
public class SchoolLunchDAOImpl extends AbstractDao implements SchoolLunchDAO {
  private static final String GET1_SQL1 = "SELECT * FROM SchoolLunches ORDER BY fruits COLLATE LOCALIZED";

  private static final String GET_ALL_SQL2 = "SELECT lunch_id, fresh, contains_meat, fruits FROM SchoolLunches";

  private static SQLiteStatement insertAllPreparedStatement0;

  private static SQLiteStatement deleteAllPreparedStatement1;

  public SchoolLunchDAOImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM SchoolLunches ORDER BY fruits COLLATE LOCALIZED</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>lunch_id</dt><dd>is associated to bean's property <strong>lunchId</strong></dd>
   * 	<dt>fresh</dt><dd>is associated to bean's property <strong>fresh</strong></dd>
   * 	<dt>contains_meat</dt><dd>is associated to bean's property <strong>containsMeat</strong></dd>
   * 	<dt>fruits</dt><dd>is associated to bean's property <strong>fruits</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<SchoolLunch> get1() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET1_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<SchoolLunch> resultList=new ArrayList<SchoolLunch>(cursor.getCount());
      SchoolLunch resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("lunch_id");
        int index1=cursor.getColumnIndex("fresh");
        int index2=cursor.getColumnIndex("contains_meat");
        int index3=cursor.getColumnIndex("fruits");

        do
         {
          resultBean=new SchoolLunch();

          resultBean.setLunchId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.setFresh(cursor.getInt(index1)==0?false:true); }
          if (!cursor.isNull(index2)) { resultBean.setContainsMeat(cursor.getInt(index2)==0?false:true); }
          if (!cursor.isNull(index3)) { resultBean.setFruits(SchoolLunchTable.parseFruits(cursor.getBlob(index3))); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT lunch_id, fresh, contains_meat, fruits FROM SchoolLunches</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>lunch_id</dt><dd>is associated to bean's property <strong>lunchId</strong></dd>
   * 	<dt>fresh</dt><dd>is associated to bean's property <strong>fresh</strong></dd>
   * 	<dt>contains_meat</dt><dd>is associated to bean's property <strong>containsMeat</strong></dd>
   * 	<dt>fruits</dt><dd>is associated to bean's property <strong>fruits</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<SchoolLunch> getAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_ALL_SQL2;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<SchoolLunch> resultList=new ArrayList<SchoolLunch>(cursor.getCount());
      SchoolLunch resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("lunch_id");
        int index1=cursor.getColumnIndex("fresh");
        int index2=cursor.getColumnIndex("contains_meat");
        int index3=cursor.getColumnIndex("fruits");

        do
         {
          resultBean=new SchoolLunch();

          resultBean.setLunchId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.setFresh(cursor.getInt(index1)==0?false:true); }
          if (!cursor.isNull(index2)) { resultBean.setContainsMeat(cursor.getInt(index2)==0?false:true); }
          if (!cursor.isNull(index3)) { resultBean.setFruits(SchoolLunchTable.parseFruits(cursor.getBlob(index3))); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO SchoolLunches (fresh, contains_meat, fruits) VALUES (${schoolLunches.fresh}, ${schoolLunches.containsMeat}, ${schoolLunches.fruits})</pre>
   *
   * <p><code>schoolLunches.lunchId</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>fresh</dt><dd>is mapped to <strong>${schoolLunches.fresh}</strong></dd>
   * 	<dt>contains_meat</dt><dd>is mapped to <strong>${schoolLunches.containsMeat}</strong></dd>
   * 	<dt>fruits</dt><dd>is mapped to <strong>${schoolLunches.fruits}</strong></dd>
   * </dl>
   *
   * @param schoolLunches
   * 	is mapped to parameter <strong>schoolLunches</strong>
   *
   */
  @Override
  public void insertAll(SchoolLunch schoolLunches) {
    KriptonContentValues _contentValues=contentValuesForUpdate(insertAllPreparedStatement0);
    _contentValues.put("fresh", schoolLunches.isFresh());
    _contentValues.put("contains_meat", schoolLunches.isContainsMeat());
    if (schoolLunches.getFruits()!=null) {
      _contentValues.put("fruits", SchoolLunchTable.serializeFruits(schoolLunches.getFruits()));
    } else {
      _contentValues.putNull("fruits");
    }

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
      Logger.info("INSERT INTO SchoolLunches (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertAllPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO SchoolLunches (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertAllPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(_context, insertAllPreparedStatement0, _contentValues);
    schoolLunches.setLunchId(result);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM SchoolLunches</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   */
  @Override
  public void deleteAll() {
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement1);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteAllPreparedStatement1==null) {
      String _sqlWhereStatement="";

      // generate sql
      String _sql="DELETE FROM SchoolLunches";
      deleteAllPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM SchoolLunches");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, deleteAllPreparedStatement1, _contentValues);
  }

  public static void clearCompiledStatements() {
    if (insertAllPreparedStatement0!=null) {
      insertAllPreparedStatement0.close();
      insertAllPreparedStatement0=null;
    }
    if (deleteAllPreparedStatement1!=null) {
      deleteAllPreparedStatement1.close();
      deleteAllPreparedStatement1=null;
    }
  }
}
