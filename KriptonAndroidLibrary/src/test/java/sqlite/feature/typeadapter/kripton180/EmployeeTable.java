package sqlite.feature.typeadapter.kripton180;

import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * <p>
 * Entity <code>Employee</code> is associated to table <code>employees</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Employee
 */
public class EmployeeTable {
  /**
   * Costant represents typeName of table employees
   */
  public static final String TABLE_NAME = "employees";

  /**
   * <p>
   * DDL to create table employees
   * </p>
   *
   * <pre>CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT, last_name TEXT, first_name TEXT, birth_date TEXT, hire_date TEXT, address TEXT, field_boolean INTEGER, field_byte INTEGER, field_character INTEGER, field_short INTEGER, field_integer INTEGER, field_long INTEGER, field_float REAL, field_double REAL, field_string TEXT, field_byte_array BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE employees (id INTEGER PRIMARY KEY AUTOINCREMENT, last_name TEXT, first_name TEXT, birth_date TEXT, hire_date TEXT, address TEXT, field_boolean INTEGER, field_byte INTEGER, field_character INTEGER, field_short INTEGER, field_integer INTEGER, field_long INTEGER, field_float REAL, field_double REAL, field_string TEXT, field_byte_array BLOB);";

  /**
   * <p>
   * DDL to drop table employees
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS employees;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS employees;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Employee#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>lastName</code> is associated to table column <code>last_name</code>. This costant represents column name.
   *
   *  @see Employee#lastName
   */
  public static final String COLUMN_LAST_NAME = "last_name";

  /**
   * Entity's property <code>firstName</code> is associated to table column <code>first_name</code>. This costant represents column name.
   *
   *  @see Employee#firstName
   */
  public static final String COLUMN_FIRST_NAME = "first_name";

  /**
   * Entity's property <code>birthDate</code> is associated to table column <code>birth_date</code>. This costant represents column name.
   *
   *  @see Employee#birthDate
   */
  public static final String COLUMN_BIRTH_DATE = "birth_date";

  /**
   * Entity's property <code>hireDate</code> is associated to table column <code>hire_date</code>. This costant represents column name.
   *
   *  @see Employee#hireDate
   */
  public static final String COLUMN_HIRE_DATE = "hire_date";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column name.
   *
   *  @see Employee#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>fieldBoolean</code> is associated to table column <code>field_boolean</code>. This costant represents column name.
   *
   *  @see Employee#fieldBoolean
   */
  public static final String COLUMN_FIELD_BOOLEAN = "field_boolean";

  /**
   * Entity's property <code>fieldByte</code> is associated to table column <code>field_byte</code>. This costant represents column name.
   *
   *  @see Employee#fieldByte
   */
  public static final String COLUMN_FIELD_BYTE = "field_byte";

  /**
   * Entity's property <code>fieldCharacter</code> is associated to table column <code>field_character</code>. This costant represents column name.
   *
   *  @see Employee#fieldCharacter
   */
  public static final String COLUMN_FIELD_CHARACTER = "field_character";

  /**
   * Entity's property <code>fieldShort</code> is associated to table column <code>field_short</code>. This costant represents column name.
   *
   *  @see Employee#fieldShort
   */
  public static final String COLUMN_FIELD_SHORT = "field_short";

  /**
   * Entity's property <code>fieldInteger</code> is associated to table column <code>field_integer</code>. This costant represents column name.
   *
   *  @see Employee#fieldInteger
   */
  public static final String COLUMN_FIELD_INTEGER = "field_integer";

  /**
   * Entity's property <code>fieldLong</code> is associated to table column <code>field_long</code>. This costant represents column name.
   *
   *  @see Employee#fieldLong
   */
  public static final String COLUMN_FIELD_LONG = "field_long";

  /**
   * Entity's property <code>fieldFloat</code> is associated to table column <code>field_float</code>. This costant represents column name.
   *
   *  @see Employee#fieldFloat
   */
  public static final String COLUMN_FIELD_FLOAT = "field_float";

  /**
   * Entity's property <code>fieldDouble</code> is associated to table column <code>field_double</code>. This costant represents column name.
   *
   *  @see Employee#fieldDouble
   */
  public static final String COLUMN_FIELD_DOUBLE = "field_double";

  /**
   * Entity's property <code>fieldString</code> is associated to table column <code>field_string</code>. This costant represents column name.
   *
   *  @see Employee#fieldString
   */
  public static final String COLUMN_FIELD_STRING = "field_string";

  /**
   * Entity's property <code>fieldByteArray</code> is associated to table column <code>field_byte_array</code>. This costant represents column name.
   *
   *  @see Employee#fieldByteArray
   */
  public static final String COLUMN_FIELD_BYTE_ARRAY = "field_byte_array";

  /**
   * AddressBindMap */
  private static AddressBindMap addressBindMap = BinderUtils.mapperFor(Address.class);

  /**
   * for attribute address serialization
   */
  public static byte[] serializeAddress(Address value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        addressBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute address parsing
   */
  public static Address parseAddress(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Address result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=addressBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
