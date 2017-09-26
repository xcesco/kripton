package sqlite.feature.typeadapter.kripton180;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
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
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeDao</code>
 * </p>
 *
 *  @see Employee
 *  @see EmployeeDao
 *  @see EmployeeTable
 */
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {
  public EmployeeDaoImpl(BindKripton180DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (${bean.fieldBoolean}, ${bean.fieldByte}, ${bean.fieldCharacter}, ${bean.fieldShort}, ${bean.fieldInteger}, ${bean.fieldLong}, ${bean.fieldFloat}, ${bean.fieldDouble}, ${bean.fieldString}, ${bean.fieldByteArray})</pre>
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
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Employee bean) {
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
    Logger.info("INSERT INTO employees (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (${bean.fieldBoolean}, ${bean.fieldByte}, ${bean.fieldCharacter}, ${bean.fieldShort}, ${bean.fieldInteger}, ${bean.fieldLong}, ${bean.fieldFloat}, ${bean.fieldDouble}, ${bean.fieldString}, ${bean.fieldByteArray})</pre>
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
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertJQL(Employee bean) {
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
    Logger.info("INSERT INTO employees (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE employees SET field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Updated columns:</h2>
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
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is mapped to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is mapped to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldCharacter}</dt><dd>is mapped to method's parameter <strong>bean.fieldCharacter</strong></dd>
   * 	<dt>${bean.fieldShort}</dt><dd>is mapped to method's parameter <strong>bean.fieldShort</strong></dd>
   * 	<dt>${bean.fieldInteger}</dt><dd>is mapped to method's parameter <strong>bean.fieldInteger</strong></dd>
   * 	<dt>${bean.fieldLong}</dt><dd>is mapped to method's parameter <strong>bean.fieldLong</strong></dd>
   * 	<dt>${bean.fieldFloat}</dt><dd>is mapped to method's parameter <strong>bean.fieldFloat</strong></dd>
   * 	<dt>${bean.fieldDouble}</dt><dd>is mapped to method's parameter <strong>bean.fieldDouble</strong></dd>
   * 	<dt>${bean.fieldString}</dt><dd>is mapped to method's parameter <strong>bean.fieldString</strong></dd>
   * 	<dt>${bean.fieldByteArray}</dt><dd>is mapped to method's parameter <strong>bean.fieldByteArray</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(Employee bean) {
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

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE employees SET field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("employees", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE employees SET field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
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
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateById(Employee bean) {
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

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE employees SET field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=?");

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

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("employees", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM employees WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is mapped to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is mapped to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldCharacter}</dt><dd>is mapped to method's parameter <strong>bean.fieldCharacter</strong></dd>
   * 	<dt>${bean.fieldShort}</dt><dd>is mapped to method's parameter <strong>bean.fieldShort</strong></dd>
   * 	<dt>${bean.fieldInteger}</dt><dd>is mapped to method's parameter <strong>bean.fieldInteger</strong></dd>
   * 	<dt>${bean.fieldLong}</dt><dd>is mapped to method's parameter <strong>bean.fieldLong</strong></dd>
   * 	<dt>${bean.fieldFloat}</dt><dd>is mapped to method's parameter <strong>bean.fieldFloat</strong></dd>
   * 	<dt>${bean.fieldDouble}</dt><dd>is mapped to method's parameter <strong>bean.fieldDouble</strong></dd>
   * 	<dt>${bean.fieldString}</dt><dd>is mapped to method's parameter <strong>bean.fieldString</strong></dd>
   * 	<dt>${bean.fieldByteArray}</dt><dd>is mapped to method's parameter <strong>bean.fieldByteArray</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Employee bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM employees WHERE id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("employees", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM employees WHERE id=${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long deleteById(Employee bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM employees WHERE id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("employees", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }
}
