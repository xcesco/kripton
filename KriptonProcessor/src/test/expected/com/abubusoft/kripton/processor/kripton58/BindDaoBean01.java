package com.abubusoft.kripton.processor.kripton58;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean01</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.Bean01
 *  @see com.abubusoft.kripton.processor.kripton58.DaoBean01
 *  @see com.abubusoft.kripton.processor.kripton58.Bean01Table
 */
public class BindDaoBean01 extends AbstractDao implements DaoBean01 {
  public BindDaoBean01(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE value=${value} and longValue=${longValue}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value, longValue]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value]</pre>
   *
   * @param value
   * @param longValue
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01 selectOne(byte[] value, long[] longValue) {
    // build where condition
    String[] args={(value==null?null:new String(value,StandardCharsets.UTF_8)), (longValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE value='%s' and long_value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE value=? and long_value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("string_value");
      int index3=cursor.getColumnIndex("long_value");
      int index4=cursor.getColumnIndex("integer_value");
      int index5=cursor.getColumnIndex("byte_value");
      int index6=cursor.getColumnIndex("short_value");
      int index7=cursor.getColumnIndex("bean_value");

      resultBean=new Bean01();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=cursor.getBlob(index1); }
      if (!cursor.isNull(index2)) { resultBean.stringValue=CollectionUtility.toStringArray(DaoHelper.toList(String.class, cursor.getBlob(index2))); }
      if (!cursor.isNull(index3)) { resultBean.longValue=CollectionUtility.toLongTypeArray(DaoHelper.toList(Long.TYPE, cursor.getBlob(index3))); }
      if (!cursor.isNull(index4)) { resultBean.integerValue=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index4))); }
      if (!cursor.isNull(index5)) { resultBean.byteValue=CollectionUtility.toByteArray(DaoHelper.toList(Byte.class, cursor.getBlob(index5))); }
      if (!cursor.isNull(index6)) { resultBean.shortValue=CollectionUtility.toShortTypeArray(DaoHelper.toList(Short.TYPE, cursor.getBlob(index6))); }
      if (!cursor.isNull(index7)) { resultBean.beanValue=CollectionUtility.toArray(DaoHelper.toList(Bean01Inner.class, cursor.getBlob(index7))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET value=${value}, longValue=${longValue} WHERE id=${uid}</pre>
   *
   * @param value
   * 	used as updated field
   * @param longValue
   * 	used as updated field
   * @param uid
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(byte[] value, long[] longValue, long uid) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (value!=null) {
      contentValues.put("value", value);
    } else {
      contentValues.putNull("value");
    }
    if (longValue!=null) {
      contentValues.put("long_value", DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)));
    } else {
      contentValues.putNull("long_value");
    }

    String[] whereConditions={String.valueOf(uid)};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET value='"+StringUtil.checkSize(contentValues.get("value"))+"', longValue='"+StringUtil.checkSize(contentValues.get("long_value"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT value FROM bean01 WHERE id=${uid}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[uid]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[value]</pre>
   *
   * @param uid
   *
   * @return single value extracted with query.
   */
  @Override
  public byte[] selectSingle(long uid) {
    // build where condition
    String[] args={String.valueOf(uid)};

    Logger.info(StringUtil.formatSQL("SELECT value FROM bean01 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT value FROM bean01 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    byte[] result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getBlob(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT count(*) FROM bean01 WHERE id=${id} and longValue=${longValue}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id, longValue]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[count(*)]</pre>
   *
   * @param id
   * @param longValue
   *
   * @return single value extracted with query.
   */
  @Override
  public long selectLongSingle(long id, long[] longValue) {
    // build where condition
    String[] args={String.valueOf(id), (longValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT count(*) FROM bean01 WHERE id='%s' and long_value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*) FROM bean01 WHERE id=? and long_value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    long result=0L;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE id=${bean.id} and longValue=${bean.longValue} and integerValue=${bean.integerValue}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[bean.id, bean.longValue, bean.integerValue]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value]</pre>
   *
   * @param bean
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean01> selectBeanSingle(Bean01 bean) {
    // build where condition
    String[] args={String.valueOf(bean.id), (bean.longValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(bean.longValue, ArrayList.class)),StandardCharsets.UTF_8)), (bean.integerValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(bean.integerValue, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE id='%s' and long_value='%s' and integer_value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE id=? and long_value=? and integer_value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean01> resultList=new LinkedList<Bean01>();
    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("string_value");
      int index3=cursor.getColumnIndex("long_value");
      int index4=cursor.getColumnIndex("integer_value");
      int index5=cursor.getColumnIndex("byte_value");
      int index6=cursor.getColumnIndex("short_value");
      int index7=cursor.getColumnIndex("bean_value");

      do
       {
        resultBean=new Bean01();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=cursor.getBlob(index1); }
        if (!cursor.isNull(index2)) { resultBean.stringValue=CollectionUtility.toStringArray(DaoHelper.toList(String.class, cursor.getBlob(index2))); }
        if (!cursor.isNull(index3)) { resultBean.longValue=CollectionUtility.toLongTypeArray(DaoHelper.toList(Long.TYPE, cursor.getBlob(index3))); }
        if (!cursor.isNull(index4)) { resultBean.integerValue=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index4))); }
        if (!cursor.isNull(index5)) { resultBean.byteValue=CollectionUtility.toByteArray(DaoHelper.toList(Byte.class, cursor.getBlob(index5))); }
        if (!cursor.isNull(index6)) { resultBean.shortValue=CollectionUtility.toShortTypeArray(DaoHelper.toList(Short.TYPE, cursor.getBlob(index6))); }
        if (!cursor.isNull(index7)) { resultBean.beanValue=CollectionUtility.toArray(DaoHelper.toList(Bean01Inner.class, cursor.getBlob(index7))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT count(*) FROM bean01 WHERE id=${id} and longValue=${longValue} or byteValue=${byteValue}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id, longValue, byteValue]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[count(*)]</pre>
   *
   * @param id
   * @param longValue
   * @param byteValue
   *
   * @return single value extracted with query.
   */
  @Override
  public long selectRawSingle(long id, long[] longValue, Byte[] byteValue) {
    // build where condition
    String[] args={String.valueOf(id), (longValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)),StandardCharsets.UTF_8)), (byteValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(byteValue, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT count(*) FROM bean01 WHERE id='%s' and long_value='%s' or byte_value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*) FROM bean01 WHERE id=? and long_value=? or byte_value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    long result=0L;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE id=${id} and longValue=${longValue}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id, longValue]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value]</pre>
   *
   * @param id
   * @param longValue
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01 selectRawSingle(long id, Long[] longValue) {
    // build where condition
    String[] args={String.valueOf(id), (longValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE id='%s' and long_value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, string_value, long_value, integer_value, byte_value, short_value, bean_value FROM bean01 WHERE id=? and long_value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("string_value");
      int index3=cursor.getColumnIndex("long_value");
      int index4=cursor.getColumnIndex("integer_value");
      int index5=cursor.getColumnIndex("byte_value");
      int index6=cursor.getColumnIndex("short_value");
      int index7=cursor.getColumnIndex("bean_value");

      resultBean=new Bean01();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=cursor.getBlob(index1); }
      if (!cursor.isNull(index2)) { resultBean.stringValue=CollectionUtility.toStringArray(DaoHelper.toList(String.class, cursor.getBlob(index2))); }
      if (!cursor.isNull(index3)) { resultBean.longValue=CollectionUtility.toLongTypeArray(DaoHelper.toList(Long.TYPE, cursor.getBlob(index3))); }
      if (!cursor.isNull(index4)) { resultBean.integerValue=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index4))); }
      if (!cursor.isNull(index5)) { resultBean.byteValue=CollectionUtility.toByteArray(DaoHelper.toList(Byte.class, cursor.getBlob(index5))); }
      if (!cursor.isNull(index6)) { resultBean.shortValue=CollectionUtility.toShortTypeArray(DaoHelper.toList(Short.TYPE, cursor.getBlob(index6))); }
      if (!cursor.isNull(index7)) { resultBean.beanValue=CollectionUtility.toArray(DaoHelper.toList(Bean01Inner.class, cursor.getBlob(index7))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (id, long_value) VALUES (${id}, ${longValue})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @param longValue
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(long id, long[] longValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (longValue!=null) {
      contentValues.put("long_value", DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)));
    } else {
      contentValues.putNull("long_value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (id, long_value) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("long_value"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (value, string_value, long_value, integer_value, byte_value, short_value, bean_value) VALUES (${bean.value}, ${bean.stringValue}, ${bean.longValue}, ${bean.integerValue}, ${bean.byteValue}, ${bean.shortValue}, ${bean.beanValue})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long update(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", bean.value);
    } else {
      contentValues.putNull("value");
    }

    if (bean.stringValue!=null) {
      contentValues.put("string_value", DaoHelper.toByteArray(CollectionUtility.toList(bean.stringValue, ArrayList.class)));
    } else {
      contentValues.putNull("string_value");
    }

    if (bean.longValue!=null) {
      contentValues.put("long_value", DaoHelper.toByteArray(CollectionUtility.toList(bean.longValue, ArrayList.class)));
    } else {
      contentValues.putNull("long_value");
    }

    if (bean.integerValue!=null) {
      contentValues.put("integer_value", DaoHelper.toByteArray(CollectionUtility.toList(bean.integerValue, ArrayList.class)));
    } else {
      contentValues.putNull("integer_value");
    }

    if (bean.byteValue!=null) {
      contentValues.put("byte_value", DaoHelper.toByteArray(CollectionUtility.toList(bean.byteValue, ArrayList.class)));
    } else {
      contentValues.putNull("byte_value");
    }

    if (bean.shortValue!=null) {
      contentValues.put("short_value", DaoHelper.toByteArray(CollectionUtility.toList(bean.shortValue, ArrayList.class)));
    } else {
      contentValues.putNull("short_value");
    }

    if (bean.beanValue!=null) {
      contentValues.put("bean_value", DaoHelper.toByteArray(CollectionUtility.toList(bean.beanValue, ArrayList.class)));
    } else {
      contentValues.putNull("bean_value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (value, string_value, long_value, integer_value, byte_value, short_value, bean_value) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("string_value"))+"', '"+StringUtil.checkSize(contentValues.get("long_value"))+"', '"+StringUtil.checkSize(contentValues.get("integer_value"))+"', '"+StringUtil.checkSize(contentValues.get("byte_value"))+"', '"+StringUtil.checkSize(contentValues.get("short_value"))+"', '"+StringUtil.checkSize(contentValues.get("bean_value"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean01 WHERE longValue=${bean.longValue}</pre>
   *
   * @param bean
   * 	used as updated field and in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Bean01 bean) {
    String[] whereConditions={(bean.longValue==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(bean.longValue, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("long_value=%s"), (Object[])whereConditions);
    int result = database().delete("bean01", "long_value=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET longValue=${longValue}, byteValue=${byteValue} WHERE id=${uid}</pre>
   *
   * @param uid
   * 	used in where condition
   * @param longValue
   * 	used as updated field
   * @param byteValue
   * 	used as updated field
   *
   * @return number of updated records
   */
  @Override
  public long update(long uid, long[] longValue, Byte[] byteValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (longValue!=null) {
      contentValues.put("long_value", DaoHelper.toByteArray(CollectionUtility.toList(longValue, ArrayList.class)));
    } else {
      contentValues.putNull("long_value");
    }
    if (byteValue!=null) {
      contentValues.put("byte_value", DaoHelper.toByteArray(CollectionUtility.toList(byteValue, ArrayList.class)));
    } else {
      contentValues.putNull("byte_value");
    }

    String[] whereConditions={String.valueOf(uid)};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET longValue='"+StringUtil.checkSize(contentValues.get("long_value"))+"', byteValue='"+StringUtil.checkSize(contentValues.get("byte_value"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, "id=?", whereConditions);
    return result;
  }
}
