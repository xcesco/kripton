package sqlite.feature.many2many.case6.persistence;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.many2many.case6.model.PersonPhone;

/**
 * <p>
 * DAO implementation for entity <code>PersonPhone</code>, based on interface <code>GeneratedPerson2PhoneDao</code>
 * </p>
 *
 *  @see PersonPhone
 *  @see GeneratedPerson2PhoneDao
 *  @see sqlite.feature.many2many.case6.model.PersonPhoneTable
 */
public class Person2PhoneDaoImpl extends Dao implements GeneratedPerson2PhoneDao {
  /**
   * SQL definition for method selectByDate
   */
  private static final String SELECT_BY_DATE_SQL11 = "SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE buy_date>?";

  /**
   * SQL definition for method selectById
   */
  private static final String SELECT_BY_ID_SQL12 = "SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE id=?";

  /**
   * SQL definition for method selectByPersonId
   */
  private static final String SELECT_BY_PERSON_ID_SQL13 = "SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE person_id=?";

  /**
   * SQL definition for method selectByPhoneNumberId
   */
  private static final String SELECT_BY_PHONE_NUMBER_ID_SQL14 = "SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE phone_number_id=?";

  private static SupportSQLiteStatement deleteByIdPreparedStatement0;

  private static SupportSQLiteStatement deleteByPersonIdPreparedStatement1;

  private static SupportSQLiteStatement deleteByPhoneNumberIdPreparedStatement2;

  private static SupportSQLiteStatement insertPreparedStatement3;

  public Person2PhoneDaoImpl(BindXenoDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE buy_date>${since}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhone}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>buy_date</dt><dd>is associated to bean's property <strong>buyDate</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>phone_number_id</dt><dd>is associated to bean's property <strong>phoneNumberId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:since</dt><dd>is binded to method's parameter <strong>since</strong></dd>
   * </dl>
   *
   * @param since
   * 	is binded to <code>:since</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PersonPhone> selectByDate(Date since) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_DATE_SQL11;
    // add where arguments
    _contentValues.addWhereArgs((since==null?"":SQLDateUtils.write(since)));
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<PersonPhone> resultList=new ArrayList<PersonPhone>(_cursor.getCount());
      PersonPhone resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("buy_date");
        int index2=_cursor.getColumnIndex("person_id");
        int index3=_cursor.getColumnIndex("phone_number_id");

        do
         {
          resultBean=new PersonPhone();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.buyDate=SQLDateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.personId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.phoneNumberId=_cursor.getLong(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE id=:id</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhone}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>buy_date</dt><dd>is associated to bean's property <strong>buyDate</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>phone_number_id</dt><dd>is associated to bean's property <strong>phoneNumberId</strong></dd>
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
  public PersonPhone selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL12;
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      PersonPhone resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("buy_date");
        int index2=_cursor.getColumnIndex("person_id");
        int index3=_cursor.getColumnIndex("phone_number_id");

        resultBean=new PersonPhone();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.buyDate=SQLDateUtils.read(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.personId=_cursor.getLong(index2); }
        if (!_cursor.isNull(index3)) { resultBean.phoneNumberId=_cursor.getLong(index3); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE person_id=:personId</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhone}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>buy_date</dt><dd>is associated to bean's property <strong>buyDate</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>phone_number_id</dt><dd>is associated to bean's property <strong>phoneNumberId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:personId</dt><dd>is binded to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is binded to <code>:personId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PersonPhone> selectByPersonId(long personId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_PERSON_ID_SQL13;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(personId));
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<PersonPhone> resultList=new ArrayList<PersonPhone>(_cursor.getCount());
      PersonPhone resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("buy_date");
        int index2=_cursor.getColumnIndex("person_id");
        int index3=_cursor.getColumnIndex("phone_number_id");

        do
         {
          resultBean=new PersonPhone();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.buyDate=SQLDateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.personId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.phoneNumberId=_cursor.getLong(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, buy_date, person_id, phone_number_id FROM person_phone WHERE phone_number_id=:phoneNumberId</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhone}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>buy_date</dt><dd>is associated to bean's property <strong>buyDate</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>phone_number_id</dt><dd>is associated to bean's property <strong>phoneNumberId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:phoneNumberId</dt><dd>is binded to method's parameter <strong>phoneNumberId</strong></dd>
   * </dl>
   *
   * @param phoneNumberId
   * 	is binded to <code>:phoneNumberId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PersonPhone> selectByPhoneNumberId(long phoneNumberId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_PHONE_NUMBER_ID_SQL14;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(phoneNumberId));
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<PersonPhone> resultList=new ArrayList<PersonPhone>(_cursor.getCount());
      PersonPhone resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("buy_date");
        int index2=_cursor.getColumnIndex("person_id");
        int index3=_cursor.getColumnIndex("phone_number_id");

        do
         {
          resultBean=new PersonPhone();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.buyDate=SQLDateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.personId=_cursor.getLong(index2); }
          if (!_cursor.isNull(index3)) { resultBean.phoneNumberId=_cursor.getLong(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_phone WHERE id=:id</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteById(long id) {
    if (deleteByIdPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person_phone WHERE id=?";
      deleteByIdPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_phone WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteByIdPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_phone WHERE person_id=:personId</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:personId</dt><dd>is mapped to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is used as where parameter <strong>:personId</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteByPersonId(long personId) {
    if (deleteByPersonIdPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person_phone WHERE person_id=?";
      deleteByPersonIdPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByPersonIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(personId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_phone WHERE person_id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteByPersonIdPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_phone WHERE phone_number_id=:phoneNumberId</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:phoneNumberId</dt><dd>is mapped to method's parameter <strong>phoneNumberId</strong></dd>
   * </dl>
   *
   * @param phoneNumberId
   * 	is used as where parameter <strong>:phoneNumberId</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteByPhoneNumberId(long phoneNumberId) {
    if (deleteByPhoneNumberIdPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person_phone WHERE phone_number_id=?";
      deleteByPhoneNumberIdPreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByPhoneNumberIdPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(phoneNumberId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_phone WHERE phone_number_id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteByPhoneNumberIdPreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person_phone (buy_date, person_id, phone_number_id) VALUES (:buyDate, :personId, :phoneNumberId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>buy_date</dt><dd>is mapped to <strong>:bean.buyDate</strong></dd>
   * 	<dt>person_id</dt><dd>is mapped to <strong>:bean.personId</strong></dd>
   * 	<dt>phone_number_id</dt><dd>is mapped to <strong>:bean.phoneNumberId</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PersonPhone bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person_phone (buy_date, person_id, phone_number_id) VALUES (?, ?, ?)";
      insertPreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement3);
    _contentValues.put("buy_date", SQLDateUtils.write(bean.buyDate));
    _contentValues.put("person_id", bean.personId);
    _contentValues.put("phone_number_id", bean.phoneNumberId);

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
      Logger.info("INSERT INTO person_phone (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement3, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    try {
      if (deleteByIdPreparedStatement0!=null) {
        deleteByIdPreparedStatement0.close();
        deleteByIdPreparedStatement0=null;
      }
      if (deleteByPersonIdPreparedStatement1!=null) {
        deleteByPersonIdPreparedStatement1.close();
        deleteByPersonIdPreparedStatement1=null;
      }
      if (deleteByPhoneNumberIdPreparedStatement2!=null) {
        deleteByPhoneNumberIdPreparedStatement2.close();
        deleteByPhoneNumberIdPreparedStatement2=null;
      }
      if (insertPreparedStatement3!=null) {
        insertPreparedStatement3.close();
        insertPreparedStatement3=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
