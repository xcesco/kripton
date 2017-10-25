package sqlite.stack44633883;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
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
  public SchoolLunchDAOImpl(BindSchoolLunchDataSource dataSet) {
    super(dataSet);
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT * FROM SchoolLunches");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY fruits COLLATE LOCALIZED";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<SchoolLunch> resultList=new LinkedList<SchoolLunch>();
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT lunch_id, fresh, contains_meat, fruits FROM SchoolLunches");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<SchoolLunch> resultList=new LinkedList<SchoolLunch>();
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
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("fresh", schoolLunches.isFresh());
    contentValues.put("contains_meat", schoolLunches.isContainsMeat());
    if (schoolLunches.getFruits()!=null) {
      contentValues.put("fruits", SchoolLunchTable.serializeFruits(schoolLunches.getFruits()));
    } else {
      contentValues.putNull("fruits");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO SchoolLunches (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // log for insert -- END 

    long result = database().insert("SchoolLunches", null, contentValues);
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
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // display log
    Logger.info("DELETE FROM SchoolLunches");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("SchoolLunches", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
  }
}
