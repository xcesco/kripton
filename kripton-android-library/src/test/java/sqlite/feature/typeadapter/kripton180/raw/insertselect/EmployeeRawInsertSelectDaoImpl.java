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
package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import io.reactivex.subjects.PublishSubject;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeRawInsertSelectDao</code>
 * </p>.
 *
 * @see sqlite.feature.typeadapter.kripton180.Employee
 * @see EmployeeRawInsertSelectDao
 * @see sqlite.feature.typeadapter.kripton180.EmployeeTable
 */
public class EmployeeRawInsertSelectDaoImpl extends Dao implements EmployeeRawInsertSelectDao {
  
  /** The Constant subject. */
  private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

  /**
   * Instantiates a new employee raw insert select dao impl.
   *
   * @param context the context
   */
  public EmployeeRawInsertSelectDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   * 
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>fieldBoolean</dt><dd>is binded to query's parameter <strong>${fieldBoolean}</strong></dd>
   * 	<dt>fieldByte</dt><dd>is binded to query's parameter <strong>${fieldByte}</strong></dd>
   * 	<dt>fieldCharacter</dt><dd>is binded to query's parameter <strong>${fieldCharacter}</strong></dd>
   * 	<dt>fieldShort</dt><dd>is binded to query's parameter <strong>${fieldShort}</strong></dd>
   * 	<dt>fieldInteger</dt><dd>is binded to query's parameter <strong>${fieldInteger}</strong></dd>
   * 	<dt>fieldLong</dt><dd>is binded to query's parameter <strong>${fieldLong}</strong></dd>
   * 	<dt>fieldFloat</dt><dd>is binded to query's parameter <strong>${fieldFloat}</strong></dd>
   * 	<dt>fieldDouble</dt><dd>is binded to query's parameter <strong>${fieldDouble}</strong></dd>
   * 	<dt>fieldString</dt><dd>is binded to query's parameter <strong>${fieldString}</strong></dd>
   * 	<dt>fieldByteArray</dt><dd>is binded to query's parameter <strong>${fieldByteArray}</strong></dd>
   * </dl>.
   *
   * @param fieldBoolean 	is used as parameter
   * @param fieldByte 	is used as parameter
   * @param fieldCharacter 	is used as parameter
   * @param fieldShort 	is used as parameter
   * @param fieldInteger 	is used as parameter
   * @param fieldLong 	is used as parameter
   * @param fieldFloat 	is used as parameter
   * @param fieldDouble 	is used as parameter
   * @param fieldString 	is used as parameter
   * @param fieldByteArray 	is used as parameter
   */
  @Override
  public void insertJQL(String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    // build where condition
    _contentValues.addWhereArgs((fieldBoolean==null?"":fieldBoolean));
    _contentValues.addWhereArgs((fieldByte==null?"":fieldByte));
    _contentValues.addWhereArgs((fieldCharacter==null?"":fieldCharacter));
    _contentValues.addWhereArgs((fieldShort==null?"":fieldShort));
    _contentValues.addWhereArgs((fieldInteger==null?"":fieldInteger));
    _contentValues.addWhereArgs((fieldLong==null?"":fieldLong));
    _contentValues.addWhereArgs((fieldFloat==null?"":fieldFloat));
    _contentValues.addWhereArgs((fieldDouble==null?"":fieldDouble));
    _contentValues.addWhereArgs((fieldString==null?"":fieldString));
    _contentValues.addWhereArgs((fieldByteArray==null?"":fieldByteArray));
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 

      Logger.info("INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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

    database().execSQL("INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?", _contentValues.whereArgsAsArray());
  }

  /**
   * Subject.
   *
   * @return the publish subject
   */
  public PublishSubject<SQLiteEvent> subject() {
    return subject;
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
  }
}
