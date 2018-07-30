package sqlite.feature.relations.case4.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.relations.case4.model.Channel;
import sqlite.feature.relations.case4.model.ChannelTable;

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

  private static final String SELECT_ALL_SQL3 = "SELECT id, copyright, description, image, language, last_build_date, link, pub_date, title FROM channel";

  private BindRssDaoFactory daoFactory;

  public DaoChannelImpl(BindRssDaoFactory daoFactory) {
    super(daoFactory.context());
    this.daoFactory=daoFactory;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO channel (copyright, description, image, language, last_build_date, link, pub_date, title) VALUES (:copyright, :description, :image, :language, :lastBuildDate, :link, :pubDate, :title)</pre>
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
      String _sql="INSERT OR REPLACE INTO channel (copyright, description, image, language, last_build_date, link, pub_date, title) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("copyright", bean.copyright);
    _contentValues.put("description", bean.description);
    _contentValues.put("image", ChannelTable.serializeImage(bean.image));
    _contentValues.put("language", bean.language);
    _contentValues.put("last_build_date", DateUtils.write(bean.lastBuildDate));
    _contentValues.put("link", bean.link);
    _contentValues.put("pub_date", DateUtils.write(bean.pubDate));
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
      Logger.info("INSERT OR REPLACE INTO channel (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return result!=-1;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, copyright, description, image, language, last_build_date, link, pub_date, title FROM channel</pre>
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
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Fields filled by subqueries:</h2>
   * <dl>
   * 	<dt>articles</dt><dd>filled by query {@link DaoArticle#selectByChannel}</dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Channel> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL3;
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

      ArrayList<Channel> resultList=new ArrayList<Channel>(_cursor.getCount());
      Channel resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("copyright");
        int index2=_cursor.getColumnIndex("description");
        int index3=_cursor.getColumnIndex("image");
        int index4=_cursor.getColumnIndex("language");
        int index5=_cursor.getColumnIndex("last_build_date");
        int index6=_cursor.getColumnIndex("link");
        int index7=_cursor.getColumnIndex("pub_date");
        int index8=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Channel();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.copyright=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.description=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.image=ChannelTable.parseImage(_cursor.getBlob(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.language=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { resultBean.lastBuildDate=DateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.link=_cursor.getString(index6); }
          if (!_cursor.isNull(index7)) { resultBean.pubDate=DateUtils.read(_cursor.getString(index7)); }
          if (!_cursor.isNull(index8)) { resultBean.title=_cursor.getString(index8); }
          // sub query: resultBean.articles=this.daoFactory.getDaoArticle().selectByChannel(resultBean.id)
          resultBean.articles=this.daoFactory.getDaoArticle().selectByChannel(resultBean.id);

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
