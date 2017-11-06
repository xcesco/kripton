package sqlite.kripton64;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.database.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean64B</code>, based on interface <code>Bean64BDao</code>
 * </p>
 *
 *  @see Bean64B
 *  @see Bean64BDao
 *  @see Bean64BTable
 */
public class Bean64BDaoImpl extends AbstractDao implements Bean64BDao {
  public Bean64BDaoImpl(BindBean64BDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_b</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64B> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_b");
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

      LinkedList<Bean64B> resultList=new LinkedList<Bean64B>();
      Bean64B resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_map_string_bean");
        int index1=cursor.getColumnIndex("value_set_string");
        int index2=cursor.getColumnIndex("value_string");
        int index3=cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean64B();

          if (!cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64BTable.parseValueMapStringBean(cursor.getBlob(index0)); }
          if (!cursor.isNull(index1)) { resultBean.valueSetString=Bean64BTable.parseValueSetString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }
          resultBean.id=cursor.getLong(index3);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_b WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64B> selectList(long id) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_b");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
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

      LinkedList<Bean64B> resultList=new LinkedList<Bean64B>();
      Bean64B resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_map_string_bean");
        int index1=cursor.getColumnIndex("value_set_string");
        int index2=cursor.getColumnIndex("value_string");
        int index3=cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean64B();

          if (!cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64BTable.parseValueMapStringBean(cursor.getBlob(index0)); }
          if (!cursor.isNull(index1)) { resultBean.valueSetString=Bean64BTable.parseValueSetString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }
          resultBean.id=cursor.getLong(index3);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean64_b (value_map_string_bean, value_set_string, value_string) VALUES (${bean.valueMapStringBean}, ${bean.valueSetString}, ${bean.valueString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is mapped to <strong>${bean.valueMapStringBean}</strong></dd>
   * 	<dt>value_set_string</dt><dd>is mapped to <strong>${bean.valueSetString}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean64B bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.valueMapStringBean!=null) {
      _contentValues.put("value_map_string_bean", Bean64BTable.serializeValueMapStringBean(bean.valueMapStringBean));
    } else {
      _contentValues.putNull("value_map_string_bean");
    }
    if (bean.valueSetString!=null) {
      _contentValues.put("value_set_string", Bean64BTable.serializeValueSetString(bean.valueSetString));
    } else {
      _contentValues.putNull("value_set_string");
    }
    if (bean.valueString!=null) {
      _contentValues.put("value_string", bean.valueString);
    } else {
      _contentValues.putNull("value_string");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean64_b (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("bean64_b", null, _contentValues.values());
    bean.id=result;

    return result;
  }
}
