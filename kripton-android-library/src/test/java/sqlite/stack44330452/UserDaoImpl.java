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
package sqlite.stack44330452;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>UserDao</code>
 * </p>.
 *
 * @see User
 * @see UserDao
 * @see UserTable
 */
public class UserDaoImpl extends Dao implements UserDao {
  
  /** The Constant LOAD_USER_SQL1. */
  private static final String LOAD_USER_SQL1 = "SELECT id FROM user";

  /**
   * Instantiates a new user dao impl.
   *
   * @param context the context
   */
  public UserDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id FROM user</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>.
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> loadUser() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=LOAD_USER_SQL1;
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

      ArrayList<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");

        do
         {
          resultBean=new User();

          resultBean.id=_cursor.getLong(index0);

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
  }
}
