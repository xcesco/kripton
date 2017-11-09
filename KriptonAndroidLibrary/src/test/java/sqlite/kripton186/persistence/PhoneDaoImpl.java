package sqlite.kripton186.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.LinkedList;
import java.util.List;
import sqlite.kripton186.model.ActionType;
import sqlite.kripton186.model.PhoneNumber;

/**
 * <p>
 * DAO implementation for entity <code>PhoneNumber</code>, based on interface <code>PhoneDao</code>
 * </p>
 *
 *  @see PhoneNumber
 *  @see PhoneDao
 *  @see sqlite.kripton186.model.PhoneNumberTable
 */
public class PhoneDaoImpl extends AbstractDao implements PhoneDao {
  private SQLiteStatement insertPreparedStatement0;

  public PhoneDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO phone_number (action, number, country_code, contact_name, contact_id) VALUES (${action}, ${number}, ${countryCode}, ${contactName}, ${contactId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>action</dt><dd>is mapped to <strong>${bean.action}</strong></dd>
   * 	<dt>number</dt><dd>is mapped to <strong>${bean.number}</strong></dd>
   * 	<dt>country_code</dt><dd>is mapped to <strong>${bean.countryCode}</strong></dd>
   * 	<dt>contact_name</dt><dd>is mapped to <strong>${bean.contactName}</strong></dd>
   * 	<dt>contact_id</dt><dd>is mapped to <strong>${bean.contactId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PhoneNumber bean) {
    KriptonContentValues _contentValues=contentValues();
    if (bean.action!=null) {
      _contentValues.put("action", bean.action.toString());
    } else {
      _contentValues.putNull("action");
    }
    if (bean.number!=null) {
      _contentValues.put("number", bean.number);
    } else {
      _contentValues.putNull("number");
    }
    if (bean.countryCode!=null) {
      _contentValues.put("country_code", bean.countryCode);
    } else {
      _contentValues.putNull("country_code");
    }
    if (bean.contactName!=null) {
      _contentValues.put("contact_name", bean.contactName);
    } else {
      _contentValues.putNull("contact_name");
    }
    if (bean.contactId!=null) {
      _contentValues.put("contact_id", bean.contactId);
    } else {
      _contentValues.putNull("contact_id");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keys()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT OR REPLACE INTO phone_number (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // insert operation
    if (insertPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO phone_number (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement0, _contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PhoneNumber selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        resultBean=new PhoneNumber();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.number=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM phone_number WHERE id = ${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteById(long id) {
    KriptonContentValues _contentValues=contentValues();
    _contentValues.addWhereArgs(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="DELETE FROM phone_number WHERE id = ?";

    // display log
    Logger.info("DELETE FROM phone_number WHERE id = ?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE number = ${number}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${number}</dt><dd>is binded to method's parameter <strong>number</strong></dd>
   * </dl>
   *
   * @param number
   * 	is binded to <code>${number}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PhoneNumber selectByNumber(String number) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE number = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((number==null?"":number));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        resultBean=new PhoneNumber();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.number=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number ORDER BY contact_name, number</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PhoneNumber> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";

    // build where condition
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY contact_name, number";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<PhoneNumber> resultList=new LinkedList<PhoneNumber>();
      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        do
         {
          resultBean=new PhoneNumber();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.number=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  public void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
