package com.abubusoft.kripton.processor.kripton58.array2;

import java.lang.String;

/**
 * <p>
 * Entity <code>BeanBean</code> is associated to table <code>bean_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.array2.BeanBean
 */
public class BeanBeanTable {
  /**
   * Costant represents name of table bean_bean
   */
  public static final String TABLE_NAME = "bean_bean";

  /**
   * <p>
   * DDL to create table bean_bean
   * </p>
   *
   * <pre>CREATE TABLE bean_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table bean_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array2.BeanBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array2.BeanBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton58.array2.BeanBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";
}
