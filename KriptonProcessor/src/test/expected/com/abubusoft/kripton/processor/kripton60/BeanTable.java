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
   * <pre>CREATE TABLE bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value_big_decimal TEXT, value_big_integer TEXT, value_bool_type INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value_big_decimal TEXT, value_big_integer TEXT, value_bool_type INTEGER);";

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
}
