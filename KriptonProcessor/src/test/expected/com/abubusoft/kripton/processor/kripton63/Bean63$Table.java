package com.abubusoft.kripton.processor.kripton63;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean63</code> is associated to table <code>bean63</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean63
 */
public class Bean63$Table {
  /**
   * Costant represents name of table bean63
   */
  public static final String TABLE_NAME = "bean63";

  /**
   * <p>
   * DDL to create table bean63
   * </p>
   *
   * <pre>CREATE TABLE bean63 (id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT, value_map_string_byte BLOB, value_map_enum_byte BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean63 (id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT, value_map_string_byte BLOB, value_map_enum_byte BLOB);";

  /**
   * <p>
   * DDL to drop table bean63
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean63;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean63;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean63#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see Bean63#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>valueMapStringByte</code> is associated to table column <code>value_map_string_byte</code>. This costant represents column name.
   *
   *  @see Bean63#valueMapStringByte
   */
  public static final String COLUMN_VALUE_MAP_STRING_BYTE = "value_map_string_byte";

  /**
   * Entity's property <code>valueMapEnumByte</code> is associated to table column <code>value_map_enum_byte</code>. This costant represents column name.
   *
   *  @see Bean63#valueMapEnumByte
   */
  public static final String COLUMN_VALUE_MAP_ENUM_BYTE = "value_map_enum_byte";
}
