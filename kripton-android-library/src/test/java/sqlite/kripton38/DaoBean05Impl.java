/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton38;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.EnumUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Bean05</code>, based on interface <code>DaoBean05</code>
 * </p>.
 *
 * @see Bean05
 * @see DaoBean05
 * @see Bean05Table
 */
public class DaoBean05Impl extends Dao implements DaoBean05 {
  
  /** The Constant SELECT_ONE_SQL1. */
  private static final String SELECT_ONE_SQL1 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?";

  /** The Constant SELECT_ONE_SQL2. */
  private static final String SELECT_ONE_SQL2 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=? and text=?";

  /** The Constant SELECT_ALL_SQL3. */
  private static final String SELECT_ALL_SQL3 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?";

  /** The Constant SELECT_P_K_SQL4. */
  private static final String SELECT_P_K_SQL4 = "SELECT pk FROM ws_bean WHERE text = ?";

  /** The Constant SELECT_COUNT_SQL5. */
  private static final String SELECT_COUNT_SQL5 = "SELECT count(*) FROM ws_bean WHERE text = ?";

  /** The Constant SELECT_CURSOR_LISTENER_SQL6. */
  private static final String SELECT_CURSOR_LISTENER_SQL6 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?";

  /** The Constant SELECT_BEAN_LISTENER_SQL7. */
  private static final String SELECT_BEAN_LISTENER_SQL7 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?";

  /** The Constant SELECT_ONE_SQL8. */
  private static final String SELECT_ONE_SQL8 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?";

  /** The Constant SELECT_ONE_SQL9. */
  private static final String SELECT_ONE_SQL9 = "SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=?";

  /** The insert raw prepared statement 0. */
  private static SQLiteStatement insertRawPreparedStatement0;

  /** The insert prepared statement 1. */
  private static SQLiteStatement insertPreparedStatement1;

  /** The update one prepared statement 2. */
  private static SQLiteStatement updateOnePreparedStatement2;

  /** The update one prepared statement 3. */
  private static SQLiteStatement updateOnePreparedStatement3;

  /** The delete one prepared statement 4. */
  private static SQLiteStatement deleteOnePreparedStatement4;

  /** The delete one prepared statement 5. */
  private static SQLiteStatement deleteOnePreparedStatement5;

  /** The delete one prepared statement 6. */
  private static SQLiteStatement deleteOnePreparedStatement6;

  /** The delete bean prepared statement 7. */
  private static SQLiteStatement deleteBeanPreparedStatement7;

  /** The Constant GET_ONE_SQL10. */
  private static final String GET_ONE_SQL10 = "SELECT content FROM ws_bean WHERE pk=?";

