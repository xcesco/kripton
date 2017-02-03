package sqlite.kripton84;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
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
 * DAO implementation for entity <code>Bean84A</code>, based on interface <code>Bean84ADao</code>
 * </p>
 *
 *  @see Bean84A
 *  @see Bean84ADao
 *  @see Bean84ATable
 */
public class Bean84ADaoImpl extends AbstractDao implements Bean84ADao {
  public Bean84ADaoImpl(BindBean84ADataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84A> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
      Bean84A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("column_list_string");
        int index2=cursor.getColumnIndex("column_map_integer_string");
        int index3=cursor.getColumnIndex("column_array_char");
        int index4=cursor.getColumnIndex("column_array_char_type");
        int index5=cursor.getColumnIndex("column_bean");
        int index6=cursor.getColumnIndex("column_array_byte_type");
        int index7=cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean84A();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index3)); }
          if (!cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.columnArrayByteType=Bean84ATable.parseColumnArrayByteType(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.valueString=cursor.getString(index7); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>uid</strong></dd>
   * </dl>
   *
   * @param uid
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84A> selectById(long uid) {
    // build where condition
    String[] args={String.valueOf(uid)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE id='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE id=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
      Bean84A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("column_list_string");
        int index2=cursor.getColumnIndex("column_map_integer_string");
        int index3=cursor.getColumnIndex("column_array_char");
        int index4=cursor.getColumnIndex("column_array_char_type");
        int index5=cursor.getColumnIndex("column_bean");
        int index6=cursor.getColumnIndex("column_array_byte_type");
        int index7=cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean84A();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index3)); }
          if (!cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.columnArrayByteType=Bean84ATable.parseColumnArrayByteType(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.valueString=cursor.getString(index7); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE columnListString=${param1} and columnMapIntegerString=${param2} and columnArrayChar=${param3}  and columnArrayCharType=${param4}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_list_string</dt><dd>is associated to bean's property <strong>columnListString</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is associated to bean's property <strong>columnMapIntegerString</strong></dd>
   * 	<dt>column_array_char</dt><dd>is associated to bean's property <strong>columnArrayChar</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is associated to bean's property <strong>columnArrayCharType</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is associated to bean's property <strong>columnArrayByteType</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${param1}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * 	<dt>${param2}</dt><dd>is binded to method's parameter <strong>param2</strong></dd>
   * 	<dt>${param3}</dt><dd>is binded to method's parameter <strong>param3</strong></dd>
   * 	<dt>${param4}</dt><dd>is binded to method's parameter <strong>param4</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to <code>${param1}</code>
   * @param param2
   * 	is binded to <code>${param2}</code>
   * @param param3
   * 	is binded to <code>${param3}</code>
   * @param param4
   * 	is binded to <code>${param4}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean84A> selectWhere(List<String> param1, Map<Integer, String> param2, Character[] param3, char[] param4) {
    // build where condition
    String[] args={(param1==null?"":new String(serializer1(param1),StandardCharsets.UTF_8)), (param2==null?"":new String(serializer2(param2),StandardCharsets.UTF_8)), (param3==null?"":new String(serializer3(param3),StandardCharsets.UTF_8)), (param4==null?"":new String(serializer4(param4),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE column_list_string='%s' and column_map_integer_string='%s' and column_array_char='%s'  and column_array_char_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string FROM bean84_a WHERE column_list_string=? and column_map_integer_string=? and column_array_char=?  and column_array_char_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean84A> resultList=new LinkedList<Bean84A>();
      Bean84A resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("column_list_string");
        int index2=cursor.getColumnIndex("column_map_integer_string");
        int index3=cursor.getColumnIndex("column_array_char");
        int index4=cursor.getColumnIndex("column_array_char_type");
        int index5=cursor.getColumnIndex("column_bean");
        int index6=cursor.getColumnIndex("column_array_byte_type");
        int index7=cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new Bean84A();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.columnListString=Bean84ATable.parseColumnListString(cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.columnMapIntegerString=Bean84ATable.parseColumnMapIntegerString(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.columnArrayChar=Bean84ATable.parseColumnArrayChar(cursor.getBlob(index3)); }
          if (!cursor.isNull(index4)) { resultBean.columnArrayCharType=Bean84ATable.parseColumnArrayCharType(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.columnBean=Bean84ATable.parseColumnBean(cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.columnArrayByteType=Bean84ATable.parseColumnArrayByteType(cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.valueString=cursor.getString(index7); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84_a (column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string) VALUES (${bean.columnListString}, ${bean.columnMapIntegerString}, ${bean.columnArrayChar}, ${bean.columnArrayCharType}, ${bean.columnBean}, ${bean.columnArrayByteType}, ${bean.valueString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>column_list_string</dt><dd>is mapped to <strong>${bean.columnListString}</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is mapped to <strong>${bean.columnMapIntegerString}</strong></dd>
   * 	<dt>column_array_char</dt><dd>is mapped to <strong>${bean.columnArrayChar}</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is mapped to <strong>${bean.columnArrayCharType}</strong></dd>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is mapped to <strong>${bean.columnArrayByteType}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertAll(Bean84A bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnListString!=null) {
      contentValues.put("column_list_string", Bean84ATable.serializeColumnListString(bean.columnListString));
    } else {
      contentValues.putNull("column_list_string");
    }

    if (bean.columnMapIntegerString!=null) {
      contentValues.put("column_map_integer_string", Bean84ATable.serializeColumnMapIntegerString(bean.columnMapIntegerString));
    } else {
      contentValues.putNull("column_map_integer_string");
    }

    if (bean.columnArrayChar!=null) {
      contentValues.put("column_array_char", Bean84ATable.serializeColumnArrayChar(bean.columnArrayChar));
    } else {
      contentValues.putNull("column_array_char");
    }

    if (bean.columnArrayCharType!=null) {
      contentValues.put("column_array_char_type", Bean84ATable.serializeColumnArrayCharType(bean.columnArrayCharType));
    } else {
      contentValues.putNull("column_array_char_type");
    }

    if (bean.columnBean!=null) {
      contentValues.put("column_bean", Bean84ATable.serializeColumnBean(bean.columnBean));
    } else {
      contentValues.putNull("column_bean");
    }

    if (bean.columnArrayByteType!=null) {
      contentValues.put("column_array_byte_type", Bean84ATable.serializeColumnArrayByteType(bean.columnArrayByteType));
    } else {
      contentValues.putNull("column_array_byte_type");
    }

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean84_a (column_list_string, column_map_integer_string, column_array_char, column_array_char_type, column_bean, column_array_byte_type, value_string) VALUES ('"+StringUtils.checkSize(contentValues.get("column_list_string"))+"', '"+StringUtils.checkSize(contentValues.get("column_map_integer_string"))+"', '"+StringUtils.checkSize(contentValues.get("column_array_char"))+"', '"+StringUtils.checkSize(contentValues.get("column_array_char_type"))+"', '"+StringUtils.checkSize(contentValues.get("column_bean"))+"', '"+StringUtils.checkSize(contentValues.get("column_array_byte_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_string"))+"')"));
    long result = database().insert("bean84_a", null, contentValues);
    bean.id=result;

    return result!=-1;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84_a (column_list_string) VALUES (${columnListString})</pre>
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean84_a (column_list_string) VALUES ('"+StringUtils.checkSize(contentValues.get("column_list_string"))+"')"));
    long result = database().insert("bean84_a", null, contentValues);
    return result!=-1;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean84_a SET column_list_string=${bean.columnListString}, column_map_integer_string=${bean.columnMapIntegerString}, column_array_char=${bean.columnArrayChar}, column_array_char_type=${bean.columnArrayCharType}, column_bean=${bean.columnBean}, column_array_byte_type=${bean.columnArrayByteType}, value_string=${bean.valueString} WHERE 1=1</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>column_list_string</dt><dd>is mapped to <strong>${bean.columnListString}</strong></dd>
   * 	<dt>column_map_integer_string</dt><dd>is mapped to <strong>${bean.columnMapIntegerString}</strong></dd>
   * 	<dt>column_array_char</dt><dd>is mapped to <strong>${bean.columnArrayChar}</strong></dd>
   * 	<dt>column_array_char_type</dt><dd>is mapped to <strong>${bean.columnArrayCharType}</strong></dd>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * 	<dt>column_array_byte_type</dt><dd>is mapped to <strong>${bean.columnArrayByteType}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateAll(Bean84A bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnListString!=null) {
      contentValues.put("column_list_string", Bean84ATable.serializeColumnListString(bean.columnListString));
    } else {
      contentValues.putNull("column_list_string");
    }

    if (bean.columnMapIntegerString!=null) {
      contentValues.put("column_map_integer_string", Bean84ATable.serializeColumnMapIntegerString(bean.columnMapIntegerString));
    } else {
      contentValues.putNull("column_map_integer_string");
    }

    if (bean.columnArrayChar!=null) {
      contentValues.put("column_array_char", Bean84ATable.serializeColumnArrayChar(bean.columnArrayChar));
    } else {
      contentValues.putNull("column_array_char");
    }

    if (bean.columnArrayCharType!=null) {
      contentValues.put("column_array_char_type", Bean84ATable.serializeColumnArrayCharType(bean.columnArrayCharType));
    } else {
      contentValues.putNull("column_array_char_type");
    }

    if (bean.columnBean!=null) {
      contentValues.put("column_bean", Bean84ATable.serializeColumnBean(bean.columnBean));
    } else {
      contentValues.putNull("column_bean");
    }

    if (bean.columnArrayByteType!=null) {
      contentValues.put("column_array_byte_type", Bean84ATable.serializeColumnArrayByteType(bean.columnArrayByteType));
    } else {
      contentValues.putNull("column_array_byte_type");
    }

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    String[] whereConditionsArray={};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean84_a SET column_list_string='"+StringUtils.checkSize(contentValues.get("column_list_string"))+"', column_map_integer_string='"+StringUtils.checkSize(contentValues.get("column_map_integer_string"))+"', column_array_char='"+StringUtils.checkSize(contentValues.get("column_array_char"))+"', column_array_char_type='"+StringUtils.checkSize(contentValues.get("column_array_char_type"))+"', column_bean='"+StringUtils.checkSize(contentValues.get("column_bean"))+"', column_array_byte_type='"+StringUtils.checkSize(contentValues.get("column_array_byte_type"))+"', value_string='"+StringUtils.checkSize(contentValues.get("value_string"))+"' WHERE 1=1", (Object[]) whereConditionsArray));
    int result = database().update("bean84_a", contentValues, "UPDATE bean84_a SET column_list_string='"+StringUtils.checkSize(contentValues.get("column_list_string"))+"', column_map_integer_string='"+StringUtils.checkSize(contentValues.get("column_map_integer_string"))+"', column_array_char='"+StringUtils.checkSize(contentValues.get("column_array_char"))+"', column_array_char_type='"+StringUtils.checkSize(contentValues.get("column_array_char_type"))+"', column_bean='"+StringUtils.checkSize(contentValues.get("column_bean"))+"', column_array_byte_type='"+StringUtils.checkSize(contentValues.get("column_array_byte_type"))+"', value_string='"+StringUtils.checkSize(contentValues.get("value_string"))+"' WHERE 1=1", whereConditionsArray);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean84_a WHERE 1=1</pre>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteAll(Bean84A bean) {
    String[] whereConditionsArray={};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean84_a WHERE 1=1 ", (Object[]) whereConditionsArray));
    int result = database().delete("bean84_a", "1=1", whereConditionsArray);
    return result!=0;
  }

  /**
   * write
   */
  private byte[] serializer1(List<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private List<String> parser1(byte[] input) {
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
  private byte[] serializer2(Map<Integer, String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Map<Integer, String> parser2(byte[] input) {
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

  /**
   * write
   */
  private byte[] serializer3(Character[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Character[] parser3(byte[] input) {
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
  private byte[] serializer4(char[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private char[] parser4(byte[] input) {
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
}
