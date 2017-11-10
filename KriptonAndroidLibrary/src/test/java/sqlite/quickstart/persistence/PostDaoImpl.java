package sqlite.quickstart.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.LinkedList;
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
public class PostDaoImpl extends AbstractDao implements PostDao {
  private SQLiteStatement insertPreparedStatement0;

  public PostDaoImpl(BindQuickStartDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO post (user_id, id, title, body) VALUES (${bean.userId}, ${bean.id}, ${bean.title}, ${bean.body})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>user_id</dt><dd>is mapped to <strong>${bean.userId}</strong></dd>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>${bean.title}</strong></dd>
   * 	<dt>body</dt><dd>is mapped to <strong>${bean.body}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(Post bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("user_id", bean.userId);
    _contentValues.put("id", bean.id);
    if (bean.title!=null) {
      _contentValues.put("title", bean.title);
    } else {
      _contentValues.putNull("title");
    }
    if (bean.body!=null) {
      _contentValues.put("body", bean.body);
    } else {
      _contentValues.putNull("body");
    }

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

    // insert operation
    if (insertPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO post (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement0, _contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT user_id, id, title, body FROM post WHERE user_id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>${value}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Post> selectByUserId(long userId) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT user_id, id, title, body FROM post");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE user_id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(userId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Post> resultList=new LinkedList<Post>();
      Post resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("user_id");
        int index1=cursor.getColumnIndex("id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("body");

        do
         {
          resultBean=new Post();

          if (!cursor.isNull(index0)) { resultBean.userId=cursor.getLong(index0); }
          resultBean.id=cursor.getLong(index1);
          if (!cursor.isNull(index2)) { resultBean.title=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.body=cursor.getString(index3); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT user_id, id, title, body FROM post WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>${value}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Post selectOneByUserId(long userId) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT user_id, id, title, body FROM post");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(userId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Post resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("user_id");
        int index1=cursor.getColumnIndex("id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("body");

        resultBean=new Post();

        if (!cursor.isNull(index0)) { resultBean.userId=cursor.getLong(index0); }
        resultBean.id=cursor.getLong(index1);
        if (!cursor.isNull(index2)) { resultBean.title=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.body=cursor.getString(index3); }

      }
      return resultBean;
    }
  }

  public void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
