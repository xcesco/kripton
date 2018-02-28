package sqlite.quickstart.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
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
public class CommentDaoImpl extends AbstractDao implements CommentDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_POST_ID_SQL5 = "SELECT post_id, id, name, email, body FROM comment WHERE post_id = ?";

  private static final String SELECT_ONE_BY_POST_ID_SQL6 = "SELECT post_id, id, name, email, body FROM comment WHERE id = ?";

  public CommentDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO comment (post_id, id, name, email, body) VALUES (${bean.postId}, ${bean.id}, ${bean.name}, ${bean.email}, ${bean.body})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>post_id</dt><dd>is mapped to <strong>${bean.postId}</strong></dd>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>body</dt><dd>is mapped to <strong>${bean.body}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(Comment bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO comment (post_id, id, name, email, body) VALUES (?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("post_id", bean.postId);
    _contentValues.put("id", bean.id);
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    if (bean.email!=null) {
      _contentValues.put("email", bean.email);
    } else {
      _contentValues.putNull("email");
    }
    if (bean.body!=null) {
      _contentValues.put("body", bean.body);
    } else {
      _contentValues.putNull("body");
    }

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT post_id, id, name, email, body FROM comment WHERE post_id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>post_id</dt><dd>is associated to bean's property <strong>postId</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>postId</strong></dd>
   * </dl>
   *
   * @param postId
   * 	is binded to <code>${value}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Comment> selectByPostId(long postId) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_POST_ID_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(postId));
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

      ArrayList<Comment> resultList=new ArrayList<Comment>(_cursor.getCount());
      Comment resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("post_id");
        int index1=_cursor.getColumnIndex("id");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("email");
        int index4=_cursor.getColumnIndex("body");

        do
         {
          resultBean=new Comment();

          if (!_cursor.isNull(index0)) { resultBean.postId=_cursor.getLong(index0); }
          resultBean.id=_cursor.getLong(index1);
          if (!_cursor.isNull(index2)) { resultBean.name=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.email=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.body=_cursor.getString(index4); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT post_id, id, name, email, body FROM comment WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>post_id</dt><dd>is associated to bean's property <strong>postId</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>postId</strong></dd>
   * </dl>
   *
   * @param postId
   * 	is binded to <code>${value}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Comment selectOneByPostId(long postId) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_BY_POST_ID_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(postId));
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

      Comment resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("post_id");
        int index1=_cursor.getColumnIndex("id");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("email");
        int index4=_cursor.getColumnIndex("body");

        resultBean=new Comment();

        if (!_cursor.isNull(index0)) { resultBean.postId=_cursor.getLong(index0); }
        resultBean.id=_cursor.getLong(index1);
        if (!_cursor.isNull(index2)) { resultBean.name=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.email=_cursor.getString(index3); }
        if (!_cursor.isNull(index4)) { resultBean.body=_cursor.getString(index4); }

      }
      return resultBean;
    }
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
