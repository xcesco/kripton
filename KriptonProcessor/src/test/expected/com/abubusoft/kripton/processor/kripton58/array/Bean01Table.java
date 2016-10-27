package com.abubusoft.kripton.processor.kripton58.array;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean01</code> is associated to table <code>bean01</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01
 */
public class Bean01Table {
  /**
   * Costant represents name of table bean01
   */
  public static final String TABLE_NAME = "bean01";

  /**
   * <p>
   * DDL to create table bean01
   * </p>
   *
   * <pre>CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, string_value BLOB, long_value BLOB, integer_value BLOB, byte_value BLOB, short_value BLOB, bean_value BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, string_value BLOB, long_value BLOB, integer_value BLOB, byte_value BLOB, short_value BLOB, bean_value BLOB);";

  /**
   * <p>
   * DDL to drop table bean01
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean01;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean01;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>stringValue</code> is associated to table column <code>string_value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#stringValue
   */
  public static final String COLUMN_STRING_VALUE = "string_value";

  /**
   * Entity's property <code>longValue</code> is associated to table column <code>long_value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#longValue
   */
  public static final String COLUMN_LONG_VALUE = "long_value";

  /**
   * Entity's property <code>integerValue</code> is associated to table column <code>integer_value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#integerValue
   */
  public static final String COLUMN_INTEGER_VALUE = "integer_value";

  /**
   * Entity's property <code>byteValue</code> is associated to table column <code>byte_value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#byteValue
   */
  public static final String COLUMN_BYTE_VALUE = "byte_value";

  /**
   * Entity's property <code>shortValue</code> is associated to table column <code>short_value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#shortValue
   */
  public static final String COLUMN_SHORT_VALUE = "short_value";

  /**
   * Entity's property <code>beanValue</code> is associated to table column <code>bean_value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array.Bean01#beanValue
   */
  public static final String COLUMN_BEAN_VALUE = "bean_value";
}
