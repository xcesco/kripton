package sqlite.feature.kotlin.immutable;

import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.common.UrlUtils;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * DAO implementation for entity <code>Article</code>, based on interface <code>DaoArticle</code>
 * </p>
 *
 *  @see Article
 *  @see DaoArticle
 *  @see ArticleTable
 */
public class DaoArticleImpl extends Dao implements DaoArticle {
  private static SQLiteStatement insertPreparedStatement0;

  private static SQLiteStatement updatePreparedStatement1;

  /**
   * SQL definition for method selectByChannelUd
   */
  private static final String SELECT_BY_CHANNEL_UD_SQL2 = "SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles WHERE channel_id=?";

  /**
   * SQL definition for method selectByGuid
   */
  private static final String SELECT_BY_GUID_SQL3 = "SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles WHERE channel_id=? AND guid=?";

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  public DaoArticleImpl(BindRssDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO articles (author, channel_id, comments, description, guid, link, read, thumbnail, title) VALUES (:author, :channelId, :comments, :description, :guid, :link, :read, :thumbnail, :title)</pre>
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
   * 	<dt>read</dt><dd>is mapped to <strong>:bean.read</strong></dd>
   * 	<dt>thumbnail</dt><dd>is mapped to <strong>:bean.thumbnail</strong></dd>
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
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO articles (author, channel_id, comments, description, guid, link, read, thumbnail, title) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("author", bean.getAuthor());
    _contentValues.put("channel_id", bean.getChannelId());
    _contentValues.put("comments", UrlUtils.write(bean.getComments()));
    _contentValues.put("description", bean.getDescription());
    _contentValues.put("guid", bean.getGuid());
    _contentValues.put("link", UrlUtils.write(bean.getLink()));
    _contentValues.put("read", bean.isRead());
    _contentValues.put("thumbnail", ArticleTable.serializeThumbnail(bean.getThumbnail()));
    _contentValues.put("title", bean.getTitle());

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
      Logger.info("INSERT OR IGNORE INTO articles (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // support for livedata
    registryEvent(result>0?1:0);

    return result!=-1;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE articles SET channel_id=:channelId, read=:read WHERE id=:id</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>channel_id</li>
   * 	<li>read</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   * @param channelId
   * 	is used as updated field <strong>channelId</strong>
   * @param read
   * 	is used as updated field <strong>read</strong>
   */
  @Override
  public void update(long id, long channelId, boolean read) {
    if (updatePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE articles SET channel_id=?, read=? WHERE id=?";
      updatePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    _contentValues.put("channel_id", channelId);
    _contentValues.put("read", read);

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE articles SET channel_id=:channel_id, read=:read WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement1, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles WHERE #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Article}
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
   * 	<dt>read</dt><dd>is associated to bean's property <strong>read</strong></dd>
   * 	<dt>thumbnail</dt><dd>is associated to bean's property <strong>thumbnail</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  protected List<Article> selectByChannelForLiveData(String where) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=StringUtils.ifNotEmptyAppend(_sqlDynamicWhere, " WHERE ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    String _sql=_sqlBuilder.toString();
    // add where arguments
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

      ArrayList<Article> resultList=new ArrayList<Article>(_cursor.getCount());
      Article resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __title=null;
      String __description=null;
      URL __link=null;
      String __author=null;
      String __guid=null;
      URL __comments=null;
      long __channelId=0;
      Thumbnail __thumbnail=null;
      boolean __read=false;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("author");
        int index2=_cursor.getColumnIndex("channel_id");
        int index3=_cursor.getColumnIndex("comments");
        int index4=_cursor.getColumnIndex("description");
        int index5=_cursor.getColumnIndex("guid");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("read");
        int index8=_cursor.getColumnIndex("thumbnail");
        int index9=_cursor.getColumnIndex("title");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __title=null;
          __description=null;
          __link=null;
          __author=null;
          __guid=null;
          __comments=null;
          __channelId=0;
          __thumbnail=null;
          __read=false;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __author=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __channelId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { __comments=UrlUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { __description=_cursor.getString(index4); }
          __guid=_cursor.getString(index5);
          if (!_cursor.isNull(index6)) { __link=UrlUtils.read(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { __read=_cursor.getInt(index7)==0?false:true; }
          if (!_cursor.isNull(index8)) { __thumbnail=ArticleTable.parseThumbnail(_cursor.getBlob(index8)); }
          if (!_cursor.isNull(index9)) { __title=_cursor.getString(index9); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles WHERE #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Article}
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
   * 	<dt>read</dt><dd>is associated to bean's property <strong>read</strong></dd>
   * 	<dt>thumbnail</dt><dd>is associated to bean's property <strong>thumbnail</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public MutableLiveData<List<Article>> selectByChannel(final String where) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonLiveDataHandlerImpl<List<Article>> builder=new KriptonLiveDataHandlerImpl<List<Article>>() {
      @Override
      protected List<Article> compute() {
        return BindRssDataSource.getInstance().executeBatch(new BindRssDataSource.Batch<List<Article>>() {
          @Override
          public List<Article> onExecute(BindRssDaoFactory daoFactory) {
            return daoFactory.getDaoArticle().selectByChannelForLiveData(where);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles WHERE channel_id=${channelId}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Article}
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
   * 	<dt>read</dt><dd>is associated to bean's property <strong>read</strong></dd>
   * 	<dt>thumbnail</dt><dd>is associated to bean's property <strong>thumbnail</strong></dd>
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
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<Article> selectByChannelUd(long channelId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CHANNEL_UD_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(channelId));
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

      ArrayList<Article> resultList=new ArrayList<Article>(_cursor.getCount());
      Article resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __title=null;
      String __description=null;
      URL __link=null;
      String __author=null;
      String __guid=null;
      URL __comments=null;
      long __channelId=0;
      Thumbnail __thumbnail=null;
      boolean __read=false;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("author");
        int index2=_cursor.getColumnIndex("channel_id");
        int index3=_cursor.getColumnIndex("comments");
        int index4=_cursor.getColumnIndex("description");
        int index5=_cursor.getColumnIndex("guid");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("read");
        int index8=_cursor.getColumnIndex("thumbnail");
        int index9=_cursor.getColumnIndex("title");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __title=null;
          __description=null;
          __link=null;
          __author=null;
          __guid=null;
          __comments=null;
          __channelId=0;
          __thumbnail=null;
          __read=false;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __author=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __channelId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { __comments=UrlUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { __description=_cursor.getString(index4); }
          __guid=_cursor.getString(index5);
          if (!_cursor.isNull(index6)) { __link=UrlUtils.read(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { __read=_cursor.getInt(index7)==0?false:true; }
          if (!_cursor.isNull(index8)) { __thumbnail=ArticleTable.parseThumbnail(_cursor.getBlob(index8)); }
          if (!_cursor.isNull(index9)) { __title=_cursor.getString(index9); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, author, channel_id, comments, description, guid, link, read, thumbnail, title FROM articles WHERE channel_id=${channelId} AND guid=${guid}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Article}
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
   * 	<dt>read</dt><dd>is associated to bean's property <strong>read</strong></dd>
   * 	<dt>thumbnail</dt><dd>is associated to bean's property <strong>thumbnail</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:channelId</dt><dd>is binded to method's parameter <strong>channelId</strong></dd>
   * 	<dt>:guid</dt><dd>is binded to method's parameter <strong>guid</strong></dd>
   * </dl>
   *
   * @param channelId
   * 	is binded to <code>:channelId</code>
   * @param guid
   * 	is binded to <code>:guid</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Article selectByGuid(long channelId, String guid) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_GUID_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(channelId));
    _contentValues.addWhereArgs((guid==null?"":guid));
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

      Article resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __title=null;
      String __description=null;
      URL __link=null;
      String __author=null;
      String __guid=null;
      URL __comments=null;
      long __channelId=0;
      Thumbnail __thumbnail=null;
      boolean __read=false;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("author");
        int index2=_cursor.getColumnIndex("channel_id");
        int index3=_cursor.getColumnIndex("comments");
        int index4=_cursor.getColumnIndex("description");
        int index5=_cursor.getColumnIndex("guid");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("read");
        int index8=_cursor.getColumnIndex("thumbnail");
        int index9=_cursor.getColumnIndex("title");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __title=null;
        __description=null;
        __link=null;
        __author=null;
        __guid=null;
        __comments=null;
        __channelId=0;
        __thumbnail=null;
        __read=false;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __author=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { __channelId=_cursor.getLong(index2); }
        if (!_cursor.isNull(index3)) { __comments=UrlUtils.read(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { __description=_cursor.getString(index4); }
        __guid=_cursor.getString(index5);
        if (!_cursor.isNull(index6)) { __link=UrlUtils.read(_cursor.getString(index6)); }
        if (!_cursor.isNull(index7)) { __read=_cursor.getInt(index7)==0?false:true; }
        if (!_cursor.isNull(index8)) { __thumbnail=ArticleTable.parseThumbnail(_cursor.getBlob(index8)); }
        if (!_cursor.isNull(index9)) { __title=_cursor.getString(index9); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindRssDataSource.DAO_ARTICLE_UID);
    } else {
      invalidateLiveData();
    }
  }

  /**
   * <p>Allows to registry change on this DAO in a transaction, in an batch operation or in a standalone operation.</p> */
  public void registryChange() {
    registryEvent(1);
  }

  protected void registryLiveData(LiveDataHandler value) {
    liveDatas.add(new WeakReference<LiveDataHandler>(value));
  }

  /**
   * <p>Invalidate livedata.</p>
   *
   */
  public void invalidateLiveData() {
    for (WeakReference<LiveDataHandler> item: liveDatas) {
      if (item.get()!=null) {
        item.get().invalidate();
      }
    }
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (updatePreparedStatement1!=null) {
      updatePreparedStatement1.close();
      updatePreparedStatement1=null;
    }
  }
}
