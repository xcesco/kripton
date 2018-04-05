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
 * DAO implementation for entity <code>Pet</code>, based on interface <code>PetDao</code>
 * </p>.
 *
 * @see Pet
 * @see PetDao
 * @see PetTable
 */
public class PetDaoImpl extends Dao implements PetDao {
  
  /** The Constant LOAD_PET_SQL2. */
  private static final String LOAD_PET_SQL2 = "SELECT id, user_id, name FROM pet";

  /**
   * Instantiates a new pet dao impl.
   *
   * @param context the context
   */
  public PetDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT id, user_id, name FROM pet</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>.
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Pet> loadPet() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=LOAD_PET_SQL2;
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

      ArrayList<Pet> resultList=new ArrayList<Pet>(_cursor.getCount());
      Pet resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("user_id");
        int index2=_cursor.getColumnIndex("name");

        do
         {
          resultBean=new Pet();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.userId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { resultBean.name=_cursor.getString(index2); }

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
