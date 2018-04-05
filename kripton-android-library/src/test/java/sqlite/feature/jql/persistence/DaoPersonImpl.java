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
package sqlite.feature.jql.persistence;

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
import sqlite.feature.jql.entities.Person;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>DaoPerson</code>
 * </p>.
 *
 * @see Person
 * @see DaoPerson
 * @see sqlite.feature.jql.entities.PersonTable
 */
public class DaoPersonImpl extends Dao implements DaoPerson {
  
  /** The Constant SELECT_ALL_SQL5. */
  private static final String SELECT_ALL_SQL5 = "SELECT _id, name, image FROM person";

  /** The insert bean prepared statement 0. */
  private static SQLiteStatement insertBeanPreparedStatement0;

  /**
   * Instantiates a new dao person impl.
   *
   * @param context the context
   */
  public DaoPersonImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT _id, name, image FROM person</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>image</dt><dd>is associated to bean's property <strong>image</strong></dd>
   * </dl>.
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL5;
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

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("_id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("image");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          resultBean.name=_cursor.getString(index1);
          if (!_cursor.isNull(index2)) { resultBean.image=_cursor.getBlob(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, image) VALUES (${name}, ${image})</pre>
   * 
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   * 
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>image</dt><dd>is mapped to <strong>${bean.image}</strong></dd>
   * </dl>
   *
   * @param bean 	is mapped to parameter <strong>bean</strong>
   * @return the person
   */
  @Override
  public Person insertBean(Person bean) {
    if (insertBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (name, image) VALUES (?, ?)";
      insertBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBeanPreparedStatement0);
    _contentValues.put("name", bean.name);
    _contentValues.put("image", bean.image);

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
      Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertBeanPreparedStatement0, _contentValues);
    bean.id=result;

    return bean;
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (insertBeanPreparedStatement0!=null) {
      insertBeanPreparedStatement0.close();
      insertBeanPreparedStatement0=null;
    }
  }
}
