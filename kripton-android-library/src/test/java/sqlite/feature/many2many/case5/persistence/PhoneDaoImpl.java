package sqlite.feature.many2many.case5.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.EnumUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.many2many.case5.model.ActionType;
import sqlite.feature.many2many.case5.model.PhoneNumber;

/**
 * <p>
 * DAO implementation for entity <code>PhoneNumber</code>, based on interface <code>PhoneDao</code>
 * </p>
 *
 *  @see PhoneNumber
 *  @see PhoneDao
 *  @see sqlite.feature.many2many.case5.model.PhoneNumberTable
 */
public class PhoneDaoImpl extends Dao implements PhoneDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_ID_SQL1 = "SELECT id, action, contact_id, contact_name, country_code, number FROM phone_number WHERE id = ?";

  private static SQLiteStatement deleteByIdPreparedStatement1;

  private static SQLiteStatement updateByIdPreparedStatement2;

  private static final String SELECT_BY_NUMBER_SQL2 = "SELECT id, action, contact_id, contact_name, country_code, number FROM phone_number WHERE number = ?";

  private static final String SELECT_ALL_SQL3 = "SELECT id, action, contact_id, contact_name, country_code, number FROM phone_number ORDER BY contact_name, number";

  public PhoneDaoImpl(BindXenoDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO phone_number (action, contact_id, contact_name, country_code, number) VALUES (:action, :contactId, :contactName, :countryCode, :number)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>action</dt><dd>is mapped to <strong>:bean.action</strong></dd>
   * 	<dt>contact_id</dt><dd>is mapped to <strong>:bean.contactId</strong></dd>
   * 	<dt>contact_name</dt><dd>is mapped to <strong>:bean.contactName</strong></dd>
   * 	<dt>country_code</dt><dd>is mapped to <strong>:bean.countryCode</strong></dd>
   * 	<dt>number</dt><dd>is mapped to <strong>:bean.number</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PhoneNumber bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO phone_number (action, contact_id, contact_name, country_code, number) VALUES (?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("action", EnumUtils.write(bean.action));
    _contentValues.put("contact_id", bean.contactId);
    _contentValues.put("contact_name", bean.contactName);
    _contentValues.put("country_code", bean.countryCode);
    _contentValues.put("number", bean.number);

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

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, contact_id, contact_name, country_code, number FROM phone_number WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PhoneNumber selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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

      PhoneNumber resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("action");
        int index2=_cursor.getColumnIndex("contact_id");
        int index3=_cursor.getColumnIndex("contact_name");
        int index4=_cursor.getColumnIndex("country_code");
        int index5=_cursor.getColumnIndex("number");

        resultBean=new PhoneNumber();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.contactId=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.contactName=_cursor.getString(index3); }
        if (!_cursor.isNull(index4)) { resultBean.countryCode=_cursor.getString(index4); }
        if (!_cursor.isNull(index5)) { resultBean.number=_cursor.getString(index5); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM phone_number WHERE id = :id</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteById(long id) {
    if (deleteByIdPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM phone_number WHERE id = ?";
      deleteByIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM phone_number WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM phone_number WHERE id = ${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean updateById(PhoneNumber bean) {
    if (updateByIdPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM phone_number WHERE id = ?";
      updateByIdPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateByIdPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM phone_number WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateByIdPreparedStatement2, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, contact_id, contact_name, country_code, number FROM phone_number WHERE number = ${number}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:number</dt><dd>is binded to method's parameter <strong>number</strong></dd>
   * </dl>
   *
   * @param number
   * 	is binded to <code>:number</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PhoneNumber selectByNumber(String number) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_NUMBER_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((number==null?"":number));
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

      PhoneNumber resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("action");
        int index2=_cursor.getColumnIndex("contact_id");
        int index3=_cursor.getColumnIndex("contact_name");
        int index4=_cursor.getColumnIndex("country_code");
        int index5=_cursor.getColumnIndex("number");

        resultBean=new PhoneNumber();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.contactId=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.contactName=_cursor.getString(index3); }
        if (!_cursor.isNull(index4)) { resultBean.countryCode=_cursor.getString(index4); }
        if (!_cursor.isNull(index5)) { resultBean.number=_cursor.getString(index5); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, contact_id, contact_name, country_code, number FROM phone_number ORDER BY contact_name, number</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PhoneNumber> selectAll() {
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

      ArrayList<PhoneNumber> resultList=new ArrayList<PhoneNumber>(_cursor.getCount());
      PhoneNumber resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("action");
        int index2=_cursor.getColumnIndex("contact_id");
        int index3=_cursor.getColumnIndex("contact_name");
        int index4=_cursor.getColumnIndex("country_code");
        int index5=_cursor.getColumnIndex("number");

        do
         {
          resultBean=new PhoneNumber();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.contactId=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.contactName=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.countryCode=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { resultBean.number=_cursor.getString(index5); }

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
    if (deleteByIdPreparedStatement1!=null) {
      deleteByIdPreparedStatement1.close();
      deleteByIdPreparedStatement1=null;
    }
    if (updateByIdPreparedStatement2!=null) {
      updateByIdPreparedStatement2.close();
      updateByIdPreparedStatement2=null;
    }
  }
}
