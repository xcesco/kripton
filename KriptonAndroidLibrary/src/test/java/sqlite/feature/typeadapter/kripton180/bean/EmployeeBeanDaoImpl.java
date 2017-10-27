package sqlite.feature.typeadapter.kripton180.bean;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterAddress;
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
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeBeanDao</code>
 * </p>
 *
 *  @see Employee
 *  @see EmployeeBeanDao
 *  @see sqlite.feature.typeadapter.kripton180.EmployeeTable
 */
public class EmployeeBeanDaoImpl extends AbstractDao implements EmployeeBeanDao {
  public EmployeeBeanDaoImpl(BindKripton180BeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${bean.id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectById(Employee bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${bean.id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByIdJQL(Employee bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is binded to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is binded to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldCharacter}</dt><dd>is binded to method's parameter <strong>bean.fieldCharacter</strong></dd>
   * 	<dt>${bean.fieldShort}</dt><dd>is binded to method's parameter <strong>bean.fieldShort</strong></dd>
   * 	<dt>${bean.fieldInteger}</dt><dd>is binded to method's parameter <strong>bean.fieldInteger</strong></dd>
   * 	<dt>${bean.fieldLong}</dt><dd>is binded to method's parameter <strong>bean.fieldLong</strong></dd>
   * 	<dt>${bean.fieldFloat}</dt><dd>is binded to method's parameter <strong>bean.fieldFloat</strong></dd>
   * 	<dt>${bean.fieldDouble}</dt><dd>is binded to method's parameter <strong>bean.fieldDouble</strong></dd>
   * 	<dt>${bean.fieldString}</dt><dd>is binded to method's parameter <strong>bean.fieldString</strong></dd>
   * 	<dt>${bean.fieldByteArray}</dt><dd>is binded to method's parameter <strong>bean.fieldByteArray</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAll(Employee bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
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
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>hire_date</dt><dd>is associated to bean's property <strong>hireDate</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>field_boolean</dt><dd>is associated to bean's property <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is associated to bean's property <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is associated to bean's property <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is associated to bean's property <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is associated to bean's property <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is associated to bean's property <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is associated to bean's property <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is associated to bean's property <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is associated to bean's property <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is associated to bean's property <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is binded to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldByte}</dt><dd>is binded to method's parameter <strong>bean.fieldByte</strong></dd>
   * 	<dt>${bean.fieldCharacter}</dt><dd>is binded to method's parameter <strong>bean.fieldCharacter</strong></dd>
   * 	<dt>${bean.fieldShort}</dt><dd>is binded to method's parameter <strong>bean.fieldShort</strong></dd>
   * 	<dt>${bean.fieldInteger}</dt><dd>is binded to method's parameter <strong>bean.fieldInteger</strong></dd>
   * 	<dt>${bean.fieldLong}</dt><dd>is binded to method's parameter <strong>bean.fieldLong</strong></dd>
   * 	<dt>${bean.fieldFloat}</dt><dd>is binded to method's parameter <strong>bean.fieldFloat</strong></dd>
   * 	<dt>${bean.fieldDouble}</dt><dd>is binded to method's parameter <strong>bean.fieldDouble</strong></dd>
   * 	<dt>${bean.fieldString}</dt><dd>is binded to method's parameter <strong>bean.fieldString</strong></dd>
   * 	<dt>${bean.fieldByteArray}</dt><dd>is binded to method's parameter <strong>bean.fieldByteArray</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAllJQL(Employee bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
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
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Employee resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("last_name");
        int index2=cursor.getColumnIndex("first_name");
        int index3=cursor.getColumnIndex("birth_date");
        int index4=cursor.getColumnIndex("hire_date");
        int index5=cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.lastName=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.firstName=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(cursor.getString(index5)); }
        if (!cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(cursor.getInt(index6)==0?false:true); }
        if (!cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)cursor.getInt(index7)); }
        if (!cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)cursor.getInt(index8)); }
        if (!cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(cursor.getShort(index9)); }
        if (!cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(cursor.getInt(index10)); }
        if (!cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(cursor.getLong(index11)); }
        if (!cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(cursor.getFloat(index12)); }
        if (!cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(cursor.getDouble(index13)); }
        if (!cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(cursor.getBlob(index15)); }

      }
      return resultBean;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO employees (last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (${bean.lastName}, ${bean.firstName}, ${bean.birthDate}, ${bean.hireDate}, ${bean.address}, ${bean.fieldBoolean}, ${bean.fieldByte}, ${bean.fieldCharacter}, ${bean.fieldShort}, ${bean.fieldInteger}, ${bean.fieldLong}, ${bean.fieldFloat}, ${bean.fieldDouble}, ${bean.fieldString}, ${bean.fieldByteArray})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>last_name</dt><dd>is mapped to <strong>${bean.lastName}</strong></dd>
   * 	<dt>first_name</dt><dd>is mapped to <strong>${bean.firstName}</strong></dd>
   * 	<dt>birth_date</dt><dd>is mapped to <strong>${bean.birthDate}</strong></dd>
   * 	<dt>hire_date</dt><dd>is mapped to <strong>${bean.hireDate}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
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

    if (bean.lastName!=null) {
      contentValues.put("last_name", bean.lastName);
    } else {
      contentValues.putNull("last_name");
    }
    if (bean.firstName!=null) {
      contentValues.put("first_name", bean.firstName);
    } else {
      contentValues.putNull("first_name");
    }
    if (bean.birthDate!=null) {
      contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    } else {
      contentValues.putNull("birth_date");
    }
    if (bean.hireDate!=null) {
      contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    } else {
      contentValues.putNull("hire_date");
    }
    if (bean.address!=null) {
      contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
    } else {
      contentValues.putNull("address");
    }
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
   * <pre>UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address, field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>last_name</dt><dd>is mapped to <strong>${bean.lastName}</strong></dd>
   * 	<dt>first_name</dt><dd>is mapped to <strong>${bean.firstName}</strong></dd>
   * 	<dt>birth_date</dt><dd>is mapped to <strong>${bean.birthDate}</strong></dd>
   * 	<dt>hire_date</dt><dd>is mapped to <strong>${bean.hireDate}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
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

    if (bean.lastName!=null) {
      contentValues.put("last_name", bean.lastName);
    } else {
      contentValues.putNull("last_name");
    }
    if (bean.firstName!=null) {
      contentValues.put("first_name", bean.firstName);
    } else {
      contentValues.putNull("first_name");
    }
    if (bean.birthDate!=null) {
      contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    } else {
      contentValues.putNull("birth_date");
    }
    if (bean.hireDate!=null) {
      contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    } else {
      contentValues.putNull("hire_date");
    }
    if (bean.address!=null) {
      contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
    } else {
      contentValues.putNull("address");
    }
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
    String _sqlWhereStatement=" id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address, field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
   * <pre>UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address, field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>last_name</dt><dd>is mapped to <strong>${bean.lastName}</strong></dd>
   * 	<dt>first_name</dt><dd>is mapped to <strong>${bean.firstName}</strong></dd>
   * 	<dt>birth_date</dt><dd>is mapped to <strong>${bean.birthDate}</strong></dd>
   * 	<dt>hire_date</dt><dd>is mapped to <strong>${bean.hireDate}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
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

    if (bean.lastName!=null) {
      contentValues.put("last_name", bean.lastName);
    } else {
      contentValues.putNull("last_name");
    }
    if (bean.firstName!=null) {
      contentValues.put("first_name", bean.firstName);
    } else {
      contentValues.putNull("first_name");
    }
    if (bean.birthDate!=null) {
      contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    } else {
      contentValues.putNull("birth_date");
    }
    if (bean.hireDate!=null) {
      contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    } else {
      contentValues.putNull("hire_date");
    }
    if (bean.address!=null) {
      contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
    } else {
      contentValues.putNull("address");
    }
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
    Logger.info("UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address, field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=?");

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
   * <pre>UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>last_name</dt><dd>is mapped to <strong>${bean.lastName}</strong></dd>
   * 	<dt>first_name</dt><dd>is mapped to <strong>${bean.firstName}</strong></dd>
   * 	<dt>birth_date</dt><dd>is mapped to <strong>${bean.birthDate}</strong></dd>
   * 	<dt>hire_date</dt><dd>is mapped to <strong>${bean.hireDate}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
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
  public long updateJQL(Employee bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.lastName!=null) {
      contentValues.put("last_name", bean.lastName);
    } else {
      contentValues.putNull("last_name");
    }
    if (bean.firstName!=null) {
      contentValues.put("first_name", bean.firstName);
    } else {
      contentValues.putNull("first_name");
    }
    if (bean.birthDate!=null) {
      contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    } else {
      contentValues.putNull("birth_date");
    }
    if (bean.hireDate!=null) {
      contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    } else {
      contentValues.putNull("hire_date");
    }
    if (bean.address!=null) {
      contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
    } else {
      contentValues.putNull("address");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));
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
    String _sqlWhereStatement=" id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
   * <pre>UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>last_name</dt><dd>is mapped to <strong>${bean.lastName}</strong></dd>
   * 	<dt>first_name</dt><dd>is mapped to <strong>${bean.firstName}</strong></dd>
   * 	<dt>birth_date</dt><dd>is mapped to <strong>${bean.birthDate}</strong></dd>
   * 	<dt>hire_date</dt><dd>is mapped to <strong>${bean.hireDate}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
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
  public long updateByIdJQL(Employee bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.lastName!=null) {
      contentValues.put("last_name", bean.lastName);
    } else {
      contentValues.putNull("last_name");
    }
    if (bean.firstName!=null) {
      contentValues.put("first_name", bean.firstName);
    } else {
      contentValues.putNull("first_name");
    }
    if (bean.birthDate!=null) {
      contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    } else {
      contentValues.putNull("birth_date");
    }
    if (bean.hireDate!=null) {
      contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    } else {
      contentValues.putNull("hire_date");
    }
    if (bean.address!=null) {
      contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
    } else {
      contentValues.putNull("address");
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
    Logger.info("UPDATE employees SET last_name=:lastName, first_name=:firstName, birth_date=:birthDate, hire_date=:hireDate, address=:address WHERE id=?");

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
   * <pre>DELETE FROM employees WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
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
    String _sqlWhereStatement=" id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM employees WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
   * <pre>DELETE FROM employees WHERE id=${bean.id} and field_byte=${bean.fieldByte} and field_character=${bean.fieldCharacter} and field_short=${bean.fieldShort} and field_integer=${bean.fieldInteger} and field_long=${bean.fieldLong} and field_float=${bean.fieldFloat} and field_double=${bean.fieldDouble} and field_string=${bean.fieldString} and field_byte_array=${bean.fieldByteArray}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
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
  public long deleteJQL(Employee bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));
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
    String _sqlWhereStatement=" id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM employees WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
