package sqlite.feature.typeadapter.kripton180.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.orm.Dao;
import com.abubusoft.kripton.android.orm.KriptonContentValues;
import com.abubusoft.kripton.android.orm.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.orm.SQLContext;
import com.abubusoft.kripton.android.orm.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
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
public class EmployeeBeanDaoImpl extends Dao implements EmployeeBeanDao {
  private static final String SELECT_BY_ID_SQL1 = "SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=?";

  private static final String SELECT_BY_ID_J_Q_L_SQL2 = "SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=?";

  private static final String SELECT_BY_ALL_SQL3 = "SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";

  private static final String SELECT_BY_ALL_J_Q_L_SQL4 = "SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";

  private static SQLiteStatement insertPreparedStatement0;

  private static SQLiteStatement insertJQLPreparedStatement1;

  private static SQLiteStatement updatePreparedStatement2;

  private static SQLiteStatement updateByIdPreparedStatement3;

  private static SQLiteStatement updateJQLPreparedStatement4;

  private static SQLiteStatement updateByIdJQLPreparedStatement5;

  private static SQLiteStatement deletePreparedStatement6;

  private static SQLiteStatement deleteJQLPreparedStatement7;

  private static SQLiteStatement deleteByIdPreparedStatement8;

  public EmployeeBeanDaoImpl(SQLContext context) {
    super(context);
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
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

      Employee resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("last_name");
        int index2=_cursor.getColumnIndex("first_name");
        int index3=_cursor.getColumnIndex("birth_date");
        int index4=_cursor.getColumnIndex("hire_date");
        int index5=_cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=_cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=_cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=_cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=_cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=_cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=_cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=_cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=_cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=_cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=_cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.lastName=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.firstName=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(_cursor.getString(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(_cursor.getString(index5)); }
        if (!_cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(_cursor.getInt(index6)==0?false:true); }
        if (!_cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)_cursor.getInt(index7)); }
        if (!_cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)_cursor.getInt(index8)); }
        if (!_cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(_cursor.getShort(index9)); }
        if (!_cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(_cursor.getInt(index10)); }
        if (!_cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(_cursor.getLong(index11)); }
        if (!_cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(_cursor.getFloat(index12)); }
        if (!_cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(_cursor.getDouble(index13)); }
        if (!_cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(_cursor.getString(index14)); }
        if (!_cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(_cursor.getBlob(index15)); }

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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_J_Q_L_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
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

      Employee resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("last_name");
        int index2=_cursor.getColumnIndex("first_name");
        int index3=_cursor.getColumnIndex("birth_date");
        int index4=_cursor.getColumnIndex("hire_date");
        int index5=_cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=_cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=_cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=_cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=_cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=_cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=_cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=_cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=_cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=_cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=_cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.lastName=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.firstName=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(_cursor.getString(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(_cursor.getString(index5)); }
        if (!_cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(_cursor.getInt(index6)==0?false:true); }
        if (!_cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)_cursor.getInt(index7)); }
        if (!_cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)_cursor.getInt(index8)); }
        if (!_cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(_cursor.getShort(index9)); }
        if (!_cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(_cursor.getInt(index10)); }
        if (!_cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(_cursor.getLong(index11)); }
        if (!_cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(_cursor.getFloat(index12)); }
        if (!_cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(_cursor.getDouble(index13)); }
        if (!_cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(_cursor.getString(index14)); }
        if (!_cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(_cursor.getBlob(index15)); }

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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ALL_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));
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

      Employee resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("last_name");
        int index2=_cursor.getColumnIndex("first_name");
        int index3=_cursor.getColumnIndex("birth_date");
        int index4=_cursor.getColumnIndex("hire_date");
        int index5=_cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=_cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=_cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=_cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=_cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=_cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=_cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=_cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=_cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=_cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=_cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.lastName=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.firstName=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(_cursor.getString(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(_cursor.getString(index5)); }
        if (!_cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(_cursor.getInt(index6)==0?false:true); }
        if (!_cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)_cursor.getInt(index7)); }
        if (!_cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)_cursor.getInt(index8)); }
        if (!_cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(_cursor.getShort(index9)); }
        if (!_cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(_cursor.getInt(index10)); }
        if (!_cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(_cursor.getLong(index11)); }
        if (!_cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(_cursor.getFloat(index12)); }
        if (!_cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(_cursor.getDouble(index13)); }
        if (!_cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(_cursor.getString(index14)); }
        if (!_cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(_cursor.getBlob(index15)); }

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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ALL_J_Q_L_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));
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

      Employee resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("last_name");
        int index2=_cursor.getColumnIndex("first_name");
        int index3=_cursor.getColumnIndex("birth_date");
        int index4=_cursor.getColumnIndex("hire_date");
        int index5=_cursor.getColumnIndex("address");
        TypeAdapterAddress addressAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterAddress.class);
        int index6=_cursor.getColumnIndex("field_boolean");
        TypeAdapterBoolean fieldBooleanAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterBoolean.class);
        int index7=_cursor.getColumnIndex("field_byte");
        TypeAdapterByte fieldByteAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByte.class);
        int index8=_cursor.getColumnIndex("field_character");
        TypeAdapterChar fieldCharacterAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterChar.class);
        int index9=_cursor.getColumnIndex("field_short");
        TypeAdapterShort fieldShortAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterShort.class);
        int index10=_cursor.getColumnIndex("field_integer");
        TypeAdapterInteger fieldIntegerAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterInteger.class);
        int index11=_cursor.getColumnIndex("field_long");
        TypeAdapterLong fieldLongAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterLong.class);
        int index12=_cursor.getColumnIndex("field_float");
        TypeAdapterFloat fieldFloatAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterFloat.class);
        int index13=_cursor.getColumnIndex("field_double");
        TypeAdapterDouble fieldDoubleAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterDouble.class);
        int index14=_cursor.getColumnIndex("field_string");
        TypeAdapterString fieldStringAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterString.class);
        int index15=_cursor.getColumnIndex("field_byte_array");
        TypeAdapterByteArray fieldByteArrayAdapter=SQLTypeAdapterUtils.getAdapter(TypeAdapterByteArray.class);

        resultBean=new Employee();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.lastName=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.firstName=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.birthDate=SQLDateUtils.read(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.hireDate=SQLDateUtils.read(_cursor.getString(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.address=addressAdapter.toJava(_cursor.getString(index5)); }
        if (!_cursor.isNull(index6)) { resultBean.fieldBoolean=fieldBooleanAdapter.toJava(_cursor.getInt(index6)==0?false:true); }
        if (!_cursor.isNull(index7)) { resultBean.fieldByte=fieldByteAdapter.toJava((byte)_cursor.getInt(index7)); }
        if (!_cursor.isNull(index8)) { resultBean.fieldCharacter=fieldCharacterAdapter.toJava((char)_cursor.getInt(index8)); }
        if (!_cursor.isNull(index9)) { resultBean.fieldShort=fieldShortAdapter.toJava(_cursor.getShort(index9)); }
        if (!_cursor.isNull(index10)) { resultBean.fieldInteger=fieldIntegerAdapter.toJava(_cursor.getInt(index10)); }
        if (!_cursor.isNull(index11)) { resultBean.fieldLong=fieldLongAdapter.toJava(_cursor.getLong(index11)); }
        if (!_cursor.isNull(index12)) { resultBean.fieldFloat=fieldFloatAdapter.toJava(_cursor.getFloat(index12)); }
        if (!_cursor.isNull(index13)) { resultBean.fieldDouble=fieldDoubleAdapter.toJava(_cursor.getDouble(index13)); }
        if (!_cursor.isNull(index14)) { resultBean.fieldString=fieldStringAdapter.toJava(_cursor.getString(index14)); }
        if (!_cursor.isNull(index15)) { resultBean.fieldByteArray=fieldByteArrayAdapter.toJava(_cursor.getBlob(index15)); }

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
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO employees (last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("last_name", bean.lastName);
    _contentValues.put("first_name", bean.firstName);
    _contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    _contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    _contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
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
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO employees (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
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
    if (insertJQLPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertJQLPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertJQLPreparedStatement1);
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
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO employees (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertJQLPreparedStatement1, _contentValues);
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
    if (updatePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE employees SET last_name=?, first_name=?, birth_date=?, hire_date=?, address=?, field_boolean=?, field_byte=?, field_character=?, field_short=?, field_integer=?, field_long=?, field_float=?, field_double=?, field_string=?, field_byte_array=? WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
      updatePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement2);
    _contentValues.put("last_name", bean.lastName);
    _contentValues.put("first_name", bean.firstName);
    _contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    _contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    _contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
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

    _contentValues.addWhereArgs(String.valueOf(bean.id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE employees SET last_name=:last_name, first_name=:first_name, birth_date=:birth_date, hire_date=:hire_date, address=:address, field_boolean=:field_boolean, field_byte=:field_byte, field_character=:field_character, field_short=:field_short, field_integer=:field_integer, field_long=:field_long, field_float=:field_float, field_double=:field_double, field_string=:field_string, field_byte_array=:field_byte_array WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement2, _contentValues);
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
    if (updateByIdPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE employees SET last_name=?, first_name=?, birth_date=?, hire_date=?, address=?, field_boolean=?, field_byte=?, field_character=?, field_short=?, field_integer=?, field_long=?, field_float=?, field_double=?, field_string=?, field_byte_array=? WHERE id=?";
      updateByIdPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateByIdPreparedStatement3);
    _contentValues.put("last_name", bean.lastName);
    _contentValues.put("first_name", bean.firstName);
    _contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    _contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    _contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));
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

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE employees SET last_name=:last_name, first_name=:first_name, birth_date=:birth_date, hire_date=:hire_date, address=:address, field_boolean=:field_boolean, field_byte=:field_byte, field_character=:field_character, field_short=:field_short, field_integer=:field_integer, field_long=:field_long, field_float=:field_float, field_double=:field_double, field_string=:field_string, field_byte_array=:field_byte_array WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateByIdPreparedStatement3, _contentValues);
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
    if (updateJQLPreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="UPDATE employees SET last_name=?, first_name=?, birth_date=?, hire_date=?, address=? WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
      updateJQLPreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateJQLPreparedStatement4);
    _contentValues.put("last_name", bean.lastName);
    _contentValues.put("first_name", bean.firstName);
    _contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    _contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    _contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));

    _contentValues.addWhereArgs(String.valueOf(bean.id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE employees SET last_name=:last_name, first_name=:first_name, birth_date=:birth_date, hire_date=:hire_date, address=:address WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateJQLPreparedStatement4, _contentValues);
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
    if (updateByIdJQLPreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="UPDATE employees SET last_name=?, first_name=?, birth_date=?, hire_date=?, address=? WHERE id=?";
      updateByIdJQLPreparedStatement5 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateByIdJQLPreparedStatement5);
    _contentValues.put("last_name", bean.lastName);
    _contentValues.put("first_name", bean.firstName);
    _contentValues.put("birth_date", SQLDateUtils.write(bean.birthDate));
    _contentValues.put("hire_date", SQLDateUtils.write(bean.hireDate));
    _contentValues.put("address", SQLTypeAdapterUtils.toData(TypeAdapterAddress.class, bean.address));

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE employees SET last_name=:last_name, first_name=:first_name, birth_date=:birth_date, hire_date=:hire_date, address=:address WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateByIdJQLPreparedStatement5, _contentValues);
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
    if (deletePreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM employees WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
      deletePreparedStatement6 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement6);
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM employees WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deletePreparedStatement6, _contentValues);
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
    if (deleteJQLPreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM employees WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
      deleteJQLPreparedStatement7 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteJQLPreparedStatement7);
    _contentValues.addWhereArgs(String.valueOf(bean.id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, bean.fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, bean.fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, bean.fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, bean.fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, bean.fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, bean.fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, bean.fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, bean.fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, bean.fieldByteArray));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM employees WHERE id=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteJQLPreparedStatement7, _contentValues);
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
    if (deleteByIdPreparedStatement8==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM employees WHERE id=?";
      deleteByIdPreparedStatement8 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement8);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM employees WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement8, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (insertJQLPreparedStatement1!=null) {
      insertJQLPreparedStatement1.close();
      insertJQLPreparedStatement1=null;
    }
    if (updatePreparedStatement2!=null) {
      updatePreparedStatement2.close();
      updatePreparedStatement2=null;
    }
    if (updateByIdPreparedStatement3!=null) {
      updateByIdPreparedStatement3.close();
      updateByIdPreparedStatement3=null;
    }
    if (updateJQLPreparedStatement4!=null) {
      updateJQLPreparedStatement4.close();
      updateJQLPreparedStatement4=null;
    }
    if (updateByIdJQLPreparedStatement5!=null) {
      updateByIdJQLPreparedStatement5.close();
      updateByIdJQLPreparedStatement5=null;
    }
    if (deletePreparedStatement6!=null) {
      deletePreparedStatement6.close();
      deletePreparedStatement6=null;
    }
    if (deleteJQLPreparedStatement7!=null) {
      deleteJQLPreparedStatement7.close();
      deleteJQLPreparedStatement7=null;
    }
    if (deleteByIdPreparedStatement8!=null) {
      deleteByIdPreparedStatement8.close();
      deleteByIdPreparedStatement8=null;
    }
  }
}
