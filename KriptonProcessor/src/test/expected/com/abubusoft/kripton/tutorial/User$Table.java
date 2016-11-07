package com.abubusoft.kripton.tutorial;

import java.lang.String;

/**
 * <p>
 * Entity <code>User</code> is associated to table <code>user</code>
 * This class represents table associated to entity.
 * </p>
 *  @see User
 */
public class User$Table {
  /**
   * Costant represents name of table user
   */
  public static final String TABLE_NAME = "user";

  /**
   * <p>
   * DDL to create table user
   * </p>
   *
   * <pre>CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, name TEXT, surname TEXT, username TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, name TEXT, surname TEXT, username TEXT);";

  /**
   * <p>
   * DDL to drop table user
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS user;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS user;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see User#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>email</code> is associated to table column <code>email</code>. This costant represents column name.
   *
   *  @see User#email
   */
  public static final String COLUMN_EMAIL = "email";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see User#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see User#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Entity's property <code>username</code> is associated to table column <code>username</code>. This costant represents column name.
   *
   *  @see User#username
   */
  public static final String COLUMN_USERNAME = "username";
}
