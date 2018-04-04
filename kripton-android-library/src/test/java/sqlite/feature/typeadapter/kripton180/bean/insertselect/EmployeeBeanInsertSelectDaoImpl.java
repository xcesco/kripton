package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import io.reactivex.subjects.PublishSubject;
import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;

/**
 * <p>
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeBeanInsertSelectDao</code>
 * </p>
 *
 *  @see Employee
 *  @see EmployeeBeanInsertSelectDao
 *  @see sqlite.feature.typeadapter.kripton180.EmployeeTable
 */
public class EmployeeBeanInsertSelectDaoImpl extends Dao implements EmployeeBeanInsertSelectDao {
  private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

  public EmployeeBeanInsertSelectDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=${bean.fieldBoolean} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>field_boolean</dt><dd>is mapped to <strong>${bean.fieldBoolean}</strong></dd>
   * 	<dt>field_byte</dt><dd>is mapped to <strong>${bean.fieldByte}</strong></dd>
   * 	<dt>field_character</dt><dd>is mapped to <strong>${bean.fieldCharacter}</strong></dd>
   * 	<dt>field_short</dt><dd>is mapped to <strong>${bean.fieldShort}</strong></dd>
   * 	<dt>field_integer</dt><dd>is mapped to <strong>${bean.fieldInteger}</strong></dd>
   * 	<dt>field_long</dt><dd>is mapped to <strong>${bean.fieldLong}</strong></dd>
   * 	<dt>field_float</dt><dd>is mapped to <strong>${bean.fieldFloat}</strong></dd>
   * 	<dt>field_double</dt><dd>is mapped to <strong>${bean.fieldDouble}</strong></dd>
   * 	<dt>field_string</dt><dd>is mapped to <strong>${bean.fieldString}</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is mapped to <strong>${bean.fieldByteArray}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertJQL(Employee bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("field_boolean", SQLTypeAdapterUtils.toData(TypeAdapterBoolean.class, bean.fieldBoolean));
    _contentValues.put("field_byte", SQLTypeAdapterUtils.toData(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.put("field_character", SQLTypeAdapterUtils.toData(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.put("field_short", SQLTypeAdapterUtils.toData(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.put("field_integer", SQLTypeAdapterUtils.toData(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.put("field_long", SQLTypeAdapterUtils.toData(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.put("field_float", SQLTypeAdapterUtils.toData(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.put("field_double", SQLTypeAdapterUtils.toData(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.put("field_string", SQLTypeAdapterUtils.toData(TypeAdapterString.class, bean.fieldString));
    _contentValues.put("field_byte_array", SQLTypeAdapterUtils.toData(TypeAdapterByteArray.class, bean.fieldByteArray));

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
    // insert operation
    // generate SQL for insert
    String _sql=String.format("INSERT INTO employees (%s) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?", _contentValues.keyList());
    long result = KriptonDatabaseWrapper.insert(_context, _sql, _contentValues);
    if (result>0) {
      subject.onNext(SQLiteEvent.createInsert(result));
    }
    bean.id=result;
  }

  public PublishSubject<SQLiteEvent> subject() {
    return subject;
  }

  public static void clearCompiledStatements() {
  }
}
