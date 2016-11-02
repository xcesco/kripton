package com.abubusoft.kripton.processor.kripton58.list;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanBean</code>, based on interface <code>BeanDao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton58.list.BeanBean
 *  @see com.abubusoft.kripton.processor.kripton58.list.BeanDao
 *  @see com.abubusoft.kripton.processor.kripton58.list.BeanBeanTable
 */
public class BindBeanDao extends AbstractDao implements BeanDao {
  public BindBeanDao(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE 1=1</pre>
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
  public BeanBean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    BeanBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new BeanBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asList(new ArrayList<BeanInner>(), BeanInner.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asList(new LinkedList<BeanInner>(), BeanInner.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value}</pre>
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
  public BeanBean selectOne(List<BeanBean> value) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    BeanBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new BeanBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asList(new ArrayList<BeanInner>(), BeanInner.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asList(new LinkedList<BeanInner>(), BeanInner.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value}</pre>
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
  public void selectOne(List<BeanBean> value, ReadBeanListener<BeanBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=?", args);
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
          resultBean.id=0L;
          resultBean.value=null;
          resultBean.value2=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asList(new ArrayList<BeanInner>(), BeanInner.class, cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asList(new LinkedList<BeanInner>(), BeanInner.class, cursor.getBlob(index2)); }

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
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value}</pre>
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
  public void selectOne(List<BeanBean> value, ReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=?", args);
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
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value}</pre>
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
  public List<BeanBean> selectList(List<BeanInner> value) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=?", args);
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

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asList(new ArrayList<BeanInner>(), BeanInner.class, cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asList(new LinkedList<BeanInner>(), BeanInner.class, cursor.getBlob(index2)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean_bean SET value=${value} WHERE id=${id} and value=${paramValue}</pre>
   *
   * @param value
   * 	used as updated field
   * @param id
   * 	used in where condition
   * @param paramValue
   * 	used in where condition
   *
   * @return true if record is updated */
  @Override
  public boolean updateOne(List<BeanInner> value, long id, List<BeanInner> paramValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditions={String.valueOf(id), (paramValue==null?null:new String(ProcessorHelper.asByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean_bean SET value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE id=%s and value=%s"), (Object[])whereConditions);
    int result = database().update("bean_bean", contentValues, "id=? and value=?", whereConditions);
    return result!=0;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean_bean (id, value) VALUES (${id}, ${value})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @param value
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(long id, List<BeanInner> value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean_bean (id, value) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("bean_bean", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(BeanBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(bean.value));
    } else {
      contentValues.putNull("value");
    }

    if (bean.value2!=null) {
      contentValues.put("value2", ProcessorHelper.asByteArray(bean.value2));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("bean_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean_bean WHERE value=${paramValue}</pre>
   *
   * @param paramValue
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<BeanInner> paramValue) {
    String[] whereConditions={(paramValue==null?null:new String(ProcessorHelper.asByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean_bean WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("bean_bean", "value=?", whereConditions);
    return result;
  }
}
