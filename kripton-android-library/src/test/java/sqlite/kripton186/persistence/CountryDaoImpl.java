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
package sqlite.kripton186.persistence;

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
import sqlite.kripton186.model.Country;
import sqlite.kripton186.model.CountryTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Country</code>, based on interface <code>CountryDao</code>
 * </p>.
 *
 * @see Country
 * @see CountryDao
 * @see CountryTable
 */
public class CountryDaoImpl extends Dao implements CountryDao {
  
  /** The insert prepared statement 0. */
  private static SQLiteStatement insertPreparedStatement0;

  /** The Constant SELECT_BY_ID_SQL6. */
  private static final String SELECT_BY_ID_SQL6 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE id = ?";

  /** The delete by id prepared statement 1. */
  private static SQLiteStatement deleteByIdPreparedStatement1;

  /** The Constant SELECT_ALL_SQL7. */
  private static final String SELECT_ALL_SQL7 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country ORDER BY name asc";

  /** The Constant SELECT_BY_CALLING_CODE_SQL8. */
  private static final String SELECT_BY_CALLING_CODE_SQL8 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE calling_code = ?";

  /** The Constant SELECT_BY_COUNTRY_SQL9. */
  private static final String SELECT_BY_COUNTRY_SQL9 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE code = ?";

  /**
   * Instantiates a new country dao impl.
   *
   * @param context the context
   */
  public CountryDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO country (area, code, calling_code, region, name, translated_name) VALUES (${area}, ${code}, ${callingCode}, ${region}, ${name}, ${translatedName})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>area</dt><dd>is mapped to <strong>${bean.area}</strong></dd>
   * 	<dt>code</dt><dd>is mapped to <strong>${bean.code}</strong></dd>
   * 	<dt>calling_code</dt><dd>is mapped to <strong>${bean.callingCode}</strong></dd>
   * 	<dt>region</dt><dd>is mapped to <strong>${bean.region}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>translated_name</dt><dd>is mapped to <strong>${bean.translatedName}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(Country bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO country (area, code, calling_code, region, name, translated_name) VALUES (?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("area", bean.area);
    _contentValues.put("code", bean.code);
    _contentValues.put("calling_code", bean.callingCode);
    _contentValues.put("region", bean.region);
    _contentValues.put("name", bean.name);
    _contentValues.put("translated_name", CountryTable.serializeTranslatedName(bean.translatedName));

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
      Logger.info("INSERT OR REPLACE INTO country (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE id = ${id}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL6;
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

      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("code");
        int index3=_cursor.getColumnIndex("calling_code");
        int index4=_cursor.getColumnIndex("region");
        int index5=_cursor.getColumnIndex("name");
        int index6=_cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
        resultBean.code=_cursor.getString(index2);
        resultBean.callingCode=_cursor.getString(index3);
        if (!_cursor.isNull(index4)) { resultBean.region=_cursor.getString(index4); }
        resultBean.name=_cursor.getString(index5);
        if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM country WHERE id = ${id}</pre>
   * 
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>.
   *
   * @param id 	is used as where parameter <strong>${id}</strong>
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteById(long id) {
    if (deleteByIdPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM country WHERE id = ?";
      deleteByIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM country WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country ORDER BY name asc</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>.
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Country> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL7;
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

      ArrayList<Country> resultList=new ArrayList<Country>(_cursor.getCount());
      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("code");
        int index3=_cursor.getColumnIndex("calling_code");
        int index4=_cursor.getColumnIndex("region");
        int index5=_cursor.getColumnIndex("name");
        int index6=_cursor.getColumnIndex("translated_name");

        do
         {
          resultBean=new Country();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
          resultBean.code=_cursor.getString(index2);
          resultBean.callingCode=_cursor.getString(index3);
          if (!_cursor.isNull(index4)) { resultBean.region=_cursor.getString(index4); }
          resultBean.name=_cursor.getString(index5);
          if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE calling_code = ${callingCode}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${callingCode}</dt><dd>is binded to method's parameter <strong>callingCode</strong></dd>
   * </dl>.
   *
   * @param callingCode 	is binded to <code>${callingCode}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectByCallingCode(String callingCode) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CALLING_CODE_SQL8;
    // add where arguments
    _contentValues.addWhereArgs((callingCode==null?"":callingCode));
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

      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("code");
        int index3=_cursor.getColumnIndex("calling_code");
        int index4=_cursor.getColumnIndex("region");
        int index5=_cursor.getColumnIndex("name");
        int index6=_cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
        resultBean.code=_cursor.getString(index2);
        resultBean.callingCode=_cursor.getString(index3);
        if (!_cursor.isNull(index4)) { resultBean.region=_cursor.getString(index4); }
        resultBean.name=_cursor.getString(index5);
        if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE code = ${code}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${code}</dt><dd>is binded to method's parameter <strong>code</strong></dd>
   * </dl>.
   *
   * @param code 	is binded to <code>${code}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectByCountry(String code) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_COUNTRY_SQL9;
    // add where arguments
    _contentValues.addWhereArgs((code==null?"":code));
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

      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("code");
        int index3=_cursor.getColumnIndex("calling_code");
        int index4=_cursor.getColumnIndex("region");
        int index5=_cursor.getColumnIndex("name");
        int index6=_cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
        resultBean.code=_cursor.getString(index2);
        resultBean.callingCode=_cursor.getString(index3);
        if (!_cursor.isNull(index4)) { resultBean.region=_cursor.getString(index4); }
        resultBean.name=_cursor.getString(index5);
        if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

      }
      return resultBean;
    }
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (deleteByIdPreparedStatement1!=null) {
      deleteByIdPreparedStatement1.close();
      deleteByIdPreparedStatement1=null;
    }
  }
}
