package sqlite.quickstart.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sqlite.quickstart.model.Todo;

/**
 * <p>
 * DAO implementation for entity <code>Todo</code>, based on interface <code>TodoDao</code>
 * </p>
 *
 *  @see Todo
 *  @see TodoDao
 *  @see sqlite.quickstart.model.TodoTable
 */
public class TodoDaoImpl extends AbstractDao implements TodoDao {
  public TodoDaoImpl(BindQuickStartDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO todo (id, user_id, title, completed) VALUES (${bean.id}, ${bean.userId}, ${bean.title}, ${bean.completed})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>user_id</dt><dd>is mapped to <strong>${bean.userId}</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>${bean.title}</strong></dd>
   * 	<dt>completed</dt><dd>is mapped to <strong>${bean.completed}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insert(Todo bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", bean.id);

    contentValues.put("user_id", bean.userId);

    if (bean.title!=null) {
      contentValues.put("title", bean.title);
    } else {
      contentValues.putNull("title");
    }

    contentValues.put("completed", bean.completed);

    //StringUtils and SqlUtils will be used to format SQL
    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO todo (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("todo", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, user_id, title, completed FROM todo WHERE user_id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>completed</dt><dd>is associated to bean's property <strong>completed</strong></dd>
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
  public List<Todo> selectByUserId(long userId) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT id, user_id, title, completed FROM todo ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE userId = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(userId));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Todo> resultList=new LinkedList<Todo>();
      Todo resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("user_id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("completed");

        do
         {
          resultBean=new Todo();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.userId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.title=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.completed=cursor.getInt(index3)==0?false:true; }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, user_id, title, completed FROM todo WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>completed</dt><dd>is associated to bean's property <strong>completed</strong></dd>
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
  public Todo selectOneByUserId(long userId) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT id, user_id, title, completed FROM todo ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(userId));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Todo resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("user_id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("completed");

        resultBean=new Todo();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.userId=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.title=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.completed=cursor.getInt(index3)==0?false:true; }

      }
      return resultBean;
    }
  }
}
