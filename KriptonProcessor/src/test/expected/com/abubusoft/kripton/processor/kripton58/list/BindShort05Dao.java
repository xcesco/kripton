package com.abubusoft.kripton.processor.kripton58.list;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Short05Bean</code>, based on interface <code>Short05Dao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Bean
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05Dao
 *  @see com.abubusoft.kripton.processor.kripton58.list.Short05BeanTable
 */
public class BindShort05Dao extends AbstractDao implements Short05Dao {
  public BindShort05Dao(BindShort05DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM short05_bean WHERE 1=1</pre>
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
  public Short05Bean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM short05_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM short05_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Short05Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new Short05Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Short>(), Short.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Short>(), Short.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM short05_bean WHERE value=${value}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Short05Bean selectOne(List<Short> value) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM short05_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM short05_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Short05Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new Short05Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Short>(), Short.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Short>(), Short.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM short05_bean WHERE value=${value}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   * @param listener
   */
  @Override
  public void selectOne(List<Short> value, ReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM short05_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM short05_bean WHERE value=?", args);
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
   * <pre>SELECT id, value, value2 FROM short05_bean WHERE value=${value}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[value]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value, value2]</pre>
   *
   * @param value
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Short05Bean> selectList(List<Short> value) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM short05_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM short05_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Short05Bean> resultList=new LinkedList<Short05Bean>();
    Short05Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new Short05Bean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Short>(), Short.class, cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Short>(), Short.class, cursor.getBlob(index2)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE short05_bean SET value=${value} WHERE id=${id} and value=${paramValue}</pre>
   *
   * @param value
   * 	used as updated field
   * @param id
   * 	used in where condition
   * @param paramValue
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(List<Short> value, long id, List<Short> paramValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditions={String.valueOf(id), (paramValue==null?null:new String(DaoHelper.toByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE short05_bean SET value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE id=%s and value=%s"), (Object[])whereConditions);
    int result = database().update("short05_bean", contentValues, "id=? and value=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO short05_bean (id, value) VALUES (${id}, ${value})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @param value
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(long id, List<Short> value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO short05_bean (id, value) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("short05_bean", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO short05_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(Short05Bean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(bean.value));
    } else {
      contentValues.putNull("value");
    }

    if (bean.value2!=null) {
      contentValues.put("value2", DaoHelper.toByteArray(bean.value2));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO short05_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("short05_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE short05_bean WHERE value=${paramValue}</pre>
   *
   * @param paramValue
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<Short> paramValue) {
    String[] whereConditions={(paramValue==null?null:new String(DaoHelper.toByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE short05_bean WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("short05_bean", "value=?", whereConditions);
    return result;
  }
}
