package com.abubusoft.kripton.processor.kripton38;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean03</code> is associated to table <code>bean03</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean03
 */
public class Bean03Table {
  /**
   * Costant represents name of table bean03
   */
  public static final String TABLE_NAME = "bean03";

  /**
   * <p>
   * DDL to create table bean03
   * </p>
   *
   * <pre>CREATE TABLE bean03 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean03 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);";

  /**
   * <p>
   * DDL to drop table bean03
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean03;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean03;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean03#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see Bean03#text
   */
  public static final String COLUMN_TEXT = "text";
}
