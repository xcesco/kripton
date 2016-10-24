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
   * <pre>CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, array BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01 (id INTEGER PRIMARY KEY AUTOINCREMENT, array BLOB);";

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
   * Entity's property <code>array</code> is associated to table column <code>array</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton52.Bean01#array
   */
  public static final String COLUMN_ARRAY = "array";
}
