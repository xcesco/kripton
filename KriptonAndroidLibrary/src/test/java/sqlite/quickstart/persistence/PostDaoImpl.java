package sqlite.quickstart.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
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
   *
   */
  @Override
  public void insert(Post bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("user_id", bean.userId);

    contentValues.put("id", bean.id);

    if (bean.title!=null) {
      contentValues.put("title", bean.title);
    } else {
      contentValues.putNull("title");
    }

    if (bean.body!=null) {
      contentValues.put("body", bean.body);
    } else {
      contentValues.putNull("body");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO post (user_id, id, title, body) VALUES ('"+StringUtils.checkSize(contentValues.get("user_id"))+"', '"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("title"))+"', '"+StringUtils.checkSize(contentValues.get("body"))+"')"));
    long result = database().insert("post", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT user_id, id, title, body FROM post WHERE userId = ${value}</pre>
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
    // build where condition
    String[] _args={String.valueOf(userId)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT user_id, id, title, body FROM post WHERE user_id = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT user_id, id, title, body FROM post WHERE user_id = ?", _args)) {
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
          if (!cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1); }
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
    // build where condition
    String[] _args={String.valueOf(userId)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT user_id, id, title, body FROM post WHERE id = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT user_id, id, title, body FROM post WHERE id = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Post resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("user_id");
        int index1=cursor.getColumnIndex("id");
        int index2=cursor.getColumnIndex("title");
        int index3=cursor.getColumnIndex("body");

        resultBean=new Post();

        if (!cursor.isNull(index0)) { resultBean.userId=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.title=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.body=cursor.getString(index3); }

      }
      return resultBean;
    }
  }
}
