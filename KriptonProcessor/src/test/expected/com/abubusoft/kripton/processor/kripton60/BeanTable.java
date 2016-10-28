package com.abubusoft.kripton.processor.kripton60;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean</code> is associated to table <code>bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton60.Bean
 */
public class BeanTable {
  /**
   * Costant represents name of table bean
   */
  public static final String TABLE_NAME = "bean";

  /**
   * <p>
   * DDL to create table bean
   * </p>
   *
   * <pre>CREATE TABLE bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value_big_decimal TEXT, value_big_integer TEXT, value_bool_type INTEGER, value_bool INTEGER, value_byte_type INTEGER, value_byte INTEGER, value_char_type INTEGER, value_char INTEGER, value_short_type INTEGER, value_short INTEGER, value_int_type INTEGER, value_int INTEGER, value_long_type INTEGER, value_long INTEGER, value_float_type REAL, value_float REAL, value_double_type REAL, value_double REAL, value_string TEXT, value_date TEXT, value_calendar TEXT, value_locale TEXT, value_url TEXT, value_time TEXT, value_currency TEXT, value_time_zone TEXT, value_enum_type TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value_big_decimal TEXT, value_big_integer TEXT, value_bool_type INTEGER, value_bool INTEGER, value_byte_type INTEGER, value_byte INTEGER, value_char_type INTEGER, value_char INTEGER, value_short_type INTEGER, value_short INTEGER, value_int_type INTEGER, value_int INTEGER, value_long_type INTEGER, value_long INTEGER, value_float_type REAL, value_float REAL, value_double_type REAL, value_double REAL, value_string TEXT, value_date TEXT, value_calendar TEXT, value_locale TEXT, value_url TEXT, value_time TEXT, value_currency TEXT, value_time_zone TEXT, value_enum_type TEXT);";

  /**
   * <p>
   * DDL to drop table bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>valueBigDecimal</code> is associated to table column <code>value_big_decimal</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueBigDecimal
   */
  public static final String COLUMN_VALUE_BIG_DECIMAL = "value_big_decimal";

  /**
   * Entity's property <code>valueBigInteger</code> is associated to table column <code>value_big_integer</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueBigInteger
   */
  public static final String COLUMN_VALUE_BIG_INTEGER = "value_big_integer";

  /**
   * Entity's property <code>valueBoolType</code> is associated to table column <code>value_bool_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueBoolType
   */
  public static final String COLUMN_VALUE_BOOL_TYPE = "value_bool_type";

  /**
   * Entity's property <code>valueBool</code> is associated to table column <code>value_bool</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueBool
   */
  public static final String COLUMN_VALUE_BOOL = "value_bool";

  /**
   * Entity's property <code>valueByteType</code> is associated to table column <code>value_byte_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueByteType
   */
  public static final String COLUMN_VALUE_BYTE_TYPE = "value_byte_type";

  /**
   * Entity's property <code>valueByte</code> is associated to table column <code>value_byte</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueByte
   */
  public static final String COLUMN_VALUE_BYTE = "value_byte";

  /**
   * Entity's property <code>valueCharType</code> is associated to table column <code>value_char_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueCharType
   */
  public static final String COLUMN_VALUE_CHAR_TYPE = "value_char_type";

  /**
   * Entity's property <code>valueChar</code> is associated to table column <code>value_char</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueChar
   */
  public static final String COLUMN_VALUE_CHAR = "value_char";

  /**
   * Entity's property <code>valueShortType</code> is associated to table column <code>value_short_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueShortType
   */
  public static final String COLUMN_VALUE_SHORT_TYPE = "value_short_type";

  /**
   * Entity's property <code>valueShort</code> is associated to table column <code>value_short</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueShort
   */
  public static final String COLUMN_VALUE_SHORT = "value_short";

  /**
   * Entity's property <code>valueIntType</code> is associated to table column <code>value_int_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueIntType
   */
  public static final String COLUMN_VALUE_INT_TYPE = "value_int_type";

  /**
   * Entity's property <code>valueInt</code> is associated to table column <code>value_int</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueInt
   */
  public static final String COLUMN_VALUE_INT = "value_int";

  /**
   * Entity's property <code>valueLongType</code> is associated to table column <code>value_long_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueLongType
   */
  public static final String COLUMN_VALUE_LONG_TYPE = "value_long_type";

  /**
   * Entity's property <code>valueLong</code> is associated to table column <code>value_long</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueLong
   */
  public static final String COLUMN_VALUE_LONG = "value_long";

  /**
   * Entity's property <code>valueFloatType</code> is associated to table column <code>value_float_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueFloatType
   */
  public static final String COLUMN_VALUE_FLOAT_TYPE = "value_float_type";

  /**
   * Entity's property <code>valueFloat</code> is associated to table column <code>value_float</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueFloat
   */
  public static final String COLUMN_VALUE_FLOAT = "value_float";

  /**
   * Entity's property <code>valueDoubleType</code> is associated to table column <code>value_double_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueDoubleType
   */
  public static final String COLUMN_VALUE_DOUBLE_TYPE = "value_double_type";

  /**
   * Entity's property <code>valueDouble</code> is associated to table column <code>value_double</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueDouble
   */
  public static final String COLUMN_VALUE_DOUBLE = "value_double";

  /**
   * Entity's property <code>valueString</code> is associated to table column <code>value_string</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueString
   */
  public static final String COLUMN_VALUE_STRING = "value_string";

  /**
   * Entity's property <code>valueDate</code> is associated to table column <code>value_date</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueDate
   */
  public static final String COLUMN_VALUE_DATE = "value_date";

  /**
   * Entity's property <code>valueCalendar</code> is associated to table column <code>value_calendar</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueCalendar
   */
  public static final String COLUMN_VALUE_CALENDAR = "value_calendar";

  /**
   * Entity's property <code>valueLocale</code> is associated to table column <code>value_locale</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueLocale
   */
  public static final String COLUMN_VALUE_LOCALE = "value_locale";

  /**
   * Entity's property <code>valueUrl</code> is associated to table column <code>value_url</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueUrl
   */
  public static final String COLUMN_VALUE_URL = "value_url";

  /**
   * Entity's property <code>valueTime</code> is associated to table column <code>value_time</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueTime
   */
  public static final String COLUMN_VALUE_TIME = "value_time";

  /**
   * Entity's property <code>valueCurrency</code> is associated to table column <code>value_currency</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueCurrency
   */
  public static final String COLUMN_VALUE_CURRENCY = "value_currency";

  /**
   * Entity's property <code>valueTimeZone</code> is associated to table column <code>value_time_zone</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueTimeZone
   */
  public static final String COLUMN_VALUE_TIME_ZONE = "value_time_zone";

  /**
   * Entity's property <code>valueEnumType</code> is associated to table column <code>value_enum_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton60.Bean#valueEnumType
   */
  public static final String COLUMN_VALUE_ENUM_TYPE = "value_enum_type";
}
