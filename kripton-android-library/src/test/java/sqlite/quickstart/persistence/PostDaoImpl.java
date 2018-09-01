package sqlite.quickstart.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.quickstart.model.Post;

/**
 * <p>
 * DAO implementation for entity <code>Post</code>, based on interface <code>PostDao</code>
 * </p>
 *
 *  @see Post
 *  @see PostDao
 *  @see sqlite.quickstart.model.PostTable
 */
public class PostDaoImpl extends Dao implements PostDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_USER_ID_SQL3 = "SELECT id, body, title, user_id FROM post WHERE user_id = ?";

  private static final String SELECT_ONE_BY_USER_ID_SQL4 = "SELECT id, body, title, user_id FROM post WHERE id = ?";

  public PostDaoImpl(BindQuickStartDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO post (id, body, title, user_id) VALUES (:bean.id, :bean.body, :bean.title, :bean.userId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>body</dt><dd>is mapped to <strong>:bean.body</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>:bean.title</strong></dd>
   * 	<dt>user_id</dt><dd>is mapped to <strong>:bean.userId</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(Post bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO post (id, body, title, user_id) VALUES (?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("id", bean.id);
    _contentValues.put("body", bean.body);
    _contentValues.put("title", bean.title);
    _contentValues.put("user_id", bean.userId);

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
      Logger.info("INSERT INTO post (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, body, title, user_id FROM post WHERE user_id = ${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Post}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:value</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Post> selectByUserId(long userId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_USER_ID_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(userId));
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Post> resultList=new ArrayList<Post>(_cursor.getCount());
      Post resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("body");
        int index2=_cursor.getColumnIndex("title");
        int index3=_cursor.getColumnIndex("user_id");

        do
         {
          resultBean=new Post();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.body=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.title=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.userId=_cursor.getLong(index3); }

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
   * <pre>SELECT id, body, title, user_id FROM post WHERE id = ${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Post}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>:value</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Post selectOneByUserId(long userId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_BY_USER_ID_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(userId));
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Post resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("body");
        int index2=_cursor.getColumnIndex("title");
        int index3=_cursor.getColumnIndex("user_id");

        resultBean=new Post();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.body=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.title=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.userId=_cursor.getLong(index3); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
