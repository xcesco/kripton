package sqlite.kripton84;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DAO implementation for entity <code>Bean84A</code>, based on interface <code>Bean84ADao</code>
 * </p>
 *
 *  @see Bean84A
 *  @see Bean84ADao
 *  @see Bean84ATable
 */
public class Bean84ADaoImpl extends AbstractDao implements Bean84ADao {
  private SQLiteStatement insertAllPreparedStatement0;

  private SQLiteStatement insertPreparedStatement1;

  private SQLiteStatement updateAllPreparedStatement2;

  private SQLiteStatement deleteAllPreparedStatement3;

  public Bean84ADaoImpl(BindBean84ADataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>param1</dt><dd>is associated to bean's property <strong>param1</strong></dd>
   * 	<dt>param2</dt><dd>is associated to bean's property <strong>param2</strong></dd>
   * 	<dt>param3</dt><dd>is associated to bean's property <strong>param3</strong></dd>
   * 	<dt>param4</dt><dd>is associated to bean's property <strong>param4</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84A> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a");
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

      LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
      Bean84A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("param1");
        int index2=cursor.getColumnIndex("param2");
        int index3=cursor.getColumnIndex("param3");
        int index4=cursor.getColumnIndex("param4");
        int index5=cursor.getColumnIndex("column_list_string");
        int index6=cursor.getColumnIndex("column_map_integer_string");
        int index7=cursor.getColumnIndex("column_array_char");
        int index8=cursor.getColumnIndex("column_array_char_type");
        int index9=cursor.getColumnIndex("column_bean");
        int index10=cursor.getColumnIndex("column_array_byte_type");
        int index11=cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean84A();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.param1=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.param2=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.param3=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.param4=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index7)); }
          if (!cursor.isNull(index8)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index8)); }
          if (!cursor.isNull(index9)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index9)); }
          if (!cursor.isNull(index10)) { resultBean.columnArrayByteType=cursor.getBlob(index10); }
          if (!cursor.isNull(index11)) { resultBean.valueString=cursor.getString(index11); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>param1</dt><dd>is associated to bean's property <strong>param1</strong></dd>
   * 	<dt>param2</dt><dd>is associated to bean's property <strong>param2</strong></dd>
   * 	<dt>param3</dt><dd>is associated to bean's property <strong>param3</strong></dd>
   * 	<dt>param4</dt><dd>is associated to bean's property <strong>param4</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>uid</strong></dd>
   * </dl>
   *
   * @param uid
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84A> selectById(long uid) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(uid));
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

      LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
      Bean84A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("param1");
        int index2=cursor.getColumnIndex("param2");
        int index3=cursor.getColumnIndex("param3");
        int index4=cursor.getColumnIndex("param4");
        int index5=cursor.getColumnIndex("column_list_string");
        int index6=cursor.getColumnIndex("column_map_integer_string");
        int index7=cursor.getColumnIndex("column_array_char");
        int index8=cursor.getColumnIndex("column_array_char_type");
        int index9=cursor.getColumnIndex("column_bean");
        int index10=cursor.getColumnIndex("column_array_byte_type");
        int index11=cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean84A();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.param1=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.param2=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.param3=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.param4=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index7)); }
          if (!cursor.isNull(index8)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index8)); }
          if (!cursor.isNull(index9)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index9)); }
          if (!cursor.isNull(index10)) { resultBean.columnArrayByteType=cursor.getBlob(index10); }
          if (!cursor.isNull(index11)) { resultBean.valueString=cursor.getString(index11); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE column_list_string=${param1} and column_map_integer_string=${param2} and column_array_char=${param3}  and column_array_char_type=${param4}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>param1</dt><dd>is associated to bean's property <strong>param1</strong></dd>
   * 	<dt>param2</dt><dd>is associated to bean's property <strong>param2</strong></dd>
   * 	<dt>param3</dt><dd>is associated to bean's property <strong>param3</strong></dd>
   * 	<dt>param4</dt><dd>is associated to bean's property <strong>param4</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${param1}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * 	<dt>${param2}</dt><dd>is binded to method's parameter <strong>param2</strong></dd>
   * 	<dt>${param3}</dt><dd>is binded to method's parameter <strong>param3</strong></dd>
   * 	<dt>${param4}</dt><dd>is binded to method's parameter <strong>param4</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to <code>${param1}</code>
   * @param param2
   * 	is binded to <code>${param2}</code>
   * @param param3
   * 	is binded to <code>${param3}</code>
   * @param param4
   * 	is binded to <code>${param4}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84A> selectWhere(List<String> param1, Map<Integer, String> param2,
      Character[] param3, char[] param4) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE column_list_string=? and column_map_integer_string=? and column_array_char=?  and column_array_char_type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((param1==null?"":new String(serializer1(param1),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((param2==null?"":new String(serializer2(param2),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((param3==null?"":new String(serializer3(param3),StandardCharsets.UTF_8)));
    _contentValues.addWhereArgs((param4==null?"":new String(serializer4(param4),StandardCharsets.UTF_8)));
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

      LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
      Bean84A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("param1");
        int index2=cursor.getColumnIndex("param2");
        int index3=cursor.getColumnIndex("param3");
        int index4=cursor.getColumnIndex("param4");
        int index5=cursor.getColumnIndex("column_list_string");
        int index6=cursor.getColumnIndex("column_map_integer_string");
        int index7=cursor.getColumnIndex("column_array_char");
        int index8=cursor.getColumnIndex("column_array_char_type");
        int index9=cursor.getColumnIndex("column_bean");
        int index10=cursor.getColumnIndex("column_array_byte_type");
        int index11=cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean84A();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.param1=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.param2=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.param3=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.param4=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index7)); }
          if (!cursor.isNull(index8)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index8)); }
          if (!cursor.isNull(index9)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index9)); }
          if (!cursor.isNull(index10)) { resultBean.columnArrayByteType=cursor.getBlob(index10); }
          if (!cursor.isNull(index11)) { resultBean.valueString=cursor.getString(index11); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84_a (param1, param2, param3, param4, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string) VALUES (${bean.param1}, ${bean.param2}, ${bean.param3}, ${bean.param4}, ${bean.columnListString}, ${bean.columnMapIntegerString}, ${bean.columnArrayChar}, ${bean.columnArrayCharType}, ${bean.columnBean}, ${bean.columnArrayByteType}, ${bean.valueString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>param1</dt><dd>is mapped to <strong>${bean.param1}</strong></dd>
   * 	<dt>param2</dt><dd>is mapped to <strong>${bean.param2}</strong></dd>
   * 	<dt>param3</dt><dd>is mapped to <strong>${bean.param3}</strong></dd>
   * 	<dt>param4</dt><dd>is mapped to <strong>${bean.param4}</strong></dd>
   * 	<dt>column_list_string</dt><dd>is mapped to <strong>${bean.columnListString}</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is mapped to <strong>${bean.columnMapIntegerString}</strong></dd>
   * 	<dt>column_array_char</dt><dd>is mapped to <strong>${bean.columnArrayChar}</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is mapped to <strong>${bean.columnArrayCharType}</strong></dd>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is mapped to <strong>${bean.columnArrayByteType}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertAll(Bean84A bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.param1!=null) {
      _contentValues.put("param1", bean.param1);
    } else {
      _contentValues.putNull("param1");
    }
    if (bean.param2!=null) {
      _contentValues.put("param2", bean.param2);
    } else {
      _contentValues.putNull("param2");
    }
    if (bean.param3!=null) {
      _contentValues.put("param3", bean.param3);
    } else {
      _contentValues.putNull("param3");
    }
    if (bean.param4!=null) {
      _contentValues.put("param4", bean.param4);
    } else {
      _contentValues.putNull("param4");
    }
    if (bean.columnListString!=null) {
      _contentValues.put("column_list_string", Bean84ATable.serializeColumnListString(bean.columnListString));
    } else {
      _contentValues.putNull("column_list_string");
    }
    if (bean.columnMapIntegerString!=null) {
      _contentValues.put("column_map_integer_string", Bean84ATable.serializeColumnMapIntegerString(bean.columnMapIntegerString));
    } else {
      _contentValues.putNull("column_map_integer_string");
    }
    if (bean.columnArrayChar!=null) {
      _contentValues.put("column_array_char", Bean84ATable.serializeColumnArrayChar(bean.columnArrayChar));
    } else {
      _contentValues.putNull("column_array_char");
    }
    if (bean.columnArrayCharType!=null) {
      _contentValues.put("column_array_char_type", Bean84ATable.serializeColumnArrayCharType(bean.columnArrayCharType));
    } else {
      _contentValues.putNull("column_array_char_type");
    }
    if (bean.columnBean!=null) {
      _contentValues.put("column_bean", Bean84ATable.serializeColumnBean(bean.columnBean));
    } else {
      _contentValues.putNull("column_bean");
    }
    if (bean.columnArrayByteType!=null) {
      _contentValues.put("column_array_byte_type", bean.columnArrayByteType);
    } else {
      _contentValues.putNull("column_array_byte_type");
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
    for (String columnName:_contentValues.keys()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean84_a (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // insert operation
    if (insertAllPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO bean84_a (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertAllPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertAllPreparedStatement0, _contentValues);
    bean.id=result;

    return result!=-1;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean84_a (column_list_string) VALUES (${param1})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>column_list_string</dt><dd>is binded to query's parameter <strong>${columnListString}</strong> and method's parameter <strong>param1</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to column value <strong>column_list_string</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(List<String> param1) {
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (param1!=null) {
      _contentValues.put("column_list_string", serializer1(param1));
    } else {
      _contentValues.putNull("column_list_string");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keys()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean84_a (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // insert operation
    if (insertPreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO bean84_a (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement1, _contentValues);
    return result!=-1;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean84_a SET param1=:param1, param2=:param2, param3=:param3, param4=:param4, column_list_string=:columnListString, column_map_integer_string=:columnMapIntegerString, column_array_char=:columnArrayChar, column_array_char_type=:columnArrayCharType, column_bean=:columnBean, column_array_byte_type=:columnArrayByteType, value_string=:valueString</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>param1</dt><dd>is mapped to <strong>${bean.param1}</strong></dd>
   * 	<dt>param2</dt><dd>is mapped to <strong>${bean.param2}</strong></dd>
   * 	<dt>param3</dt><dd>is mapped to <strong>${bean.param3}</strong></dd>
   * 	<dt>param4</dt><dd>is mapped to <strong>${bean.param4}</strong></dd>
   * 	<dt>column_list_string</dt><dd>is mapped to <strong>${bean.columnListString}</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is mapped to <strong>${bean.columnMapIntegerString}</strong></dd>
   * 	<dt>column_array_char</dt><dd>is mapped to <strong>${bean.columnArrayChar}</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is mapped to <strong>${bean.columnArrayCharType}</strong></dd>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is mapped to <strong>${bean.columnArrayByteType}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateAll(Bean84A bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.param1!=null) {
      _contentValues.put("param1", bean.param1);
    } else {
      _contentValues.putNull("param1");
    }
    if (bean.param2!=null) {
      _contentValues.put("param2", bean.param2);
    } else {
      _contentValues.putNull("param2");
    }
    if (bean.param3!=null) {
      _contentValues.put("param3", bean.param3);
    } else {
      _contentValues.putNull("param3");
    }
    if (bean.param4!=null) {
      _contentValues.put("param4", bean.param4);
    } else {
      _contentValues.putNull("param4");
    }
    if (bean.columnListString!=null) {
      _contentValues.put("column_list_string", Bean84ATable.serializeColumnListString(bean.columnListString));
    } else {
      _contentValues.putNull("column_list_string");
    }
    if (bean.columnMapIntegerString!=null) {
      _contentValues.put("column_map_integer_string", Bean84ATable.serializeColumnMapIntegerString(bean.columnMapIntegerString));
    } else {
      _contentValues.putNull("column_map_integer_string");
    }
    if (bean.columnArrayChar!=null) {
      _contentValues.put("column_array_char", Bean84ATable.serializeColumnArrayChar(bean.columnArrayChar));
    } else {
      _contentValues.putNull("column_array_char");
    }
    if (bean.columnArrayCharType!=null) {
      _contentValues.put("column_array_char_type", Bean84ATable.serializeColumnArrayCharType(bean.columnArrayCharType));
    } else {
      _contentValues.putNull("column_array_char_type");
    }
    if (bean.columnBean!=null) {
      _contentValues.put("column_bean", Bean84ATable.serializeColumnBean(bean.columnBean));
    } else {
      _contentValues.putNull("column_bean");
    }
    if (bean.columnArrayByteType!=null) {
      _contentValues.put("column_array_byte_type", bean.columnArrayByteType);
    } else {
      _contentValues.putNull("column_array_byte_type");
    }
    if (bean.valueString!=null) {
      _contentValues.put("value_string", bean.valueString);
    } else {
      _contentValues.putNull("value_string");
    }


    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateAllPreparedStatement2==null) {
      String _sqlWhereStatement="";

      // generate sql
      String _sql="UPDATE bean84_a SET param1=?, param2=?, param3=?, param4=?, column_list_string=?, column_map_integer_string=?, column_array_char=?, column_array_char_type=?, column_bean=?, column_array_byte_type=?, value_string=?";
      updateAllPreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }

    // display log
    Logger.info("UPDATE bean84_a SET param1=:param1, param2=:param2, param3=:param3, param4=:param4, column_list_string=:columnListString, column_map_integer_string=:columnMapIntegerString, column_array_char=:columnArrayChar, column_array_char_type=:columnArrayCharType, column_bean=:columnBean, column_array_byte_type=:columnArrayByteType, value_string=:valueString");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateAllPreparedStatement2, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM bean84_a</pre>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteAll(Bean84A bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteAllPreparedStatement3==null) {
      String _sqlWhereStatement="";

      // generate sql
      String _sql="DELETE FROM bean84_a";
      deleteAllPreparedStatement3 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }

    // display log
    Logger.info("DELETE FROM bean84_a");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteAllPreparedStatement3, _contentValues);
    return result!=0;
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(List<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing
   */
  private List<String> parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<String> collection=new ArrayList<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer2 serialization
   */
  private byte[] serializer2(Map<Integer, String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Integer, String> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeNumberField(null, item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField(null);
            } else {
              jacksonSerializer.writeStringField(null, item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser2 parsing
   */
  private Map<Integer, String> parser2(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Map<Integer, String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<Integer, String> collection=new HashMap<>();
        Integer key=null;
        String value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getIntValue();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getText();
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer3 serialization
   */
  private byte[] serializer3(Character[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser3 parsing
   */
  private Character[] parser3(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Character[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param serializer4 serialization
   */
  private byte[] serializer4(char[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser4 parsing
   */
  private char[] parser4(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      char[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  public void clearCompiledStatements() {
    if (insertAllPreparedStatement0!=null) {
      insertAllPreparedStatement0.close();
      insertAllPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
    if (updateAllPreparedStatement2!=null) {
      updateAllPreparedStatement2.close();
      updateAllPreparedStatement2=null;
    }
    if (deleteAllPreparedStatement3!=null) {
      deleteAllPreparedStatement3.close();
      deleteAllPreparedStatement3=null;
    }
  }
}
