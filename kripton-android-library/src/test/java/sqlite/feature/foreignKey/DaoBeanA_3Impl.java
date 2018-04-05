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
package sqlite.feature.foreignKey;

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
 * DAO implementation for entity <code>BeanA_3</code>, based on interface <code>DaoBeanA_3</code>
 * </p>.
 *
 * @see BeanA_3
 * @see DaoBeanA_3
 * @see BeanA_3Table
 */
public class DaoBeanA_3Impl extends Dao implements DaoBeanA_3 {
  
  /** The Constant SELECT_ALL_SQL1. */
  private static final String SELECT_ALL_SQL1 = "SELECT pk, value_string2 FROM bean_a_3";

  /** The Constant SELECT_BY_ID_SQL2. */
  private static final String SELECT_BY_ID_SQL2 = "SELECT pk, value_string2 FROM bean_a_3 WHERE pk=?";

  /** The Constant SELECT_BY_STRING_SQL3. */
  private static final String SELECT_BY_STRING_SQL3 = "SELECT pk FROM bean_a_3 WHERE value_string2=?";

  /** The insert prepared statement 0. */
  private static SQLiteStatement insertPreparedStatement0;

  /** The update prepared statement 1. */
  private static SQLiteStatement updatePreparedStatement1;

  /**
   * Instantiates a new dao bean A 3 impl.
   *
   * @param context the context
   */
  public DaoBeanA_3Impl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT pk, value_string2 FROM bean_a_3</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
   * </dl>.
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_3> selectAll() {
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

      ArrayList<BeanA_3> resultList=new ArrayList<BeanA_3>(_cursor.getCount());
      BeanA_3 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("value_string2");

        do
         {
          resultBean=new BeanA_3();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.valueString2=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT pk, value_string2 FROM bean_a_3 WHERE pk=${id}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
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
  public List<BeanA_3> selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL2;
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

      ArrayList<BeanA_3> resultList=new ArrayList<BeanA_3>(_cursor.getCount());
      BeanA_3 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("value_string2");

        do
         {
          resultBean=new BeanA_3();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.valueString2=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT pk FROM bean_a_3 WHERE value_string2=${dummy}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>.
   *
   * @param value 	is binded to <code>${dummy}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_3> selectByString(String value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_STRING_SQL3;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":value));
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

      ArrayList<BeanA_3> resultList=new ArrayList<BeanA_3>(_cursor.getCount());
      BeanA_3 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");

        do
         {
          resultBean=new BeanA_3();

          resultBean.id=_cursor.getLong(index0);

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_a_3 (value_string2) VALUES (${bean.valueString2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(BeanA_3 bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean_a_3 (value_string2) VALUES (?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("value_string2", bean.valueString2);

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
      Logger.info("INSERT INTO bean_a_3 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    return (int)result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean_a_3 SET value_string2=:valueString2 WHERE value_string2=${bean.valueString2}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.valueString2}</dt><dd>is mapped to method's parameter <strong>bean.valueString2</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public int update(BeanA_3 bean) {
    if (updatePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean_a_3 SET value_string2=? WHERE value_string2=?";
      updatePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    _contentValues.put("value_string2", bean.valueString2);

    _contentValues.addWhereArgs((bean.valueString2==null?"":bean.valueString2));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean_a_3 SET value_string2=:value_string2 WHERE value_string2=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement1, _contentValues);
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
    if (updatePreparedStatement1!=null) {
      updatePreparedStatement1.close();
      updatePreparedStatement1=null;
    }
  }
}
