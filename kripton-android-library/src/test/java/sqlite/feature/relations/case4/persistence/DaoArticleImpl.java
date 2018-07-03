package sqlite.feature.relations.case4.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.common.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.relations.case4.model.Article;

/**
 * <p>
 * DAO implementation for entity <code>Article</code>, based on interface <code>DaoArticle</code>
 * </p>
 *
 *  @see Article
 *  @see DaoArticle
 *  @see sqlite.feature.relations.case4.model.ArticleTable
 */
public class DaoArticleImpl extends Dao implements DaoArticle {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_CHANNEL_SQL1 = "SELECT id, author, channel_id, comments, description, guid, link, title FROM article WHERE channel_id=?";

  private static final String SELECT_ALL_SQL2 = "SELECT id, author, channel_id, comments, description, guid, link, title FROM article";

  public DaoArticleImpl(BindRssDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO article (author, channel_id, comments, description, guid, link, title) VALUES (:author, :channelId, :comments, :description, :guid, :link, :title)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>author</dt><dd>is mapped to <strong>:bean.author</strong></dd>
   * 	<dt>channel_id</dt><dd>is mapped to <strong>:bean.channelId</strong></dd>
   * 	<dt>comments</dt><dd>is mapped to <strong>:bean.comments</strong></dd>
   * 	<dt>description</dt><dd>is mapped to <strong>:bean.description</strong></dd>
   * 	<dt>guid</dt><dd>is mapped to <strong>:bean.guid</strong></dd>
   * 	<dt>link</dt><dd>is mapped to <strong>:bean.link</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>:bean.title</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(Article bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO article (author, channel_id, comments, description, guid, link, title) VALUES (?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("author", bean.author);
    _contentValues.put("channel_id", bean.channelId);
    _contentValues.put("comments", UrlUtils.write(bean.comments));
    _contentValues.put("description", bean.description);
    _contentValues.put("guid", bean.guid);
    _contentValues.put("link", UrlUtils.write(bean.link));
    _contentValues.put("title", bean.title);

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
      Logger.info("INSERT OR REPLACE INTO article (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    return result!=-1;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, author, channel_id, comments, description, guid, link, title FROM article WHERE channel_id=${channelId}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>author</dt><dd>is associated to bean's property <strong>author</strong></dd>
   * 	<dt>channel_id</dt><dd>is associated to bean's property <strong>channelId</strong></dd>
   * 	<dt>comments</dt><dd>is associated to bean's property <strong>comments</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>guid</dt><dd>is associated to bean's property <strong>guid</strong></dd>
   * 	<dt>link</dt><dd>is associated to bean's property <strong>link</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:channelId</dt><dd>is binded to method's parameter <strong>channelId</strong></dd>
   * </dl>
   *
   * @param channelId
   * 	is binded to <code>:channelId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Article> selectByChannel(long channelId) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CHANNEL_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(channelId));
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

      ArrayList<Article> resultList=new ArrayList<Article>(_cursor.getCount());
      Article resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("author");
        int index2=_cursor.getColumnIndex("channel_id");
        int index3=_cursor.getColumnIndex("comments");
        int index4=_cursor.getColumnIndex("description");
        int index5=_cursor.getColumnIndex("guid");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Article();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.author=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.channelId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.comments=UrlUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.description=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { resultBean.guid=_cursor.getString(index5); }
          if (!_cursor.isNull(index6)) { resultBean.link=UrlUtils.read(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.title=_cursor.getString(index7); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, author, channel_id, comments, description, guid, link, title FROM article</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>author</dt><dd>is associated to bean's property <strong>author</strong></dd>
   * 	<dt>channel_id</dt><dd>is associated to bean's property <strong>channelId</strong></dd>
   * 	<dt>comments</dt><dd>is associated to bean's property <strong>comments</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>guid</dt><dd>is associated to bean's property <strong>guid</strong></dd>
   * 	<dt>link</dt><dd>is associated to bean's property <strong>link</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Article> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL2;
    // add where arguments
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

      ArrayList<Article> resultList=new ArrayList<Article>(_cursor.getCount());
      Article resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("author");
        int index2=_cursor.getColumnIndex("channel_id");
        int index3=_cursor.getColumnIndex("comments");
        int index4=_cursor.getColumnIndex("description");
        int index5=_cursor.getColumnIndex("guid");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Article();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.author=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.channelId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.comments=UrlUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.description=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { resultBean.guid=_cursor.getString(index5); }
          if (!_cursor.isNull(index6)) { resultBean.link=UrlUtils.read(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.title=_cursor.getString(index7); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
