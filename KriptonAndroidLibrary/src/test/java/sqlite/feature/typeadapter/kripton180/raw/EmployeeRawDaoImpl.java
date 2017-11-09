package sqlite.feature.typeadapter.kripton180.raw;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
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
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeRawDao</code>
 * </p>
 *
 *  @see Employee
 *  @see EmployeeRawDao
 *  @see sqlite.feature.typeadapter.kripton180.EmployeeTable
 */
public class EmployeeRawDaoImpl extends AbstractDao implements EmployeeRawDao {
  private SQLiteStatement insertPreparedStatement0;

  private SQLiteStatement insertWithAdapterPreparedStatement1;

  public EmployeeRawDaoImpl(BindKripton180RawDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
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
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByIdJQL(long id) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
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
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is binded to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is binded to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is binded to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is binded to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is binded to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is binded to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is binded to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is binded to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is binded to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param fieldBoolean
   * 	is binded to <code>${fieldBoolean}</code>
   * @param fieldByte
   * 	is binded to <code>${fieldByte}</code>
   * @param fieldCharacter
   * 	is binded to <code>${fieldCharacter}</code>
   * @param fieldShort
   * 	is binded to <code>${fieldShort}</code>
   * @param fieldInteger
   * 	is binded to <code>${fieldInteger}</code>
   * @param fieldLong
   * 	is binded to <code>${fieldLong}</code>
   * @param fieldFloat
   * 	is binded to <code>${fieldFloat}</code>
   * @param fieldDouble
   * 	is binded to <code>${fieldDouble}</code>
   * @param fieldString
   * 	is binded to <code>${fieldString}</code>
   * @param fieldByteArray
   * 	is binded to <code>${fieldByteArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAllWithAdapter(long id, String fieldBoolean, String fieldByte,
      String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong,
      String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
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
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
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
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is binded to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is binded to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is binded to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is binded to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is binded to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is binded to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is binded to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is binded to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is binded to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param fieldBoolean
   * 	is binded to <code>${fieldBoolean}</code>
   * @param fieldByte
   * 	is binded to <code>${fieldByte}</code>
   * @param fieldCharacter
   * 	is binded to <code>${fieldCharacter}</code>
   * @param fieldShort
   * 	is binded to <code>${fieldShort}</code>
   * @param fieldInteger
   * 	is binded to <code>${fieldInteger}</code>
   * @param fieldLong
   * 	is binded to <code>${fieldLong}</code>
   * @param fieldFloat
   * 	is binded to <code>${fieldFloat}</code>
   * @param fieldDouble
   * 	is binded to <code>${fieldDouble}</code>
   * @param fieldString
   * 	is binded to <code>${fieldString}</code>
   * @param fieldByteArray
   * 	is binded to <code>${fieldByteArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAll(long id, String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterBoolean.class, fieldBoolean));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, fieldByteArray));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
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
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is binded to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is binded to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is binded to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is binded to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is binded to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is binded to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is binded to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is binded to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is binded to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param fieldBoolean
   * 	is binded to <code>${fieldBoolean}</code>
   * @param fieldByte
   * 	is binded to <code>${fieldByte}</code>
   * @param fieldCharacter
   * 	is binded to <code>${fieldCharacter}</code>
   * @param fieldShort
   * 	is binded to <code>${fieldShort}</code>
   * @param fieldInteger
   * 	is binded to <code>${fieldInteger}</code>
   * @param fieldLong
   * 	is binded to <code>${fieldLong}</code>
   * @param fieldFloat
   * 	is binded to <code>${fieldFloat}</code>
   * @param fieldDouble
   * 	is binded to <code>${fieldDouble}</code>
   * @param fieldString
   * 	is binded to <code>${fieldString}</code>
   * @param fieldByteArray
   * 	is binded to <code>${fieldByteArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAllJQL(long id, String fieldBoolean, String fieldByte,
      String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong,
      String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_boolean=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs((fieldBoolean==null?"":fieldBoolean));
    _contentValues.addWhereArgs((fieldByte==null?"":fieldByte));
    _contentValues.addWhereArgs((fieldByte==null?"":fieldByte));
    _contentValues.addWhereArgs((fieldCharacter==null?"":fieldCharacter));
    _contentValues.addWhereArgs((fieldShort==null?"":fieldShort));
    _contentValues.addWhereArgs((fieldInteger==null?"":fieldInteger));
    _contentValues.addWhereArgs((fieldLong==null?"":fieldLong));
    _contentValues.addWhereArgs((fieldFloat==null?"":fieldFloat));
    _contentValues.addWhereArgs((fieldDouble==null?"":fieldDouble));
    _contentValues.addWhereArgs((fieldString==null?"":fieldString));
    _contentValues.addWhereArgs((fieldByteArray==null?"":fieldByteArray));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
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
   * <pre>SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is binded to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is binded to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is binded to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is binded to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is binded to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is binded to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is binded to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is binded to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is binded to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is binded to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param fieldBoolean
   * 	is binded to <code>${fieldBoolean}</code>
   * @param fieldByte
   * 	is binded to <code>${fieldByte}</code>
   * @param fieldCharacter
   * 	is binded to <code>${fieldCharacter}</code>
   * @param fieldShort
   * 	is binded to <code>${fieldShort}</code>
   * @param fieldInteger
   * 	is binded to <code>${fieldInteger}</code>
   * @param fieldLong
   * 	is binded to <code>${fieldLong}</code>
   * @param fieldFloat
   * 	is binded to <code>${fieldFloat}</code>
   * @param fieldDouble
   * 	is binded to <code>${fieldDouble}</code>
   * @param fieldString
   * 	is binded to <code>${fieldString}</code>
   * @param fieldByteArray
   * 	is binded to <code>${fieldByteArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Employee selectByAllJQLWithAdapter(long id, String fieldBoolean, String fieldByte,
      String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong,
      String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, last_name, first_name, birth_date, hire_date, address, field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array FROM employees");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and field_boolean=? and field_byte=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterBoolean.class, fieldBoolean));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, fieldByteArray));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
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
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (${fieldBoolean}, ${fieldByte}, ${fieldCharacter}, ${fieldShort}, ${fieldInteger}, ${fieldLong}, ${fieldFloat}, ${fieldDouble}, ${fieldString}, ${fieldByteArray})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>field_boolean</dt><dd>is binded to query's parameter <strong>${fieldBoolean}</strong> and method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is binded to query's parameter <strong>${fieldByte}</strong> and method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is binded to query's parameter <strong>${fieldCharacter}</strong> and method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is binded to query's parameter <strong>${fieldShort}</strong> and method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is binded to query's parameter <strong>${fieldInteger}</strong> and method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is binded to query's parameter <strong>${fieldLong}</strong> and method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is binded to query's parameter <strong>${fieldFloat}</strong> and method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is binded to query's parameter <strong>${fieldDouble}</strong> and method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is binded to query's parameter <strong>${fieldString}</strong> and method's parameter <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is binded to query's parameter <strong>${fieldByteArray}</strong> and method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param fieldBoolean
   * 	is binded to column value <strong>field_boolean</strong>
   * @param fieldByte
   * 	is binded to column value <strong>field_byte</strong>
   * @param fieldCharacter
   * 	is binded to column value <strong>field_character</strong>
   * @param fieldShort
   * 	is binded to column value <strong>field_short</strong>
   * @param fieldInteger
   * 	is binded to column value <strong>field_integer</strong>
   * @param fieldLong
   * 	is binded to column value <strong>field_long</strong>
   * @param fieldFloat
   * 	is binded to column value <strong>field_float</strong>
   * @param fieldDouble
   * 	is binded to column value <strong>field_double</strong>
   * @param fieldString
   * 	is binded to column value <strong>field_string</strong>
   * @param fieldByteArray
   * 	is binded to column value <strong>field_byte_array</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();

    if (fieldBoolean!=null) {
      _contentValues.put("field_boolean", SQLTypeAdapterUtils.toData(TypeAdapterBoolean.class, fieldBoolean));
    } else {
      _contentValues.putNull("field_boolean");
    }
    if (fieldByte!=null) {
      _contentValues.put("field_byte", SQLTypeAdapterUtils.toData(TypeAdapterByte.class, fieldByte));
    } else {
      _contentValues.putNull("field_byte");
    }
    if (fieldCharacter!=null) {
      _contentValues.put("field_character", (int)SQLTypeAdapterUtils.toData(TypeAdapterChar.class, fieldCharacter));
    } else {
      _contentValues.putNull("field_character");
    }
    if (fieldShort!=null) {
      _contentValues.put("field_short", (int)SQLTypeAdapterUtils.toData(TypeAdapterShort.class, fieldShort));
    } else {
      _contentValues.putNull("field_short");
    }
    if (fieldInteger!=null) {
      _contentValues.put("field_integer", SQLTypeAdapterUtils.toData(TypeAdapterInteger.class, fieldInteger));
    } else {
      _contentValues.putNull("field_integer");
    }
    if (fieldLong!=null) {
      _contentValues.put("field_long", SQLTypeAdapterUtils.toData(TypeAdapterLong.class, fieldLong));
    } else {
      _contentValues.putNull("field_long");
    }
    if (fieldFloat!=null) {
      _contentValues.put("field_float", SQLTypeAdapterUtils.toData(TypeAdapterFloat.class, fieldFloat));
    } else {
      _contentValues.putNull("field_float");
    }
    if (fieldDouble!=null) {
      _contentValues.put("field_double", SQLTypeAdapterUtils.toData(TypeAdapterDouble.class, fieldDouble));
    } else {
      _contentValues.putNull("field_double");
    }
    if (fieldString!=null) {
      _contentValues.put("field_string", SQLTypeAdapterUtils.toData(TypeAdapterString.class, fieldString));
    } else {
      _contentValues.putNull("field_string");
    }
    if (fieldByteArray!=null) {
      _contentValues.put("field_byte_array", SQLTypeAdapterUtils.toData(TypeAdapterByteArray.class, fieldByteArray));
    } else {
      _contentValues.putNull("field_byte_array");
    }

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

    // insert operation
    if (insertPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO employees (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) VALUES (${fieldBoolean}, ${fieldByte}, ${fieldCharacter}, ${fieldShort}, ${fieldInteger}, ${fieldLong}, ${fieldFloat}, ${fieldDouble}, ${fieldString}, ${fieldByteArray})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>field_boolean</dt><dd>is binded to query's parameter <strong>${fieldBoolean}</strong> and method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>field_byte</dt><dd>is binded to query's parameter <strong>${fieldByte}</strong> and method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>field_character</dt><dd>is binded to query's parameter <strong>${fieldCharacter}</strong> and method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>field_short</dt><dd>is binded to query's parameter <strong>${fieldShort}</strong> and method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>field_integer</dt><dd>is binded to query's parameter <strong>${fieldInteger}</strong> and method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>field_long</dt><dd>is binded to query's parameter <strong>${fieldLong}</strong> and method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>field_float</dt><dd>is binded to query's parameter <strong>${fieldFloat}</strong> and method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>field_double</dt><dd>is binded to query's parameter <strong>${fieldDouble}</strong> and method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>field_string</dt><dd>is binded to query's parameter <strong>${fieldString}</strong> and method's parameter <strong>fieldString</strong></dd>
   * 	<dt>field_byte_array</dt><dd>is binded to query's parameter <strong>${fieldByteArray}</strong> and method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param fieldBoolean
   * 	is binded to column value <strong>field_boolean</strong>
   * @param fieldByte
   * 	is binded to column value <strong>field_byte</strong>
   * @param fieldCharacter
   * 	is binded to column value <strong>field_character</strong>
   * @param fieldShort
   * 	is binded to column value <strong>field_short</strong>
   * @param fieldInteger
   * 	is binded to column value <strong>field_integer</strong>
   * @param fieldLong
   * 	is binded to column value <strong>field_long</strong>
   * @param fieldFloat
   * 	is binded to column value <strong>field_float</strong>
   * @param fieldDouble
   * 	is binded to column value <strong>field_double</strong>
   * @param fieldString
   * 	is binded to column value <strong>field_string</strong>
   * @param fieldByteArray
   * 	is binded to column value <strong>field_byte_array</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertWithAdapter(String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();

    if (fieldBoolean!=null) {
      _contentValues.put("field_boolean", SQLTypeAdapterUtils.toData(TypeAdapterBoolean.class, fieldBoolean));
    } else {
      _contentValues.putNull("field_boolean");
    }
    if (fieldByte!=null) {
      _contentValues.put("field_byte", SQLTypeAdapterUtils.toData(TypeAdapterByte.class, fieldByte));
    } else {
      _contentValues.putNull("field_byte");
    }
    if (fieldCharacter!=null) {
      _contentValues.put("field_character", (int)SQLTypeAdapterUtils.toData(TypeAdapterChar.class, fieldCharacter));
    } else {
      _contentValues.putNull("field_character");
    }
    if (fieldShort!=null) {
      _contentValues.put("field_short", (int)SQLTypeAdapterUtils.toData(TypeAdapterShort.class, fieldShort));
    } else {
      _contentValues.putNull("field_short");
    }
    if (fieldInteger!=null) {
      _contentValues.put("field_integer", SQLTypeAdapterUtils.toData(TypeAdapterInteger.class, fieldInteger));
    } else {
      _contentValues.putNull("field_integer");
    }
    if (fieldLong!=null) {
      _contentValues.put("field_long", SQLTypeAdapterUtils.toData(TypeAdapterLong.class, fieldLong));
    } else {
      _contentValues.putNull("field_long");
    }
    if (fieldFloat!=null) {
      _contentValues.put("field_float", SQLTypeAdapterUtils.toData(TypeAdapterFloat.class, fieldFloat));
    } else {
      _contentValues.putNull("field_float");
    }
    if (fieldDouble!=null) {
      _contentValues.put("field_double", SQLTypeAdapterUtils.toData(TypeAdapterDouble.class, fieldDouble));
    } else {
      _contentValues.putNull("field_double");
    }
    if (fieldString!=null) {
      _contentValues.put("field_string", SQLTypeAdapterUtils.toData(TypeAdapterString.class, fieldString));
    } else {
      _contentValues.putNull("field_string");
    }
    if (fieldByteArray!=null) {
      _contentValues.put("field_byte_array", SQLTypeAdapterUtils.toData(TypeAdapterByteArray.class, fieldByteArray));
    } else {
      _contentValues.putNull("field_byte_array");
    }

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

    // insert operation
    if (insertWithAdapterPreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO employees (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertWithAdapterPreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertWithAdapterPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE employees SET field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>field_boolean</li>
   * 	<li>field_byte</li>
   * 	<li>field_character</li>
   * 	<li>field_short</li>
   * 	<li>field_integer</li>
   * 	<li>field_long</li>
   * 	<li>field_float</li>
   * 	<li>field_double</li>
   * 	<li>field_string</li>
   * 	<li>field_byte_array</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param fieldBoolean
   * 	is used as updated field <strong>fieldBoolean</strong>
   * @param fieldByte
   * 	is used as updated field <strong>fieldByte</strong>
   * @param fieldCharacter
   * 	is used as updated field <strong>fieldCharacter</strong>
   * @param fieldShort
   * 	is used as updated field <strong>fieldShort</strong>
   * @param fieldInteger
   * 	is used as updated field <strong>fieldInteger</strong>
   * @param fieldLong
   * 	is used as updated field <strong>fieldLong</strong>
   * @param fieldFloat
   * 	is used as updated field <strong>fieldFloat</strong>
   * @param fieldDouble
   * 	is used as updated field <strong>fieldDouble</strong>
   * @param fieldString
   * 	is used as updated field <strong>fieldString</strong>
   * @param fieldByteArray
   * 	is used as updated field <strong>fieldByteArray</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateById(long id, String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    if (fieldBoolean!=null) {
      _contentValues.put("field_boolean", SQLTypeAdapterUtils.toData(TypeAdapterBoolean.class, fieldBoolean));
    } else {
      _contentValues.putNull("field_boolean");
    }
    if (fieldByte!=null) {
      _contentValues.put("field_byte", SQLTypeAdapterUtils.toData(TypeAdapterByte.class, fieldByte));
    } else {
      _contentValues.putNull("field_byte");
    }
    if (fieldCharacter!=null) {
      _contentValues.put("field_character", (int)SQLTypeAdapterUtils.toData(TypeAdapterChar.class, fieldCharacter));
    } else {
      _contentValues.putNull("field_character");
    }
    if (fieldShort!=null) {
      _contentValues.put("field_short", (int)SQLTypeAdapterUtils.toData(TypeAdapterShort.class, fieldShort));
    } else {
      _contentValues.putNull("field_short");
    }
    if (fieldInteger!=null) {
      _contentValues.put("field_integer", SQLTypeAdapterUtils.toData(TypeAdapterInteger.class, fieldInteger));
    } else {
      _contentValues.putNull("field_integer");
    }
    if (fieldLong!=null) {
      _contentValues.put("field_long", SQLTypeAdapterUtils.toData(TypeAdapterLong.class, fieldLong));
    } else {
      _contentValues.putNull("field_long");
    }
    if (fieldFloat!=null) {
      _contentValues.put("field_float", SQLTypeAdapterUtils.toData(TypeAdapterFloat.class, fieldFloat));
    } else {
      _contentValues.putNull("field_float");
    }
    if (fieldDouble!=null) {
      _contentValues.put("field_double", SQLTypeAdapterUtils.toData(TypeAdapterDouble.class, fieldDouble));
    } else {
      _contentValues.putNull("field_double");
    }
    if (fieldString!=null) {
      _contentValues.put("field_string", SQLTypeAdapterUtils.toData(TypeAdapterString.class, fieldString));
    } else {
      _contentValues.putNull("field_string");
    }
    if (fieldByteArray!=null) {
      _contentValues.put("field_byte_array", SQLTypeAdapterUtils.toData(TypeAdapterByteArray.class, fieldByteArray));
    } else {
      _contentValues.putNull("field_byte_array");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="UPDATE employees SET field_boolean=?, field_byte=?, field_character=?, field_short=?, field_integer=?, field_long=?, field_float=?, field_double=?, field_string=?, field_byte_array=? WHERE id=?";

    // display log
    Logger.info("UPDATE employees SET field_boolean=:fieldBoolean, field_byte=:fieldByte, field_character=:fieldCharacter, field_short=:fieldShort, field_integer=:fieldInteger, field_long=:fieldLong, field_float=:fieldFloat, field_double=:fieldDouble, field_string=:fieldString, field_byte_array=:fieldByteArray WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE employees SET first_name=:firstName WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>first_name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is mapped to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is mapped to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is mapped to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is mapped to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is mapped to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is mapped to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is mapped to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is mapped to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is mapped to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is mapped to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param firstName
   * 	is used as updated field <strong>firstName</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param fieldBoolean
   * 	is used as where parameter <strong>${fieldBoolean}</strong>
   * @param fieldByte
   * 	is used as where parameter <strong>${fieldByte}</strong>
   * @param fieldCharacter
   * 	is used as where parameter <strong>${fieldCharacter}</strong>
   * @param fieldShort
   * 	is used as where parameter <strong>${fieldShort}</strong>
   * @param fieldInteger
   * 	is used as where parameter <strong>${fieldInteger}</strong>
   * @param fieldLong
   * 	is used as where parameter <strong>${fieldLong}</strong>
   * @param fieldFloat
   * 	is used as where parameter <strong>${fieldFloat}</strong>
   * @param fieldDouble
   * 	is used as where parameter <strong>${fieldDouble}</strong>
   * @param fieldString
   * 	is used as where parameter <strong>${fieldString}</strong>
   * @param fieldByteArray
   * 	is used as where parameter <strong>${fieldByteArray}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long update(String firstName, long id, String fieldBoolean, String fieldByte,
      String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong,
      String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    if (firstName!=null) {
      _contentValues.put("first_name", firstName);
    } else {
      _contentValues.putNull("first_name");
    }

    _contentValues.addWhereArgs(String.valueOf(id));
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

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="UPDATE employees SET first_name=? WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";

    // display log
    Logger.info("UPDATE employees SET first_name=:firstName WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is mapped to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is mapped to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is mapped to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is mapped to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is mapped to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is mapped to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is mapped to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is mapped to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is mapped to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is mapped to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param fieldBoolean
   * 	is used as where parameter <strong>${fieldBoolean}</strong>
   * @param fieldByte
   * 	is used as where parameter <strong>${fieldByte}</strong>
   * @param fieldCharacter
   * 	is used as where parameter <strong>${fieldCharacter}</strong>
   * @param fieldShort
   * 	is used as where parameter <strong>${fieldShort}</strong>
   * @param fieldInteger
   * 	is used as where parameter <strong>${fieldInteger}</strong>
   * @param fieldLong
   * 	is used as where parameter <strong>${fieldLong}</strong>
   * @param fieldFloat
   * 	is used as where parameter <strong>${fieldFloat}</strong>
   * @param fieldDouble
   * 	is used as where parameter <strong>${fieldDouble}</strong>
   * @param fieldString
   * 	is used as where parameter <strong>${fieldString}</strong>
   * @param fieldByteArray
   * 	is used as where parameter <strong>${fieldByteArray}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(long id, String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs(String.valueOf(id));
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

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="DELETE FROM employees WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";

    // display log
    Logger.info("DELETE FROM employees WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is mapped to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is mapped to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is mapped to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is mapped to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is mapped to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is mapped to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is mapped to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is mapped to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is mapped to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is mapped to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param fieldBoolean
   * 	is used as where parameter <strong>${fieldBoolean}</strong>
   * @param fieldByte
   * 	is used as where parameter <strong>${fieldByte}</strong>
   * @param fieldCharacter
   * 	is used as where parameter <strong>${fieldCharacter}</strong>
   * @param fieldShort
   * 	is used as where parameter <strong>${fieldShort}</strong>
   * @param fieldInteger
   * 	is used as where parameter <strong>${fieldInteger}</strong>
   * @param fieldLong
   * 	is used as where parameter <strong>${fieldLong}</strong>
   * @param fieldFloat
   * 	is used as where parameter <strong>${fieldFloat}</strong>
   * @param fieldDouble
   * 	is used as where parameter <strong>${fieldDouble}</strong>
   * @param fieldString
   * 	is used as where parameter <strong>${fieldString}</strong>
   * @param fieldByteArray
   * 	is used as where parameter <strong>${fieldByteArray}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteJQL(long id, String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs(String.valueOf(id));
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

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="DELETE FROM employees WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";

    // display log
    Logger.info("DELETE FROM employees WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM employees WHERE id=${id} and field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${fieldBoolean}</dt><dd>is mapped to method's parameter <strong>fieldBoolean</strong></dd>
   * 	<dt>${fieldByte}</dt><dd>is mapped to method's parameter <strong>fieldByte</strong></dd>
   * 	<dt>${fieldCharacter}</dt><dd>is mapped to method's parameter <strong>fieldCharacter</strong></dd>
   * 	<dt>${fieldShort}</dt><dd>is mapped to method's parameter <strong>fieldShort</strong></dd>
   * 	<dt>${fieldInteger}</dt><dd>is mapped to method's parameter <strong>fieldInteger</strong></dd>
   * 	<dt>${fieldLong}</dt><dd>is mapped to method's parameter <strong>fieldLong</strong></dd>
   * 	<dt>${fieldFloat}</dt><dd>is mapped to method's parameter <strong>fieldFloat</strong></dd>
   * 	<dt>${fieldDouble}</dt><dd>is mapped to method's parameter <strong>fieldDouble</strong></dd>
   * 	<dt>${fieldString}</dt><dd>is mapped to method's parameter <strong>fieldString</strong></dd>
   * 	<dt>${fieldByteArray}</dt><dd>is mapped to method's parameter <strong>fieldByteArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param fieldBoolean
   * 	is used as where parameter <strong>${fieldBoolean}</strong>
   * @param fieldByte
   * 	is used as where parameter <strong>${fieldByte}</strong>
   * @param fieldCharacter
   * 	is used as where parameter <strong>${fieldCharacter}</strong>
   * @param fieldShort
   * 	is used as where parameter <strong>${fieldShort}</strong>
   * @param fieldInteger
   * 	is used as where parameter <strong>${fieldInteger}</strong>
   * @param fieldLong
   * 	is used as where parameter <strong>${fieldLong}</strong>
   * @param fieldFloat
   * 	is used as where parameter <strong>${fieldFloat}</strong>
   * @param fieldDouble
   * 	is used as where parameter <strong>${fieldDouble}</strong>
   * @param fieldString
   * 	is used as where parameter <strong>${fieldString}</strong>
   * @param fieldByteArray
   * 	is used as where parameter <strong>${fieldByteArray}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteJQLWithAdapter(long id, String fieldBoolean, String fieldByte,
      String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong,
      String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterBoolean.class, fieldBoolean));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByte.class, fieldByte));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterChar.class, fieldCharacter));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterShort.class, fieldShort));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterInteger.class, fieldInteger));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterLong.class, fieldLong));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterFloat.class, fieldFloat));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterDouble.class, fieldDouble));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterString.class, fieldString));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(TypeAdapterByteArray.class, fieldByteArray));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="DELETE FROM employees WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?";

    // display log
    Logger.info("DELETE FROM employees WHERE id=? and field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result;
  }

  public void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (insertWithAdapterPreparedStatement1!=null) {
      insertWithAdapterPreparedStatement1.close();
      insertWithAdapterPreparedStatement1=null;
    }
  }
}
