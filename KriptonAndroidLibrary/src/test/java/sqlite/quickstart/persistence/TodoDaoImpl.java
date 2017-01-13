package sqlite.quickstart.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
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

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO todo (id, user_id, title, completed) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("user_id"))+"', '"+StringUtils.checkSize(contentValues.get("title"))+"', '"+StringUtils.checkSize(contentValues.get("completed"))+"')"));
    long result = database().insert("todo", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, user_id, title, completed FROM todo WHERE userId = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>completed</dt><dd>is associated to bean's property <strong>completed</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to ${value}
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Todo> selectByUserId(long userId) {
    // build where condition
    String[] args={String.valueOf(userId)};

    Logger.info(StringUtils.formatSQL("SELECT id, user_id, title, completed FROM todo WHERE user_id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, user_id, title, completed FROM todo WHERE user_id = ?", args);
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
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, user_id, title, completed FROM todo WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>completed</dt><dd>is associated to bean's property <strong>completed</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to ${value}
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Todo selectOneByUserId(long userId) {
    // build where condition
    String[] args={String.valueOf(userId)};

    Logger.info(StringUtils.formatSQL("SELECT id, user_id, title, completed FROM todo WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, user_id, title, completed FROM todo WHERE id = ?", args);
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
    cursor.close();

    return resultBean;
  }
}
