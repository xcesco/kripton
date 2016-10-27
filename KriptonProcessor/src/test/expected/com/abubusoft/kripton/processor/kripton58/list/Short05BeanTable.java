package com.abubusoft.kripton.processor.kripton58.list;

import java.lang.String;

/**
 * <p>
 * Entity <code>Short05Bean</code> is associated to table <code>short05_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Bean
 */
public class Short05BeanTable {
  /**
   * Costant represents name of table short05_bean
   */
  public static final String TABLE_NAME = "short05_bean";

  /**
   * <p>
   * DDL to create table short05_bean
   * </p>
   *
   * <pre>CREATE TABLE short05_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE short05_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table short05_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS short05_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS short05_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Bean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Bean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
