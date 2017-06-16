package sqlite.test03;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean02</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBean02
 *  @see Bean01Table
 */
public class DaoBean02Impl extends AbstractDao implements DaoBean02 {
  public DaoBean02Impl(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (lista, message_date, message_text, bean_list, value) VALUES (${bean.lista}, ${bean.messageDate}, ${bean.messageText}, ${bean.beanList}, ${bean.value})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>lista</dt><dd>is mapped to <strong>${bean.lista}</strong></dd>
   * 	<dt>message_date</dt><dd>is mapped to <strong>${bean.messageDate}</strong></dd>
   * 	<dt>message_text</dt><dd>is mapped to <strong>${bean.messageText}</strong></dd>
   * 	<dt>bean_list</dt><dd>is mapped to <strong>${bean.beanList}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getLista()!=null) {
      contentValues.put("lista", Bean01Table.serializeLista(bean.getLista()));
    } else {
      contentValues.putNull("lista");
    }

    contentValues.put("message_date", bean.getMessageDate());

    if (bean.getMessageText()!=null) {
      contentValues.put("message_text", bean.getMessageText());
    } else {
      contentValues.putNull("message_text");
    }

    if (bean.getBeanList()!=null) {
      contentValues.put("bean_list", Bean01Table.serializeBeanList(bean.getBeanList()));
    } else {
      contentValues.putNull("bean_list");
    }

    contentValues.put("value", bean.getValue());

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
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean01 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString()));

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("bean01", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (value, message_date) VALUES (${value}, ${messageDate})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * 	<dt>message_date</dt><dd>is binded to query's parameter <strong>${messageDate}</strong> and method's parameter <strong>messageDate</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to column <strong>value</strong>
   * @param messageDate
   * 	is binded to column <strong>message_date</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long value, long messageDate) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value", value);

    contentValues.put("message_date", messageDate);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean01 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString()));

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("bean01", null, contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean01 WHERE id=${id}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(long id) {
    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("DELETE FROM bean01 WHERE id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().delete("bean01", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean01 WHERE id=${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Bean01 bean) {
    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add(String.valueOf(bean.getId()));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("DELETE FROM bean01 WHERE id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().delete("bean01", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean01 SET value=${value} WHERE id>${id}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long update(long value, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("value", value);

    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id>?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("UPDATE bean01 SET value=:value WHERE id>?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().update("bean01", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean01 SET lista=${bean.lista}, message_date=${bean.messageDate}, message_text=${bean.messageText}, bean_list=${bean.beanList}, value=${bean.value} WHERE value=${bean.value}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>lista</dt><dd>is mapped to <strong>${bean.lista}</strong></dd>
   * 	<dt>message_date</dt><dd>is mapped to <strong>${bean.messageDate}</strong></dd>
   * 	<dt>message_text</dt><dd>is mapped to <strong>${bean.messageText}</strong></dd>
   * 	<dt>bean_list</dt><dd>is mapped to <strong>${bean.beanList}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.value}</dt><dd>is mapped to method's parameter <strong>bean.value</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getLista()!=null) {
      contentValues.put("lista", Bean01Table.serializeLista(bean.getLista()));
    } else {
      contentValues.putNull("lista");
    }

    contentValues.put("message_date", bean.getMessageDate());

    if (bean.getMessageText()!=null) {
      contentValues.put("message_text", bean.getMessageText());
    } else {
      contentValues.putNull("message_text");
    }

    if (bean.getBeanList()!=null) {
      contentValues.put("bean_list", Bean01Table.serializeBeanList(bean.getBeanList()));
    } else {
      contentValues.putNull("bean_list");
    }

    contentValues.put("value", bean.getValue());

    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add(String.valueOf(bean.getValue()));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("UPDATE bean01 SET lista=:lista, messageDate=:message_date, messageText=:message_text, beanList=:bean_list, value=:value WHERE value=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().update("bean01", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }
}
