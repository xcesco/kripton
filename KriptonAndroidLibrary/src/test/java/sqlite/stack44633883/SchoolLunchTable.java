package sqlite.stack44633883;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashSet;

/**
 * <p>
 * Entity <code>SchoolLunch</code> is associated to table <code>SchoolLunches</code>
 * This class represents table associated to entity.
 * </p>
 *  @see SchoolLunch
 */
public class SchoolLunchTable {
  /**
   * Costant represents typeName of table SchoolLunches
   */
  public static final String TABLE_NAME = "SchoolLunches";

  /**
   * <p>
   * DDL to create table SchoolLunches
   * </p>
   *
   * <pre>CREATE TABLE SchoolLunches (lunch_id INTEGER PRIMARY KEY AUTOINCREMENT, fresh INTEGER, contains_meat INTEGER, fruits BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE SchoolLunches (lunch_id INTEGER PRIMARY KEY AUTOINCREMENT, fresh INTEGER, contains_meat INTEGER, fruits BLOB);";

  /**
   * <p>
   * DDL to drop table SchoolLunches
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS SchoolLunches;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS SchoolLunches;";

  /**
   * Entity's property <code>lunchId</code> is associated to table column <code>lunch_id</code>. This costant represents column name.
   *
   *  @see SchoolLunch#lunchId
   */
  public static final String COLUMN_LUNCH_ID = "lunch_id";

  /**
   * Entity's property <code>fresh</code> is associated to table column <code>fresh</code>. This costant represents column name.
   *
   *  @see SchoolLunch#fresh
   */
  public static final String COLUMN_FRESH = "fresh";

  /**
   * Entity's property <code>containsMeat</code> is associated to table column <code>contains_meat</code>. This costant represents column name.
   *
   *  @see SchoolLunch#containsMeat
   */
  public static final String COLUMN_CONTAINS_MEAT = "contains_meat";

  /**
   * Entity's property <code>fruits</code> is associated to table column <code>fruits</code>. This costant represents column name.
   *
   *  @see SchoolLunch#fruits
   */
  public static final String COLUMN_FRUITS = "fruits";

  /**
   * for attribute fruits serialization
   */
  public static byte[] serializeFruits(HashSet<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (String item: value) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute fruits parsing
   */
  public static HashSet<String> parseFruits(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      HashSet<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<String> collection=new HashSet<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
