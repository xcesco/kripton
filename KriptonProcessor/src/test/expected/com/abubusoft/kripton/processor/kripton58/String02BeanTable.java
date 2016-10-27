package com.abubusoft.kripton.processor.kripton58;

import java.lang.String;

/**
 * <p>
 * Entity <code>String02Bean</code> is associated to table <code>string02_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.list.String02Bean
 */
public class String02BeanTable {
  /**
   * Costant represents name of table string02_bean
   */
  public static final String TABLE_NAME = "string02_bean";

  /**
   * <p>
   * DDL to create table string02_bean
   * </p>
   *
   * <pre>CREATE TABLE string02_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE string02_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB);";

  /**
   * <p>
   * DDL to drop table string02_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS string02_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS string02_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.list.String02Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.list.String02Bean#value
   */
  public static final String COLUMN_VALUE = "value";
}
