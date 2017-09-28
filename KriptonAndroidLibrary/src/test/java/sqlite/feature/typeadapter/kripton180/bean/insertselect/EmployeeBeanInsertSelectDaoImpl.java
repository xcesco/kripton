package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
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
public class EmployeeBeanInsertSelectDaoImpl extends AbstractDao implements EmployeeBeanInsertSelectDao {
  public EmployeeBeanInsertSelectDaoImpl(BindKripton180BeanInsertSelectDataSource dataSet) {
    super(dataSet);
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
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.fieldBoolean!=null) {
      contentValues.put("field_boolean", SQLTypeAdapterUtils.toData(TypeAdapterBoolean.class, bean.fieldBoolean));
    } else {
      contentValues.putNull("field_boolean");
    }
    if (bean.fieldByte!=null) {
      contentValues.put("field_byte", SQLTypeAdapterUtils.toData(TypeAdapterByte.class, bean.fieldByte));
    } else {
      contentValues.putNull("field_byte");
    }
    if (bean.fieldCharacter!=null) {
      contentValues.put("field_character", (int)SQLTypeAdapterUtils.toData(TypeAdapterChar.class, bean.fieldCharacter));
    } else {
      contentValues.putNull("field_character");
    }
    if (bean.fieldShort!=null) {
      contentValues.put("field_short", (int)SQLTypeAdapterUtils.toData(TypeAdapterShort.class, bean.fieldShort));
    } else {
      contentValues.putNull("field_short");
    }
    if (bean.fieldInteger!=null) {
      contentValues.put("field_integer", SQLTypeAdapterUtils.toData(TypeAdapterInteger.class, bean.fieldInteger));
    } else {
      contentValues.putNull("field_integer");
    }
    if (bean.fieldLong!=null) {
      contentValues.put("field_long", SQLTypeAdapterUtils.toData(TypeAdapterLong.class, bean.fieldLong));
    } else {
      contentValues.putNull("field_long");
    }
    if (bean.fieldFloat!=null) {
      contentValues.put("field_float", SQLTypeAdapterUtils.toData(TypeAdapterFloat.class, bean.fieldFloat));
    } else {
      contentValues.putNull("field_float");
    }
    if (bean.fieldDouble!=null) {
      contentValues.put("field_double", SQLTypeAdapterUtils.toData(TypeAdapterDouble.class, bean.fieldDouble));
    } else {
      contentValues.putNull("field_double");
    }
    if (bean.fieldString!=null) {
      contentValues.put("field_string", SQLTypeAdapterUtils.toData(TypeAdapterString.class, bean.fieldString));
    } else {
      contentValues.putNull("field_string");
    }
    if (bean.fieldByteArray!=null) {
      contentValues.put("field_byte_array", SQLTypeAdapterUtils.toData(TypeAdapterByteArray.class, bean.fieldByteArray));
    } else {
      contentValues.putNull("field_byte_array");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO employees (%s) select fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray  from employees where fieldBoolean=${bean.fieldBoolean} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("employees", null, contentValues);
    bean.id=result;
  }
}
