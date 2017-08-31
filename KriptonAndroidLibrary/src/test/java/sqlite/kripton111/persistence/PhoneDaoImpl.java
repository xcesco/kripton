package sqlite.kripton111.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sqlite.kripton111.model.ActionType;
import sqlite.kripton111.model.PhoneNumber;

/**
 * <p>
 * DAO implementation for entity <code>PhoneNumber</code>, based on interface <code>PhoneDao</code>
 * </p>
 *
 *  @see PhoneNumber
 *  @see PhoneDao
 *  @see sqlite.kripton111.model.PhoneNumberTable
 */
public class PhoneDaoImpl extends AbstractDao implements PhoneDao {
  public PhoneDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO phone_number (action_type, number, country_code, contact_name, contact_id) VALUES (${bean.actionType}, ${bean.number}, ${bean.countryCode}, ${bean.contactName}, ${bean.contactId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>action_type</dt><dd>is mapped to <strong>${bean.actionType}</strong></dd>
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
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.actionType!=null) {
      contentValues.put("action_type", bean.actionType.toString());
    } else {
      contentValues.putNull("action_type");
    }
    if (bean.number!=null) {
      contentValues.put("number", bean.number);
    } else {
      contentValues.putNull("number");
    }
    if (bean.countryCode!=null) {
      contentValues.put("country_code", bean.countryCode);
    } else {
      contentValues.putNull("country_code");
    }
    if (bean.contactName!=null) {
      contentValues.put("contact_name", bean.contactName);
    } else {
      contentValues.putNull("contact_name");
    }
    if (bean.contactId!=null) {
      contentValues.put("contact_id", bean.contactId);
    } else {
      contentValues.putNull("contact_id");
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
    Logger.info("INSERT OR REPLACE INTO phone_number (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    // conflict algorithm REPLACE
    long result = database().insertWithOnConflict("phone_number", null, contentValues, 5);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action_type, number, country_code, contact_name, contact_id FROM phone_number WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action_type</dt><dd>is associated to bean's property <strong>actionType</strong></dd>
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, action_type, number, country_code, contact_name, contact_id FROM phone_number");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action_type");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        resultBean=new PhoneNumber();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.actionType=ActionType.valueOf(cursor.getString(index1)); }
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
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM phone_number WHERE id = ?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("phone_number", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action_type, number, country_code, contact_name, contact_id FROM phone_number WHERE number = ${number}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action_type</dt><dd>is associated to bean's property <strong>actionType</strong></dd>
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, action_type, number, country_code, contact_name, contact_id FROM phone_number");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE number = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add((number==null?"":number));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action_type");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        resultBean=new PhoneNumber();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.actionType=ActionType.valueOf(cursor.getString(index1)); }
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
   * <pre>SELECT id, action_type, number, country_code, contact_name, contact_id FROM phone_number ORDER BY contact_name, number</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action_type</dt><dd>is associated to bean's property <strong>actionType</strong></dd>
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, action_type, number, country_code, contact_name, contact_id FROM phone_number");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY contact_name, number";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<PhoneNumber> resultList=new LinkedList<PhoneNumber>();
      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action_type");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        do
         {
          resultBean=new PhoneNumber();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.actionType=ActionType.valueOf(cursor.getString(index1)); }
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
}
