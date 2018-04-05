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
package sqlite.kripton38;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean01</code>
 * </p>.
 *
 * @see Bean01
 * @see DaoBean01
 * @see Bean01Table
 */
public class DaoBean01Impl extends Dao implements DaoBean01 {
  
  /** The Constant SELECT_ONE_SQL1. */
  private static final String SELECT_ONE_SQL1 = "SELECT temp, id, text FROM bean01 WHERE id=?";

  /** The update one prepared statement 0. */
  private static SQLiteStatement updateOnePreparedStatement0;

  /**
   * Instantiates a new dao bean 01 impl.
   *
   * @param context the context
   */
  public DaoBean01Impl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT temp, id, text FROM bean01 WHERE id=${id}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>temp</dt><dd>is associated to bean's property <strong>temp</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
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
  public Bean01 selectOne(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
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

      Bean01 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("temp");
        int index1=_cursor.getColumnIndex("id");
        int index2=_cursor.getColumnIndex("text");

        resultBean=new Bean01();

        if (!_cursor.isNull(index0)) { resultBean.temp=Bean01Table.parseTemp(_cursor.getBlob(index0)); }
        resultBean.setId(_cursor.getLong(index1));
        if (!_cursor.isNull(index2)) { resultBean.setText(_cursor.getString(index2)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean01 SET text=:text WHERE id=${id}</pre>
   * 
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>text</li>
   * </ul>
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>.
   *
   * @param text 	is used as updated field <strong>text</strong>
   * @param id 	is used as where parameter <strong>${id}</strong>
   * @return number of updated records
   */
  @Override
  public long updateOne(String text, long id) {
    if (updateOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean01 SET text=? WHERE id=?";
      updateOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement0);
    _contentValues.put("text", text);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean01 SET text=:text WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (updateOnePreparedStatement0!=null) {
      updateOnePreparedStatement0.close();
      updateOnePreparedStatement0=null;
    }
  }
}
