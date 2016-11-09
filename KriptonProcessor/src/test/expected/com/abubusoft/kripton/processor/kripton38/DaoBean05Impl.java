package com.abubusoft.kripton.processor.kripton38;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.common.StringUtil;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean05</code>, based on interface <code>DaoBean05</code>
 * </p>
 *
 *  @see Bean05
 *  @see DaoBean05
 *  @see Bean05Table
 */
public class DaoBean05Impl extends AbstractDao implements DaoBean05 {
  public DaoBean05Impl(BindDummy05DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong> is associated to bean's property <strong>pk</strong></li>
   * 	<li><strong>number</strong> is associated to bean's property <strong>number</strong></li>
   * 	<li><strong>bean_type</strong> is associated to bean's property <strong>beanType</strong></li>
   * 	<li><strong>text</strong> is associated to bean's property <strong>text</strong></li>
   * 	<li><strong>content</strong> is associated to bean's property <strong>content</strong></li>
   * 	<li><strong>creation_time</strong> is associated to bean's property <strong>creationTime</strong></li>
   * </ul>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Long id) {
    // build where condition
    String[] args={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean05 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("pk");
      int index1=cursor.getColumnIndex("number");
      int index2=cursor.getColumnIndex("bean_type");
      int index3=cursor.getColumnIndex("text");
      int index4=cursor.getColumnIndex("content");
      int index5=cursor.getColumnIndex("creation_time");

      resultBean=new Bean05();

      if (!cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1)); }
      if (!cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2))); }
      if (!cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3)); }
      if (!cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4)); }
      if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${bean.pk} and prova=${bean.text}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>bean.pk</strong> is binded to method's parameter <strong>bean.pk</strong></li>
   * 	<li>Param <strong>bean.text</strong> is binded to method's parameter <strong>bean.text</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong> is associated to bean's property <strong>pk</strong></li>
   * 	<li><strong>number</strong> is associated to bean's property <strong>number</strong></li>
   * 	<li><strong>bean_type</strong> is associated to bean's property <strong>beanType</strong></li>
   * 	<li><strong>text</strong> is associated to bean's property <strong>text</strong></li>
   * 	<li><strong>content</strong> is associated to bean's property <strong>content</strong></li>
   * 	<li><strong>creation_time</strong> is associated to bean's property <strong>creationTime</strong></li>
   * </ul>
   *
   * @param bean
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Bean05 bean) {
    // build where condition
    String[] args={String.valueOf(bean.getPk()), (bean.getText()==null?null:bean.getText())};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s' and prova='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=? and prova=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean05 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("pk");
      int index1=cursor.getColumnIndex("number");
      int index2=cursor.getColumnIndex("bean_type");
      int index3=cursor.getColumnIndex("text");
      int index4=cursor.getColumnIndex("content");
      int index5=cursor.getColumnIndex("creation_time");

      resultBean=new Bean05();

      if (!cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1)); }
      if (!cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2))); }
      if (!cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3)); }
      if (!cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4)); }
      if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong> is associated to bean's property <strong>pk</strong></li>
   * 	<li><strong>number</strong> is associated to bean's property <strong>number</strong></li>
   * 	<li><strong>bean_type</strong> is associated to bean's property <strong>beanType</strong></li>
   * 	<li><strong>text</strong> is associated to bean's property <strong>text</strong></li>
   * 	<li><strong>content</strong> is associated to bean's property <strong>content</strong></li>
   * 	<li><strong>creation_time</strong> is associated to bean's property <strong>creationTime</strong></li>
   * </ul>
   *
   * @param id
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean05> selectAll(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean05> resultList=new LinkedList<Bean05>();
    Bean05 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("pk");
      int index1=cursor.getColumnIndex("number");
      int index2=cursor.getColumnIndex("bean_type");
      int index3=cursor.getColumnIndex("text");
      int index4=cursor.getColumnIndex("content");
      int index5=cursor.getColumnIndex("creation_time");

      do
       {
        resultBean=new Bean05();

        if (!cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2))); }
        if (!cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT pk FROM ws_bean WHERE text = ${text}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>text</strong> is binded to method's parameter <strong>text</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong></li>
   * </ul>
   *
   * @param text
   *
   * @return list of single value extracted with query.
   */
  @Override
  public List<Long> selectPK(String text) {
    // build where condition
    String[] args={(text==null?null:text)};

    Logger.info(StringUtil.formatSQL("SELECT pk FROM ws_bean WHERE text = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk FROM ws_bean WHERE text = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Long> resultList=new LinkedList<Long>();


    try {
      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(cursor.getLong(0));
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

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT count(*) FROM ws_bean WHERE text = ${text}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>text</strong> is binded to method's parameter <strong>text</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>count(*)</strong></li>
   * </ul>
   *
   * @param text
   *
   * @return single value extracted with query.
   */
  @Override
  public Long selectCount(String text) {
    // build where condition
    String[] args={(text==null?null:text)};

    Logger.info(StringUtil.formatSQL("SELECT count(*) FROM ws_bean WHERE text = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT count(*) FROM ws_bean WHERE text = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Long result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
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
   * <p>Select SQL:</p>
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong></li>
   * 	<li><strong>number</strong></li>
   * 	<li><strong>bean_type</strong></li>
   * 	<li><strong>text</strong></li>
   * 	<li><strong>content</strong></li>
   * 	<li><strong>creation_time</strong></li>
   * </ul>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectCursorListener(Long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?", args);
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
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong> is associated to bean's property <strong>pk</strong></li>
   * 	<li><strong>number</strong> is associated to bean's property <strong>number</strong></li>
   * 	<li><strong>bean_type</strong> is associated to bean's property <strong>beanType</strong></li>
   * 	<li><strong>text</strong> is associated to bean's property <strong>text</strong></li>
   * 	<li><strong>content</strong> is associated to bean's property <strong>content</strong></li>
   * 	<li><strong>creation_time</strong> is associated to bean's property <strong>creationTime</strong></li>
   * </ul>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectBeanListener(Long id, OnReadBeanListener<Bean05> listener) {
    // build where condition
    String[] args={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean05 resultBean=new Bean05();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("pk");
        int index1=cursor.getColumnIndex("number");
        int index2=cursor.getColumnIndex("bean_type");
        int index3=cursor.getColumnIndex("text");
        int index4=cursor.getColumnIndex("content");
        int index5=cursor.getColumnIndex("creation_time");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.setPk(0L);
          resultBean.setNumber(0L);
          resultBean.setBeanType(null);
          resultBean.setText(null);
          resultBean.setContent(null);
          resultBean.setCreationTime(null);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2))); }
          if (!cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5))); }

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
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong></li>
   * 	<li><strong>number</strong></li>
   * 	<li><strong>bean_type</strong></li>
   * 	<li><strong>text</strong></li>
   * 	<li><strong>content</strong></li>
   * 	<li><strong>creation_time</strong></li>
   * </ul>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(Long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?", args);
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
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>pk</strong> is associated to bean's property <strong>pk</strong></li>
   * 	<li><strong>number</strong> is associated to bean's property <strong>number</strong></li>
   * 	<li><strong>bean_type</strong> is associated to bean's property <strong>beanType</strong></li>
   * 	<li><strong>text</strong> is associated to bean's property <strong>text</strong></li>
   * 	<li><strong>content</strong> is associated to bean's property <strong>content</strong></li>
   * 	<li><strong>creation_time</strong> is associated to bean's property <strong>creationTime</strong></li>
   * </ul>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(long id, OnReadBeanListener<Bean05> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean05 resultBean=new Bean05();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("pk");
        int index1=cursor.getColumnIndex("number");
        int index2=cursor.getColumnIndex("bean_type");
        int index3=cursor.getColumnIndex("text");
        int index4=cursor.getColumnIndex("content");
        int index5=cursor.getColumnIndex("creation_time");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.setPk(0L);
          resultBean.setNumber(0L);
          resultBean.setBeanType(null);
          resultBean.setText(null);
          resultBean.setContent(null);
          resultBean.setCreationTime(null);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setPk(cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setNumber(cursor.getLong(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(cursor.getString(index2))); }
          if (!cursor.isNull(index3)) { resultBean.setText(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.setContent(cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtil.read(cursor.getString(index5))); }

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
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO ws_bean (text, content, creation_time) VALUES (${text}, ${content}, ${creationTime})</pre>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>text</dt><dd>is mapped to parameter <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is mapped to parameter <strong>content</strong></dd>
   * 	<dt>creationTime</dt><dd>is mapped to parameter <strong>creationTime</strong></dd>
   * </dl>
   *
   * @param text
   * 	is binded to column <strong>text</strong>
   * @param content
   * 	is binded to column <strong>content</strong>
   * @param creationTime
   * 	is binded to column <strong>creation_time</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertRaw(String text, byte[] content, Date creationTime) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (text!=null) {
      contentValues.put("text", text);
    } else {
      contentValues.putNull("text");
    }

    if (content!=null) {
      contentValues.put("content", content);
    } else {
      contentValues.putNull("content");
    }

    if (creationTime!=null) {
      contentValues.put("creation_time", DateUtil.write(creationTime));
    } else {
      contentValues.putNull("creation_time");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO ws_bean (text, content, creation_time) VALUES ('"+StringUtil.checkSize(contentValues.get("text"))+"', '"+StringUtil.checkSize(contentValues.get("content"))+"', '"+StringUtil.checkSize(contentValues.get("creation_time"))+"')"));
    long result = database().insert("ws_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL Insert used:</p>
   * <pre>INSERT INTO ws_bean (number, bean_type, text, content, creation_time) VALUES (${bean.number}, ${bean.beanType}, ${bean.text}, ${bean.content}, ${bean.creationTime})</pre>
   *
   * <p><code>bean.pk</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted fields:</strong></p>
   * <dl>
   * 	<dt>number</dt><dd>is mapped to <strong>bean.number</strong></dd>
   * 	<dt>bean_type</dt><dd>is mapped to <strong>bean.beanType</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>bean.text</strong></dd>
   * 	<dt>content</dt><dd>is mapped to <strong>bean.content</strong></dd>
   * 	<dt>creation_time</dt><dd>is mapped to <strong>bean.creationTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insert(Bean05 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("number", bean.getNumber());

    if (bean.getBeanType()!=null) {
      contentValues.put("bean_type", bean.getBeanType().toString());
    } else {
      contentValues.putNull("bean_type");
    }

    if (bean.getText()!=null) {
      contentValues.put("text", bean.getText());
    } else {
      contentValues.putNull("text");
    }

    if (bean.getContent()!=null) {
      contentValues.put("content", bean.getContent());
    } else {
      contentValues.putNull("content");
    }

    if (bean.getCreationTime()!=null) {
      contentValues.put("creation_time", DateUtil.write(bean.getCreationTime()));
    } else {
      contentValues.putNull("creation_time");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO ws_bean (number, bean_type, text, content, creation_time) VALUES ('"+StringUtil.checkSize(contentValues.get("number"))+"', '"+StringUtil.checkSize(contentValues.get("bean_type"))+"', '"+StringUtil.checkSize(contentValues.get("text"))+"', '"+StringUtil.checkSize(contentValues.get("content"))+"', '"+StringUtil.checkSize(contentValues.get("creation_time"))+"')"));
    long result = database().insert("ws_bean", null, contentValues);
    bean.setPk(result);
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE ws_bean SET number=${bean.number}, bean_type=${bean.beanType}, text=${bean.text}, content=${bean.content}, creation_time=${bean.creationTime} WHERE pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}</pre>
   *
   * @param bean
   * 	is used as where parameter ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean05 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("number", bean.getNumber());

    if (bean.getBeanType()!=null) {
      contentValues.put("bean_type", bean.getBeanType().toString());
    } else {
      contentValues.putNull("bean_type");
    }

    if (bean.getText()!=null) {
      contentValues.put("text", bean.getText());
    } else {
      contentValues.putNull("text");
    }

    if (bean.getContent()!=null) {
      contentValues.put("content", bean.getContent());
    } else {
      contentValues.putNull("content");
    }

    if (bean.getCreationTime()!=null) {
      contentValues.put("creation_time", DateUtil.write(bean.getCreationTime()));
    } else {
      contentValues.putNull("creation_time");
    }

    String[] whereConditions={String.valueOf(bean.getPk()), (bean.getText()==null?null:bean.getText()), (bean.getCreationTime()==null?null:String.valueOf(DateUtil.write(bean.getCreationTime())))};

    Logger.info(StringUtil.formatSQL("UPDATE ws_bean SET number='"+StringUtil.checkSize(contentValues.get("number"))+"', bean_type='"+StringUtil.checkSize(contentValues.get("bean_type"))+"', text='"+StringUtil.checkSize(contentValues.get("text"))+"', content='"+StringUtil.checkSize(contentValues.get("content"))+"', creation_time='"+StringUtil.checkSize(contentValues.get("creation_time"))+"' WHERE pk=%s and text=%s and creationTime=%s"), (Object[])whereConditions);
    int result = database().update("ws_bean", contentValues, "pk=? and text=? and creation_time=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Update used:</p>
   * <pre>UPDATE ws_bean SET content=${content}, text=${text} WHERE pk=${uid} and creationTime=${valido} and creationTime=${validoIn}</pre>
   *
   * <p><strong>Updated fields:</strong></p>
   * <dl>
   * 	<dt>content</dt><dd>is mapped to parameter <strong>content</strong></dd>
   * 	<dt>text</dt><dd>is mapped to parameter <strong>text</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${uid}</dt><dd>is mapped to parameter <strong>uid</strong></dd>
   * 	<dt>${valido}</dt><dd>is mapped to parameter <strong>valido</strong></dd>
   * 	<dt>${validoIn}</dt><dd>is mapped to parameter <strong>validoIn</strong></dd>
   * </dl>
   *
   * @param content
   * 	is used as updated field <strong>content</strong>
   * @param text
   * 	is used as updated field <strong>text</strong>
   * @param uid
   * 	is used as where parameter <strong>${uid}</strong>
   * @param validoIn
   * 	is used as where parameter <strong>${validoIn}</strong>
   * @param valido
   * 	is used as where parameter <strong>${valido}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(byte[] content, String text, long uid, Date validoIn, Date valido) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (content!=null) {
      contentValues.put("content", content);
    } else {
      contentValues.putNull("content");
    }
    if (text!=null) {
      contentValues.put("text", text);
    } else {
      contentValues.putNull("text");
    }

    String[] whereConditions={String.valueOf(uid), (valido==null?null:String.valueOf(DateUtil.write(valido))), (validoIn==null?null:String.valueOf(DateUtil.write(validoIn)))};

    Logger.info(StringUtil.formatSQL("UPDATE ws_bean SET content='"+StringUtil.checkSize(contentValues.get("content"))+"', text='"+StringUtil.checkSize(contentValues.get("text"))+"' WHERE pk=%s and creationTime=%s and creationTime=%s"), (Object[])whereConditions);
    int result = database().update("ws_bean", contentValues, "pk=? and creation_time=? and creation_time=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Delete used:</p>
   * <pre>DELETE ws_bean WHERE pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}</pre>
   *
   * @param bean
   * 	is used as where parameter ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(Bean05 bean) {
    String[] whereConditions={String.valueOf(bean.getPk()), (bean.getText()==null?null:bean.getText()), (bean.getCreationTime()==null?null:String.valueOf(DateUtil.write(bean.getCreationTime())))};

    Logger.info(StringUtil.formatSQL("pk=%s and text=%s and creation_time=%s"), (Object[])whereConditions);
    int result = database().delete("ws_bean", "pk=? and text=? and creation_time=?", whereConditions);
    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE ws_bean WHERE pk=${uid} and creationTime=${valido} and creationTime=${validoIn}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${uid}</dt><dd>is mapped to parameter <strong>uid</strong></dd>
   * 	<dt>${valido}</dt><dd>is mapped to parameter <strong>valido</strong></dd>
   * 	<dt>${validoIn}</dt><dd>is mapped to parameter <strong>validoIn</strong></dd>
   * </dl>
   *
   * @param uid
   * 	is used as where parameter <strong>${uid}</strong>
   * @param validoIn
   * 	is used as where parameter <strong>${validoIn}</strong>
   * @param valido
   * 	is used as where parameter <strong>${valido}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long uid, Date validoIn, Date valido) {
    String[] whereConditions={String.valueOf(uid), (valido==null?null:String.valueOf(DateUtil.write(valido))), (validoIn==null?null:String.valueOf(DateUtil.write(validoIn)))};

    Logger.info(StringUtil.formatSQL("DELETE ws_bean WHERE pk=%s and creationTime=%s and creationTime=%s"), (Object[])whereConditions);
    int result = database().delete("ws_bean", "pk=? and creation_time=? and creation_time=?", whereConditions);
    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE ws_bean WHERE pk=${id}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE ws_bean WHERE pk=%s"), (Object[])whereConditions);
    int result = database().delete("ws_bean", "pk=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL Delete used:</p>
   * <pre>DELETE ws_bean WHERE pk=${va.pk}</pre>
   *
   * @param va
   * 	is used as where parameter ${va}
   *
   * @return number of deleted records
   */
  @Override
  public long deleteBean(Bean05 va) {
    String[] whereConditions={String.valueOf(va.getPk())};

    Logger.info(StringUtil.formatSQL("pk=%s"), (Object[])whereConditions);
    int result = database().delete("ws_bean", "pk=?", whereConditions);
    return result;
  }

  /**
   * <p>Select SQL:</p>
   * <pre>SELECT content FROM ws_bean WHERE pk=${id}</pre>
   *
   * <p>Query's parameters are:</p>
   * <ul>
   * 	<li>Param <strong>id</strong> is binded to method's parameter <strong>id</strong></li>
   * </ul>
   *
   * <p>Projected columns are:</p>
   * <ul>
   * 	<li><strong>content</strong></li>
   * </ul>
   *
   * @param id
   *
   * @return single value extracted with query.
   */
  @Override
  public byte[] getOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT content FROM ws_bean WHERE pk='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT content FROM ws_bean WHERE pk=?", args);
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
}
