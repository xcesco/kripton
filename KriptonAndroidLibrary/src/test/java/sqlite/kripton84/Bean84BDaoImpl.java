package sqlite.kripton84;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * <p>
 * DAO implementation for entity <code>Bean84B</code>, based on interface <code>Bean84BDao</code>
 * </p>
 *
 *  @see Bean84B
 *  @see Bean84BDao
 *  @see Bean84BTable
 */
public class Bean84BDaoImpl extends AbstractDao implements Bean84BDao {
  /**
   * Bean84B2BindMap */
  private Bean84B2BindMap bean84B2BindMap = BinderUtils.mapperFor(Bean84B2.class);

  public Bean84BDaoImpl(BindBean84BDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, columnBean FROM bean84_b WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean84B selectById(long param1) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT id, column_bean FROM bean84_b ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(param1));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean84B resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("column_bean");

        resultBean=new Bean84B();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.columnBean=Bean84BTable.parseColumnBean(cursor.getBlob(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, columnBean FROM bean84_b WHERE cast(columnBean as TEXT) = ${param1}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${param1}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to <code>${param1}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean84B selectByBean(Bean84B2 param1) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT id, column_bean FROM bean84_b ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE cast(column_bean as TEXT) = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add((param1==null?"":new String(serializer1(param1),StandardCharsets.UTF_8)));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean84B resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("column_bean");

        resultBean=new Bean84B();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.columnBean=Bean84BTable.parseColumnBean(cursor.getBlob(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84_b (column_bean) VALUES (${bean.columnBean})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(Bean84B bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnBean!=null) {
      contentValues.put("column_bean", Bean84BTable.serializeColumnBean(bean.columnBean));
    } else {
      contentValues.putNull("column_bean");
    }

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
    Logger.info("INSERT INTO bean84_b (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("bean84_b", null, contentValues);
    bean.id=result;

    return result!=-1;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean84_b SET column_bean=${bean.columnBean} WHERE </pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateAll(Bean84B bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnBean!=null) {
      contentValues.put("column_bean", Bean84BTable.serializeColumnBean(bean.columnBean));
    } else {
      contentValues.putNull("column_bean");
    }

    ArrayList<String> _sqlWhereParams=new ArrayList<String>();

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("UPDATE bean84_b SET columnBean=:column_bean");

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

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().update("bean84_b", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean84_b WHERE WHERE 1=1</pre>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteAll(Bean84B bean) {
    ArrayList<String> _sqlWhereParams=new ArrayList<String>();

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" 1=1";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("DELETE FROM bean84_b WHERE 1=1");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().delete("bean84_b", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result!=0;
  }

  /**
   * write
   */
  private byte[] serializer1(Bean84B2 value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        bean84B2BindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Bean84B2 parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Bean84B2 result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=bean84B2BindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
