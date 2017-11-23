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
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_USER_ID_SQL7 = "SELECT id, user_id, title, completed FROM todo WHERE user_id = ?";

  private static final String SELECT_ONE_BY_USER_ID_SQL8 = "SELECT id, user_id, title, completed FROM todo WHERE id = ?";

  public TodoDaoImpl(SQLContext context) {
    super(context);
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
   */
  @Override
  public void insert(Todo bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for insert
      String _sql="INSERT INTO todo (id, user_id, title, completed) VALUES (?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("id", bean.id);
    _contentValues.put("user_id", bean.userId);
    if (bean.title!=null) {
      _contentValues.put("title", bean.title);
    } else {
      _contentValues.putNull("title");
    }
    _contentValues.put("completed", bean.completed);

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
      Logger.info("INSERT INTO todo (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertPreparedStatement0, _contentValues);
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_USER_ID_SQL7;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(userId));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Todo> resultList=new ArrayList<Todo>(cursor.getCount());
      Todo resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("user_id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("completed");

        do
         {
          resultBean=new Todo();

          resultBean.id=cursor.getLong(index0);
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_BY_USER_ID_SQL8;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(userId));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Todo resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("user_id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("completed");

        resultBean=new Todo();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.userId=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.title=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.completed=cursor.getInt(index3)==0?false:true; }

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
