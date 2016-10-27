package com.abubusoft.kripton.processor.kripton58;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean04Bean</code>, based on interface <code>Bean04Dao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.Bean04Bean
 *  @see com.abubusoft.kripton.processor.kripton58.Bean04Dao
 *  @see com.abubusoft.kripton.processor.kripton58.Bean04BeanTable
 */
public class BindBean04Dao extends AbstractDao implements Bean04Dao {
  public BindBean04Dao(BindBean04DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM bean04_bean WHERE 1=1</pre>
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
  public Bean04Bean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean04_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean04_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean04Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new Bean04Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Bean04Inner>(), Bean04Inner.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Bean04Inner>(), Bean04Inner.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM bean04_bean WHERE stringValue=${value}</pre>
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
  public Bean04Bean selectOne(List<Bean04Inner> value) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean04_bean WHERE stringValue='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean04_bean WHERE stringValue=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean04Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new Bean04Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Bean04Inner>(), Bean04Inner.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Bean04Inner>(), Bean04Inner.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM bean04_bean WHERE stringValue=${value}</pre>
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
  public List<Bean04Bean> selectList(List<Bean04Inner> value) {
    // build where condition
    String[] args={(value==null?null:new String(DaoHelper.toByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean04_bean WHERE stringValue='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean04_bean WHERE stringValue=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean04Bean> resultList=new LinkedList<Bean04Bean>();
    Bean04Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new Bean04Bean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=DaoHelper.toList(new ArrayList<Bean04Inner>(), Bean04Inner.class, cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=DaoHelper.toList(new LinkedList<Bean04Inner>(), Bean04Inner.class, cursor.getBlob(index2)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean04_bean SET value=${value} WHERE id=${id} and value=${paramValue}</pre>
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
  public long updateOne(List<Bean04Inner> value, long id, List<Bean04Inner> paramValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditions={String.valueOf(id), (paramValue==null?null:new String(DaoHelper.toByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean04_bean SET value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE id=%s and value=%s"), (Object[])whereConditions);
    int result = database().update("bean04_bean", contentValues, "id=? and value=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean04_bean (id, value) VALUES (${id}, ${value})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @param value
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(long id, List<Bean04Inner> value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", DaoHelper.toByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean04_bean (id, value) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("bean04_bean", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean04_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(Bean04Bean bean) {
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
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean04_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("bean04_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean04_bean WHERE value=${paramValue}</pre>
   *
   * @param paramValue
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<Bean04Inner> paramValue) {
    String[] whereConditions={(paramValue==null?null:new String(DaoHelper.toByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean04_bean WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("bean04_bean", "value=?", whereConditions);
    return result;
  }
}
