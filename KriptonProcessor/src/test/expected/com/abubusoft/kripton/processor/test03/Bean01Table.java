package com.abubusoft.kripton.processor.test03;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean01</code> is associated to table <code>bean01</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.test03.Bean01
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
   * <pre>CREATE TABLE bean01 (lista BLOB, id INTEGER PRIMARY KEY AUTOINCREMENT, message_date INTEGER, message_text TEXT NOT NULL, bean_list BLOB, value INTEGER);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01 (lista BLOB, id INTEGER PRIMARY KEY AUTOINCREMENT, message_date INTEGER, message_text TEXT NOT NULL, bean_list BLOB, value INTEGER);";

  /**
   * <p>
   * DDL to drop table bean01
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean01;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean01;";

  /**
   * Entity's property <code>lista</code> is associated to table column <code>lista</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.test03.Bean01#lista
   */
  public static final String COLUMN_LISTA = "lista";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.test03.Bean01#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>messageDate</code> is associated to table column <code>message_date</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.test03.Bean01#messageDate
   */
  public static final String COLUMN_MESSAGE_DATE = "message_date";

  /**
   * Entity's property <code>messageText</code> is associated to table column <code>message_text</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.test03.Bean01#messageText
   */
  public static final String COLUMN_MESSAGE_TEXT = "message_text";

  /**
   * Entity's property <code>beanList</code> is associated to table column <code>bean_list</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.test03.Bean01#beanList
   */
  public static final String COLUMN_BEAN_LIST = "bean_list";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.test03.Bean01#value
   */
  public static final String COLUMN_VALUE = "value";
}
