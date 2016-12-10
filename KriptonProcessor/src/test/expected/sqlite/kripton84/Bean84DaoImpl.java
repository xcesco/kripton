package sqlite.kripton84;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DAO implementation for entity <code>Bean84</code>, based on interface <code>Bean84Dao</code>
 * </p>
 *
 *  @see Bean84
 *  @see Bean84Dao
 *  @see Bean84Table
 */
public class Bean84DaoImpl extends AbstractDao implements Bean84Dao {
  public Bean84DaoImpl(BindBean84DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type FROM bean84 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type FROM bean84 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type FROM bean84 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean84> resultList=new LinkedList<Bean84>();
    Bean84 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("column_list_string");
      int index2=cursor.getColumnIndex("column_map_integer_string");
      int index3=cursor.getColumnIndex("column_array_char");
      int index4=cursor.getColumnIndex("column_array_char_type");
      int index5=cursor.getColumnIndex("column_array_byte_type");

      do
       {
        resultBean=new Bean84();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.columnListString=Bean84Table.parseColumnListString(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84Table.parseColumnMapIntegerString(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84Table.parseColumnArrayChar(cursor.getBlob(index3)); }
        if (!cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84Table.parseColumnArrayCharType(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.columnArrayByteType=Bean84Table.parseColumnArrayByteType(cursor.getBlob(index5)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type FROM bean84 WHERE columnListString=${param1} and columnMapIntegerString=${param2} and columnArrayChar=${param3}  and columnArrayCharType=${param4}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${param1}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * 	<dt>${param2}</dt><dd>is binded to method's parameter <strong>param2</strong></dd>
   * 	<dt>${param3}</dt><dd>is binded to method's parameter <strong>param3</strong></dd>
   * 	<dt>${param4}</dt><dd>is binded to method's parameter <strong>param4</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to ${param1}
   * @param param2
   * 	is binded to ${param2}
   * @param param3
   * 	is binded to ${param3}
   * @param param4
   * 	is binded to ${param4}
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84> selectWhere(List<String> param1, Map<Integer, String> param2, Character[] param3, char[] param4) {
    // build where condition
    String[] args={(param1==null?null:new String(serializer1(param1),StandardCharsets.UTF_8)), (param2==null?null:String.valueOf(serializer2(param2))), (param3==null?null:new String(serializer3(param3),StandardCharsets.UTF_8)), (param4==null?null:new String(serializer4(param4),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type FROM bean84 WHERE column_list_string='%s' and column_map_integer_string='%s' and column_array_char='%s'  and column_array_char_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type FROM bean84 WHERE column_list_string=? and column_map_integer_string=? and column_array_char=?  and column_array_char_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean84> resultList=new LinkedList<Bean84>();
    Bean84 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("column_list_string");
      int index2=cursor.getColumnIndex("column_map_integer_string");
      int index3=cursor.getColumnIndex("column_array_char");
      int index4=cursor.getColumnIndex("column_array_char_type");
      int index5=cursor.getColumnIndex("column_array_byte_type");

      do
       {
        resultBean=new Bean84();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.columnListString=Bean84Table.parseColumnListString(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84Table.parseColumnMapIntegerString(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84Table.parseColumnArrayChar(cursor.getBlob(index3)); }
        if (!cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84Table.parseColumnArrayCharType(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.columnArrayByteType=Bean84Table.parseColumnArrayByteType(cursor.getBlob(index5)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84 (column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type) VALUES (${bean.columnListString}, ${bean.columnMapIntegerString}, ${bean.columnArrayChar}, ${bean.columnArrayCharType}, ${bean.columnArrayByteType})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>column_list_string</dt><dd>is mapped to <strong>${bean.columnListString}</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is mapped to <strong>${bean.columnMapIntegerString}</strong></dd>
   * 	<dt>column_array_char</dt><dd>is mapped to <strong>${bean.columnArrayChar}</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is mapped to <strong>${bean.columnArrayCharType}</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is mapped to <strong>${bean.columnArrayByteType}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertAll(Bean84 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnListString!=null) {
      contentValues.put("column_list_string", Bean84Table.serializeColumnListString(bean.columnListString));
    } else {
      contentValues.putNull("column_list_string");
    }

    if (bean.columnMapIntegerString!=null) {
      contentValues.put("column_map_integer_string", Bean84Table.serializeColumnMapIntegerString(bean.columnMapIntegerString));
    } else {
      contentValues.putNull("column_map_integer_string");
    }

    if (bean.columnArrayChar!=null) {
      contentValues.put("column_array_char", Bean84Table.serializeColumnArrayChar(bean.columnArrayChar));
    } else {
      contentValues.putNull("column_array_char");
    }

    if (bean.columnArrayCharType!=null) {
      contentValues.put("column_array_char_type", Bean84Table.serializeColumnArrayCharType(bean.columnArrayCharType));
    } else {
      contentValues.putNull("column_array_char_type");
    }

    if (bean.columnArrayByteType!=null) {
      contentValues.put("column_array_byte_type", Bean84Table.serializeColumnArrayByteType(bean.columnArrayByteType));
    } else {
      contentValues.putNull("column_array_byte_type");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO bean84 (column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_array_byte_type) VALUES ('"+StringUtils.checkSize(contentValues.get("column_list_string"))+"', '"+StringUtils.checkSize(contentValues.get("column_map_integer_string"))+"', '"+StringUtils.checkSize(contentValues.get("column_array_char"))+"', '"+StringUtils.checkSize(contentValues.get("column_array_char_type"))+"', '"+StringUtils.checkSize(contentValues.get("column_array_byte_type"))+"')"));
    long result = database().insert("bean84", null, contentValues);
    bean.id=result;

    return result!=-1;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84 (column_list_string) VALUES (${columnListString})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>column_list_string</dt><dd>is binded to query's parameter <strong>${columnListString}</strong> and method's parameter <strong>param1</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to column <strong>column_list_string</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(List<String> param1) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (param1!=null) {
      contentValues.put("column_list_string", serializer1(param1));
    } else {
      contentValues.putNull("column_list_string");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO bean84 (column_list_string) VALUES ('"+StringUtils.checkSize(contentValues.get("column_list_string"))+"')"));
    long result = database().insert("bean84", null, contentValues);
    return result!=-1;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE bean84 SET column_list_string=${bean.columnListString}, column_map_integer_string=${bean.columnMapIntegerString}, column_array_char=${bean.columnArrayChar}, column_array_char_type=${bean.columnArrayCharType}, column_array_byte_type=${bean.columnArrayByteType} WHERE 1=1</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>column_list_string</dt><dd>is mapped to <strong>${bean.columnListString}</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is mapped to <strong>${bean.columnMapIntegerString}</strong></dd>
   * 	<dt>column_array_char</dt><dd>is mapped to <strong>${bean.columnArrayChar}</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is mapped to <strong>${bean.columnArrayCharType}</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is mapped to <strong>${bean.columnArrayByteType}</strong></dd>
   * </dl>
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateAll(Bean84 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnListString!=null) {
      contentValues.put("column_list_string", Bean84Table.serializeColumnListString(bean.columnListString));
    } else {
      contentValues.putNull("column_list_string");
    }

    if (bean.columnMapIntegerString!=null) {
      contentValues.put("column_map_integer_string", Bean84Table.serializeColumnMapIntegerString(bean.columnMapIntegerString));
    } else {
      contentValues.putNull("column_map_integer_string");
    }

    if (bean.columnArrayChar!=null) {
      contentValues.put("column_array_char", Bean84Table.serializeColumnArrayChar(bean.columnArrayChar));
    } else {
      contentValues.putNull("column_array_char");
    }

    if (bean.columnArrayCharType!=null) {
      contentValues.put("column_array_char_type", Bean84Table.serializeColumnArrayCharType(bean.columnArrayCharType));
    } else {
      contentValues.putNull("column_array_char_type");
    }

    if (bean.columnArrayByteType!=null) {
      contentValues.put("column_array_byte_type", Bean84Table.serializeColumnArrayByteType(bean.columnArrayByteType));
    } else {
      contentValues.putNull("column_array_byte_type");
    }

    String[] whereConditions={};

    Logger.info(StringUtils.formatSQL("UPDATE bean84 SET column_list_string='"+StringUtils.checkSize(contentValues.get("column_list_string"))+"', column_map_integer_string='"+StringUtils.checkSize(contentValues.get("column_map_integer_string"))+"', column_array_char='"+StringUtils.checkSize(contentValues.get("column_array_char"))+"', column_array_char_type='"+StringUtils.checkSize(contentValues.get("column_array_char_type"))+"', column_array_byte_type='"+StringUtils.checkSize(contentValues.get("column_array_byte_type"))+"' WHERE 1=1"), (Object[])whereConditions);
    int result = database().update("bean84", contentValues, "1=1", whereConditions);
    return result!=0;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE bean84 WHERE 1=1</pre>
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteAll(Bean84 bean) {
    String[] whereConditions={};

    Logger.info(StringUtils.formatSQL("1=1"), (Object[])whereConditions);
    int result = database().delete("bean84", "1=1", whereConditions);
    return result!=0;
  }

  /**
   * write
   */
  protected static byte[] serializer4(char[] value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        int n=value.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected static char[] parser4(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      char[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected static byte[] serializer3(Character[] value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        int n=value.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected static Character[] parser3(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Character[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected static byte[] serializer1(List<String> value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        int n=value.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
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
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected static List<String> parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<String> collection=new ArrayList<>();
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

  /**
   * write
   */
  protected static byte[] serializer2(Map<Integer, String> value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("element");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Integer, String> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeNumberField(null, item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField(null);
            } else {
              jacksonSerializer.writeStringField(null, item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("element");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected static Map<Integer, String> parser2(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Map<Integer, String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<Integer, String> collection=new HashMap<>();
        Integer key=null;
        String value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getIntValue();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getText();
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
