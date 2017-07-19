package sqlite.kripton49.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean01Entity</code> is associated to table <code>bean01_entity</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean01Entity
 */
public class Bean01EntityTable {
  /**
   * Costant represents typeName of table bean01_entity
   */
  public static final String TABLE_NAME = "bean01_entity";

  /**
   * <p>
   * DDL to create table bean01_entity
   * </p>
   *
   * <pre>CREATE TABLE bean01_entity (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean01_entity (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);";

  /**
   * <p>
   * DDL to drop table bean01_entity
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean01_entity;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean01_entity;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see Bean01Entity#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column typeName.
   *
   *  @see Bean01Entity#text
   */
  public static final String COLUMN_TEXT = "text";
}
