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
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanUpdateOK</code>
 * </p>.
 *
 * @see Bean01
 * @see DaoBeanUpdateOK
 * @see Bean01Table
 */
public class DaoBeanUpdateOKImpl extends Dao implements DaoBeanUpdateOK {
  
  /** The update distance prepared statement 0. */
  private static SQLiteStatement updateDistancePreparedStatement0;

  /**
   * Instantiates a new dao bean update OK impl.
   *
   * @param context the context
   */
  public DaoBeanUpdateOKImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean01 SET id=:id, value=:value WHERE id=${test}</pre>
   * 
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * 	<li>value</li>
   * </ul>
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${test}</dt><dd>is mapped to method's parameter <strong>test</strong></dd>
   * </dl>.
   *
   * @param id 	is used as updated field <strong>id</strong>
   * @param value 	is used as updated field <strong>value</strong>
   * @param test 	is used as where parameter <strong>${test}</strong>
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateDistance(long id, Double value, long test) {
    if (updateDistancePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean01 SET id=?, value=? WHERE id=?";
      updateDistancePreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateDistancePreparedStatement0);
    _contentValues.put("id", id);
    _contentValues.put("value", value);

    _contentValues.addWhereArgs(String.valueOf(test));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean01 SET id=:id, value=:value WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateDistancePreparedStatement0, _contentValues);
    return result!=0;
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (updateDistancePreparedStatement0!=null) {
      updateDistancePreparedStatement0.close();
      updateDistancePreparedStatement0=null;
    }
  }
}
