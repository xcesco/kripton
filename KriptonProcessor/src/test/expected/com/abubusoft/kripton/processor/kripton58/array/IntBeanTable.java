package com.abubusoft.kripton.processor.kripton58.array;

import java.lang.String;

/**
 * <p>
 * Entity <code>IntBean</code> is associated to table <code>int_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see IntBean
 */
public class IntBeanTable {
  /**
   * Costant represents name of table int_bean
   */
  public static final String TABLE_NAME = "int_bean";

  /**
   * <p>
   * DDL to create table int_bean
   * </p>
   *
   * <pre>CREATE TABLE int_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE int_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table int_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS int_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS int_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see IntBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see IntBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see IntBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