  /**
   * Instantiates a new dao bean 05 impl.
   *
   * @param context the context
   */
  public DaoBean05Impl(SQLContext context) {
    super(context);
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      Bean05 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("number");
        int index2=_cursor.getColumnIndex("bean_type");
        int index3=_cursor.getColumnIndex("text");
        int index4=_cursor.getColumnIndex("content");
        int index5=_cursor.getColumnIndex("creation_time");

        resultBean=new Bean05();

        resultBean.setPk(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.setNumber(_cursor.getLong(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index2))); }
        if (!_cursor.isNull(index3)) { resultBean.setText(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.setContent(_cursor.getBlob(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index5))); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, number, bean_type, text, content, creation_time FROM ws_bean WHERE pk=${bean.pk} and text=${bean.text}</pre>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getPk()));
    _contentValues.addWhereArgs(bean.getText());
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      Bean05 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("number");
        int index2=_cursor.getColumnIndex("bean_type");
        int index3=_cursor.getColumnIndex("text");
        int index4=_cursor.getColumnIndex("content");
        int index5=_cursor.getColumnIndex("creation_time");

        resultBean=new Bean05();

        resultBean.setPk(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.setNumber(_cursor.getLong(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index2))); }
        if (!_cursor.isNull(index3)) { resultBean.setText(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.setContent(_cursor.getBlob(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index5))); }

      }
      return resultBean;
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean05> selectAll(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<Bean05> resultList=new ArrayList<Bean05>(_cursor.getCount());
      Bean05 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("number");
        int index2=_cursor.getColumnIndex("bean_type");
        int index3=_cursor.getColumnIndex("text");
        int index4=_cursor.getColumnIndex("content");
        int index5=_cursor.getColumnIndex("creation_time");

        do
         {
          resultBean=new Bean05();

          resultBean.setPk(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setNumber(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.setText(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setContent(_cursor.getBlob(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index5))); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT pk FROM ws_bean WHERE text = ${text}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${text}</dt><dd>is binded to method's parameter <strong>text</strong></dd>
   * </dl>.
   *
   * @param text 	is binded to <code>${text}</code>
   * @return collection of single value extracted by query.
   */
  @Override
  public List<Long> selectPK(String text) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_P_K_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((text==null?"":text));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<Long> resultList=new ArrayList<Long>(_cursor.getCount());


      if (_cursor.moveToFirst()) {

        do
         {
          if (!_cursor.isNull(0)) {
            resultList.add(parser1(_cursor.getBlob(0)));
          } else {
            resultList.add(null);
          }
        } while (_cursor.moveToNext());
      }
      return resultList;
    }
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
   * </dl>.
   *
   * @param text 	is binded to <code>${text}</code>
   * @return single value extracted by query.
   */
  @Override
  public Long selectCount(String text) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_COUNT_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((text==null?"":text));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      Long result=null;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return null; }
        result=_cursor.getLong(0);
      }
      return result;
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @param listener 	is the cursor listener
   */
  @Override
  public void selectCursorListener(Long id, OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_CURSOR_LISTENER_SQL6;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @param listener 	is the Bean05 listener
   */
  @Override
  public void selectBeanListener(Long id, OnReadBeanListener<Bean05> listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN_LISTENER_SQL7;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      Bean05 resultBean=new Bean05();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("number");
        int index2=_cursor.getColumnIndex("bean_type");
        int index3=_cursor.getColumnIndex("text");
        int index4=_cursor.getColumnIndex("content");
        int index5=_cursor.getColumnIndex("creation_time");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // pk does not need reset
          resultBean.setNumber(0L);
          resultBean.setBeanType(null);
          resultBean.setText(null);
          resultBean.setContent(null);
          resultBean.setCreationTime(null);

          // generate mapping
          resultBean.setPk(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setNumber(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.setText(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setContent(_cursor.getBlob(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index5))); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @param listener 	is the cursor listener
   */
  @Override
  public void selectOne(Long id, OnReadCursorListener listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL8;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
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
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @param listener 	is the Bean05 listener
   */
  @Override
  public void selectOne(long id, OnReadBeanListener<Bean05> listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL9;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      Bean05 resultBean=new Bean05();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("number");
        int index2=_cursor.getColumnIndex("bean_type");
        int index3=_cursor.getColumnIndex("text");
        int index4=_cursor.getColumnIndex("content");
        int index5=_cursor.getColumnIndex("creation_time");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // pk does not need reset
          resultBean.setNumber(0L);
          resultBean.setBeanType(null);
          resultBean.setText(null);
          resultBean.setContent(null);
          resultBean.setCreationTime(null);

          // generate mapping
          resultBean.setPk(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setNumber(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.setText(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setContent(_cursor.getBlob(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index5))); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO ws_bean (text, content, creation_time) VALUES (${text}, ${content}, ${creationTime})</pre>
   * 
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>${text}</strong> and method's parameter <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is binded to query's parameter <strong>${content}</strong> and method's parameter <strong>content</strong></dd>
   * 	<dt>creationTime</dt><dd>is binded to query's parameter <strong>${creationTime}</strong> and method's parameter <strong>creationTime</strong></dd>
   * </dl>.
   *
   * @param text 	is binded to column value <strong>text</strong>
   * @param content 	is binded to column value <strong>content</strong>
   * @param creationTime 	is binded to column value <strong>creation_time</strong>
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertRaw(String text, byte[] content, Date creationTime) {
    if (insertRawPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO ws_bean (text, content, creation_time) VALUES (?, ?, ?)";
      insertRawPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertRawPreparedStatement0);

    _contentValues.put("text", text);
    _contentValues.put("content", content);
    _contentValues.put("creation_time", DateUtils.write(creationTime));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO ws_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertRawPreparedStatement0, _contentValues);
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
   */
  @Override
  public void insert(Bean05 bean) {
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO ws_bean (number, bean_type, text, content, creation_time) VALUES (?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put("number", bean.getNumber());
    _contentValues.put("bean_type", EnumUtils.write(bean.getBeanType()));
    _contentValues.put("text", bean.getText());
    _contentValues.put("content", bean.getContent());
    _contentValues.put("creation_time", DateUtils.write(bean.getCreationTime()));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO ws_bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
    bean.setPk(result);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE ws_bean SET number=:number, bean_type=:beanType, text=:text, content=:content, creation_time=:creationTime WHERE pk=${bean.pk} and text=${bean.text} and creation_time=${bean.creationTime}</pre>
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
    if (updateOnePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE ws_bean SET number=?, bean_type=?, text=?, content=?, creation_time=? WHERE pk=? and text=? and creation_time=?";
      updateOnePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement2);
    _contentValues.put("number", bean.getNumber());
    _contentValues.put("bean_type", EnumUtils.write(bean.getBeanType()));
    _contentValues.put("text", bean.getText());
    _contentValues.put("content", bean.getContent());
    _contentValues.put("creation_time", DateUtils.write(bean.getCreationTime()));

    _contentValues.addWhereArgs(String.valueOf(bean.getPk()));
    _contentValues.addWhereArgs((bean.getText()==null?"":bean.getText()));
    _contentValues.addWhereArgs((bean.getCreationTime()==null?"":DateUtils.write(bean.getCreationTime())));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE ws_bean SET number=:number, bean_type=:bean_type, text=:text, content=:content, creation_time=:creation_time WHERE pk=? and text=? and creation_time=?");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE ws_bean SET content=:content, text=:text WHERE pk=${uid} and creation_time=${valido} and creation_time=${validoIn}</pre>
   * 
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>content</li>
   * 	<li>text</li>
   * </ul>
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${uid}</dt><dd>is mapped to method's parameter <strong>uid</strong></dd>
   * 	<dt>${valido}</dt><dd>is mapped to method's parameter <strong>valido</strong></dd>
   * 	<dt>${validoIn}</dt><dd>is mapped to method's parameter <strong>validoIn</strong></dd>
   * </dl>.
   *
   * @param content 	is used as updated field <strong>content</strong>
   * @param text 	is used as updated field <strong>text</strong>
   * @param uid 	is used as where parameter <strong>${uid}</strong>
   * @param validoIn 	is used as where parameter <strong>${validoIn}</strong>
   * @param valido 	is used as where parameter <strong>${valido}</strong>
   * @return number of updated records
   */
  @Override
  public long updateOne(byte[] content, String text, long uid, Date validoIn, Date valido) {
    if (updateOnePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE ws_bean SET content=?, text=? WHERE pk=? and creation_time=? and creation_time=?";
      updateOnePreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement3);
    _contentValues.put("content", content);
    _contentValues.put("text", text);

    _contentValues.addWhereArgs(String.valueOf(uid));
    _contentValues.addWhereArgs((valido==null?"":DateUtils.write(valido)));
    _contentValues.addWhereArgs((validoIn==null?"":DateUtils.write(validoIn)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE ws_bean SET content=:content, text=:text WHERE pk=? and creation_time=? and creation_time=?");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateOnePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=${bean.pk} and text=${bean.text} and creation_time=${bean.creationTime}</pre>
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
    if (deleteOnePreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=? and text=? and creation_time=?";
      deleteOnePreparedStatement4 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOnePreparedStatement4);
    _contentValues.addWhereArgs(String.valueOf(bean.getPk()));
    _contentValues.addWhereArgs((bean.getText()==null?"":bean.getText()));
    _contentValues.addWhereArgs((bean.getCreationTime()==null?"":DateUtils.write(bean.getCreationTime())));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM ws_bean WHERE pk=? and text=? and creation_time=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteOnePreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=${uid} and creation_time=${valido} and creation_time=${validoIn}</pre>
   * 
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${uid}</dt><dd>is mapped to method's parameter <strong>uid</strong></dd>
   * 	<dt>${valido}</dt><dd>is mapped to method's parameter <strong>valido</strong></dd>
   * 	<dt>${validoIn}</dt><dd>is mapped to method's parameter <strong>validoIn</strong></dd>
   * </dl>.
   *
   * @param uid 	is used as where parameter <strong>${uid}</strong>
   * @param validoIn 	is used as where parameter <strong>${validoIn}</strong>
   * @param valido 	is used as where parameter <strong>${valido}</strong>
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long uid, Date validoIn, Date valido) {
    if (deleteOnePreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=? and creation_time=? and creation_time=?";
      deleteOnePreparedStatement5 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOnePreparedStatement5);
    _contentValues.addWhereArgs(String.valueOf(uid));
    _contentValues.addWhereArgs((valido==null?"":DateUtils.write(valido)));
    _contentValues.addWhereArgs((validoIn==null?"":DateUtils.write(validoIn)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM ws_bean WHERE pk=? and creation_time=? and creation_time=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteOnePreparedStatement5, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=${id}</pre>
   * 
   * 
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>.
   *
   * @param id 	is used as where parameter <strong>${id}</strong>
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    if (deleteOnePreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=?";
      deleteOnePreparedStatement6 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteOnePreparedStatement6);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM ws_bean WHERE pk=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteOnePreparedStatement6, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=${va.pk}</pre>
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
    if (deleteBeanPreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=?";
      deleteBeanPreparedStatement7 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteBeanPreparedStatement7);
    _contentValues.addWhereArgs(String.valueOf(va.getPk()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM ws_bean WHERE pk=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteBeanPreparedStatement7, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   * 
   * <pre>SELECT content FROM ws_bean WHERE pk=${id}</pre>
   * 
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * </dl>
   * 
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>.
   *
   * @param id 	is binded to <code>${id}</code>
   * @return single value extracted by query.
   */
  @Override
  public byte[] getOne(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_ONE_SQL10;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      byte[] result=null;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return null; }
        result=_cursor.getBlob(0);
      }
      return result;
    }
  }

  /**
   * for param serializer1 serialization.
   *
   * @param value the value
   * @return the byte[]
   */
  private byte[] serializer1(Long value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        jacksonSerializer.writeNumberField("element", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing.
   *
   * @param input the input
   * @return the long
   */
  private Long parser1(byte[] input) {
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
      Long result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getLongValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (insertRawPreparedStatement0!=null) {
      insertRawPreparedStatement0.close();
      insertRawPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
    if (updateOnePreparedStatement2!=null) {
      updateOnePreparedStatement2.close();
      updateOnePreparedStatement2=null;
    }
    if (updateOnePreparedStatement3!=null) {
      updateOnePreparedStatement3.close();
      updateOnePreparedStatement3=null;
    }
    if (deleteOnePreparedStatement4!=null) {
      deleteOnePreparedStatement4.close();
      deleteOnePreparedStatement4=null;
    }
    if (deleteOnePreparedStatement5!=null) {
      deleteOnePreparedStatement5.close();
      deleteOnePreparedStatement5=null;
    }
    if (deleteOnePreparedStatement6!=null) {
      deleteOnePreparedStatement6.close();
      deleteOnePreparedStatement6=null;
    }
    if (deleteBeanPreparedStatement7!=null) {
      deleteBeanPreparedStatement7.close();
      deleteBeanPreparedStatement7=null;
    }
  }
}
