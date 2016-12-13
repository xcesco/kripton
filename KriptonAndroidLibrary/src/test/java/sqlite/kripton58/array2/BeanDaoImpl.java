package sqlite.kripton58.array2;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.binder.KriptonBinder;
import com.abubusoft.kripton.binder.context.JacksonContext;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sqlite.kripton58.BeanInner;

/**
 * <p>
 * DAO implementation for entity <code>BeanBean</code>, based on interface <code>BeanDao</code>
 * </p>
 *
 *  @see BeanBean
 *  @see BeanDao
 *  @see BeanBeanTable
 */
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public BeanBean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    BeanBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new BeanBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(BeanBeanTable.parseValue(cursor.getBlob(index1))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(BeanBeanTable.parseValue2(cursor.getBlob(index2))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   * @param value2
   * 	is binded to ${value2}
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public BeanBean selectOne(BeanInner[] value, BeanInner[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer1(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    BeanBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new BeanBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(BeanBeanTable.parseValue(cursor.getBlob(index1))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(BeanBeanTable.parseValue2(cursor.getBlob(index2))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   * @param value2
   * 	is binded to ${value2}
   * @param listener
   * 	is the BeanBean listener
   */
  @Override
  public void selectOne(BeanInner[] value, BeanInner[] value2, OnReadBeanListener<BeanBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer1(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    BeanBean resultBean=new BeanBean();
    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.setId(0L);
          resultBean.setValue(null);
          resultBean.setValue2(null);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setValue(BeanBeanTable.parseValue(cursor.getBlob(index1))); }
          if (!cursor.isNull(index2)) { resultBean.setValue2(BeanBeanTable.parseValue2(cursor.getBlob(index2))); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value2</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   * @param value2
   * 	is binded to ${value2}
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(BeanInner[] value, BeanInner[] value2, OnReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer1(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   * @param value2
   * 	is binded to ${value2}
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanBean> selectList(BeanInner[] value, BeanInner[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer1(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanBean> resultList=new LinkedList<BeanBean>();
    BeanBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new BeanBean();

        if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setValue(BeanBeanTable.parseValue(cursor.getBlob(index1))); }
        if (!cursor.isNull(index2)) { resultBean.setValue2(BeanBeanTable.parseValue2(cursor.getBlob(index2))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE bean_bean SET  WHERE id=${id} and value=${value} and value2=${value2}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   * @param value2
   * 	is used as where parameter <strong>${value2}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, BeanInner[] value, BeanInner[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(id), (value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer1(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("UPDATE bean_bean SET  WHERE id=%s and value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().update("bean_bean", contentValues, "id=? and value=? and value2=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is binded to query's parameter <strong>${value2}</strong> and method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   * @param value
   * 	is binded to column <strong>value</strong>
   * @param value2
   * 	is binded to column <strong>value2</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, BeanInner[] value, BeanInner[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", serializer1(value));
    } else {
      contentValues.putNull("value");
    }

    if (value2!=null) {
      contentValues.put("value2", serializer1(value2));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO bean_bean (id, value, value2) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("bean_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>${bean.value2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(BeanBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getValue()!=null) {
      contentValues.put("value", BeanBeanTable.serializeValue(bean.getValue()));
    } else {
      contentValues.putNull("value");
    }

    if (bean.getValue2()!=null) {
      contentValues.put("value2", BeanBeanTable.serializeValue2(bean.getValue2()));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO bean_bean (value, value2) VALUES ('"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("bean_bean", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   * @param value2
   * 	is used as where parameter <strong>${value2}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(BeanInner[] value, BeanInner[] value2) {
    String[] whereConditions={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer1(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("DELETE bean_bean WHERE value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().delete("bean_bean", "value=? and value2=?", whereConditions);
    return result;
  }

  /**
   * write
   */
  protected static byte[] serializer1(BeanInner[] value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        BeanInner item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            context.mapperFor(BeanInner.class).serializeOnJackson(context, item, wrapper);
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
  protected static BeanInner[] parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      BeanInner[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<BeanInner> collection=new ArrayList<>();
        BeanInner item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=context.mapperFor(BeanInner.class).parseOnJackson(context, wrapper);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new BeanInner[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
