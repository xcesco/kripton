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

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Bean03</code>, based on interface <code>DaoBean03</code>
 * </p>.
 *
 * @see Bean03
 * @see DaoBean03
 * @see Bean03Table
 */
public class DaoBean03Impl extends Dao implements DaoBean03 {
  
  /** The Constant SELECT_ONE_SQL1. */
  private static final String SELECT_ONE_SQL1 = "SELECT id, text FROM bean03 WHERE id=?";

  /** The delete one prepared statement 0. */
  private static SQLiteStatement deleteOnePreparedStatement0;

  /**
   * Instantiates a new dao bean 03 impl.
   *
   * @param context the context
   */
  public DaoBean03Impl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, text FROM bean03 WHERE id=${id}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
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
  public Bean03 selectOne(long id) {
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

      Bean03 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("text");

        resultBean=new Bean03();

        resultBean.setId(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.setText(_cursor.getString(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean03 WHERE id=${id}</pre>
   * 
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>.
   *
   * @param id 	is used as where parameter <strong>${id}</strong>
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    if (deleteOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM bean03 WHERE id=?";
      deleteOnePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOnePreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM bean03 WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (deleteOnePreparedStatement0!=null) {
      deleteOnePreparedStatement0.close();
      deleteOnePreparedStatement0=null;
    }
  }
}
