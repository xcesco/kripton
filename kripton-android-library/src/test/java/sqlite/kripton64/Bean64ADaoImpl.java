/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton64;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Bean64A</code>, based on interface <code>Bean64ADao</code>
 * </p>.
 *
 * @see Bean64A
 * @see Bean64ADao
 * @see Bean64ATable
 */
public class Bean64ADaoImpl extends Dao implements Bean64ADao {
  
  /** The Constant SELECT_ALL_SQL1. */
  private static final String SELECT_ALL_SQL1 = "SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a";

  /** The Constant SELECT_LIST_SQL2. */
  private static final String SELECT_LIST_SQL2 = "SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id=?";

  /** The insert prepared statement 0. */
  private static SQLiteStatement insertPreparedStatement0;

  /**
   * Instantiates a new bean 64 A dao impl.
   *
   * @param context the context
   */
  public Bean64ADaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>.
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64A> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<Bean64A> resultList=new ArrayList<Bean64A>(_cursor.getCount());
      Bean64A resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("value_map_string_bean");
        int index1=_cursor.getColumnIndex("value_set_string");
        int index2=_cursor.getColumnIndex("value_string");
        int index3=_cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean64A();

          if (!_cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64ATable.parseValueMapStringBean(_cursor.getBlob(index0)); }
          if (!_cursor.isNull(index1)) { resultBean.valueSetString=Bean64ATable.parseValueSetString(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.valueString=_cursor.getString(index2); }
          resultBean.id=_cursor.getLong(index3);

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id=${id}</pre>
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64A> selectList(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<Bean64A> resultList=new ArrayList<Bean64A>(_cursor.getCount());
      Bean64A resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("value_map_string_bean");
        int index1=_cursor.getColumnIndex("value_set_string");
        int index2=_cursor.getColumnIndex("value_string");
        int index3=_cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean64A();

          if (!_cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64ATable.parseValueMapStringBean(_cursor.getBlob(index0)); }
          if (!_cursor.isNull(index1)) { resultBean.valueSetString=Bean64ATable.parseValueSetString(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.valueString=_cursor.getString(index2); }
          resultBean.id=_cursor.getLong(index3);

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean64_a (value_map_string_bean, value_set_string, value_string) VALUES (${bean.valueMapStringBean}, ${bean.valueSetString}, ${bean.valueString})</pre>
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
  public long insert(Bean64A bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean64_a (value_map_string_bean, value_set_string, value_string) VALUES (?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("value_map_string_bean", Bean64ATable.serializeValueMapStringBean(bean.valueMapStringBean));
    _contentValues.put("value_set_string", Bean64ATable.serializeValueSetString(bean.valueSetString));
    _contentValues.put("value_string", bean.valueString);

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
      Logger.info("INSERT INTO bean64_a (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    bean.id=result;

    return result;
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
