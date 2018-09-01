package sqlite.feature.rx.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>PersonPhoneNumber</code>, based on interface <code>GeneratedPerson2PhoneDao</code>
 * </p>
 *
 *  @see PersonPhoneNumber
 *  @see GeneratedPerson2PhoneDao
 *  @see PersonPhoneNumberTable
 */
public class Person2PhoneDaoImpl extends Dao implements GeneratedPerson2PhoneDao {
  private static final String SELECT_BY_ID_SQL12 = "SELECT id, person_id, phone_number_id FROM person_phone_number WHERE id=?";

  private static final String SELECT_BY_PERSON_ID_SQL13 = "SELECT id, person_id, phone_number_id FROM person_phone_number WHERE person_id=?";

  private static final String SELECT_BY_PHONE_NUMBER_ID_SQL14 = "SELECT id, person_id, phone_number_id FROM person_phone_number WHERE phone_number_id=?";

  private static SQLiteStatement deleteByIdPreparedStatement0;

  private static SQLiteStatement deleteByPersonIdPreparedStatement1;

  private static SQLiteStatement deleteByPhoneNumberIdPreparedStatement2;

  private static SQLiteStatement insertPreparedStatement3;

  private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

  public Person2PhoneDaoImpl(BindXenoDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_id, phone_number_id FROM person_phone_number WHERE id=:id</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhoneNumber}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
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
  public PersonPhoneNumber selectById(long id) {
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      PersonPhoneNumber resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      long __personId=0;
      long __phoneNumberId=0;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_id");
        int index2=_cursor.getColumnIndex("phone_number_id");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __personId=0;
        __phoneNumberId=0;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __personId=_cursor.getLong(index1); }
        if (!_cursor.isNull(index2)) { __phoneNumberId=_cursor.getLong(index2); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new PersonPhoneNumber(__id,__personId,__phoneNumberId);
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_id, phone_number_id FROM person_phone_number WHERE person_id=:personId</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhoneNumber}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
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
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<PersonPhoneNumber> selectByPersonId(long personId) {
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<PersonPhoneNumber> resultList=new ArrayList<PersonPhoneNumber>(_cursor.getCount());
      PersonPhoneNumber resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      long __personId=0;
      long __phoneNumberId=0;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_id");
        int index2=_cursor.getColumnIndex("phone_number_id");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __personId=0;
          __phoneNumberId=0;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __personId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { __phoneNumberId=_cursor.getLong(index2); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new PersonPhoneNumber(__id,__personId,__phoneNumberId);
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
   * <pre>SELECT id, person_id, phone_number_id FROM person_phone_number WHERE phone_number_id=:phoneNumberId</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link PersonPhoneNumber}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
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
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<PersonPhoneNumber> selectByPhoneNumberId(long phoneNumberId) {
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<PersonPhoneNumber> resultList=new ArrayList<PersonPhoneNumber>(_cursor.getCount());
      PersonPhoneNumber resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      long __personId=0;
      long __phoneNumberId=0;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("person_id");
        int index2=_cursor.getColumnIndex("phone_number_id");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __personId=0;
          __phoneNumberId=0;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __personId=_cursor.getLong(index1); }
          if (!_cursor.isNull(index2)) { __phoneNumberId=_cursor.getLong(index2); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new PersonPhoneNumber(__id,__personId,__phoneNumberId);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_phone_number WHERE id=:id</pre>
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
      String _sql="DELETE FROM person_phone_number WHERE id=?";
      deleteByIdPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_phone_number WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement0, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createDelete(result));
    }
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_phone_number WHERE person_id=:personId</pre>
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
      String _sql="DELETE FROM person_phone_number WHERE person_id=?";
      deleteByPersonIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByPersonIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(personId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_phone_number WHERE person_id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByPersonIdPreparedStatement1, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createDelete(result));
    }
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_phone_number WHERE phone_number_id=:phoneNumberId</pre>
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
      String _sql="DELETE FROM person_phone_number WHERE phone_number_id=?";
      deleteByPhoneNumberIdPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByPhoneNumberIdPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(phoneNumberId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_phone_number WHERE phone_number_id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByPhoneNumberIdPreparedStatement2, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createDelete(result));
    }
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person_phone_number (person_id, phone_number_id) VALUES (:bean.personId, :bean.phoneNumberId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
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
  public int insert(PersonPhoneNumber bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person_phone_number (person_id, phone_number_id) VALUES (?, ?)";
      insertPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement3);
    _contentValues.put("person_id", bean.getPersonId());
    _contentValues.put("phone_number_id", bean.getPhoneNumberId());

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
      Logger.info("INSERT INTO person_phone_number (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement3, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithId(result));
    }

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  public PublishSubject<SQLiteEvent> getSubject() {
    return subject;
  }

  public static void clearCompiledStatements() {
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
  }
}
