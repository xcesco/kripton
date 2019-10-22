package sqlite.quickstart.persistence;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sqlite.quickstart.model.Comment;

/**
 * <p>
 * DAO implementation for entity <code>Comment</code>, based on interface <code>CommentDao</code>
 * </p>
 *
 *  @see Comment
 *  @see CommentDao
 *  @see sqlite.quickstart.model.CommentTable
 */
public class CommentDaoImpl extends Dao implements CommentDao {
  private static SupportSQLiteStatement insertPreparedStatement0;

  /**
   * SQL definition for method selectByPostId
   */
  private static final String SELECT_BY_POST_ID_SQL5 = "SELECT id, body, email, name, post_id FROM comment WHERE post_id = ?";

  /**
   * SQL definition for method selectOneByPostId
   */
  private static final String SELECT_ONE_BY_POST_ID_SQL6 = "SELECT id, body, email, name, post_id FROM comment WHERE id = ?";

  public CommentDaoImpl(BindQuickStartDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO comment (id, body, email, name, post_id) VALUES (:bean.id, :bean.body, :bean.email, :bean.name, :bean.postId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>body</dt><dd>is mapped to <strong>:bean.body</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>:bean.email</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>post_id</dt><dd>is mapped to <strong>:bean.postId</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(Comment bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO comment (id, body, email, name, post_id) VALUES (?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("id", bean.id);
    _contentValues.put("body", bean.body);
    _contentValues.put("email", bean.email);
    _contentValues.put("name", bean.name);
    _contentValues.put("post_id", bean.postId);

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
      Logger.info("INSERT INTO comment (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, body, email, name, post_id FROM comment WHERE post_id = ${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Comment}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>post_id</dt><dd>is associated to bean's property <strong>postId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>postId</strong></dd>
   * </dl>
   *
   * @param postId
   * 	is binded to <code>:value</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Comment> selectByPostId(long postId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_POST_ID_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(postId));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Comment> resultList=new ArrayList<Comment>(_cursor.getCount());
      Comment resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("body");
        int index2=_cursor.getColumnIndex("email");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("post_id");

        do
         {
          resultBean=new Comment();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.body=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.email=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.postId=_cursor.getLong(index4); }

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
   * <pre>SELECT id, body, email, name, post_id FROM comment WHERE id = ${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Comment}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>post_id</dt><dd>is associated to bean's property <strong>postId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>postId</strong></dd>
   * </dl>
   *
   * @param postId
   * 	is binded to <code>:value</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Comment selectOneByPostId(long postId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_BY_POST_ID_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(postId));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Comment resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("body");
        int index2=_cursor.getColumnIndex("email");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("post_id");

        resultBean=new Comment();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.body=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.email=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }
        if (!_cursor.isNull(index4)) { resultBean.postId=_cursor.getLong(index4); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  public static void clearCompiledStatements() {
    try {
      if (insertPreparedStatement0!=null) {
        insertPreparedStatement0.close();
        insertPreparedStatement0=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
