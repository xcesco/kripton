package com.abubusoft.kripton.processor.kripton58.array2;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>LongBean</code>, based on interface <code>LongDao</code>
 * </p>
 *  @see LongBean
 *  @see LongDao
 *  @see LongBean$Table
 */
public class LongDaoImpl extends AbstractDao implements LongDao {
  public LongDaoImpl(BindLongDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM long_bean WHERE 1=1</pre>
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
  public LongBean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM long_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM long_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LongBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new LongBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index1)))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index2)))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM long_bean WHERE value=${value} and value2=${value2}</pre>
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
  public LongBean selectOne(long[] value, Long[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM long_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM long_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LongBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new LongBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index1)))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index2)))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM long_bean WHERE value=${value} and value2=${value2}</pre>
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
  public void selectOne(long[] value, Long[] value2, ReadBeanListener<LongBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM long_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM long_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    LongBean resultBean=new LongBean();

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
          if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index1)))); }
          if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index2)))); }

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
   * <pre>SELECT id, value, value2 FROM long_bean WHERE value=${value} and value2=${value2}</pre>
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
  public void selectOne(long[] value, Long[] value2, ReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM long_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM long_bean WHERE value=? and value2=?", args);
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
   * <pre>SELECT id, value, value2 FROM long_bean WHERE value=${value} and value2=${value2}</pre>
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
  public List<LongBean> selectList(long[] value, Long[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM long_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM long_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<LongBean> resultList=new LinkedList<LongBean>();
    LongBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new LongBean();

        if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asLongTypeArray(ProcessorHelper.asList(Long.TYPE, cursor.getBlob(index1)))); }
        if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asLongArray(ProcessorHelper.asList(Long.class, cursor.getBlob(index2)))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE long_bean SET  WHERE id=${id} and value=${value} and value2=${value2}</pre>
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
  public long updateOne(long id, long[] value, Long[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={String.valueOf(id), (value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE long_bean SET  WHERE id=%s and value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().update("long_bean", contentValues, "id=? and value=? and value2=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO long_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
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
  public long insert(long id, long[] value, Long[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)));
    } else {
      contentValues.putNull("value");
    }

    if (value2!=null) {
      contentValues.put("value2", ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO long_bean (id, value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("long_bean", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO long_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(LongBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getValue()!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.getValue(), ArrayList.class)));
    } else {
      contentValues.putNull("value");
    }

    if (bean.getValue2()!=null) {
      contentValues.put("value2", ProcessorHelper.asByteArray(CollectionUtility.asList(bean.getValue2(), ArrayList.class)));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO long_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("long_bean", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE long_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * @param value
   * 	used in where condition
   * @param value2
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(long[] value, Long[] value2) {
    String[] whereConditions={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE long_bean WHERE value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().delete("long_bean", "value=? and value2=?", whereConditions);
    return result;
  }
}
