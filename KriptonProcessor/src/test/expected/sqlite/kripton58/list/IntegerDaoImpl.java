package sqlite.kripton58.list;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>IntegerBean</code>, based on interface <code>IntegerDao</code>
 * </p>
 *
 *  @see IntegerBean
 *  @see IntegerDao
 *  @see IntegerBeanTable
 */
public class IntegerDaoImpl extends AbstractDao implements IntegerDao {
  public IntegerDaoImpl(BindIntegerDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntegerBean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    IntegerBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new IntegerBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Integer>(), Integer.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Integer>(), Integer.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public IntegerBean selectOne(List<Integer> value) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    IntegerBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new IntegerBean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Integer>(), Integer.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Integer>(), Integer.class, cursor.getBlob(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   * @param listener
   * 	is the IntegerBean listener
   */
  @Override
  public void selectOne(List<Integer> value, OnReadBeanListener<IntegerBean> listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    IntegerBean resultBean=new IntegerBean();
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
          if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Integer>(), Integer.class, cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Integer>(), Integer.class, cursor.getBlob(index2)); }

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
   * <p>
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value2</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(List<Integer> value, OnReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", args);
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
   * <p>
   * <pre>SELECT id, value, value2 FROM integer_bean WHERE value=${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${value}
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<IntegerBean> selectList(List<Integer> value) {
    // build where condition
    String[] args={(value==null?null:new String(ProcessorHelper.asByteArray(value),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM integer_bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM integer_bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<IntegerBean> resultList=new LinkedList<IntegerBean>();
    IntegerBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new IntegerBean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.value=ProcessorHelper.asCollection(new ArrayList<Integer>(), Integer.class, cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.value2=ProcessorHelper.asCollection(new LinkedList<Integer>(), Integer.class, cursor.getBlob(index2)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE integer_bean SET value=${value} WHERE id=${id} and value=${paramValue}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${paramValue}</dt><dd>is mapped to method's parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param paramValue
   * 	is used as where parameter <strong>${paramValue}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(List<Integer> value, long id, List<Integer> paramValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditions={String.valueOf(id), (paramValue==null?null:new String(ProcessorHelper.asByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("UPDATE integer_bean SET value='"+StringUtils.checkSize(contentValues.get("value"))+"' WHERE id=%s and value=%s"), (Object[])whereConditions);
    int result = database().update("integer_bean", contentValues, "id=? and value=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO integer_bean (id, value) VALUES (${id}, ${value})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   * @param value
   * 	is binded to column <strong>value</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, List<Integer> value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", ProcessorHelper.asByteArray(value));
    } else {
      contentValues.putNull("value");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO integer_bean (id, value) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("integer_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO integer_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>${bean.value2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(IntegerBean bean) {
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
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO integer_bean (value, value2) VALUES ('"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("integer_bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE integer_bean WHERE value=${paramValue}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${paramValue}</dt><dd>is mapped to method's parameter <strong>paramValue</strong></dd>
   * </dl>
   *
   * @param paramValue
   * 	is used as where parameter <strong>${paramValue}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(List<Integer> paramValue) {
    String[] whereConditions={(paramValue==null?null:new String(ProcessorHelper.asByteArray(paramValue),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("DELETE integer_bean WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("integer_bean", "value=?", whereConditions);
    return result;
  }
}
