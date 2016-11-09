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
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE 1=1</pre>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
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

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index1)))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index2)))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * 	<li>Param <strong>value2</strong> is binded to method's parameter <strong>value2</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   * @param value
   * @param value2
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public BeanBean selectOne(BeanInner[] value, BeanInner[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM bean_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    BeanBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new BeanBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index1)))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index2)))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * 	<li>Param <strong>value2</strong> is binded to method's parameter <strong>value2</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   * @param value
   * @param value2
   * @param listener
   */
  @Override
  public void selectOne(BeanInner[] value, BeanInner[] value2, ReadBeanListener<BeanBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
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
          if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index1)))); }
          if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index2)))); }

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
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * 	<li>Param <strong>value2</strong> is binded to method's parameter <strong>value2</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong></li>
   * 	<li><strong>value</strong></li>
   * 	<li><strong>value2</strong></li>
   * </ul>
   *
   * @param value
   * @param value2
   * @param listener
   */
  @Override
  public void selectOne(BeanInner[] value, BeanInner[] value2, ReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
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
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT id, value, value2 FROM bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>value</strong> is binded to method's parameter <strong>value</strong></li>
   * 	<li>Param <strong>value2</strong> is binded to method's parameter <strong>value2</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>id</strong> is associated to bean's property <strong>id</strong></li>
   * 	<li><strong>value</strong> is associated to bean's property <strong>value</strong></li>
   * 	<li><strong>value2</strong> is associated to bean's property <strong>value2</strong></li>
   * </ul>
   *
   * @param value
   * @param value2
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<BeanBean> selectList(BeanInner[] value, BeanInner[] value2) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("SELECT id, value, value2 FROM bean_bean WHERE value='%s' and value2='%s'"),(Object[])args);
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
        if (!cursor.isNull(index1)) { resultBean.setValue(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index1)))); }
        if (!cursor.isNull(index2)) { resultBean.setValue2(CollectionUtility.asArray(ProcessorHelper.asList(BeanInner.class, cursor.getBlob(index2)))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL Update used:</p>
   * <pre>UPDATE bean_bean SET  WHERE id=${id} and value=${value} and value2=${value2}</pre>
   *
   * <p><strong>Updated fields:</strong></p>
   * <dl>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * 	<dt>${value}</dt><dd>is mapped to parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to parameter <strong>value2</strong></dd>
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

    String[] whereConditions={String.valueOf(id), (value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("UPDATE bean_bean SET  WHERE id=%s and value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().update("bean_bean", contentValues, "id=? and value=? and value2=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO bean_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is mapped to parameter <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to parameter <strong>value2</strong></dd>
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
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean_bean (id, value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("bean_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO bean_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>bean.value</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>bean.value2</strong></dd>
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
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean_bean (value, value2) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("bean_bean", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to parameter <strong>value2</strong></dd>
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
    String[] whereConditions={(value==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value, ArrayList.class)),StandardCharsets.UTF_8)), (value2==null?null:new String(ProcessorHelper.asByteArray(CollectionUtility.asList(value2, ArrayList.class)),StandardCharsets.UTF_8))};

    Logger.info(StringUtil.formatSQL("DELETE bean_bean WHERE value=%s and value2=%s"), (Object[])whereConditions);
    int result = database().delete("bean_bean", "value=? and value2=?", whereConditions);
    return result;
  }
}
