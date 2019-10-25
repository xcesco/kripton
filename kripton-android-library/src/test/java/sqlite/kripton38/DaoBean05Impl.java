package sqlite.kripton38;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.EnumUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class DaoBean05Impl extends Dao implements DaoBean05 {
  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL1 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=?";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL2 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=? and text=?";

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL3 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=?";

  /**
   * SQL definition for method selectPK
   */
  private static final String SELECT_P_K_SQL4 = "SELECT pk FROM ws_bean WHERE text = ?";

  /**
   * SQL definition for method selectCount
   */
  private static final String SELECT_COUNT_SQL5 = "SELECT count(*) FROM ws_bean WHERE text = ?";

  /**
   * SQL definition for method selectCursorListener
   */
  private static final String SELECT_CURSOR_LISTENER_SQL6 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=?";

  /**
   * SQL definition for method selectBeanListener
   */
  private static final String SELECT_BEAN_LISTENER_SQL7 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=?";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL8 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=?";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL9 = "SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=?";

  private static SupportSQLiteStatement insertRawPreparedStatement0;

  private static SupportSQLiteStatement insertPreparedStatement1;

  private static SupportSQLiteStatement updateOnePreparedStatement2;

  private static SupportSQLiteStatement updateOnePreparedStatement3;

  private static SupportSQLiteStatement deleteOnePreparedStatement4;

  private static SupportSQLiteStatement deleteOnePreparedStatement5;

  private static SupportSQLiteStatement deleteOnePreparedStatement6;

  private static SupportSQLiteStatement deleteBeanPreparedStatement7;

  /**
   * SQL definition for method getOne
   */
  private static final String GET_ONE_SQL10 = "SELECT content FROM ws_bean WHERE pk=?";

  public DaoBean05Impl(BindDummy05DaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean05 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_type");
        int index2=_cursor.getColumnIndex("content");
        int index3=_cursor.getColumnIndex("creation_time");
        int index4=_cursor.getColumnIndex("number");
        int index5=_cursor.getColumnIndex("text");

        resultBean=new Bean05();

        resultBean.setPk(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index1))); }
        if (!_cursor.isNull(index2)) { resultBean.setContent(_cursor.getBlob(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index3))); }
        if (!_cursor.isNull(index4)) { resultBean.setNumber(_cursor.getLong(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.setText(_cursor.getString(index5)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${bean.pk} and text=${bean.text}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.pk</dt><dd>is binded to method's parameter <strong>bean.pk</strong></dd>
   * 	<dt>:bean.text</dt><dd>is binded to method's parameter <strong>bean.text</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean05 selectOne(Bean05 bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getPk()));
    _contentValues.addWhereArgs(bean.getText());
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean05 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_type");
        int index2=_cursor.getColumnIndex("content");
        int index3=_cursor.getColumnIndex("creation_time");
        int index4=_cursor.getColumnIndex("number");
        int index5=_cursor.getColumnIndex("text");

        resultBean=new Bean05();

        resultBean.setPk(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index1))); }
        if (!_cursor.isNull(index2)) { resultBean.setContent(_cursor.getBlob(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index3))); }
        if (!_cursor.isNull(index4)) { resultBean.setNumber(_cursor.getLong(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.setText(_cursor.getString(index5)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean05> selectAll(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean05> resultList=new ArrayList<Bean05>(_cursor.getCount());
      Bean05 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_type");
        int index2=_cursor.getColumnIndex("content");
        int index3=_cursor.getColumnIndex("creation_time");
        int index4=_cursor.getColumnIndex("number");
        int index5=_cursor.getColumnIndex("text");

        do
         {
          resultBean=new Bean05();

          resultBean.setPk(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index1))); }
          if (!_cursor.isNull(index2)) { resultBean.setContent(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index3))); }
          if (!_cursor.isNull(index4)) { resultBean.setNumber(_cursor.getLong(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setText(_cursor.getString(index5)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk FROM ws_bean WHERE text = ${text}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:text</dt><dd>is binded to method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * @param text
   * 	is binded to <code>:text</code>
   * @return collection of single value extracted by query.
   */
  @Override
  public List<Long> selectPK(String text) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_P_K_SQL4;
    // add where arguments
    _contentValues.addWhereArgs((text==null?"":text));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarListHelper - BEGIN

      ArrayList<Long> resultList=new ArrayList<Long>(_cursor.getCount());


      if (_cursor.moveToFirst()) {

        do
         {
          if (!_cursor.isNull(0)) {
            resultList.add(_cursor.getLong(0));
          } else {
            resultList.add(null);
          }
        } while (_cursor.moveToNext());
      }
      return resultList;
    }
    // Specialized part - SelectScalarListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT count(*) FROM ws_bean WHERE text = ${text}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:text</dt><dd>is binded to method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * @param text
   * 	is binded to <code>:text</code>
   * @return single value extracted by query.
   */
  @Override
  public Long selectCount(String text) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_COUNT_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((text==null?"":text));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarHelper - BEGIN
      Long result=null;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return null; }
        result=_cursor.getLong(0);
      }
      return result;
    }
    // Specialized part - SelectScalarHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectCursorListener(Long id, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_CURSOR_LISTENER_SQL6;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectRawListenerHelper - BEGIN

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectRawListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the Bean05 listener
   */
  @Override
  public void selectBeanListener(Long id, OnReadBeanListener<Bean05> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BEAN_LISTENER_SQL7;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Bean05 resultBean=new Bean05();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_type");
        int index2=_cursor.getColumnIndex("content");
        int index3=_cursor.getColumnIndex("creation_time");
        int index4=_cursor.getColumnIndex("number");
        int index5=_cursor.getColumnIndex("text");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // pk does not need reset (it will be taken from db)
          resultBean.setBeanType(null);
          resultBean.setContent(null);
          resultBean.setCreationTime(null);
          resultBean.setNumber(0L);
          resultBean.setText(null);

          // generate mapping
          resultBean.setPk(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index1))); }
          if (!_cursor.isNull(index2)) { resultBean.setContent(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index3))); }
          if (!_cursor.isNull(index4)) { resultBean.setNumber(_cursor.getLong(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setText(_cursor.getString(index5)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(Long id, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL8;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectRawListenerHelper - BEGIN

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectRawListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_type, content, creation_time, number, text FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>pk</strong></dd>
   * 	<dt>bean_type</dt><dd>is associated to bean's property <strong>beanType</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>creation_time</dt><dd>is associated to bean's property <strong>creationTime</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the Bean05 listener
   */
  @Override
  public void selectOne(long id, OnReadBeanListener<Bean05> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL9;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Bean05 resultBean=new Bean05();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_type");
        int index2=_cursor.getColumnIndex("content");
        int index3=_cursor.getColumnIndex("creation_time");
        int index4=_cursor.getColumnIndex("number");
        int index5=_cursor.getColumnIndex("text");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // pk does not need reset (it will be taken from db)
          resultBean.setBeanType(null);
          resultBean.setContent(null);
          resultBean.setCreationTime(null);
          resultBean.setNumber(0L);
          resultBean.setText(null);

          // generate mapping
          resultBean.setPk(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setBeanType(BeanType.valueOf(_cursor.getString(index1))); }
          if (!_cursor.isNull(index2)) { resultBean.setContent(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setCreationTime(DateUtils.read(_cursor.getString(index3))); }
          if (!_cursor.isNull(index4)) { resultBean.setNumber(_cursor.getLong(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setText(_cursor.getString(index5)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO ws_bean (text, content, creation_time) VALUES (:text, :content, :creationTime)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>:text</strong> and method's parameter <strong>text</strong></dd>
   * 	<dt>content</dt><dd>is binded to query's parameter <strong>:content</strong> and method's parameter <strong>content</strong></dd>
   * 	<dt>creationTime</dt><dd>is binded to query's parameter <strong>:creationTime</strong> and method's parameter <strong>creationTime</strong></dd>
   * </dl>
   *
   * @param text
   * 	is binded to column value <strong>text</strong>
   * @param content
   * 	is binded to column value <strong>content</strong>
   * @param creationTime
   * 	is binded to column value <strong>creation_time</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertRaw(String text, byte[] content, Date creationTime) {
    // Specialized Insert - InsertType - BEGIN
    if (insertRawPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO ws_bean (text, content, creation_time) VALUES (?, ?, ?)";
      insertRawPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
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
    long result = KriptonDatabaseHelper.insert(insertRawPreparedStatement0, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO ws_bean (bean_type, content, creation_time, number, text) VALUES (:bean.beanType, :bean.content, :bean.creationTime, :bean.number, :bean.text)</pre>
   *
   * <p><code>bean.pk</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>bean_type</dt><dd>is mapped to <strong>:bean.beanType</strong></dd>
   * 	<dt>content</dt><dd>is mapped to <strong>:bean.content</strong></dd>
   * 	<dt>creation_time</dt><dd>is mapped to <strong>:bean.creationTime</strong></dd>
   * 	<dt>number</dt><dd>is mapped to <strong>:bean.number</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>:bean.text</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(Bean05 bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO ws_bean (bean_type, content, creation_time, number, text) VALUES (?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put("bean_type", EnumUtils.write(bean.getBeanType()));
    _contentValues.put("content", bean.getContent());
    _contentValues.put("creation_time", DateUtils.write(bean.getCreationTime()));
    _contentValues.put("number", bean.getNumber());
    _contentValues.put("text", bean.getText());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement1, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.setPk(result);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE ws_bean SET bean_type=:beanType, content=:content, creation_time=:creationTime, number=:number, text=:text WHERE pk=${bean.pk} and text=${bean.text} and creation_time=${bean.creationTime}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>bean_type</dt><dd>is mapped to <strong>:bean.beanType</strong></dd>
   * 	<dt>content</dt><dd>is mapped to <strong>:bean.content</strong></dd>
   * 	<dt>creation_time</dt><dd>is mapped to <strong>:bean.creationTime</strong></dd>
   * 	<dt>number</dt><dd>is mapped to <strong>:bean.number</strong></dd>
   * 	<dt>text</dt><dd>is mapped to <strong>:bean.text</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.pk</dt><dd>is mapped to method's parameter <strong>bean.pk</strong></dd>
   * 	<dt>:bean.text</dt><dd>is mapped to method's parameter <strong>bean.text</strong></dd>
   * 	<dt>:bean.creationTime</dt><dd>is mapped to method's parameter <strong>bean.creationTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean05 bean) {
    if (updateOnePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE ws_bean SET bean_type=?, content=?, creation_time=?, number=?, text=? WHERE pk=? and text=? and creation_time=?";
      updateOnePreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement2);
    _contentValues.put("bean_type", EnumUtils.write(bean.getBeanType()));
    _contentValues.put("content", bean.getContent());
    _contentValues.put("creation_time", DateUtils.write(bean.getCreationTime()));
    _contentValues.put("number", bean.getNumber());
    _contentValues.put("text", bean.getText());

    _contentValues.addWhereArgs(String.valueOf(bean.getPk()));
    _contentValues.addWhereArgs((bean.getText()==null?"":bean.getText()));
    _contentValues.addWhereArgs((bean.getCreationTime()==null?"":DateUtils.write(bean.getCreationTime())));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE ws_bean SET bean_type=:bean_type, content=:content, creation_time=:creation_time, number=:number, text=:text WHERE pk=? and text=? and creation_time=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updateOnePreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE ws_bean SET content=:content, text=:text WHERE pk=:uid and creation_time=:valido and creation_time=:validoIn</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>content</li>
   * 	<li>text</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:uid</dt><dd>is mapped to method's parameter <strong>uid</strong></dd>
   * 	<dt>:valido</dt><dd>is mapped to method's parameter <strong>valido</strong></dd>
   * 	<dt>:validoIn</dt><dd>is mapped to method's parameter <strong>validoIn</strong></dd>
   * </dl>
   *
   * @param content
   * 	is used as updated field <strong>content</strong>
   * @param text
   * 	is used as updated field <strong>text</strong>
   * @param uid
   * 	is used as where parameter <strong>:uid</strong>
   * @param validoIn
   * 	is used as where parameter <strong>:validoIn</strong>
   * @param valido
   * 	is used as where parameter <strong>:valido</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(byte[] content, String text, long uid, Date validoIn, Date valido) {
    if (updateOnePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE ws_bean SET content=?, text=? WHERE pk=? and creation_time=? and creation_time=?";
      updateOnePreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(updateOnePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=${bean.pk} and text=${bean.text} and creation_time=${bean.creationTime}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.pk</dt><dd>is mapped to method's parameter <strong>bean.pk</strong></dd>
   * 	<dt>:bean.text</dt><dd>is mapped to method's parameter <strong>bean.text</strong></dd>
   * 	<dt>:bean.creationTime</dt><dd>is mapped to method's parameter <strong>bean.creationTime</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(Bean05 bean) {
    if (deleteOnePreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=? and text=? and creation_time=?";
      deleteOnePreparedStatement4 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteOnePreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=:uid and creation_time=:valido and creation_time=:validoIn</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:uid</dt><dd>is mapped to method's parameter <strong>uid</strong></dd>
   * 	<dt>:valido</dt><dd>is mapped to method's parameter <strong>valido</strong></dd>
   * 	<dt>:validoIn</dt><dd>is mapped to method's parameter <strong>validoIn</strong></dd>
   * </dl>
   *
   * @param uid
   * 	is used as where parameter <strong>:uid</strong>
   * @param validoIn
   * 	is used as where parameter <strong>:validoIn</strong>
   * @param valido
   * 	is used as where parameter <strong>:valido</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long uid, Date validoIn, Date valido) {
    if (deleteOnePreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=? and creation_time=? and creation_time=?";
      deleteOnePreparedStatement5 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteOnePreparedStatement5, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=:id</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    if (deleteOnePreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=?";
      deleteOnePreparedStatement6 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteOnePreparedStatement6, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM ws_bean WHERE pk=${va.pk}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:va.pk</dt><dd>is mapped to method's parameter <strong>va.pk</strong></dd>
   * </dl>
   *
   * @param va
   * 	is used as <code>:va</code>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteBean(Bean05 va) {
    if (deleteBeanPreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM ws_bean WHERE pk=?";
      deleteBeanPreparedStatement7 = KriptonDatabaseHelper.compile(_context, _sql);
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
    int result = KriptonDatabaseHelper.updateDelete(deleteBeanPreparedStatement7, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT content FROM ws_bean WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean05}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return single value extracted by query.
   */
  @Override
  public byte[] getOne(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_ONE_SQL10;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectScalarHelper - BEGIN
      byte[] result=null;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return null; }
        result=_cursor.getBlob(0);
      }
      return result;
    }
    // Specialized part - SelectScalarHelper - END
  }

  public static void clearCompiledStatements() {
    try {
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
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
