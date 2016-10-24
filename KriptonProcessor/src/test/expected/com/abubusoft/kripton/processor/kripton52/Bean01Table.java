package com.abubusoft.kripton.processor.kripton52;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean01</code> is associated to table <code>bean01</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton52.Bean01
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
   * <pre>CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, a_byte BLOB, a_string BLOB, a_long BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, a_byte BLOB, a_string BLOB, a_long BLOB);";

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
   *  @see com.abubusoft.kripton.processor.kripton52.Bean01#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>aByte</code> is associated to table column <code>a_byte</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton52.Bean01#aByte
   */
  public static final String COLUMN_A_BYTE = "a_byte";

  /**
   * Entity's property <code>aString</code> is associated to table column <code>a_string</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton52.Bean01#aString
   */
  public static final String COLUMN_A_STRING = "a_string";

  /**
   * Entity's property <code>aLong</code> is associated to table column <code>a_long</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton52.Bean01#aLong
   */
  public static final String COLUMN_A_LONG = "a_long";
}
