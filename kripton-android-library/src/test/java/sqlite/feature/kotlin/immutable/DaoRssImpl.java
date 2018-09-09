package sqlite.feature.kotlin.immutable;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>RssFeed</code>, based on interface <code>DaoRss</code>
 * </p>
 *
 *  @see RssFeed
 *  @see DaoRss
 *  @see RssFeedTable
 */
public class DaoRssImpl extends Dao implements DaoRss {
  private static SQLiteStatement insertPreparedStatement0;

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL2 = "SELECT id, uid, version FROM rss_feed WHERE uid=?";

  private BindRssDaoFactory daoFactory;

  public DaoRssImpl(BindRssDaoFactory daoFactory) {
    super(daoFactory.context());
    this.daoFactory=daoFactory;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO rss_feed (uid, version) VALUES (:uid, :version)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>:bean.uid</strong></dd>
   * 	<dt>version</dt><dd>is mapped to <strong>:bean.version</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(RssFeed bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO rss_feed (uid, version) VALUES (?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("uid", bean.getUid());
    _contentValues.put("version", bean.getVersion());

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
      Logger.info("INSERT OR IGNORE INTO rss_feed (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    return result!=-1;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, uid, version FROM rss_feed WHERE uid=${uid}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link RssFeed}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>version</dt><dd>is associated to bean's property <strong>version</strong></dd>
   * </dl>
   *
   * <h2>Fields filled by subqueries:</h2>
   * <dl>
   * 	<dt>channels</dt><dd>filled by query {@link DaoChannel#selectByRssFeedId}</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:uid</dt><dd>is binded to method's parameter <strong>uid</strong></dd>
   * </dl>
   *
   * @param uid
   * 	is binded to <code>:uid</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public RssFeed selectOne(String uid) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((uid==null?"":uid));
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

      RssFeed resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __uid=null;
      String __version=null;
      List<Channel> __channels=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("uid");
        int index2=_cursor.getColumnIndex("version");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __uid=null;
        __version=null;
        __channels=null;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __uid=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { __version=_cursor.getString(index2); }
        // sub query: __channels=this.daoFactory.getDaoChannel().selectByRssFeedId(__id)
        __channels=this.daoFactory.getDaoChannel().selectByRssFeedId(__id);

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new RssFeed(__id,__uid,__version,(__channels==null ? null : Collections.unmodifiableList(__channels)));
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
