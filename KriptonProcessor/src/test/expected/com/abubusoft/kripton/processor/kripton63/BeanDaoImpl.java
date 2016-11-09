package com.abubusoft.kripton.processor.kripton63;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
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
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE 1=1</pre>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong> is associated to bean's property <strong>valueMapStringByte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE 1=1", args);
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
      if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=ProcessorHelper.asMap(new HashMap<String,Byte>(), String.class, Byte.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index3)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong> is associated to bean's property <strong>valueMapStringByte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean63> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean63 resultBean=new Bean63();

    try {
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
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=ProcessorHelper.asMap(new HashMap<String,Byte>(), String.class, Byte.class, cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong></li>
   * 	<li><strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong></li>
   * </ul>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong> is associated to bean's property <strong>valueMapStringByte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * @param id
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean63> selectList(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE id = ?", args);
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
        if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=ProcessorHelper.asMap(new HashMap<String,Byte>(), String.class, Byte.class, cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index3)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean63 SET value=${value.value}, value_map_string_byte=${value.valueMapStringByte}, value_map_enum_byte=${value.valueMapEnumByte} WHERE id=${value.id}</pre>
   *
   * @param value
   * 	is used as where parameter ${value}
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
      contentValues.put("value_map_string_byte", ProcessorHelper.asByteArray(value.valueMapStringByte));
    } else {
      contentValues.putNull("value_map_string_byte");
    }

    if (value.valueMapEnumByte!=null) {
      contentValues.put("value_map_enum_byte", ProcessorHelper.asByteArray(value.valueMapEnumByte));
    } else {
      contentValues.putNull("value_map_enum_byte");
    }

    String[] whereConditions={String.valueOf(value.id)};

    Logger.info(StringUtil.formatSQL("UPDATE bean63 SET value='"+StringUtil.checkSize(contentValues.get("value"))+"', value_map_string_byte='"+StringUtil.checkSize(contentValues.get("value_map_string_byte"))+"', value_map_enum_byte='"+StringUtil.checkSize(contentValues.get("value_map_enum_byte"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean63", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO bean63 (value, value_map_string_byte, value_map_enum_byte) VALUES (${bean.value}, ${bean.valueMapStringByte}, ${bean.valueMapEnumByte})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>bean.value</strong></dd>
   * 	<dt>value_map_string_byte</dt><dd>is mapped to <strong>bean.valueMapStringByte</strong></dd>
   * 	<dt>value_map_enum_byte</dt><dd>is mapped to <strong>bean.valueMapEnumByte</strong></dd>
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
      contentValues.put("value_map_string_byte", ProcessorHelper.asByteArray(bean.valueMapStringByte));
    } else {
      contentValues.putNull("value_map_string_byte");
    }

    if (bean.valueMapEnumByte!=null) {
      contentValues.put("value_map_enum_byte", ProcessorHelper.asByteArray(bean.valueMapEnumByte));
    } else {
      contentValues.putNull("value_map_enum_byte");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean63 (value, value_map_string_byte, value_map_enum_byte) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value_map_string_byte"))+"', '"+StringUtil.checkSize(contentValues.get("value_map_enum_byte"))+"')"));
    long result = database().insert("bean63", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO bean63 (value_map_string_byte) VALUES (${valueMapStringByte})</pre>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>valueMapStringByte</dt><dd>is mapped to parameter <strong>valueMapStringByte</strong></dd>
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
      contentValues.put("value_map_string_byte", ProcessorHelper.asByteArray(valueMapStringByte));
    } else {
      contentValues.putNull("value_map_string_byte");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean63 (value_map_string_byte) VALUES ('"+StringUtil.checkSize(contentValues.get("value_map_string_byte"))+"')"));
    long result = database().insert("bean63", null, contentValues);
    return result;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapStringByte}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>valueMapStringByte</strong> is binded to method's parameter <strong>valueMapStringByte</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong> is associated to bean's property <strong>valueMapStringByte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * @param valueMapStringByte
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(Map<String, Byte> valueMapStringByte) {
    // build where condition
    String[] args={(valueMapStringByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapStringByte)))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args);
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
      if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=ProcessorHelper.asMap(new HashMap<String,Byte>(), String.class, Byte.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index3)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean63 WHERE value=${valueMapStringByte}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is mapped to parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is used as where parameter <strong>${valueMapStringByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Map<String, Byte> valueMapStringByte) {
    String[] whereConditions={(valueMapStringByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapStringByte)))};

    Logger.info(StringUtil.formatSQL("DELETE bean63 WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("bean63", "value=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Update used:</p>
   * <pre>UPDATE bean63 SET  WHERE value=${valueMapStringByte}</pre>
   *
   * <p><strong>Updated fields:</strong></p>
   * <dl>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${valueMapStringByte}</dt><dd>is mapped to parameter <strong>valueMapStringByte</strong></dd>
   * </dl>
   *
   * @param valueMapStringByte
   * 	is used as where parameter <strong>${valueMapStringByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Map<String, Byte> valueMapStringByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueMapStringByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapStringByte)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean63 SET  WHERE value=%s"), (Object[])whereConditions);
    int result = database().update("bean63", contentValues, "value=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO bean63 (value_map_enum_byte) VALUES (${valueMapEnumByte})</pre>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>valueMapEnumByte</dt><dd>is mapped to parameter <strong>valueMapEnumByte</strong></dd>
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
      contentValues.put("value_map_enum_byte", ProcessorHelper.asByteArray(valueMapEnumByte));
    } else {
      contentValues.putNull("value_map_enum_byte");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean63 (value_map_enum_byte) VALUES ('"+StringUtil.checkSize(contentValues.get("value_map_enum_byte"))+"')"));
    long result = database().insert("bean63", null, contentValues);
    return result;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>valueMapEnumByte</strong> is binded to method's parameter <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong> is associated to bean's property <strong>valueMapStringByte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * @param valueMapEnumByte
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean63 selectOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    // build where condition
    String[] args={(valueMapEnumByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapEnumByte)))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args);
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
      if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=ProcessorHelper.asMap(new HashMap<String,Byte>(), String.class, Byte.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index3)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>valueMapEnumByte</strong> is binded to method's parameter <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong></li>
   * 	<li><strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong></li>
   * </ul>
   *
   * @param valueMapEnumByte
   *
   * @return cursor. Closing the cursor is delegated to the calling code.
   */
  @Override
  public Cursor selectCursorOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    // build where condition
    String[] args={(valueMapEnumByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapEnumByte)))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    return cursor;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>valueMapEnumByte</strong> is binded to method's parameter <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong> is associated to bean's property <strong>valueMapStringByte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * @param valueMapEnumByte
   * @param listener
   */
  @Override
  public void selectListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, OnReadBeanListener<Bean63> listener) {
    // build where condition
    String[] args={(valueMapEnumByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapEnumByte)))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean63 resultBean=new Bean63();

    try {
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
          if (!cursor.isNull(index2)) { resultBean.valueMapStringByte=ProcessorHelper.asMap(new HashMap<String,Byte>(), String.class, Byte.class, cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>valueMapEnumByte</strong> is binded to method's parameter <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong></li>
   * 	<li><strong>value</strong></li>
   * 	<li><strong>value_map_string_byte</strong></li>
   * 	<li><strong>value_map_enum_byte</strong></li>
   * </ul>
   *
   * @param valueMapEnumByte
   * @param listener
   */
  @Override
  public void selectCursorListenerOne(HashMap<EnumType, Byte> valueMapEnumByte, OnReadCursorListener listener) {
    // build where condition
    String[] args={(valueMapEnumByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapEnumByte)))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value_map_string_byte, value_map_enum_byte FROM bean63 WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean63 WHERE value=${valueMapEnumByte}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is mapped to parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>${valueMapEnumByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashMap<EnumType, Byte> valueMapEnumByte) {
    String[] whereConditions={(valueMapEnumByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapEnumByte)))};

    Logger.info(StringUtil.formatSQL("DELETE bean63 WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("bean63", "value=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Update used:</p>
   * <pre>UPDATE bean63 SET  WHERE value=${valueMapEnumByte}</pre>
   *
   * <p><strong>Updated fields:</strong></p>
   * <dl>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${valueMapEnumByte}</dt><dd>is mapped to parameter <strong>valueMapEnumByte</strong></dd>
   * </dl>
   *
   * @param valueMapEnumByte
   * 	is used as where parameter <strong>${valueMapEnumByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(HashMap<EnumType, Byte> valueMapEnumByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueMapEnumByte==null?null:String.valueOf(ProcessorHelper.asByteArray(valueMapEnumByte)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean63 SET  WHERE value=%s"), (Object[])whereConditions);
    int result = database().update("bean63", contentValues, "value=?", whereConditions);
    return result;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT value_map_enum_byte FROM bean63 WHERE 1=1</pre>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>value_map_enum_byte</strong> is associated to bean's property <strong>valueMapEnumByte</strong></li>
   * </ul>
   *
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean63> selectMapEnumByteOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_map_enum_byte FROM bean63 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_map_enum_byte FROM bean63 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean63> resultList=new LinkedList<Bean63>();
    Bean63 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_map_enum_byte");

      do
       {
        resultBean=new Bean63();

        if (!cursor.isNull(index0)) { resultBean.valueMapEnumByte=ProcessorHelper.asMap(new HashMap<EnumType,Byte>(), EnumType.class, Byte.class, cursor.getBlob(index0)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT value_map_enum_byte FROM bean63 WHERE 1=1</pre>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>value_map_enum_byte</strong></li>
   * </ul>
   *
   *
   * @return list of single value extracted with query.
   */
  @Override
  public List<String> selectMapEnumByteOneString() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT value_map_enum_byte FROM bean63 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value_map_enum_byte FROM bean63 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<String> resultList=new LinkedList<String>();


    try {
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
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return resultList;
  }
}
