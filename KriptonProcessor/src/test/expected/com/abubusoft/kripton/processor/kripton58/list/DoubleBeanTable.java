package com.abubusoft.kripton.processor.kripton58.list;

import java.lang.String;

/**
 * <p>
 * Entity <code>DoubleBean</code> is associated to table <code>double_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see DoubleBean
 */
public class DoubleBeanTable {
  /**
   * Costant represents name of table double_bean
   */
  public static final String TABLE_NAME = "double_bean";

  /**
   * <p>
   * DDL to create table double_bean
   * </p>
   *
   * <pre>CREATE TABLE double_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE double_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table double_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS double_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS double_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see DoubleBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see DoubleBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see DoubleBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
