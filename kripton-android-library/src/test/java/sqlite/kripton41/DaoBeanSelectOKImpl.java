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
package sqlite.kripton41;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanSelectOK</code>
 * </p>.
 *
 * @see Bean01
 * @see DaoBeanSelectOK
 * @see Bean01Table
 */
public class DaoBeanSelectOKImpl extends Dao implements DaoBeanSelectOK {
  
  /** The Constant SELECT_DISTANCE_SQL1. */
  private static final String SELECT_DISTANCE_SQL1 = "SELECT count(*) FROM bean01 WHERE id=? and value=?";

  /**
   * Instantiates a new dao bean select OK impl.
   *
   * @param context the context
   */
  public DaoBeanSelectOKImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT count(*) FROM bean01 WHERE id=${id} and value=${value}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @param value 	is binded to <code>${value}</code>
   * @return single value extracted by query.
   */
  @Override
  public Boolean selectDistance(long id, double value) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_DISTANCE_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs(String.valueOf(value));
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
      Boolean result=null;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return null; }
        result=_cursor.getInt(0)==0?false:true;
      }
      return result;
    }
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
  }
}
