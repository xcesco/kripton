package sqlite.kripton62;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean</code> is associated to table <code>bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean
 */
public class BeanTable {
  /**
   * Costant represents name of table bean
   */
  public static final String TABLE_NAME = "bean";

  /**
   * <p>
   * DDL to create table bean
   * </p>
   *
   * <pre>CREATE TABLE bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value_byte_set BLOB, value_short_set BLOB, value_integer_set BLOB, value_string_set BLOB, value_character_set BLOB, value_float_set BLOB, value_double_set BLOB, value_big_decimal_set BLOB, value_bean_set BLOB, value_enum_type_set BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value_byte_set BLOB, value_short_set BLOB, value_integer_set BLOB, value_string_set BLOB, value_character_set BLOB, value_float_set BLOB, value_double_set BLOB, value_big_decimal_set BLOB, value_bean_set BLOB, value_enum_type_set BLOB);";

  /**
   * <p>
   * DDL to drop table bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>valueByteSet</code> is associated to table column <code>value_byte_set</code>. This costant represents column name.
   *
   *  @see Bean#valueByteSet
   */
  public static final String COLUMN_VALUE_BYTE_SET = "value_byte_set";

  /**
   * Entity's property <code>valueShortSet</code> is associated to table column <code>value_short_set</code>. This costant represents column name.
   *
   *  @see Bean#valueShortSet
   */
  public static final String COLUMN_VALUE_SHORT_SET = "value_short_set";

  /**
   * Entity's property <code>valueIntegerSet</code> is associated to table column <code>value_integer_set</code>. This costant represents column name.
   *
   *  @see Bean#valueIntegerSet
   */
  public static final String COLUMN_VALUE_INTEGER_SET = "value_integer_set";

  /**
   * Entity's property <code>valueStringSet</code> is associated to table column <code>value_string_set</code>. This costant represents column name.
   *
   *  @see Bean#valueStringSet
   */
  public static final String COLUMN_VALUE_STRING_SET = "value_string_set";

  /**
   * Entity's property <code>valueCharacterSet</code> is associated to table column <code>value_character_set</code>. This costant represents column name.
   *
   *  @see Bean#valueCharacterSet
   */
  public static final String COLUMN_VALUE_CHARACTER_SET = "value_character_set";

  /**
   * Entity's property <code>valueFloatSet</code> is associated to table column <code>value_float_set</code>. This costant represents column name.
   *
   *  @see Bean#valueFloatSet
   */
  public static final String COLUMN_VALUE_FLOAT_SET = "value_float_set";

  /**
   * Entity's property <code>valueDoubleSet</code> is associated to table column <code>value_double_set</code>. This costant represents column name.
   *
   *  @see Bean#valueDoubleSet
   */
  public static final String COLUMN_VALUE_DOUBLE_SET = "value_double_set";

  /**
   * Entity's property <code>valueBigDecimalSet</code> is associated to table column <code>value_big_decimal_set</code>. This costant represents column name.
   *
   *  @see Bean#valueBigDecimalSet
   */
  public static final String COLUMN_VALUE_BIG_DECIMAL_SET = "value_big_decimal_set";

  /**
   * Entity's property <code>valueBeanSet</code> is associated to table column <code>value_bean_set</code>. This costant represents column name.
   *
   *  @see Bean#valueBeanSet
   */
  public static final String COLUMN_VALUE_BEAN_SET = "value_bean_set";

  /**
   * Entity's property <code>valueEnumTypeSet</code> is associated to table column <code>value_enum_type_set</code>. This costant represents column name.
   *
   *  @see Bean#valueEnumTypeSet
   */
  public static final String COLUMN_VALUE_ENUM_TYPE_SET = "value_enum_type_set";
}
