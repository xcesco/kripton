package com.abubusoft.kripton.processor.kripton60;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.CalendarUtil;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.CurrencyUtil;
import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.common.LocaleUtil;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.common.TimeUtil;
import com.abubusoft.kripton.common.TimeZoneUtil;
import com.abubusoft.kripton.common.UrlUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * <p>
 * DAO implementation for entity <code>Bean</code>, based on interface <code>BeanDao</code>
 * </p>
 *  @see Bean
 *  @see BeanDao
 *  @see Bean$Table
 */
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public boolean selectValueBoolType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    boolean result=false;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return false; }
        result=cursor.getInt(0)==0?false:true;
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Boolean selectValueBool() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_bool FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Boolean result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getInt(0)==0?false:true;
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_byte_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_byte_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public byte selectValueByteType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_byte_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_byte_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    byte result=0;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=(byte)cursor.getInt(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_byte FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_byte]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Byte selectValueByte() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_byte FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_byte FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Byte result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=(byte)cursor.getInt(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_short_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_short_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public short selectValueShortType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_short_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_short_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    short result=0;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getShort(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_short FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_short]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Short selectValueShort() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_short FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_short FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Short result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getShort(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_int_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_int_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public int selectValueIntType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_int_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_int_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    int result=0;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getInt(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_int FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_int]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Integer selectValueInt() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_int FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_int FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Integer result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getInt(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_string FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_string]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public String selectValueString() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_string FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_string FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    String result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getString(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_char_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_char_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public char selectValueCharType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_char_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_char_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    char result=0;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=(char)cursor.getInt(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_char FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_char]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Character selectValueChar() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_char FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_char FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Character result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=(char)cursor.getInt(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_float_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_float_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public float selectValueFloatType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_float_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_float_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    float result=0f;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0f; }
        result=cursor.getFloat(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_float FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_float]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Float selectValueFloat() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_float FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_float FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Float result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getFloat(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_long_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_long_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public long selectValueLongType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_long_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_long_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    long result=0L;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_long FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_long]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Long selectValueLong() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_long FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_long FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Long result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getLong(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_double_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_double_type]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public double selectValueDoubleType() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_double_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_double_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    double result=0;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getDouble(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_double FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_double]</pre>
   *
   *
   * @return single value extracted with query.
   */
  @Override
  public Double selectValueDouble() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_double FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_double FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Double result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getDouble(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(int id, ReadBeanListener<Bean> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean resultBean=new Bean();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.valueBoolType=false;
          resultBean.valueBool=null;
          resultBean.valueByteType=0;
          resultBean.valueByte=0;
          resultBean.valueShortType=0;
          resultBean.valueShort=null;
          resultBean.valueIntType=0;
          resultBean.valueInt=null;
          resultBean.valueString=null;
          resultBean.valueCharType=0;
          resultBean.valueChar=0;
          resultBean.valueFloatType=0f;
          resultBean.valueFloat=null;
          resultBean.valueBigInteger=null;
          resultBean.valueBigDecimal=null;
          resultBean.valueEnumType=null;
          resultBean.valueLongType=0L;
          resultBean.valueLong=null;
          resultBean.valueDoubleType=0;
          resultBean.valueDouble=null;
          resultBean.valueLocale=null;
          resultBean.valueCalendar=null;
          resultBean.valueDate=null;
          resultBean.valueUrl=null;
          resultBean.valueTime=null;
          resultBean.valueCurrency=null;
          resultBean.valueTimeZone=null;
          resultBean.valueTimeList=null;
          resultBean.valueStrinList=null;
          resultBean.valueLongList=null;
          resultBean.valueByteArray=null;
          resultBean.valueLongTypeArray=null;
          resultBean.valueLongArray=null;
          resultBean.valueBeanArray=null;
          resultBean.valueStringArray=null;
          resultBean.valueCharList=null;
          resultBean.valueCharTypeArray=null;
          resultBean.valueCharArray=null;
          resultBean.valueMapStringBean=null;
          resultBean.valueLinkedMapStringBean=null;
          resultBean.valueSetString=null;
          resultBean.id=0L;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
          if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
          if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
          if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
          if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
          if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
          if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
          if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
          if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
          if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
          if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
          if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
          if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
          if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
          if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
          if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
          if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
          if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
          if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
          if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
          if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
          if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
          if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
          if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
          if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
          if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
          if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
          if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
          if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
          if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
          if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
          if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
          if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
          if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
          if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
          if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
          if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
          if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
          if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
          if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
          if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
          if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(long id, ReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param id
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean> selectList(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean> resultList=new LinkedList<Bean>();
    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      do
       {
        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET value_bool_type=${value.valueBoolType}, value_bool=${value.valueBool}, value_byte_type=${value.valueByteType}, value_byte=${value.valueByte}, value_short_type=${value.valueShortType}, value_short=${value.valueShort}, value_int_type=${value.valueIntType}, value_int=${value.valueInt}, value_string=${value.valueString}, value_char_type=${value.valueCharType}, value_char=${value.valueChar}, value_float_type=${value.valueFloatType}, value_float=${value.valueFloat}, value_big_integer=${value.valueBigInteger}, value_big_decimal=${value.valueBigDecimal}, value_enum_type=${value.valueEnumType}, value_long_type=${value.valueLongType}, value_long=${value.valueLong}, value_double_type=${value.valueDoubleType}, value_double=${value.valueDouble}, value_locale=${value.valueLocale}, value_calendar=${value.valueCalendar}, value_date=${value.valueDate}, value_url=${value.valueUrl}, value_time=${value.valueTime}, value_currency=${value.valueCurrency}, value_time_zone=${value.valueTimeZone}, value_time_list=${value.valueTimeList}, value_strin_list=${value.valueStrinList}, value_long_list=${value.valueLongList}, value_byte_array=${value.valueByteArray}, value_long_type_array=${value.valueLongTypeArray}, value_long_array=${value.valueLongArray}, value_bean_array=${value.valueBeanArray}, value_string_array=${value.valueStringArray}, value_char_list=${value.valueCharList}, value_char_type_array=${value.valueCharTypeArray}, value_char_array=${value.valueCharArray}, value_map_string_bean=${value.valueMapStringBean}, value_linked_map_string_bean=${value.valueLinkedMapStringBean}, value_set_string=${value.valueSetString} WHERE id=${value.id}</pre>
   *
   * @param value
   * 	used as updated field and in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_bool_type", value.valueBoolType);

    if (value.valueBool!=null) {
      contentValues.put("value_bool", value.valueBool);
    } else {
      contentValues.putNull("value_bool");
    }

    contentValues.put("value_byte_type", value.valueByteType);

    if (value.valueByte!=null) {
      contentValues.put("value_byte", value.valueByte);
    } else {
      contentValues.putNull("value_byte");
    }

    contentValues.put("value_short_type", (int)value.valueShortType);

    if (value.valueShort!=null) {
      contentValues.put("value_short", (int)value.valueShort);
    } else {
      contentValues.putNull("value_short");
    }

    contentValues.put("value_int_type", value.valueIntType);

    if (value.valueInt!=null) {
      contentValues.put("value_int", value.valueInt);
    } else {
      contentValues.putNull("value_int");
    }

    if (value.valueString!=null) {
      contentValues.put("value_string", value.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    contentValues.put("value_char_type", (int)value.valueCharType);

    if (value.valueChar!=null) {
      contentValues.put("value_char", (int)value.valueChar);
    } else {
      contentValues.putNull("value_char");
    }

    contentValues.put("value_float_type", value.valueFloatType);

    if (value.valueFloat!=null) {
      contentValues.put("value_float", value.valueFloat);
    } else {
      contentValues.putNull("value_float");
    }

    if (value.valueBigInteger!=null) {
      contentValues.put("value_big_integer", value.valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    if (value.valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", value.valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    if (value.valueEnumType!=null) {
      contentValues.put("value_enum_type", value.valueEnumType.toString());
    } else {
      contentValues.putNull("value_enum_type");
    }

    contentValues.put("value_long_type", value.valueLongType);

    if (value.valueLong!=null) {
      contentValues.put("value_long", value.valueLong);
    } else {
      contentValues.putNull("value_long");
    }

    contentValues.put("value_double_type", value.valueDoubleType);

    if (value.valueDouble!=null) {
      contentValues.put("value_double", value.valueDouble);
    } else {
      contentValues.putNull("value_double");
    }

    if (value.valueLocale!=null) {
      contentValues.put("value_locale", LocaleUtil.write(value.valueLocale));
    } else {
      contentValues.putNull("value_locale");
    }

    if (value.valueCalendar!=null) {
      contentValues.put("value_calendar", CalendarUtil.write(value.valueCalendar));
    } else {
      contentValues.putNull("value_calendar");
    }

    if (value.valueDate!=null) {
      contentValues.put("value_date", DateUtil.write(value.valueDate));
    } else {
      contentValues.putNull("value_date");
    }

    if (value.valueUrl!=null) {
      contentValues.put("value_url", UrlUtil.write(value.valueUrl));
    } else {
      contentValues.putNull("value_url");
    }

    if (value.valueTime!=null) {
      contentValues.put("value_time", TimeUtil.write(value.valueTime));
    } else {
      contentValues.putNull("value_time");
    }

    if (value.valueCurrency!=null) {
      contentValues.put("value_currency", CurrencyUtil.write(value.valueCurrency));
    } else {
      contentValues.putNull("value_currency");
    }

    if (value.valueTimeZone!=null) {
      contentValues.put("value_time_zone", TimeZoneUtil.write(value.valueTimeZone));
    } else {
      contentValues.putNull("value_time_zone");
    }

    if (value.valueTimeList!=null) {
      contentValues.put("value_time_list", ProcessorHelper.asByteArray(value.valueTimeList));
    } else {
      contentValues.putNull("value_time_list");
    }

    if (value.valueStrinList!=null) {
      contentValues.put("value_strin_list", ProcessorHelper.asByteArray(value.valueStrinList));
    } else {
      contentValues.putNull("value_strin_list");
    }

    if (value.valueLongList!=null) {
      contentValues.put("value_long_list", ProcessorHelper.asByteArray(value.valueLongList));
    } else {
      contentValues.putNull("value_long_list");
    }

    if (value.valueByteArray!=null) {
      contentValues.put("value_byte_array", value.valueByteArray);
    } else {
      contentValues.putNull("value_byte_array");
    }

    if (value.valueLongTypeArray!=null) {
      contentValues.put("value_long_type_array", ProcessorHelper.asByteArray(CollectionUtility.asList(value.valueLongTypeArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_long_type_array");
    }

    if (value.valueLongArray!=null) {
      contentValues.put("value_long_array", ProcessorHelper.asByteArray(CollectionUtility.asList(value.valueLongArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_long_array");
    }

    if (value.valueBeanArray!=null) {
      contentValues.put("value_bean_array", ProcessorHelper.asByteArray(CollectionUtility.asList(value.valueBeanArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_bean_array");
    }

    if (value.valueStringArray!=null) {
      contentValues.put("value_string_array", ProcessorHelper.asByteArray(CollectionUtility.asList(value.valueStringArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_string_array");
    }

    if (value.valueCharList!=null) {
      contentValues.put("value_char_list", ProcessorHelper.asByteArray(value.valueCharList));
    } else {
      contentValues.putNull("value_char_list");
    }

    if (value.valueCharTypeArray!=null) {
      contentValues.put("value_char_type_array", ProcessorHelper.asByteArray(CollectionUtility.asList(value.valueCharTypeArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_char_type_array");
    }

    if (value.valueCharArray!=null) {
      contentValues.put("value_char_array", ProcessorHelper.asByteArray(CollectionUtility.asList(value.valueCharArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_char_array");
    }

    if (value.valueMapStringBean!=null) {
      contentValues.put("value_map_string_bean", ProcessorHelper.asByteArray(value.valueMapStringBean));
    } else {
      contentValues.putNull("value_map_string_bean");
    }

    if (value.valueLinkedMapStringBean!=null) {
      contentValues.put("value_linked_map_string_bean", ProcessorHelper.asByteArray(value.valueLinkedMapStringBean));
    } else {
      contentValues.putNull("value_linked_map_string_bean");
    }

    if (value.valueSetString!=null) {
      contentValues.put("value_set_string", ProcessorHelper.asByteArray(value.valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    String[] whereConditions={String.valueOf(value.id)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET value_bool_type='"+StringUtil.checkSize(contentValues.get("value_bool_type"))+"', value_bool='"+StringUtil.checkSize(contentValues.get("value_bool"))+"', value_byte_type='"+StringUtil.checkSize(contentValues.get("value_byte_type"))+"', value_byte='"+StringUtil.checkSize(contentValues.get("value_byte"))+"', value_short_type='"+StringUtil.checkSize(contentValues.get("value_short_type"))+"', value_short='"+StringUtil.checkSize(contentValues.get("value_short"))+"', value_int_type='"+StringUtil.checkSize(contentValues.get("value_int_type"))+"', value_int='"+StringUtil.checkSize(contentValues.get("value_int"))+"', value_string='"+StringUtil.checkSize(contentValues.get("value_string"))+"', value_char_type='"+StringUtil.checkSize(contentValues.get("value_char_type"))+"', value_char='"+StringUtil.checkSize(contentValues.get("value_char"))+"', value_float_type='"+StringUtil.checkSize(contentValues.get("value_float_type"))+"', value_float='"+StringUtil.checkSize(contentValues.get("value_float"))+"', value_big_integer='"+StringUtil.checkSize(contentValues.get("value_big_integer"))+"', value_big_decimal='"+StringUtil.checkSize(contentValues.get("value_big_decimal"))+"', value_enum_type='"+StringUtil.checkSize(contentValues.get("value_enum_type"))+"', value_long_type='"+StringUtil.checkSize(contentValues.get("value_long_type"))+"', value_long='"+StringUtil.checkSize(contentValues.get("value_long"))+"', value_double_type='"+StringUtil.checkSize(contentValues.get("value_double_type"))+"', value_double='"+StringUtil.checkSize(contentValues.get("value_double"))+"', value_locale='"+StringUtil.checkSize(contentValues.get("value_locale"))+"', value_calendar='"+StringUtil.checkSize(contentValues.get("value_calendar"))+"', value_date='"+StringUtil.checkSize(contentValues.get("value_date"))+"', value_url='"+StringUtil.checkSize(contentValues.get("value_url"))+"', value_time='"+StringUtil.checkSize(contentValues.get("value_time"))+"', value_currency='"+StringUtil.checkSize(contentValues.get("value_currency"))+"', value_time_zone='"+StringUtil.checkSize(contentValues.get("value_time_zone"))+"', value_time_list='"+StringUtil.checkSize(contentValues.get("value_time_list"))+"', value_strin_list='"+StringUtil.checkSize(contentValues.get("value_strin_list"))+"', value_long_list='"+StringUtil.checkSize(contentValues.get("value_long_list"))+"', value_byte_array='"+StringUtil.checkSize(contentValues.get("value_byte_array"))+"', value_long_type_array='"+StringUtil.checkSize(contentValues.get("value_long_type_array"))+"', value_long_array='"+StringUtil.checkSize(contentValues.get("value_long_array"))+"', value_bean_array='"+StringUtil.checkSize(contentValues.get("value_bean_array"))+"', value_string_array='"+StringUtil.checkSize(contentValues.get("value_string_array"))+"', value_char_list='"+StringUtil.checkSize(contentValues.get("value_char_list"))+"', value_char_type_array='"+StringUtil.checkSize(contentValues.get("value_char_type_array"))+"', value_char_array='"+StringUtil.checkSize(contentValues.get("value_char_array"))+"', value_map_string_bean='"+StringUtil.checkSize(contentValues.get("value_map_string_bean"))+"', value_linked_map_string_bean='"+StringUtil.checkSize(contentValues.get("value_linked_map_string_bean"))+"', value_set_string='"+StringUtil.checkSize(contentValues.get("value_set_string"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string) VALUES (${bean.valueBoolType}, ${bean.valueBool}, ${bean.valueByteType}, ${bean.valueByte}, ${bean.valueShortType}, ${bean.valueShort}, ${bean.valueIntType}, ${bean.valueInt}, ${bean.valueString}, ${bean.valueCharType}, ${bean.valueChar}, ${bean.valueFloatType}, ${bean.valueFloat}, ${bean.valueBigInteger}, ${bean.valueBigDecimal}, ${bean.valueEnumType}, ${bean.valueLongType}, ${bean.valueLong}, ${bean.valueDoubleType}, ${bean.valueDouble}, ${bean.valueLocale}, ${bean.valueCalendar}, ${bean.valueDate}, ${bean.valueUrl}, ${bean.valueTime}, ${bean.valueCurrency}, ${bean.valueTimeZone}, ${bean.valueTimeList}, ${bean.valueStrinList}, ${bean.valueLongList}, ${bean.valueByteArray}, ${bean.valueLongTypeArray}, ${bean.valueLongArray}, ${bean.valueBeanArray}, ${bean.valueStringArray}, ${bean.valueCharList}, ${bean.valueCharTypeArray}, ${bean.valueCharArray}, ${bean.valueMapStringBean}, ${bean.valueLinkedMapStringBean}, ${bean.valueSetString})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(Bean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_bool_type", bean.valueBoolType);

    if (bean.valueBool!=null) {
      contentValues.put("value_bool", bean.valueBool);
    } else {
      contentValues.putNull("value_bool");
    }

    contentValues.put("value_byte_type", bean.valueByteType);

    if (bean.valueByte!=null) {
      contentValues.put("value_byte", bean.valueByte);
    } else {
      contentValues.putNull("value_byte");
    }

    contentValues.put("value_short_type", (int)bean.valueShortType);

    if (bean.valueShort!=null) {
      contentValues.put("value_short", (int)bean.valueShort);
    } else {
      contentValues.putNull("value_short");
    }

    contentValues.put("value_int_type", bean.valueIntType);

    if (bean.valueInt!=null) {
      contentValues.put("value_int", bean.valueInt);
    } else {
      contentValues.putNull("value_int");
    }

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    contentValues.put("value_char_type", (int)bean.valueCharType);

    if (bean.valueChar!=null) {
      contentValues.put("value_char", (int)bean.valueChar);
    } else {
      contentValues.putNull("value_char");
    }

    contentValues.put("value_float_type", bean.valueFloatType);

    if (bean.valueFloat!=null) {
      contentValues.put("value_float", bean.valueFloat);
    } else {
      contentValues.putNull("value_float");
    }

    if (bean.valueBigInteger!=null) {
      contentValues.put("value_big_integer", bean.valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    if (bean.valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", bean.valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    if (bean.valueEnumType!=null) {
      contentValues.put("value_enum_type", bean.valueEnumType.toString());
    } else {
      contentValues.putNull("value_enum_type");
    }

    contentValues.put("value_long_type", bean.valueLongType);

    if (bean.valueLong!=null) {
      contentValues.put("value_long", bean.valueLong);
    } else {
      contentValues.putNull("value_long");
    }

    contentValues.put("value_double_type", bean.valueDoubleType);

    if (bean.valueDouble!=null) {
      contentValues.put("value_double", bean.valueDouble);
    } else {
      contentValues.putNull("value_double");
    }

    if (bean.valueLocale!=null) {
      contentValues.put("value_locale", LocaleUtil.write(bean.valueLocale));
    } else {
      contentValues.putNull("value_locale");
    }

    if (bean.valueCalendar!=null) {
      contentValues.put("value_calendar", CalendarUtil.write(bean.valueCalendar));
    } else {
      contentValues.putNull("value_calendar");
    }

    if (bean.valueDate!=null) {
      contentValues.put("value_date", DateUtil.write(bean.valueDate));
    } else {
      contentValues.putNull("value_date");
    }

    if (bean.valueUrl!=null) {
      contentValues.put("value_url", UrlUtil.write(bean.valueUrl));
    } else {
      contentValues.putNull("value_url");
    }

    if (bean.valueTime!=null) {
      contentValues.put("value_time", TimeUtil.write(bean.valueTime));
    } else {
      contentValues.putNull("value_time");
    }

    if (bean.valueCurrency!=null) {
      contentValues.put("value_currency", CurrencyUtil.write(bean.valueCurrency));
    } else {
      contentValues.putNull("value_currency");
    }

    if (bean.valueTimeZone!=null) {
      contentValues.put("value_time_zone", TimeZoneUtil.write(bean.valueTimeZone));
    } else {
      contentValues.putNull("value_time_zone");
    }

    if (bean.valueTimeList!=null) {
      contentValues.put("value_time_list", ProcessorHelper.asByteArray(bean.valueTimeList));
    } else {
      contentValues.putNull("value_time_list");
    }

    if (bean.valueStrinList!=null) {
      contentValues.put("value_strin_list", ProcessorHelper.asByteArray(bean.valueStrinList));
    } else {
      contentValues.putNull("value_strin_list");
    }

    if (bean.valueLongList!=null) {
      contentValues.put("value_long_list", ProcessorHelper.asByteArray(bean.valueLongList));
    } else {
      contentValues.putNull("value_long_list");
    }

    if (bean.valueByteArray!=null) {
      contentValues.put("value_byte_array", bean.valueByteArray);
    } else {
      contentValues.putNull("value_byte_array");
    }

    if (bean.valueLongTypeArray!=null) {
      contentValues.put("value_long_type_array", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.valueLongTypeArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_long_type_array");
    }

    if (bean.valueLongArray!=null) {
      contentValues.put("value_long_array", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.valueLongArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_long_array");
    }

    if (bean.valueBeanArray!=null) {
      contentValues.put("value_bean_array", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.valueBeanArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_bean_array");
    }

    if (bean.valueStringArray!=null) {
      contentValues.put("value_string_array", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.valueStringArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_string_array");
    }

    if (bean.valueCharList!=null) {
      contentValues.put("value_char_list", ProcessorHelper.asByteArray(bean.valueCharList));
    } else {
      contentValues.putNull("value_char_list");
    }

    if (bean.valueCharTypeArray!=null) {
      contentValues.put("value_char_type_array", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.valueCharTypeArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_char_type_array");
    }

    if (bean.valueCharArray!=null) {
      contentValues.put("value_char_array", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.valueCharArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_char_array");
    }

    if (bean.valueMapStringBean!=null) {
      contentValues.put("value_map_string_bean", ProcessorHelper.asByteArray(bean.valueMapStringBean));
    } else {
      contentValues.putNull("value_map_string_bean");
    }

    if (bean.valueLinkedMapStringBean!=null) {
      contentValues.put("value_linked_map_string_bean", ProcessorHelper.asByteArray(bean.valueLinkedMapStringBean));
    } else {
      contentValues.putNull("value_linked_map_string_bean");
    }

    if (bean.valueSetString!=null) {
      contentValues.put("value_set_string", ProcessorHelper.asByteArray(bean.valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string) VALUES ('"+StringUtil.checkSize(contentValues.get("value_bool_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_bool"))+"', '"+StringUtil.checkSize(contentValues.get("value_byte_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_byte"))+"', '"+StringUtil.checkSize(contentValues.get("value_short_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_short"))+"', '"+StringUtil.checkSize(contentValues.get("value_int_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_int"))+"', '"+StringUtil.checkSize(contentValues.get("value_string"))+"', '"+StringUtil.checkSize(contentValues.get("value_char_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_char"))+"', '"+StringUtil.checkSize(contentValues.get("value_float_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_float"))+"', '"+StringUtil.checkSize(contentValues.get("value_big_integer"))+"', '"+StringUtil.checkSize(contentValues.get("value_big_decimal"))+"', '"+StringUtil.checkSize(contentValues.get("value_enum_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_long_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_long"))+"', '"+StringUtil.checkSize(contentValues.get("value_double_type"))+"', '"+StringUtil.checkSize(contentValues.get("value_double"))+"', '"+StringUtil.checkSize(contentValues.get("value_locale"))+"', '"+StringUtil.checkSize(contentValues.get("value_calendar"))+"', '"+StringUtil.checkSize(contentValues.get("value_date"))+"', '"+StringUtil.checkSize(contentValues.get("value_url"))+"', '"+StringUtil.checkSize(contentValues.get("value_time"))+"', '"+StringUtil.checkSize(contentValues.get("value_currency"))+"', '"+StringUtil.checkSize(contentValues.get("value_time_zone"))+"', '"+StringUtil.checkSize(contentValues.get("value_time_list"))+"', '"+StringUtil.checkSize(contentValues.get("value_strin_list"))+"', '"+StringUtil.checkSize(contentValues.get("value_long_list"))+"', '"+StringUtil.checkSize(contentValues.get("value_byte_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_long_type_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_long_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_bean_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_string_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_char_list"))+"', '"+StringUtil.checkSize(contentValues.get("value_char_type_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_char_array"))+"', '"+StringUtil.checkSize(contentValues.get("value_map_string_bean"))+"', '"+StringUtil.checkSize(contentValues.get("value_linked_map_string_bean"))+"', '"+StringUtil.checkSize(contentValues.get("value_set_string"))+"')"));
    long result = database().insert("bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_big_decimal) VALUES (${valueBigDecimal})</pre>
   *
   * @param valueBigDecimal
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(BigDecimal valueBigDecimal) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_big_decimal) VALUES ('"+StringUtil.checkSize(contentValues.get("value_big_decimal"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimal]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueBigDecimal
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(BigDecimal valueBigDecimal) {
    // build where condition
    String[] args={(valueBigDecimal==null?null:valueBigDecimal.toPlainString())};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * @param valueBigDecimal
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(BigDecimal valueBigDecimal) {
    String[] whereConditions={(valueBigDecimal==null?null:valueBigDecimal.toPlainString())};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueBigDecimal=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_big_decimal=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * @param valueBigDecimal
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(BigDecimal valueBigDecimal) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueBigDecimal==null?null:valueBigDecimal.toPlainString())};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueBigDecimal=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_big_decimal=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET valueSetString=${valueSetString} WHERE id=${id}</pre>
   *
   * @param id
   * 	used in where condition
   * @param valueSetString
   * 	used as updated field
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, Set<String> valueSetString) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (valueSetString!=null) {
      contentValues.put("value_set_string", ProcessorHelper.asByteArray(valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET valueSetString='"+StringUtil.checkSize(contentValues.get("value_set_string"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_big_integer) VALUES (${valueBigInteger})</pre>
   *
   * @param valueBigInteger
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(BigInteger valueBigInteger) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBigInteger!=null) {
      contentValues.put("value_big_integer", valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_big_integer) VALUES ('"+StringUtil.checkSize(contentValues.get("value_big_integer"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimal]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueBigDecimal
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(BigInteger valueBigDecimal) {
    // build where condition
    String[] args={(valueBigDecimal==null?null:String.valueOf(valueBigDecimal.toString()))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * @param valueBigDecimal
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(BigInteger valueBigDecimal) {
    String[] whereConditions={(valueBigDecimal==null?null:String.valueOf(valueBigDecimal.toString()))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueBigDecimal=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_big_decimal=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * @param valueBigDecimal
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(BigInteger valueBigDecimal) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueBigDecimal==null?null:String.valueOf(valueBigDecimal.toString()))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueBigDecimal=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_big_decimal=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_bool_type) VALUES (${valueBoolType})</pre>
   *
   * @param valueBoolType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(boolean valueBoolType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_bool_type", valueBoolType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_bool_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_bool_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBoolType=${valueBoolType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBoolType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueBoolType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(boolean valueBoolType) {
    // build where condition
    String[] args={String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueBoolType=${valueBoolType}</pre>
   *
   * @param valueBoolType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(boolean valueBoolType) {
    String[] whereConditions={String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueBoolType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_bool_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueBoolType=${valueBoolType}</pre>
   *
   * @param valueBoolType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(boolean valueBoolType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueBoolType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_bool_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_bool) VALUES (${valueBool})</pre>
   *
   * @param valueBool
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(Boolean valueBool) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBool!=null) {
      contentValues.put("value_bool", valueBool);
    } else {
      contentValues.putNull("value_bool");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_bool) VALUES ('"+StringUtil.checkSize(contentValues.get("value_bool"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBool=${valueBool}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBool]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueBool
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(Boolean valueBool) {
    // build where condition
    String[] args={(valueBool==null?null:String.valueOf(valueBool))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueBool=${valueBool}</pre>
   *
   * @param valueBool
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Boolean valueBool) {
    String[] whereConditions={(valueBool==null?null:String.valueOf(valueBool))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueBool=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_bool=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueBool=${valueBool}</pre>
   *
   * @param valueBool
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Boolean valueBool) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueBool==null?null:String.valueOf(valueBool))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueBool=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_bool=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_byte_type) VALUES (${valueByteType})</pre>
   *
   * @param valueByteType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertByteType(byte valueByteType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_byte_type", valueByteType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_byte_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_byte_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueByteType=${valueByteType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueByteType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueByteType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneByteType(boolean valueByteType) {
    // build where condition
    String[] args={String.valueOf(valueByteType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueByteType=${valueByteType}</pre>
   *
   * @param valueByteType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteByteType(boolean valueByteType) {
    String[] whereConditions={String.valueOf(valueByteType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueByteType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_byte_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueByteType=${valueByteType}</pre>
   *
   * @param valueByteType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneByteType(boolean valueByteType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(valueByteType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueByteType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_byte_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_byte) VALUES (${valueByte})</pre>
   *
   * @param valueByte
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertByte(Byte valueByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueByte!=null) {
      contentValues.put("value_byte", valueByte);
    } else {
      contentValues.putNull("value_byte");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_byte) VALUES ('"+StringUtil.checkSize(contentValues.get("value_byte"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueByte=${valueByte}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueByte]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueByte
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneByte(Byte valueByte) {
    // build where condition
    String[] args={(valueByte==null?null:String.valueOf(valueByte))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueByte=${valueByte}</pre>
   *
   * @param valueByte
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteByte(Byte valueByte) {
    String[] whereConditions={(valueByte==null?null:String.valueOf(valueByte))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueByte=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_byte=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueByte=${valueByte}</pre>
   *
   * @param valueByte
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneByte(Byte valueByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueByte==null?null:String.valueOf(valueByte))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueByte=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_byte=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_char_type) VALUES (${valueCharType})</pre>
   *
   * @param valueCharType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertCharType(char valueCharType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_char_type", (int)valueCharType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_char_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_char_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCharType=${valueCharType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueCharType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueCharType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneCharType(char valueCharType) {
    // build where condition
    String[] args={String.valueOf((int)valueCharType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueCharType=${valueCharType}</pre>
   *
   * @param valueCharType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteCharType(char valueCharType) {
    String[] whereConditions={String.valueOf((int)valueCharType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueCharType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_char_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueCharType=${valueCharType}</pre>
   *
   * @param valueCharType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneCharType(char valueCharType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf((int)valueCharType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueCharType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_char_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_char) VALUES (${valueChar})</pre>
   *
   * @param valueChar
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertChar(Character valueChar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueChar!=null) {
      contentValues.put("value_char", (int)valueChar);
    } else {
      contentValues.putNull("value_char");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_char) VALUES ('"+StringUtil.checkSize(contentValues.get("value_char"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCharType=${valueChar}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueChar]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueChar
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneChar(Character valueChar) {
    // build where condition
    String[] args={(valueChar==null?null:String.valueOf((int)valueChar))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueCharType=${valueChar}</pre>
   *
   * @param valueChar
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteChar(Character valueChar) {
    String[] whereConditions={(valueChar==null?null:String.valueOf((int)valueChar))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueCharType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_char_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueCharType=${valueChar}</pre>
   *
   * @param valueChar
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneChar(Character valueChar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueChar==null?null:String.valueOf((int)valueChar))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueCharType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_char_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_short_type) VALUES (${valueShortType})</pre>
   *
   * @param valueShortType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertShortType(short valueShortType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_short_type", (int)valueShortType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_short_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_short_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueShortType=${valueShortType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueShortType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueShortType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneShortType(short valueShortType) {
    // build where condition
    String[] args={String.valueOf((int)valueShortType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueShortType=${valueShortType}</pre>
   *
   * @param valueShortType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteShortType(short valueShortType) {
    String[] whereConditions={String.valueOf((int)valueShortType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueShortType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_short_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueShortType=${valueShortType}</pre>
   *
   * @param valueShortType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneShortType(short valueShortType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf((int)valueShortType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueShortType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_short_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_short) VALUES (${valueShort})</pre>
   *
   * @param valueShort
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertShort(Short valueShort) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueShort!=null) {
      contentValues.put("value_short", (int)valueShort);
    } else {
      contentValues.putNull("value_short");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_short) VALUES ('"+StringUtil.checkSize(contentValues.get("value_short"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueShort=${valueShort}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueShort]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueShort
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneShort(Short valueShort) {
    // build where condition
    String[] args={(valueShort==null?null:String.valueOf((int)valueShort))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueShort=${valueShort}</pre>
   *
   * @param valueShort
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteShort(Short valueShort) {
    String[] whereConditions={(valueShort==null?null:String.valueOf((int)valueShort))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueShort=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_short=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueShort=${valueShort}</pre>
   *
   * @param valueShort
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneShort(Short valueShort) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueShort==null?null:String.valueOf((int)valueShort))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueShort=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_short=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_int_type) VALUES (${valueIntType})</pre>
   *
   * @param valueIntType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertIntType(int valueIntType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_int_type", valueIntType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_int_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_int_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueIntType=${valueIntType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueIntType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueIntType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneIntType(int valueIntType) {
    // build where condition
    String[] args={String.valueOf(valueIntType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueIntType=${valueIntType}</pre>
   *
   * @param valueIntType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteIntType(int valueIntType) {
    String[] whereConditions={String.valueOf(valueIntType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueIntType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_int_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueIntType=${valueIntType}</pre>
   *
   * @param valueIntType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneIntType(int valueIntType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(valueIntType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueIntType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_int_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_int) VALUES (${valueInt})</pre>
   *
   * @param valueInt
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertInt(Integer valueInt) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueInt!=null) {
      contentValues.put("value_int", valueInt);
    } else {
      contentValues.putNull("value_int");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_int) VALUES ('"+StringUtil.checkSize(contentValues.get("value_int"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueInt=${valueInt}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueInt]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueInt
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneInt(Integer valueInt) {
    // build where condition
    String[] args={(valueInt==null?null:String.valueOf(valueInt))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueInt=${valueInt}</pre>
   *
   * @param valueInt
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteInt(Integer valueInt) {
    String[] whereConditions={(valueInt==null?null:String.valueOf(valueInt))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueInt=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_int=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueInt=${valueInt}</pre>
   *
   * @param valueInt
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneInt(Integer valueInt) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueInt==null?null:String.valueOf(valueInt))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueInt=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_int=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_long_type) VALUES (${valueLongType})</pre>
   *
   * @param valueLongType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertLongType(long valueLongType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_long_type", valueLongType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_long_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_long_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongType=${valueLongType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueLongType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueLongType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneLongType(long valueLongType) {
    // build where condition
    String[] args={String.valueOf(valueLongType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueLongType=${valueLongType}</pre>
   *
   * @param valueLongType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteLongType(long valueLongType) {
    String[] whereConditions={String.valueOf(valueLongType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueLongType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_long_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueLongType=${valueLongType}</pre>
   *
   * @param valueLongType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneLongType(long valueLongType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(valueLongType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueLongType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_long_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_long) VALUES (${valueLong})</pre>
   *
   * @param valueLong
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertLong(Long valueLong) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLong!=null) {
      contentValues.put("value_long", valueLong);
    } else {
      contentValues.putNull("value_long");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_long) VALUES ('"+StringUtil.checkSize(contentValues.get("value_long"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLong=${valueLong}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueLong]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueLong
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneLong(Long valueLong) {
    // build where condition
    String[] args={(valueLong==null?null:String.valueOf(valueLong))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueLong=${valueLong}</pre>
   *
   * @param valueLong
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteLong(Long valueLong) {
    String[] whereConditions={(valueLong==null?null:String.valueOf(valueLong))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueLong=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_long=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueLong=${valueLong}</pre>
   *
   * @param valueLong
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneLong(Long valueLong) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueLong==null?null:String.valueOf(valueLong))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueLong=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_long=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_float_type) VALUES (${valueFloatType})</pre>
   *
   * @param valueFloatType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertFloatType(float valueFloatType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_float_type", valueFloatType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_float_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_float_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueFloatType=${valueFloatType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueFloatType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueFloatType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneFloatType(float valueFloatType) {
    // build where condition
    String[] args={String.valueOf(valueFloatType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueFloatType=${valueFloatType}</pre>
   *
   * @param valueFloatType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteFloatType(float valueFloatType) {
    String[] whereConditions={String.valueOf(valueFloatType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueFloatType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_float_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueFloatType=${valueFloatType}</pre>
   *
   * @param valueFloatType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneFloatType(float valueFloatType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(valueFloatType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueFloatType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_float_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_float) VALUES (${valueFloat})</pre>
   *
   * @param valueFloat
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertFloat(Float valueFloat) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueFloat!=null) {
      contentValues.put("value_float", valueFloat);
    } else {
      contentValues.putNull("value_float");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_float) VALUES ('"+StringUtil.checkSize(contentValues.get("value_float"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueFloat=${valueFloat}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueFloat]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueFloat
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneFloat(Float valueFloat) {
    // build where condition
    String[] args={(valueFloat==null?null:String.valueOf(valueFloat))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueFloat=${valueFloat}</pre>
   *
   * @param valueFloat
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteFloat(Float valueFloat) {
    String[] whereConditions={(valueFloat==null?null:String.valueOf(valueFloat))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueFloat=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_float=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueFloat=${valueFloat}</pre>
   *
   * @param valueFloat
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneFloat(Float valueFloat) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueFloat==null?null:String.valueOf(valueFloat))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueFloat=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_float=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_double_type) VALUES (${valueDoubleType})</pre>
   *
   * @param valueDoubleType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertDoubleType(double valueDoubleType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_double_type", valueDoubleType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_double_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_double_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueDoubleType=${valueDoubleType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueDoubleType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueDoubleType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneDoubleType(double valueDoubleType) {
    // build where condition
    String[] args={String.valueOf(valueDoubleType)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueDoubleType=${valueDoubleType}</pre>
   *
   * @param valueDoubleType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteDoubleType(double valueDoubleType) {
    String[] whereConditions={String.valueOf(valueDoubleType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueDoubleType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_double_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueDoubleType=${valueDoubleType}</pre>
   *
   * @param valueDoubleType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneDoubleType(double valueDoubleType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(valueDoubleType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueDoubleType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_double_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_double) VALUES (${valueDouble})</pre>
   *
   * @param valueDouble
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertDouble(Double valueDouble) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueDouble!=null) {
      contentValues.put("value_double", valueDouble);
    } else {
      contentValues.putNull("value_double");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_double) VALUES ('"+StringUtil.checkSize(contentValues.get("value_double"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueDouble=${valueDouble}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueDouble]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueDouble
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneDouble(Double valueDouble) {
    // build where condition
    String[] args={(valueDouble==null?null:String.valueOf(valueDouble))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueDouble=${valueDouble}</pre>
   *
   * @param valueDouble
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteDouble(Double valueDouble) {
    String[] whereConditions={(valueDouble==null?null:String.valueOf(valueDouble))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueDouble=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_double=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueDouble=${valueDouble}</pre>
   *
   * @param valueDouble
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneDouble(Double valueDouble) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueDouble==null?null:String.valueOf(valueDouble))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueDouble=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_double=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_string) VALUES (${valueString})</pre>
   *
   * @param valueString
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertString(String valueString) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueString!=null) {
      contentValues.put("value_string", valueString);
    } else {
      contentValues.putNull("value_string");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_string) VALUES ('"+StringUtil.checkSize(contentValues.get("value_string"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueString=${valueString}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueString]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueString
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneString(String valueString) {
    // build where condition
    String[] args={(valueString==null?null:valueString)};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_string='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_string=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueString=${valueString}</pre>
   *
   * @param valueString
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteString(String valueString) {
    String[] whereConditions={(valueString==null?null:valueString)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueString=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_string=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueString=${valueString}</pre>
   *
   * @param valueString
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneString(Double valueString) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueString==null?null:String.valueOf(valueString))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueString=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_string=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_date) VALUES (${valueDate})</pre>
   *
   * @param valueDate
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertDate(Date valueDate) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueDate!=null) {
      contentValues.put("value_date", DateUtil.write(valueDate));
    } else {
      contentValues.putNull("value_date");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_date) VALUES ('"+StringUtil.checkSize(contentValues.get("value_date"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueDate=${valueDate}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueDate]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueDate
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneDate(Date valueDate) {
    // build where condition
    String[] args={(valueDate==null?null:String.valueOf(DateUtil.write(valueDate)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_date='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_date=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueDate=${valueDate}</pre>
   *
   * @param valueDate
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteDate(Date valueDate) {
    String[] whereConditions={(valueDate==null?null:String.valueOf(DateUtil.write(valueDate)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueDate=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_date=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueDate=${valueDate}</pre>
   *
   * @param valueDate
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneDate(Date valueDate) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueDate==null?null:String.valueOf(DateUtil.write(valueDate)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueDate=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_date=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_calendar) VALUES (${valueCalendar})</pre>
   *
   * @param valueCalendar
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertCalendar(Calendar valueCalendar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueCalendar!=null) {
      contentValues.put("value_calendar", CalendarUtil.write(valueCalendar));
    } else {
      contentValues.putNull("value_calendar");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_calendar) VALUES ('"+StringUtil.checkSize(contentValues.get("value_calendar"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCalendar=${valueCalendar}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueCalendar]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueCalendar
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneCalendar(Calendar valueCalendar) {
    // build where condition
    String[] args={(valueCalendar==null?null:String.valueOf(CalendarUtil.write(valueCalendar)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_calendar='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_calendar=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueCalendar=${valueCalendar}</pre>
   *
   * @param valueCalendar
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteCalendar(Date valueCalendar) {
    String[] whereConditions={(valueCalendar==null?null:String.valueOf(DateUtil.write(valueCalendar)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueCalendar=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_calendar=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueCalendar=${valueCalendar}</pre>
   *
   * @param valueCalendar
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneCalendar(Calendar valueCalendar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueCalendar==null?null:String.valueOf(CalendarUtil.write(valueCalendar)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueCalendar=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_calendar=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_locale) VALUES (${valueLocale})</pre>
   *
   * @param valueLocale
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertLocale(Locale valueLocale) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLocale!=null) {
      contentValues.put("value_locale", LocaleUtil.write(valueLocale));
    } else {
      contentValues.putNull("value_locale");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_locale) VALUES ('"+StringUtil.checkSize(contentValues.get("value_locale"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLocale=${valueLocale}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueLocale]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueLocale
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneLocale(Calendar valueLocale) {
    // build where condition
    String[] args={(valueLocale==null?null:String.valueOf(CalendarUtil.write(valueLocale)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_locale='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_locale=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueLocale=${valueLocale}</pre>
   *
   * @param valueLocale
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteLocale(Date valueLocale) {
    String[] whereConditions={(valueLocale==null?null:String.valueOf(DateUtil.write(valueLocale)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueLocale=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_locale=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueLocale=${valueLocale}</pre>
   *
   * @param valueLocale
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneLocale(Locale valueLocale) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueLocale==null?null:String.valueOf(LocaleUtil.write(valueLocale)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueLocale=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_locale=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_url) VALUES (${valueUrl})</pre>
   *
   * @param valueUrl
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertURL(URL valueUrl) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueUrl!=null) {
      contentValues.put("value_url", UrlUtil.write(valueUrl));
    } else {
      contentValues.putNull("value_url");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_url) VALUES ('"+StringUtil.checkSize(contentValues.get("value_url"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueUrl=${valueUrl}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueUrl]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueUrl
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneURL(URL valueUrl) {
    // build where condition
    String[] args={(valueUrl==null?null:String.valueOf(UrlUtil.write(valueUrl)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_url='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_url=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueUrl=${valueUrl}</pre>
   *
   * @param valueUrl
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteURL(URL valueUrl) {
    String[] whereConditions={(valueUrl==null?null:String.valueOf(UrlUtil.write(valueUrl)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueUrl=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_url=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueUrl=${valueUrl}</pre>
   *
   * @param valueUrl
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneURL(URL valueUrl) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueUrl==null?null:String.valueOf(UrlUtil.write(valueUrl)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueUrl=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_url=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_time) VALUES (${valueTime})</pre>
   *
   * @param valueTime
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertTime(Time valueTime) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueTime!=null) {
      contentValues.put("value_time", TimeUtil.write(valueTime));
    } else {
      contentValues.putNull("value_time");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_time) VALUES ('"+StringUtil.checkSize(contentValues.get("value_time"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueTime=${valueTime}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueTime]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueTime
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneTime(Time valueTime) {
    // build where condition
    String[] args={(valueTime==null?null:String.valueOf(TimeUtil.write(valueTime)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueTime=${valueTime}</pre>
   *
   * @param valueTime
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteTime(Time valueTime) {
    String[] whereConditions={(valueTime==null?null:String.valueOf(TimeUtil.write(valueTime)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueTime=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_time=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueTime=${valueTime}</pre>
   *
   * @param valueTime
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneTime(Time valueTime) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueTime==null?null:String.valueOf(TimeUtil.write(valueTime)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueTime=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_time=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_currency) VALUES (${valueCurrency})</pre>
   *
   * @param valueCurrency
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertCurrency(Currency valueCurrency) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueCurrency!=null) {
      contentValues.put("value_currency", CurrencyUtil.write(valueCurrency));
    } else {
      contentValues.putNull("value_currency");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_currency) VALUES ('"+StringUtil.checkSize(contentValues.get("value_currency"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCurrency=${valueCurrency}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueCurrency]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueCurrency
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneCurrencye(Currency valueCurrency) {
    // build where condition
    String[] args={(valueCurrency==null?null:String.valueOf(CurrencyUtil.write(valueCurrency)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_currency='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_currency=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueCurrency=${valueCurrency}</pre>
   *
   * @param valueCurrency
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteCurrency(Currency valueCurrency) {
    String[] whereConditions={(valueCurrency==null?null:String.valueOf(CurrencyUtil.write(valueCurrency)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueCurrency=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_currency=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueCurrency=${valueCurrency}</pre>
   *
   * @param valueCurrency
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneCurrency(Currency valueCurrency) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueCurrency==null?null:String.valueOf(CurrencyUtil.write(valueCurrency)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueCurrency=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_currency=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_time_zone) VALUES (${valueTimeZone})</pre>
   *
   * @param valueTimeZone
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertTimeZone(TimeZone valueTimeZone) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueTimeZone!=null) {
      contentValues.put("value_time_zone", TimeZoneUtil.write(valueTimeZone));
    } else {
      contentValues.putNull("value_time_zone");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_time_zone) VALUES ('"+StringUtil.checkSize(contentValues.get("value_time_zone"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueTimeZone=${valueTimeZone}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueTimeZone]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueTimeZone
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneTimeZone(TimeZone valueTimeZone) {
    // build where condition
    String[] args={(valueTimeZone==null?null:String.valueOf(TimeZoneUtil.write(valueTimeZone)))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time_zone='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time_zone=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueTimeZone=${valueTimeZone}</pre>
   *
   * @param valueTimeZone
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteTimeZone(TimeZone valueTimeZone) {
    String[] whereConditions={(valueTimeZone==null?null:String.valueOf(TimeZoneUtil.write(valueTimeZone)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueTimeZone=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_time_zone=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueTimeZone=${valueTimeZone}</pre>
   *
   * @param valueTimeZone
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneTimeZone(TimeZone valueTimeZone) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueTimeZone==null?null:String.valueOf(TimeZoneUtil.write(valueTimeZone)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueTimeZone=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_time_zone=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_enum_type) VALUES (${valueEnumType})</pre>
   *
   * @param valueEnumType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertEnumType(EnumType valueEnumType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueEnumType!=null) {
      contentValues.put("value_enum_type", valueEnumType.toString());
    } else {
      contentValues.putNull("value_enum_type");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_enum_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_enum_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueEnumType=${valueEnumType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueEnumType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueEnumType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneEnumType(EnumType valueEnumType) {
    // build where condition
    String[] args={(valueEnumType==null?null:String.valueOf(valueEnumType.toString()))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_enum_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_enum_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueEnumType=${valueEnumType}</pre>
   *
   * @param valueEnumType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteEnumType(EnumType valueEnumType) {
    String[] whereConditions={(valueEnumType==null?null:String.valueOf(valueEnumType.toString()))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueEnumType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_enum_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueEnumType=${valueEnumType}</pre>
   *
   * @param valueEnumType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneEnumType(EnumType valueEnumType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueEnumType==null?null:String.valueOf(valueEnumType.toString()))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueEnumType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_enum_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_bean_array) VALUES (${valueBeanArray})</pre>
   *
   * @param valueBeanArray
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertArrayBeanType(Bean[] valueBeanArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBeanArray!=null) {
      contentValues.put("value_bean_array", ProcessorHelper.asByteArray(CollectionUtility.asList(valueBeanArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_bean_array");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_bean_array) VALUES ('"+StringUtil.checkSize(contentValues.get("value_bean_array"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBeanArray=${valueBeanArray}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBeanArray]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueBeanArray
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneArrayBeanType(Bean[] valueBeanArray) {
    // build where condition
    String[] args={(valueBeanArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueBeanArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bean_array='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bean_array=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueBeanArray=${valueBeanArray}</pre>
   *
   * @param valueBeanArray
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteArrayBeanType(Bean[] valueBeanArray) {
    String[] whereConditions={(valueBeanArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueBeanArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueBeanArray=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_bean_array=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueBeanArray=${valueBeanArray}</pre>
   *
   * @param valueBeanArray
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneArrayBean(Bean[] valueBeanArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueBeanArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueBeanArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueBeanArray=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_bean_array=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_long_type_array) VALUES (${valueLongTypeArray})</pre>
   *
   * @param valueLongTypeArray
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertArrayLongType(long[] valueLongTypeArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLongTypeArray!=null) {
      contentValues.put("value_long_type_array", ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongTypeArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_long_type_array");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_long_type_array) VALUES ('"+StringUtil.checkSize(contentValues.get("value_long_type_array"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongTypeArray=${valueLongTypeArray}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueLongTypeArray]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueLongTypeArray
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneArrayLongType(long[] valueLongTypeArray) {
    // build where condition
    String[] args={(valueLongTypeArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongTypeArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type_array='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type_array=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueLongTypeArray=${valueLongTypeArray}</pre>
   *
   * @param valueLongTypeArray
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteArrayLongType(long[] valueLongTypeArray) {
    String[] whereConditions={(valueLongTypeArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongTypeArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueLongTypeArray=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_long_type_array=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueLongTypeArray=${valueLongTypeArray}</pre>
   *
   * @param valueLongTypeArray
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneArrayLongType(long[] valueLongTypeArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueLongTypeArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongTypeArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueLongTypeArray=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_long_type_array=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_long_array) VALUES (${valueLongArray})</pre>
   *
   * @param valueLongArray
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertArrayLong(Long[] valueLongArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLongArray!=null) {
      contentValues.put("value_long_array", ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongArray, ArrayList.class)));
    } else {
      contentValues.putNull("value_long_array");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_long_array) VALUES ('"+StringUtil.checkSize(contentValues.get("value_long_array"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongArray=${valueLongArray}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueLongArray]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueLongArray
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneArrayLong(Long[] valueLongArray) {
    // build where condition
    String[] args={(valueLongArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_array='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_array=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueLongArray=${valueLongArray}</pre>
   *
   * @param valueLongArray
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteArrayLong(Long[] valueLongArray) {
    String[] whereConditions={(valueLongArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueLongArray=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_long_array=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueLongArray=${valueLongArray}</pre>
   *
   * @param valueLongArray
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneArrayLong(Long[] valueLongArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueLongArray==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(valueLongArray, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueLongArray=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_long_array=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_long_list) VALUES (${valueLongList})</pre>
   *
   * @param valueLongList
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertListLong(LinkedList<Long> valueLongList) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLongList!=null) {
      contentValues.put("value_long_list", ProcessorHelper.asByteArray(valueLongList));
    } else {
      contentValues.putNull("value_long_list");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_long_list) VALUES ('"+StringUtil.checkSize(contentValues.get("value_long_list"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongList=${valueLongList}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueLongList]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id]</pre>
   *
   * @param valueLongList
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneListLong(LinkedList<Long> valueLongList) {
    // build where condition
    String[] args={(valueLongList==null?null:new String(ProcessorHelper.asByteArray(valueLongList),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_list='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_list=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_bool_type");
      int index1=cursor.getColumnIndex("value_bool");
      int index2=cursor.getColumnIndex("value_byte_type");
      int index3=cursor.getColumnIndex("value_byte");
      int index4=cursor.getColumnIndex("value_short_type");
      int index5=cursor.getColumnIndex("value_short");
      int index6=cursor.getColumnIndex("value_int_type");
      int index7=cursor.getColumnIndex("value_int");
      int index8=cursor.getColumnIndex("value_string");
      int index9=cursor.getColumnIndex("value_char_type");
      int index10=cursor.getColumnIndex("value_char");
      int index11=cursor.getColumnIndex("value_float_type");
      int index12=cursor.getColumnIndex("value_float");
      int index13=cursor.getColumnIndex("value_big_integer");
      int index14=cursor.getColumnIndex("value_big_decimal");
      int index15=cursor.getColumnIndex("value_enum_type");
      int index16=cursor.getColumnIndex("value_long_type");
      int index17=cursor.getColumnIndex("value_long");
      int index18=cursor.getColumnIndex("value_double_type");
      int index19=cursor.getColumnIndex("value_double");
      int index20=cursor.getColumnIndex("value_locale");
      int index21=cursor.getColumnIndex("value_calendar");
      int index22=cursor.getColumnIndex("value_date");
      int index23=cursor.getColumnIndex("value_url");
      int index24=cursor.getColumnIndex("value_time");
      int index25=cursor.getColumnIndex("value_currency");
      int index26=cursor.getColumnIndex("value_time_zone");
      int index27=cursor.getColumnIndex("value_time_list");
      int index28=cursor.getColumnIndex("value_strin_list");
      int index29=cursor.getColumnIndex("value_long_list");
      int index30=cursor.getColumnIndex("value_byte_array");
      int index31=cursor.getColumnIndex("value_long_type_array");
      int index32=cursor.getColumnIndex("value_long_array");
      int index33=cursor.getColumnIndex("value_bean_array");
      int index34=cursor.getColumnIndex("value_string_array");
      int index35=cursor.getColumnIndex("value_char_list");
      int index36=cursor.getColumnIndex("value_char_type_array");
      int index37=cursor.getColumnIndex("value_char_array");
      int index38=cursor.getColumnIndex("value_map_string_bean");
      int index39=cursor.getColumnIndex("value_linked_map_string_bean");
      int index40=cursor.getColumnIndex("value_set_string");
      int index41=cursor.getColumnIndex("id");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
      if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
      if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
      if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
      if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
      if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
      if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
      if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
      if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
      if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
      if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
      if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
      if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
      if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
      if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
      if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
      if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
      if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
      if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
      if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
      if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtil.read(cursor.getString(index20)); }
      if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtil.read(cursor.getString(index21)); }
      if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtil.read(cursor.getString(index22)); }
      if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtil.read(cursor.getString(index23)); }
      if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtil.read(cursor.getString(index24)); }
      if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtil.read(cursor.getString(index25)); }
      if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtil.read(cursor.getString(index26)); }
      if (!cursor.isNull(index27)) { resultBean.valueTimeList=ProcessorHelper.asCollection(new ArrayList<Time>(), Time.class, cursor.getBlob(index27)); }
      if (!cursor.isNull(index28)) { resultBean.valueStrinList=ProcessorHelper.asCollection(new LinkedList<String>(), String.class, cursor.getBlob(index28)); }
      if (!cursor.isNull(index29)) { resultBean.valueLongList=ProcessorHelper.asCollection(new LinkedList<Long>(), Long.class, cursor.getBlob(index29)); }
      if (!cursor.isNull(index30)) { resultBean.valueByteArray=cursor.getBlob(index30); }
      if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index31))); }
      if (!cursor.isNull(index32)) { resultBean.valueLongArray=CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index32))); }
      if (!cursor.isNull(index33)) { resultBean.valueBeanArray=CollectionUtility.asArray(ProcessorHelper.asList(Bean.class, cursor.getBlob(index33))); }
      if (!cursor.isNull(index34)) { resultBean.valueStringArray=CollectionUtility.asStringArray(ProcessorHelper.asList(String.class, cursor.getBlob(index34))); }
      if (!cursor.isNull(index35)) { resultBean.valueCharList=ProcessorHelper.asCollection(new LinkedList<Character>(), Character.class, cursor.getBlob(index35)); }
      if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=CollectionUtility.asCharacterTypeArray(ProcessorHelper.asList(Character.TYPE, cursor.getBlob(index36))); }
      if (!cursor.isNull(index37)) { resultBean.valueCharArray=CollectionUtility.asCharacterArray(ProcessorHelper.asList(Character.class, cursor.getBlob(index37))); }
      if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=ProcessorHelper.asMap(new HashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index38)); }
      if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=ProcessorHelper.asMap(new LinkedHashMap<String,Bean>(), String.class, Bean.class, cursor.getBlob(index39)); }
      if (!cursor.isNull(index40)) { resultBean.valueSetString=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index40)); }
      if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueLongList=${valueLongList}</pre>
   *
   * @param valueLongList
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteListLong(LinkedList<Long> valueLongList) {
    String[] whereConditions={(valueLongList==null?null:new String(ProcessorHelper.asByteArray(valueLongList),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueLongList=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_long_list=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueLongList=${valueLongList}</pre>
   *
   * @param valueLongList
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOneListLong(LinkedList<Long> valueLongList) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueLongList==null?null:new String(ProcessorHelper.asByteArray(valueLongList),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueLongList=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_long_list=?", whereConditions);
    return result;
  }
}
