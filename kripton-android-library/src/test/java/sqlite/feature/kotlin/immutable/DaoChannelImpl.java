package sqlite.feature.kotlin.immutable;

import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * DAO implementation for entity <code>Channel</code>, based on interface <code>DaoChannel</code>
 * </p>
 *
 *  @see Channel
 *  @see DaoChannel
 *  @see ChannelTable
 */
public class DaoChannelImpl extends Dao implements DaoChannel {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_RSS_FEED_ID_SQL4 = "SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels WHERE rss_feed_id=?";

  private static final String SELECT_ONE_BY_RSS_FEED_ID_SQL5 = "SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels WHERE rss_feed_id=?";

  private static final String SELECT_ONE_SQL6 = "SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels";

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  private BindRssDaoFactory daoFactory;

  public DaoChannelImpl(BindRssDaoFactory daoFactory) {
    super(daoFactory.context());
    this.daoFactory=daoFactory;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO channels (copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title) VALUES (:copyright, :description, :image, :language, :lastBuildDate, :link, :pubDate, :rssFeedId, :title)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>copyright</dt><dd>is mapped to <strong>:bean.copyright</strong></dd>
   * 	<dt>description</dt><dd>is mapped to <strong>:bean.description</strong></dd>
   * 	<dt>image</dt><dd>is mapped to <strong>:bean.image</strong></dd>
   * 	<dt>language</dt><dd>is mapped to <strong>:bean.language</strong></dd>
   * 	<dt>last_build_date</dt><dd>is mapped to <strong>:bean.lastBuildDate</strong></dd>
   * 	<dt>link</dt><dd>is mapped to <strong>:bean.link</strong></dd>
   * 	<dt>pub_date</dt><dd>is mapped to <strong>:bean.pubDate</strong></dd>
   * 	<dt>rss_feed_id</dt><dd>is mapped to <strong>:bean.rssFeedId</strong></dd>
   * 	<dt>title</dt><dd>is mapped to <strong>:bean.title</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(Channel bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO channels (copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("copyright", bean.getCopyright());
    _contentValues.put("description", bean.getDescription());
    _contentValues.put("image", ChannelTable.serializeImage(bean.getImage()));
    _contentValues.put("language", bean.getLanguage());
    _contentValues.put("last_build_date", DateUtils.write(bean.getLastBuildDate()));
    _contentValues.put("link", bean.getLink());
    _contentValues.put("pub_date", DateUtils.write(bean.getPubDate()));
    _contentValues.put("rss_feed_id", bean.getRssFeedId());
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
      Logger.info("INSERT OR IGNORE INTO channels (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels WHERE rss_feed_id=${rssFeedId}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>copyright</dt><dd>is associated to bean's property <strong>copyright</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>image</dt><dd>is associated to bean's property <strong>image</strong></dd>
   * 	<dt>language</dt><dd>is associated to bean's property <strong>language</strong></dd>
   * 	<dt>last_build_date</dt><dd>is associated to bean's property <strong>lastBuildDate</strong></dd>
   * 	<dt>link</dt><dd>is associated to bean's property <strong>link</strong></dd>
   * 	<dt>pub_date</dt><dd>is associated to bean's property <strong>pubDate</strong></dd>
   * 	<dt>rss_feed_id</dt><dd>is associated to bean's property <strong>rssFeedId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Fields filled by subqueries:</h2>
   * <dl>
   * 	<dt>articles</dt><dd>filled by query {@link DaoArticle#selectByChannelUd}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:rssFeedId</dt><dd>is binded to method's parameter <strong>rssFeedId</strong></dd>
   * </dl>
   *
   * @param rssFeedId
   * 	is binded to <code>:rssFeedId</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<Channel> selectByRssFeedId(long rssFeedId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_RSS_FEED_ID_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(rssFeedId));
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

      ArrayList<Channel> resultList=new ArrayList<Channel>(_cursor.getCount());
      Channel resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __title=null;
      String __link=null;
      String __description=null;
      String __language=null;
      String __copyright=null;
      Date __pubDate=null;
      Date __lastBuildDate=null;
      Image __image=null;
      long __rssFeedId=0;
      List<Article> __articles=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("copyright");
        int index2=_cursor.getColumnIndex("description");
        int index3=_cursor.getColumnIndex("image");
        int index4=_cursor.getColumnIndex("language");
        int index5=_cursor.getColumnIndex("last_build_date");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("pub_date");
        int index8=_cursor.getColumnIndex("rss_feed_id");
        int index9=_cursor.getColumnIndex("title");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __title=null;
          __link=null;
          __description=null;
          __language=null;
          __copyright=null;
          __pubDate=null;
          __lastBuildDate=null;
          __image=null;
          __rssFeedId=0;
          __articles=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __copyright=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __description=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { __image=ChannelTable.parseImage(_cursor.getBlob(index3)); }
          if (!_cursor.isNull(index4)) { __language=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { __lastBuildDate=DateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { __link=_cursor.getString(index6); }
          if (!_cursor.isNull(index7)) { __pubDate=DateUtils.read(_cursor.getString(index7)); }
          if (!_cursor.isNull(index8)) { __rssFeedId=_cursor.getLong(index8); }
          if (!_cursor.isNull(index9)) { __title=_cursor.getString(index9); }
          // sub query: __articles=this.daoFactory.getDaoArticle().selectByChannelUd(__id)
          __articles=this.daoFactory.getDaoArticle().selectByChannelUd(__id);

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
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
   * <pre>SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels WHERE rss_feed_id=${rssFeedId}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>copyright</dt><dd>is associated to bean's property <strong>copyright</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>image</dt><dd>is associated to bean's property <strong>image</strong></dd>
   * 	<dt>language</dt><dd>is associated to bean's property <strong>language</strong></dd>
   * 	<dt>last_build_date</dt><dd>is associated to bean's property <strong>lastBuildDate</strong></dd>
   * 	<dt>link</dt><dd>is associated to bean's property <strong>link</strong></dd>
   * 	<dt>pub_date</dt><dd>is associated to bean's property <strong>pubDate</strong></dd>
   * 	<dt>rss_feed_id</dt><dd>is associated to bean's property <strong>rssFeedId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:rssFeedId</dt><dd>is binded to method's parameter <strong>rssFeedId</strong></dd>
   * </dl>
   *
   * @param rssFeedId
   * 	is binded to <code>:rssFeedId</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Channel selectOneByRssFeedId(long rssFeedId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_BY_RSS_FEED_ID_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(rssFeedId));
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

      Channel resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __title=null;
      String __link=null;
      String __description=null;
      String __language=null;
      String __copyright=null;
      Date __pubDate=null;
      Date __lastBuildDate=null;
      Image __image=null;
      long __rssFeedId=0;
      List<Article> __articles=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("copyright");
        int index2=_cursor.getColumnIndex("description");
        int index3=_cursor.getColumnIndex("image");
        int index4=_cursor.getColumnIndex("language");
        int index5=_cursor.getColumnIndex("last_build_date");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("pub_date");
        int index8=_cursor.getColumnIndex("rss_feed_id");
        int index9=_cursor.getColumnIndex("title");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __title=null;
        __link=null;
        __description=null;
        __language=null;
        __copyright=null;
        __pubDate=null;
        __lastBuildDate=null;
        __image=null;
        __rssFeedId=0;
        __articles=null;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __copyright=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { __description=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { __image=ChannelTable.parseImage(_cursor.getBlob(index3)); }
        if (!_cursor.isNull(index4)) { __language=_cursor.getString(index4); }
        if (!_cursor.isNull(index5)) { __lastBuildDate=DateUtils.read(_cursor.getString(index5)); }
        if (!_cursor.isNull(index6)) { __link=_cursor.getString(index6); }
        if (!_cursor.isNull(index7)) { __pubDate=DateUtils.read(_cursor.getString(index7)); }
        if (!_cursor.isNull(index8)) { __rssFeedId=_cursor.getLong(index8); }
        if (!_cursor.isNull(index9)) { __title=_cursor.getString(index9); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>copyright</dt><dd>is associated to bean's property <strong>copyright</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>image</dt><dd>is associated to bean's property <strong>image</strong></dd>
   * 	<dt>language</dt><dd>is associated to bean's property <strong>language</strong></dd>
   * 	<dt>last_build_date</dt><dd>is associated to bean's property <strong>lastBuildDate</strong></dd>
   * 	<dt>link</dt><dd>is associated to bean's property <strong>link</strong></dd>
   * 	<dt>pub_date</dt><dd>is associated to bean's property <strong>pubDate</strong></dd>
   * 	<dt>rss_feed_id</dt><dd>is associated to bean's property <strong>rssFeedId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  protected Channel selectOneForLiveData() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL6;
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
      // Specialized part - SelectBeanHelper - BEGIN

      Channel resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __title=null;
      String __link=null;
      String __description=null;
      String __language=null;
      String __copyright=null;
      Date __pubDate=null;
      Date __lastBuildDate=null;
      Image __image=null;
      long __rssFeedId=0;
      List<Article> __articles=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("copyright");
        int index2=_cursor.getColumnIndex("description");
        int index3=_cursor.getColumnIndex("image");
        int index4=_cursor.getColumnIndex("language");
        int index5=_cursor.getColumnIndex("last_build_date");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("pub_date");
        int index8=_cursor.getColumnIndex("rss_feed_id");
        int index9=_cursor.getColumnIndex("title");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __title=null;
        __link=null;
        __description=null;
        __language=null;
        __copyright=null;
        __pubDate=null;
        __lastBuildDate=null;
        __image=null;
        __rssFeedId=0;
        __articles=null;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __copyright=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { __description=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { __image=ChannelTable.parseImage(_cursor.getBlob(index3)); }
        if (!_cursor.isNull(index4)) { __language=_cursor.getString(index4); }
        if (!_cursor.isNull(index5)) { __lastBuildDate=DateUtils.read(_cursor.getString(index5)); }
        if (!_cursor.isNull(index6)) { __link=_cursor.getString(index6); }
        if (!_cursor.isNull(index7)) { __pubDate=DateUtils.read(_cursor.getString(index7)); }
        if (!_cursor.isNull(index8)) { __rssFeedId=_cursor.getLong(index8); }
        if (!_cursor.isNull(index9)) { __title=_cursor.getString(index9); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, copyright, description, image, language, last_build_date, link, pub_date, rss_feed_id, title FROM channels</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Channel}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>copyright</dt><dd>is associated to bean's property <strong>copyright</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>image</dt><dd>is associated to bean's property <strong>image</strong></dd>
   * 	<dt>language</dt><dd>is associated to bean's property <strong>language</strong></dd>
   * 	<dt>last_build_date</dt><dd>is associated to bean's property <strong>lastBuildDate</strong></dd>
   * 	<dt>link</dt><dd>is associated to bean's property <strong>link</strong></dd>
   * 	<dt>pub_date</dt><dd>is associated to bean's property <strong>pubDate</strong></dd>
   * 	<dt>rss_feed_id</dt><dd>is associated to bean's property <strong>rssFeedId</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public LiveData<Channel> selectOne() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonLiveDataHandlerImpl<Channel> builder=new KriptonLiveDataHandlerImpl<Channel>() {
      @Override
      protected Channel compute() {
        return BindRssDataSource.getInstance().executeBatch(new BindRssDataSource.Batch<Channel>() {
          @Override
          public Channel onExecute(BindRssDaoFactory daoFactory) {
            return daoFactory.getDaoChannel().selectOneForLiveData();
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindRssDataSource.DAO_CHANNEL_UID);
    } else {
      invalidateLiveData();
    }
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
  }
}
