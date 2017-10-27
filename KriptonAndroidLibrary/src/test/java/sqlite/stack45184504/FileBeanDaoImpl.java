package sqlite.stack45184504;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>FileBean</code>, based on interface <code>FileBeanDao</code>
 * </p>
 *
 *  @see FileBean
 *  @see FileBeanDao
 *  @see FileBeanTable
 */
public class FileBeanDaoImpl extends AbstractDao implements FileBeanDao {
  public FileBeanDaoImpl(BindExampleDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO files (name, content, content_type) VALUES (${bean.name}, ${bean.content}, ${bean.contentType})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>content</dt><dd>is mapped to <strong>${bean.content}</strong></dd>
   * 	<dt>content_type</dt><dd>is mapped to <strong>${bean.contentType}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(FileBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }
    if (bean.content!=null) {
      contentValues.put("content", bean.content);
    } else {
      contentValues.putNull("content");
    }
    if (bean.contentType!=null) {
      contentValues.put("content_type", bean.contentType);
    } else {
      contentValues.putNull("content_type");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO files (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("files", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO files (name, content_type, content) VALUES (${name}, ${contentType}, ${content})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>content_type</dt><dd>is binded to query's parameter <strong>${contentType}</strong> and method's parameter <strong>contentType</strong></dd>
   * 	<dt>content</dt><dd>is binded to query's parameter <strong>${content}</strong> and method's parameter <strong>content</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column value <strong>name</strong>
   * @param contentType
   * 	is binded to column value <strong>content_type</strong>
   * @param content
   * 	is binded to column value <strong>content</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(String name, String contentType, byte[] content) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
    }
    if (contentType!=null) {
      contentValues.put("content_type", contentType);
    } else {
      contentValues.putNull("content_type");
    }
    if (content!=null) {
      contentValues.put("content", content);
    } else {
      contentValues.putNull("content");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO files (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("files", null, contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, content, content_type FROM files WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>content</dt><dd>is associated to bean's property <strong>content</strong></dd>
   * 	<dt>content_type</dt><dd>is associated to bean's property <strong>contentType</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<FileBean> selectById(long id) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, content, content_type FROM files");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<FileBean> resultList=new LinkedList<FileBean>();
      FileBean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("content");
        int index3=cursor.getColumnIndex("content_type");

        do
         {
          resultBean=new FileBean();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.content=cursor.getBlob(index2); }
          if (!cursor.isNull(index3)) { resultBean.contentType=cursor.getString(index3); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
