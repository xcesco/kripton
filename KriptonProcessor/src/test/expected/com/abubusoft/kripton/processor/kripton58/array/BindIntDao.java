package com.abubusoft.kripton.processor.kripton58.array;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>IntBean</code>, based on interface <code>IntDao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.array.IntBean
 *  @see com.abubusoft.kripton.processor.kripton58.array.IntDao
 *  @see com.abubusoft.kripton.processor.kripton58.array.IntBeanTable
 */
public class BindIntDao extends AbstractDao implements IntDao {
  public BindIntDao(BindIntDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM int_bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntBean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM int_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM int_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    IntBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new IntBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=CollectionUtility.toIntegerTypeArray(DaoHelper.toList(Integer.TYPE, cursor.getBlob(index1))); }
      if (!cursor.isNull(index2)) { resultBean.value2=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index2))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value, value2]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   * @param value2
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntBean selectOne(int[] value, Integer[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM int_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    IntBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new IntBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=CollectionUtility.toIntegerTypeArray(DaoHelper.toList(Integer.TYPE, cursor.getBlob(index1))); }
      if (!cursor.isNull(index2)) { resultBean.value2=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index2))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value, value2]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   * @param value2
   * @param listener
   */
  @Override
  public void selectOne(int[] value, Integer[] value2, ReadBeanListener<IntBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM int_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    IntBean resultBean=new IntBean();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=CollectionUtility.toIntegerTypeArray(DaoHelper.toList(Integer.TYPE, cursor.getBlob(index1))); }
          if (!cursor.isNull(index2)) { resultBean.value2=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index2))); }

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
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value, value2]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   * @param value2
   * @param listener
   */
  @Override
  public void selectOne(int[] value, Integer[] value2, ReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM int_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?", args);
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
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM int_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value, value2]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   * @param value2
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<IntBean> selectList(int[] value, Integer[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM int_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM int_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<IntBean> resultList=new LinkedList<IntBean>();
    IntBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new IntBean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=CollectionUtility.toIntegerTypeArray(DaoHelper.toList(Integer.TYPE, cursor.getBlob(index1))); }
        if (!cursor.isNull(index2)) { resultBean.value2=CollectionUtility.toIntegerArray(DaoHelper.toList(Integer.class, cursor.getBlob(index2))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE int_bean SET  WHERE id=${id} and value=${value} and value2=${value2}</pre>
   *
   * @param id
   * 	used in where condition
   * @param value
   * 	used in where condition
   * @param value2
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, int[] value, Integer[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(id), (value==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE int_bean SET  WHERE id=%s and value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().update("int_bean", contentValues, "id=? and value=? and value2=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO int_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @param value
   * 	used as updated field and in where condition
   * @param value2
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(long id, int[] value, Integer[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)));
    } else {
      contentValues.putNull("value");
    }

    if (value2!=null) {
      contentValues.put("value2", DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO int_bean (id, value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("int_bean", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO int_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(IntBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(CollectionUtility.toList(bean.value, ArrayList.class)));
    } else {
      contentValues.putNull("value");
    }

    if (bean.value2!=null) {
      contentValues.put("value2", DaoHelper.toByteArray(CollectionUtility.toList(bean.value2, ArrayList.class)));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO int_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("int_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE int_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * @param value
   * 	used in where condition
   * @param value2
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(int[] value, Integer[] value2) {
    String[] whereConditions={(value==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(DaoHelper.toByteArray(CollectionUtility.toList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE int_bean WHERE value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().delete("int_bean", "value=? and value2=?", whereConditions);
    return result;
  }
}
