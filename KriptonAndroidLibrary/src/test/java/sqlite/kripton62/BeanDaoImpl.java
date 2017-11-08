package sqlite.kripton62;

import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean</code>, based on interface <code>BeanDao</code>
 * </p>
 *
 *  @see Bean
 *  @see BeanDao
 *  @see BeanTable
 */
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";

    // build where condition
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

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_byte_set");
        int index3=cursor.getColumnIndex("value_short_set");
        int index4=cursor.getColumnIndex("value_integer_set");
        int index5=cursor.getColumnIndex("value_string_set");
        int index6=cursor.getColumnIndex("value_character_set");
        int index7=cursor.getColumnIndex("value_float_set");
        int index8=cursor.getColumnIndex("value_double_set");
        int index9=cursor.getColumnIndex("value_big_decimal_set");
        int index10=cursor.getColumnIndex("value_bean_set");
        int index11=cursor.getColumnIndex("value_enum_type_set");

        resultBean=new Bean();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueByteSet=BeanTable.parseValueByteSet(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueShortSet=BeanTable.parseValueShortSet(cursor.getBlob(index3)); }
        if (!cursor.isNull(index4)) { resultBean.valueIntegerSet=BeanTable.parseValueIntegerSet(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.valueStringSet=BeanTable.parseValueStringSet(cursor.getBlob(index5)); }
        if (!cursor.isNull(index6)) { resultBean.valueCharacterSet=BeanTable.parseValueCharacterSet(cursor.getBlob(index6)); }
        if (!cursor.isNull(index7)) { resultBean.valueFloatSet=BeanTable.parseValueFloatSet(cursor.getBlob(index7)); }
        if (!cursor.isNull(index8)) { resultBean.valueDoubleSet=BeanTable.parseValueDoubleSet(cursor.getBlob(index8)); }
        if (!cursor.isNull(index9)) { resultBean.valueBigDecimalSet=BeanTable.parseValueBigDecimalSet(cursor.getBlob(index9)); }
        if (!cursor.isNull(index10)) { resultBean.valueBeanSet=BeanTable.parseValueBeanSet(cursor.getBlob(index10)); }
        if (!cursor.isNull(index11)) { resultBean.valueEnumTypeSet=BeanTable.parseValueEnumTypeSet(cursor.getBlob(index11)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the Bean listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean> listener) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
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
      Bean resultBean=new Bean();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_byte_set");
        int index3=cursor.getColumnIndex("value_short_set");
        int index4=cursor.getColumnIndex("value_integer_set");
        int index5=cursor.getColumnIndex("value_string_set");
        int index6=cursor.getColumnIndex("value_character_set");
        int index7=cursor.getColumnIndex("value_float_set");
        int index8=cursor.getColumnIndex("value_double_set");
        int index9=cursor.getColumnIndex("value_big_decimal_set");
        int index10=cursor.getColumnIndex("value_bean_set");
        int index11=cursor.getColumnIndex("value_enum_type_set");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.value=null;
          resultBean.valueByteSet=null;
          resultBean.valueShortSet=null;
          resultBean.valueIntegerSet=null;
          resultBean.valueStringSet=null;
          resultBean.valueCharacterSet=null;
          resultBean.valueFloatSet=null;
          resultBean.valueDoubleSet=null;
          resultBean.valueBigDecimalSet=null;
          resultBean.valueBeanSet=null;
          resultBean.valueEnumTypeSet=null;

          // generate mapping
          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueByteSet=BeanTable.parseValueByteSet(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueShortSet=BeanTable.parseValueShortSet(cursor.getBlob(index3)); }
          if (!cursor.isNull(index4)) { resultBean.valueIntegerSet=BeanTable.parseValueIntegerSet(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.valueStringSet=BeanTable.parseValueStringSet(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.valueCharacterSet=BeanTable.parseValueCharacterSet(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.valueFloatSet=BeanTable.parseValueFloatSet(cursor.getBlob(index7)); }
          if (!cursor.isNull(index8)) { resultBean.valueDoubleSet=BeanTable.parseValueDoubleSet(cursor.getBlob(index8)); }
          if (!cursor.isNull(index9)) { resultBean.valueBigDecimalSet=BeanTable.parseValueBigDecimalSet(cursor.getBlob(index9)); }
          if (!cursor.isNull(index10)) { resultBean.valueBeanSet=BeanTable.parseValueBeanSet(cursor.getBlob(index10)); }
          if (!cursor.isNull(index11)) { resultBean.valueEnumTypeSet=BeanTable.parseValueEnumTypeSet(cursor.getBlob(index11)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
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

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean> selectList(long id) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
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

      LinkedList<Bean> resultList=new LinkedList<Bean>();
      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_byte_set");
        int index3=cursor.getColumnIndex("value_short_set");
        int index4=cursor.getColumnIndex("value_integer_set");
        int index5=cursor.getColumnIndex("value_string_set");
        int index6=cursor.getColumnIndex("value_character_set");
        int index7=cursor.getColumnIndex("value_float_set");
        int index8=cursor.getColumnIndex("value_double_set");
        int index9=cursor.getColumnIndex("value_big_decimal_set");
        int index10=cursor.getColumnIndex("value_bean_set");
        int index11=cursor.getColumnIndex("value_enum_type_set");

        do
         {
          resultBean=new Bean();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueByteSet=BeanTable.parseValueByteSet(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueShortSet=BeanTable.parseValueShortSet(cursor.getBlob(index3)); }
          if (!cursor.isNull(index4)) { resultBean.valueIntegerSet=BeanTable.parseValueIntegerSet(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.valueStringSet=BeanTable.parseValueStringSet(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.valueCharacterSet=BeanTable.parseValueCharacterSet(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.valueFloatSet=BeanTable.parseValueFloatSet(cursor.getBlob(index7)); }
          if (!cursor.isNull(index8)) { resultBean.valueDoubleSet=BeanTable.parseValueDoubleSet(cursor.getBlob(index8)); }
          if (!cursor.isNull(index9)) { resultBean.valueBigDecimalSet=BeanTable.parseValueBigDecimalSet(cursor.getBlob(index9)); }
          if (!cursor.isNull(index10)) { resultBean.valueBeanSet=BeanTable.parseValueBeanSet(cursor.getBlob(index10)); }
          if (!cursor.isNull(index11)) { resultBean.valueEnumTypeSet=BeanTable.parseValueEnumTypeSet(cursor.getBlob(index11)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET value=:value, value_byte_set=:valueByteSet, value_short_set=:valueShortSet, value_integer_set=:valueIntegerSet, value_string_set=:valueStringSet, value_character_set=:valueCharacterSet, value_float_set=:valueFloatSet, value_double_set=:valueDoubleSet, value_big_decimal_set=:valueBigDecimalSet, value_bean_set=:valueBeanSet, value_enum_type_set=:valueEnumTypeSet WHERE id=${value.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${value.value}</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is mapped to <strong>${value.valueByteSet}</strong></dd>
   * 	<dt>value_short_set</dt><dd>is mapped to <strong>${value.valueShortSet}</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is mapped to <strong>${value.valueIntegerSet}</strong></dd>
   * 	<dt>value_string_set</dt><dd>is mapped to <strong>${value.valueStringSet}</strong></dd>
   * 	<dt>value_character_set</dt><dd>is mapped to <strong>${value.valueCharacterSet}</strong></dd>
   * 	<dt>value_float_set</dt><dd>is mapped to <strong>${value.valueFloatSet}</strong></dd>
   * 	<dt>value_double_set</dt><dd>is mapped to <strong>${value.valueDoubleSet}</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is mapped to <strong>${value.valueBigDecimalSet}</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is mapped to <strong>${value.valueBeanSet}</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is mapped to <strong>${value.valueEnumTypeSet}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${value}
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean value) {
    KriptonContentValues _contentValues=contentValues();
    if (value.value!=null) {
      _contentValues.put("value", value.value);
    } else {
      _contentValues.putNull("value");
    }
    if (value.valueByteSet!=null) {
      _contentValues.put("value_byte_set", BeanTable.serializeValueByteSet(value.valueByteSet));
    } else {
      _contentValues.putNull("value_byte_set");
    }
    if (value.valueShortSet!=null) {
      _contentValues.put("value_short_set", BeanTable.serializeValueShortSet(value.valueShortSet));
    } else {
      _contentValues.putNull("value_short_set");
    }
    if (value.valueIntegerSet!=null) {
      _contentValues.put("value_integer_set", BeanTable.serializeValueIntegerSet(value.valueIntegerSet));
    } else {
      _contentValues.putNull("value_integer_set");
    }
    if (value.valueStringSet!=null) {
      _contentValues.put("value_string_set", BeanTable.serializeValueStringSet(value.valueStringSet));
    } else {
      _contentValues.putNull("value_string_set");
    }
    if (value.valueCharacterSet!=null) {
      _contentValues.put("value_character_set", BeanTable.serializeValueCharacterSet(value.valueCharacterSet));
    } else {
      _contentValues.putNull("value_character_set");
    }
    if (value.valueFloatSet!=null) {
      _contentValues.put("value_float_set", BeanTable.serializeValueFloatSet(value.valueFloatSet));
    } else {
      _contentValues.putNull("value_float_set");
    }
    if (value.valueDoubleSet!=null) {
      _contentValues.put("value_double_set", BeanTable.serializeValueDoubleSet(value.valueDoubleSet));
    } else {
      _contentValues.putNull("value_double_set");
    }
    if (value.valueBigDecimalSet!=null) {
      _contentValues.put("value_big_decimal_set", BeanTable.serializeValueBigDecimalSet(value.valueBigDecimalSet));
    } else {
      _contentValues.putNull("value_big_decimal_set");
    }
    if (value.valueBeanSet!=null) {
      _contentValues.put("value_bean_set", BeanTable.serializeValueBeanSet(value.valueBeanSet));
    } else {
      _contentValues.putNull("value_bean_set");
    }
    if (value.valueEnumTypeSet!=null) {
      _contentValues.put("value_enum_type_set", BeanTable.serializeValueEnumTypeSet(value.valueEnumTypeSet));
    } else {
      _contentValues.putNull("value_enum_type_set");
    }

    _contentValues.addWhereArgs(String.valueOf(value.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="UPDATE bean SET value=?, value_byte_set=?, value_short_set=?, value_integer_set=?, value_string_set=?, value_character_set=?, value_float_set=?, value_double_set=?, value_big_decimal_set=?, value_bean_set=?, value_enum_type_set=? WHERE id=?";

    // display log
    Logger.info("UPDATE bean SET value=:value, value_byte_set=:valueByteSet, value_short_set=:valueShortSet, value_integer_set=:valueIntegerSet, value_string_set=:valueStringSet, value_character_set=:valueCharacterSet, value_float_set=:valueFloatSet, value_double_set=:valueDoubleSet, value_big_decimal_set=:valueBigDecimalSet, value_bean_set=:valueBeanSet, value_enum_type_set=:valueEnumTypeSet WHERE id=?");

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
    int result = KriptonDatabaseWrapper.update(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set) VALUES (${bean.value}, ${bean.valueByteSet}, ${bean.valueShortSet}, ${bean.valueIntegerSet}, ${bean.valueStringSet}, ${bean.valueCharacterSet}, ${bean.valueFloatSet}, ${bean.valueDoubleSet}, ${bean.valueBigDecimalSet}, ${bean.valueBeanSet}, ${bean.valueEnumTypeSet})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is mapped to <strong>${bean.valueByteSet}</strong></dd>
   * 	<dt>value_short_set</dt><dd>is mapped to <strong>${bean.valueShortSet}</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is mapped to <strong>${bean.valueIntegerSet}</strong></dd>
   * 	<dt>value_string_set</dt><dd>is mapped to <strong>${bean.valueStringSet}</strong></dd>
   * 	<dt>value_character_set</dt><dd>is mapped to <strong>${bean.valueCharacterSet}</strong></dd>
   * 	<dt>value_float_set</dt><dd>is mapped to <strong>${bean.valueFloatSet}</strong></dd>
   * 	<dt>value_double_set</dt><dd>is mapped to <strong>${bean.valueDoubleSet}</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is mapped to <strong>${bean.valueBigDecimalSet}</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is mapped to <strong>${bean.valueBeanSet}</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is mapped to <strong>${bean.valueEnumTypeSet}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.value!=null) {
      _contentValues.put("value", bean.value);
    } else {
      _contentValues.putNull("value");
    }
    if (bean.valueByteSet!=null) {
      _contentValues.put("value_byte_set", BeanTable.serializeValueByteSet(bean.valueByteSet));
    } else {
      _contentValues.putNull("value_byte_set");
    }
    if (bean.valueShortSet!=null) {
      _contentValues.put("value_short_set", BeanTable.serializeValueShortSet(bean.valueShortSet));
    } else {
      _contentValues.putNull("value_short_set");
    }
    if (bean.valueIntegerSet!=null) {
      _contentValues.put("value_integer_set", BeanTable.serializeValueIntegerSet(bean.valueIntegerSet));
    } else {
      _contentValues.putNull("value_integer_set");
    }
    if (bean.valueStringSet!=null) {
      _contentValues.put("value_string_set", BeanTable.serializeValueStringSet(bean.valueStringSet));
    } else {
      _contentValues.putNull("value_string_set");
    }
    if (bean.valueCharacterSet!=null) {
      _contentValues.put("value_character_set", BeanTable.serializeValueCharacterSet(bean.valueCharacterSet));
    } else {
      _contentValues.putNull("value_character_set");
    }
    if (bean.valueFloatSet!=null) {
      _contentValues.put("value_float_set", BeanTable.serializeValueFloatSet(bean.valueFloatSet));
    } else {
      _contentValues.putNull("value_float_set");
    }
    if (bean.valueDoubleSet!=null) {
      _contentValues.put("value_double_set", BeanTable.serializeValueDoubleSet(bean.valueDoubleSet));
    } else {
      _contentValues.putNull("value_double_set");
    }
    if (bean.valueBigDecimalSet!=null) {
      _contentValues.put("value_big_decimal_set", BeanTable.serializeValueBigDecimalSet(bean.valueBigDecimalSet));
    } else {
      _contentValues.putNull("value_big_decimal_set");
    }
    if (bean.valueBeanSet!=null) {
      _contentValues.put("value_bean_set", BeanTable.serializeValueBeanSet(bean.valueBeanSet));
    } else {
      _contentValues.putNull("value_bean_set");
    }
    if (bean.valueEnumTypeSet!=null) {
      _contentValues.put("value_enum_type_set", BeanTable.serializeValueEnumTypeSet(bean.valueEnumTypeSet));
    } else {
      _contentValues.putNull("value_enum_type_set");
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
    Logger.info("INSERT INTO bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // generate SQL for insert
    String _sql=String.format("INSERT INTO bean (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean (value_big_decimal_set) VALUES (${valueBigDecimalSet})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>value_big_decimal_set</dt><dd>is binded to query's parameter <strong>${valueBigDecimalSet}</strong> and method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param valueBigDecimalSet
   * 	is binded to column value <strong>value_big_decimal_set</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(HashSet<BigDecimal> valueBigDecimalSet) {
    KriptonContentValues _contentValues=contentValues();

    if (valueBigDecimalSet!=null) {
      _contentValues.put("value_big_decimal_set", serializer1(valueBigDecimalSet));
    } else {
      _contentValues.putNull("value_big_decimal_set");
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
    Logger.info("INSERT INTO bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // generate SQL for insert
    String _sql=String.format("INSERT INTO bean (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE value=${valueBigDecimalSet}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimalSet}</dt><dd>is binded to method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param valueBigDecimalSet
   * 	is binded to <code>${valueBigDecimalSet}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(HashSet<BigDecimal> valueBigDecimalSet) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, value, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((valueBigDecimalSet==null?"":new String(serializer1(valueBigDecimalSet),StandardCharsets.UTF_8)));
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

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_byte_set");
        int index3=cursor.getColumnIndex("value_short_set");
        int index4=cursor.getColumnIndex("value_integer_set");
        int index5=cursor.getColumnIndex("value_string_set");
        int index6=cursor.getColumnIndex("value_character_set");
        int index7=cursor.getColumnIndex("value_float_set");
        int index8=cursor.getColumnIndex("value_double_set");
        int index9=cursor.getColumnIndex("value_big_decimal_set");
        int index10=cursor.getColumnIndex("value_bean_set");
        int index11=cursor.getColumnIndex("value_enum_type_set");

        resultBean=new Bean();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueByteSet=BeanTable.parseValueByteSet(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueShortSet=BeanTable.parseValueShortSet(cursor.getBlob(index3)); }
        if (!cursor.isNull(index4)) { resultBean.valueIntegerSet=BeanTable.parseValueIntegerSet(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.valueStringSet=BeanTable.parseValueStringSet(cursor.getBlob(index5)); }
        if (!cursor.isNull(index6)) { resultBean.valueCharacterSet=BeanTable.parseValueCharacterSet(cursor.getBlob(index6)); }
        if (!cursor.isNull(index7)) { resultBean.valueFloatSet=BeanTable.parseValueFloatSet(cursor.getBlob(index7)); }
        if (!cursor.isNull(index8)) { resultBean.valueDoubleSet=BeanTable.parseValueDoubleSet(cursor.getBlob(index8)); }
        if (!cursor.isNull(index9)) { resultBean.valueBigDecimalSet=BeanTable.parseValueBigDecimalSet(cursor.getBlob(index9)); }
        if (!cursor.isNull(index10)) { resultBean.valueBeanSet=BeanTable.parseValueBeanSet(cursor.getBlob(index10)); }
        if (!cursor.isNull(index11)) { resultBean.valueEnumTypeSet=BeanTable.parseValueEnumTypeSet(cursor.getBlob(index11)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean WHERE value=${valueBigDecimalSet}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimalSet}</dt><dd>is mapped to method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param valueBigDecimalSet
   * 	is used as where parameter <strong>${valueBigDecimalSet}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashSet<BigDecimal> valueBigDecimalSet) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs((valueBigDecimalSet==null?"":new String(serializer1(valueBigDecimalSet),StandardCharsets.UTF_8)));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="DELETE FROM bean WHERE value=?";

    // display log
    Logger.info("DELETE FROM bean WHERE value=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.delete(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean SET id=:id WHERE value=${valueBigDecimalSet}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimalSet}</dt><dd>is mapped to method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBigDecimalSet
   * 	is used as where parameter <strong>${valueBigDecimalSet}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, HashSet<BigDecimal> valueBigDecimalSet) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((valueBigDecimalSet==null?"":new String(serializer1(valueBigDecimalSet),StandardCharsets.UTF_8)));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="UPDATE bean SET id=? WHERE value=?";

    // display log
    Logger.info("UPDATE bean SET id=:id WHERE value=?");

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
    int result = KriptonDatabaseWrapper.update(dataSource, _sql, _contentValues);
    return result;
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(HashSet<BigDecimal> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (BigDecimal item: value) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigDecimalUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing
   */
  private HashSet<BigDecimal> parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      HashSet<BigDecimal> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<BigDecimal> collection=new HashSet<>();
        BigDecimal item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=BigDecimalUtils.read(jacksonParser.getText());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
