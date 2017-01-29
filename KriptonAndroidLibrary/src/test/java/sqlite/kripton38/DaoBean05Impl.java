package sqlite.kripton38;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Long id) {
    // build where condition
    String[] args={(id==null?"":String.valueOf(id))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'",(Object[])args));
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
      if (!cursor.isNull(index4)) { resultBean.setContent(Bean05Table.parseContent(cursor.getBlob(index4))); }
      if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(cursor.getString(index5))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${bean.pk} and prova=${bean.text}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.pk}</dt><dd>is binded to method's parameter <strong>bean.pk</strong></dd>
   * 	<dt>${bean.text}</dt><dd>is binded to method's parameter <strong>bean.text</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Bean05 bean) {
    // build where condition
    String[] args={String.valueOf(bean.getPk()), (bean.getText()==null?"":bean.getText())};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s' and prova='%s'",(Object[])args));
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
      if (!cursor.isNull(index4)) { resultBean.setContent(Bean05Table.parseContent(cursor.getBlob(index4))); }
      if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(cursor.getString(index5))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean05> selectAll(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'",(Object[])args));
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
        if (!cursor.isNull(index4)) { resultBean.setContent(Bean05Table.parseContent(cursor.getBlob(index4))); }
        if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(cursor.getString(index5))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk FROM ws_bean WHERE text = ${text}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${text}</dt><dd>is binded to method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * @param text
   * 	is binded to <code>${text}</code>
   * @return collection of single value extracted with query.
   */
  @Override
  public List<Long> selectPK(String text) {
    // build where condition
    String[] args={(text==null?"":text)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk FROM ws_bean WHERE text = '%s'",(Object[])args));
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
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT count(*) FROM ws_bean WHERE text = ${text}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${text}</dt><dd>is binded to method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * @param text
   * 	is binded to <code>${text}</code>
   * @return single value extracted with query.
   */
  @Override
  public Long selectCount(String text) {
    // build where condition
    String[] args={(text==null?"":text)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT count(*) FROM ws_bean WHERE text = '%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT count(*) FROM ws_bean WHERE text = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Long result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getLong(0);
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>no bean's property is associated</dd>
   * 	<dt>number</dt><dd>no bean's property is associated</dd>
   * 	<dt>bean_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>text</dt><dd>no bean's property is associated</dd>
   * 	<dt>content</dt><dd>no bean's property is associated</dd>
   * 	<dt>creation_time</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectCursorListener(Long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={(id==null?"":String.valueOf(id))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'",(Object[])args));
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
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the Bean05 listener
   */
  @Override
  public void selectBeanListener(Long id, OnReadBeanListener<Bean05> listener) {
    // build where condition
    String[] args={(id==null?"":String.valueOf(id))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'",(Object[])args));
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
          if (!cursor.isNull(index4)) { resultBean.setContent(Bean05Table.parseContent(cursor.getBlob(index4))); }
          if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(cursor.getString(index5))); }

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
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>no bean's property is associated</dd>
   * 	<dt>number</dt><dd>no bean's property is associated</dd>
   * 	<dt>bean_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>text</dt><dd>no bean's property is associated</dd>
   * 	<dt>content</dt><dd>no bean's property is associated</dd>
   * 	<dt>creation_time</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(Long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={(id==null?"":String.valueOf(id))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'",(Object[])args));
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
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the Bean05 listener
   */
  @Override
  public void selectOne(long id, OnReadBeanListener<Bean05> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk='%s'",(Object[])args));
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
          if (!cursor.isNull(index4)) { resultBean.setContent(Bean05Table.parseContent(cursor.getBlob(index4))); }
          if (!cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(cursor.getString(index5))); }

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
   * <p>SQL insert:</p>
   * <pre>INSERT INTO ws_bean (text, content, creation_time) VALUES (${text}, ${content}, ${creationTime})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>${text}</strong> and method's parameter <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is binded to query's parameter <strong>${content}</strong> and method's parameter <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is binded to query's parameter <strong>${creationTime}</strong> and method's parameter <strong>creationTime</strong></dd>
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
      contentValues.put("content", serializer1(content));
    } else {
      contentValues.putNull("content");
    }

    if (creationTime!=null) {
      contentValues.put("creation_time", DateUtils.write(creationTime));
    } else {
      contentValues.putNull("creation_time");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO ws_bean (text, content, creation_time) VALUES ('"+StringUtils.checkSize(contentValues.get("text"))+"', '"+StringUtils.checkSize(contentValues.get("content"))+"', '"+StringUtils.checkSize(contentValues.get("creation_time"))+"')"));
    long result = database().insert("ws_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO ws_bean (number, bean_type, text, content, creation_time) VALUES (${bean.number}, ${bean.beanType}, ${bean.text}, ${bean.content}, ${bean.creationTime})</pre>
   *
   * <p><code>bean.pk</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>number</dt><dd>is mapped to <strong>${bean.number}</strong></dd>
   * 	<dt>bean_type</dt><dd>is mapped to <strong>${bean.beanType}</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>${bean.text}</strong></dd>
   * 	<dt>content</dt><dd>is mapped to <strong>${bean.content}</strong></dd>
   * 	<dt>creation_time</dt><dd>is mapped to <strong>${bean.creationTime}</strong></dd>
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
      contentValues.put("content", Bean05Table.serializeContent(bean.getContent()));
    } else {
      contentValues.putNull("content");
    }

    if (bean.getCreationTime()!=null) {
      contentValues.put("creation_time", DateUtils.write(bean.getCreationTime()));
    } else {
      contentValues.putNull("creation_time");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO ws_bean (number, bean_type, text, content, creation_time) VALUES ('"+StringUtils.checkSize(contentValues.get("number"))+"', '"+StringUtils.checkSize(contentValues.get("bean_type"))+"', '"+StringUtils.checkSize(contentValues.get("text"))+"', '"+StringUtils.checkSize(contentValues.get("content"))+"', '"+StringUtils.checkSize(contentValues.get("creation_time"))+"')"));
    long result = database().insert("ws_bean", null, contentValues);
    bean.setPk(result);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE ws_bean SET number=${bean.number}, bean_type=${bean.beanType}, text=${bean.text}, content=${bean.content}, creation_time=${bean.creationTime} WHERE pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>number</dt><dd>is mapped to <strong>${bean.number}</strong></dd>
   * 	<dt>bean_type</dt><dd>is mapped to <strong>${bean.beanType}</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>${bean.text}</strong></dd>
   * 	<dt>content</dt><dd>is mapped to <strong>${bean.content}</strong></dd>
   * 	<dt>creation_time</dt><dd>is mapped to <strong>${bean.creationTime}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.pk}</dt><dd>is mapped to method's parameter <strong>bean.pk</strong></dd>
   * 	<dt>${bean.text}</dt><dd>is mapped to method's parameter <strong>bean.text</strong></dd>
   * 	<dt>${bean.creationTime}</dt><dd>is mapped to method's parameter <strong>bean.creationTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
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
      contentValues.put("content", Bean05Table.serializeContent(bean.getContent()));
    } else {
      contentValues.putNull("content");
    }

    if (bean.getCreationTime()!=null) {
      contentValues.put("creation_time", DateUtils.write(bean.getCreationTime()));
    } else {
      contentValues.putNull("creation_time");
    }

    String[] whereConditionsArray={String.valueOf(bean.getPk()), (bean.getText()==null?"":bean.getText()), (bean.getCreationTime()==null?"":DateUtils.write(bean.getCreationTime()))};

    Logger.info(StringUtils.formatSQL("UPDATE ws_bean SET number='"+StringUtils.checkSize(contentValues.get("number"))+"', bean_type='"+StringUtils.checkSize(contentValues.get("bean_type"))+"', text='"+StringUtils.checkSize(contentValues.get("text"))+"', content='"+StringUtils.checkSize(contentValues.get("content"))+"', creation_time='"+StringUtils.checkSize(contentValues.get("creation_time"))+"' WHERE pk='%s' and text='%s' and creationTime='%s'", (Object[]) whereConditionsArray));
    int result = database().update("ws_bean", contentValues, "UPDATE ws_bean SET number='"+StringUtils.checkSize(contentValues.get("number"))+"', bean_type='"+StringUtils.checkSize(contentValues.get("bean_type"))+"', text='"+StringUtils.checkSize(contentValues.get("text"))+"', content='"+StringUtils.checkSize(contentValues.get("content"))+"', creation_time='"+StringUtils.checkSize(contentValues.get("creation_time"))+"' WHERE pk='%s' and text='%s' and creationTime='%s'", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE ws_bean SET content=${content}, text=${text} WHERE pk=${uid} and creationTime=${valido} and creationTime=${validoIn}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>content</dt><dd>is binded to query's parameter <strong>${content}</strong> and method's parameter <strong>content</strong></dd>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>${text}</strong> and method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${uid}</dt><dd>is mapped to method's parameter <strong>uid</strong></dd>
   * 	<dt>${valido}</dt><dd>is mapped to method's parameter <strong>valido</strong></dd>
   * 	<dt>${validoIn}</dt><dd>is mapped to method's parameter <strong>validoIn</strong></dd>
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
      contentValues.put("content", serializer1(content));
    } else {
      contentValues.putNull("content");
    }
    if (text!=null) {
      contentValues.put("text", text);
    } else {
      contentValues.putNull("text");
    }

    String[] whereConditionsArray={String.valueOf(uid), (valido==null?"":DateUtils.write(valido)), (validoIn==null?"":DateUtils.write(validoIn))};

    Logger.info(StringUtils.formatSQL("UPDATE ws_bean SET content='"+StringUtils.checkSize(contentValues.get("content"))+"', text='"+StringUtils.checkSize(contentValues.get("text"))+"' WHERE pk=%s and creationTime=%s and creationTime=%s", (Object[])whereConditionsArray));
    int result = database().update("ws_bean", contentValues, "pk=? and creation_time=? and creation_time=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE ws_bean WHERE pk=${bean.pk} and text=${bean.text} and creationTime=${bean.creationTime}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.pk}</dt><dd>is mapped to method's parameter <strong>bean.pk</strong></dd>
   * 	<dt>${bean.text}</dt><dd>is mapped to method's parameter <strong>bean.text</strong></dd>
   * 	<dt>${bean.creationTime}</dt><dd>is mapped to method's parameter <strong>bean.creationTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(Bean05 bean) {
    String[] whereConditionsArray={String.valueOf(bean.getPk()), (bean.getText()==null?"":bean.getText()), (bean.getCreationTime()==null?"":DateUtils.write(bean.getCreationTime()))};

    Logger.info(StringUtils.formatSQL("DELETE ws_bean WHERE pk=%s and text=%s and creationTime=%s ", (Object[]) whereConditionsArray));
    int result = database().delete("ws_bean", "pk=? and text=? and creation_time=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE ws_bean WHERE pk=${uid} and creationTime=${valido} and creationTime=${validoIn}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${uid}</dt><dd>is mapped to method's parameter <strong>uid</strong></dd>
   * 	<dt>${valido}</dt><dd>is mapped to method's parameter <strong>valido</strong></dd>
   * 	<dt>${validoIn}</dt><dd>is mapped to method's parameter <strong>validoIn</strong></dd>
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
    String[] whereConditionsArray={String.valueOf(uid), (valido==null?"":DateUtils.write(valido)), (validoIn==null?"":DateUtils.write(validoIn))};

    Logger.info(StringUtils.formatSQL("DELETE ws_bean WHERE pk=%s and creationTime=%s and creationTime=%s", (Object[])whereConditionsArray));
    int result = database().delete("ws_bean", "pk=? and creation_time=? and creation_time=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE ws_bean WHERE pk=${id}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    String[] whereConditionsArray={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("DELETE ws_bean WHERE pk=%s", (Object[])whereConditionsArray));
    int result = database().delete("ws_bean", "pk=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE ws_bean WHERE pk=${va.pk}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${va.pk}</dt><dd>is mapped to method's parameter <strong>va.pk</strong></dd>
   * </dl>
   *
   * @param va
   * 	is used as ${va}
   *
   * @return number of deleted records
   */
  @Override
  public long deleteBean(Bean05 va) {
    String[] whereConditionsArray={String.valueOf(va.getPk())};

    Logger.info(StringUtils.formatSQL("DELETE ws_bean WHERE pk=%s ", (Object[]) whereConditionsArray));
    int result = database().delete("ws_bean", "pk=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT content FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>content</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return single value extracted with query.
   */
  @Override
  public byte[] getOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT content FROM ws_bean WHERE pk='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT content FROM ws_bean WHERE pk=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    byte[] result=null;

    try {
      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=parser1(cursor.getBlob(0));
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
    return result;
  }

  /**
   * write
   */
  private byte[] serializer1(byte[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        jacksonSerializer.writeBinaryField("element", value);
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
  private byte[] parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      byte[] result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getBinaryValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
