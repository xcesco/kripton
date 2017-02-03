package sqlite.kripton63;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DAO implementation for entity <code>Bean63</code>, based on interface <code>BeanDao</code>
 * </p>
 *
 *  @see Bean63
 *  @see BeanDao
 *  @see Bean63Table
 */
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        resultBean=new Bean63();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the Bean63 listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean63> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = '%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Bean63 resultBean=new Bean63();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.valueMapStringByte=null;
          resultBean.valueMapEnumByte=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_string_byte</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_enum_byte</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = '%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean63> selectList(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = '%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean63> resultList=new LinkedList<Bean63>();
      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        do
         {
          resultBean=new Bean63();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean63 SET value=${value.value}, value_map_string_byte=${value.valueMapStringByte}, value_map_enum_byte=${value.valueMapEnumByte} WHERE id=${value.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${value.value}</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>${value.valueMapStringByte}</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>${value.valueMapEnumByte}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${value}
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean63 value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (value.value!=null) {
      contentValues.put("value", value.value);
    } else {
      contentValues.putNull("value");
    }

    if (value.valueMapStringByte!=null) {
      contentValues.put("value_map_string_byte", Bean63Table.serializeValueMapStringByte(value.valueMapStringByte));
    } else {
      contentValues.putNull("value_map_string_byte");
    }

    if (value.valueMapEnumByte!=null) {
      contentValues.put("value_map_enum_byte", Bean63Table.serializeValueMapEnumByte(value.valueMapEnumByte));
    } else {
      contentValues.putNull("value_map_enum_byte");
    }

    String[] whereConditionsArray={String.valueOf(value.id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean63 SET value='"+StringUtils.checkSize(contentValues.get("value"))+"', value_map_string_byte='"+StringUtils.checkSize(contentValues.get("value_map_string_byte"))+"', value_map_enum_byte='"+StringUtils.checkSize(contentValues.get("value_map_enum_byte"))+"' WHERE id='%s'", (Object[]) whereConditionsArray));
    int result = database().update("bean63", contentValues, "UPDATE bean63 SET value='"+StringUtils.checkSize(contentValues.get("value"))+"', value_map_string_byte='"+StringUtils.checkSize(contentValues.get("value_map_string_byte"))+"', value_map_enum_byte='"+StringUtils.checkSize(contentValues.get("value_map_enum_byte"))+"' WHERE id='%s'", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean63 (value, value_map_string_byte, value_map_enum_byte) VALUES (${bean.value}, ${bean.valueMapStringByte}, ${bean.valueMapEnumByte})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>${bean.valueMapStringByte}</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>${bean.valueMapEnumByte}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean63 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", bean.value);
    } else {
      contentValues.putNull("value");
    }

    if (bean.valueMapStringByte!=null) {
      contentValues.put("value_map_string_byte", Bean63Table.serializeValueMapStringByte(bean.valueMapStringByte));
    } else {
      contentValues.putNull("value_map_string_byte");
    }

    if (bean.valueMapEnumByte!=null) {
      contentValues.put("value_map_enum_byte", Bean63Table.serializeValueMapEnumByte(bean.valueMapEnumByte));
    } else {
      contentValues.putNull("value_map_enum_byte");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean63 (value, value_map_string_byte, value_map_enum_byte) VALUES ('"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value_map_string_byte"))+"', '"+StringUtils.checkSize(contentValues.get("value_map_enum_byte"))+"')"));
    long result = database().insert("bean63", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean63 (value_map_string_byte) VALUES (${valueMapStringByte})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_map_string_byte</dt><dd>is binded to query's parameter <strong>${valueMapStringByte}</strong> and method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is binded to column <strong>value_map_string_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Map<String, Byte> valueMapStringByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueMapStringByte!=null) {
      contentValues.put("value_map_string_byte", serializer1(valueMapStringByte));
    } else {
      contentValues.putNull("value_map_string_byte");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean63 (value_map_string_byte) VALUES ('"+StringUtils.checkSize(contentValues.get("value_map_string_byte"))+"')"));
    long result = database().insert("bean63", null, contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapStringByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is binded to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is binded to <code>${valueMapStringByte}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(Map<String, Byte> valueMapStringByte) {
    // build where condition
    String[] args={(valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        resultBean=new Bean63();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean63 WHERE value=${valueMapStringByte}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is mapped to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is used as where parameter <strong>${valueMapStringByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Map<String, Byte> valueMapStringByte) {
    String[] whereConditionsArray={(valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean63 WHERE value=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean63", "value=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean63 SET id=${id} WHERE value=${valueMapStringByte}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is mapped to method's parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueMapStringByte
   * 	is used as where parameter <strong>${valueMapStringByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, Map<String, Byte> valueMapStringByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueMapStringByte==null?"":new String(serializer1(valueMapStringByte),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean63 SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE value=%s", (Object[])whereConditionsArray));
    int result = database().update("bean63", contentValues, "value=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean63 (value_map_enum_byte) VALUES (${valueMapEnumByte})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_map_enum_byte</dt><dd>is binded to query's parameter <strong>${valueMapEnumByte}</strong> and method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to column <strong>value_map_enum_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(HashMap<EnumType, Byte> valueMapEnumByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueMapEnumByte!=null) {
      contentValues.put("value_map_enum_byte", serializer2(valueMapEnumByte));
    } else {
      contentValues.putNull("value_map_enum_byte");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean63 (value_map_enum_byte) VALUES ('"+StringUtils.checkSize(contentValues.get("value_map_enum_byte"))+"')"));
    long result = database().insert("bean63", null, contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    // build where condition
    String[] args={(valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        resultBean=new Bean63();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_string_byte</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_enum_byte</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    // build where condition
    String[] args={(valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    return cursor;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is associated to bean's property <strong>valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @param listener
   * 	is the Bean63 listener
   */
  @Override
  public void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, OnReadBeanListener<Bean63> listener) {
    // build where condition
    String[] args={(valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Bean63 resultBean=new Bean63();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value_map_string_byte");
        int index3=cursor.getColumnIndex("value_map_enum_byte");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.valueMapStringByte=null;
          resultBean.valueMapEnumByte=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=Bean63Table.parseValueMapStringByte(cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_string_byte</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_enum_byte</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is binded to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is binded to <code>${valueMapEnumByte}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, OnReadCursorListener listener) {
    // build where condition
    String[] args={(valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean63 WHERE value=${valueMapEnumByte}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is mapped to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>${valueMapEnumByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashMap<EnumType, Byte> valueMapEnumByte) {
    String[] whereConditionsArray={(valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean63 WHERE value=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean63", "value=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean63 SET id=${id} WHERE value=${valueMapEnumByte}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is mapped to method's parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>${valueMapEnumByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, HashMap<EnumType, Byte> valueMapEnumByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueMapEnumByte==null?"":new String(serializer2(valueMapEnumByte),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean63 SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE value=%s", (Object[])whereConditionsArray));
    int result = database().update("bean63", contentValues, "value=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_enum_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_enum_byte</dt><dd>is associated to bean's property <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean63> selectMapEnumByteOne() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_map_enum_byte FROM bean63",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_map_enum_byte FROM bean63", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean63> resultList=new LinkedList<Bean63>();
      Bean63 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_map_enum_byte");

        do
         {
          resultBean=new Bean63();

          if (!cursor.isNull(index0)) { resultBean.valueMapEnumByte=Bean63Table.parseValueMapEnumByte(cursor.getBlob(index0)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_enum_byte FROM bean63</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_enum_byte</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return collection of single value extracted by query.
   */
  @Override
  public List<String> selectMapEnumByteOneString() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_map_enum_byte FROM bean63",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_map_enum_byte FROM bean63", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<String> resultList=new LinkedList<String>();


      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(cursor.getString(0));
          } else {
            resultList.add(null);
          }
        } while (cursor.moveToNext());
      }
      return resultList;
    }
  }

  /**
   * write
   */
  private byte[] serializer2(HashMap<EnumType, Byte> value) {
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
          for (Map.Entry<EnumType, Byte> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField(null, item.getKey().toString());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField(null);
            } else {
              jacksonSerializer.writeNumberField(null, item.getValue());
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
  private HashMap<EnumType, Byte> parser2(byte[] input) {
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
      HashMap<EnumType, Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<EnumType, Byte> collection=new HashMap<>();
        EnumType key=null;
        Byte value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
           {
            String tempEnum=jacksonParser.getText();
            key=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
          }
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getByteValue();
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
  private byte[] serializer1(Map<String, Byte> value) {
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
          for (Map.Entry<String, Byte> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField(null, item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField(null);
            } else {
              jacksonSerializer.writeNumberField(null, item.getValue());
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
  private Map<String, Byte> parser1(byte[] input) {
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
      Map<String, Byte> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<String, Byte> collection=new HashMap<>();
        String key=null;
        Byte value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
            value=jacksonParser.getByteValue();
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
