package com.abubusoft.kripton.processor.kripton58.list;

import java.lang.String;

/**
 * <p>
 * Entity <code>CharBean</code> is associated to table <code>char_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see CharBean
 */
public class CharBean$Table {
  /**
   * Costant represents name of table char_bean
   */
  public static final String TABLE_NAME = "char_bean";

  /**
   * <p>
   * DDL to create table char_bean
   * </p>
   *
   * <pre>CREATE TABLE char_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE char_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table char_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS char_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS char_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see CharBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see CharBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see CharBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
