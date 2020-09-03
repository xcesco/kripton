package sqlite.feature.immutable.adapter;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.androidx.livedata.KriptonXLiveDataHandlerImpl;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import io.reactivex.subjects.PublishSubject;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see PersonTable
 */
public class PersonDAOImpl extends Dao implements PersonDAO {
  private static SupportSQLiteStatement insertPreparedStatement0;

  /**
   * SQL definition for method selectTheOne
   */
  private static final String SELECT_THE_ONE_SQL1 = "SELECT id, birth, name, surname FROM person";

  /**
   * SQL definition for method selectByBirthday
   */
  private static final String SELECT_BY_BIRTHDAY_SQL2 = "SELECT id, birth, name, surname FROM person WHERE birth=?";

  /**
   * SQL definition for method select
   */
  private static final String SELECT_SQL3 = "SELECT id, birth, name, surname FROM person WHERE birth=?";

  /**
   * SQL definition for method selectByBirthday
   */
  private static final String SELECT_BY_BIRTHDAY_SQL4 = "SELECT id, birth, name, surname FROM person WHERE birth=?";

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

  public PersonDAOImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (birth, name, surname) VALUES (:bean.birthDate, :bean.name, :bean.surname)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>birth</dt><dd>is mapped to <strong>:bean.birthDate</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public Person insert(Person bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (birth, name, surname) VALUES (?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("birth", SQLDateUtils.write(bean.getBirthDate()));
    _contentValues.put("name", bean.getName());
    _contentValues.put("surname", bean.getSurname());

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
      Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement0, _contentValues);
    // immutable POJO - create a copy with new id
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __name=null;
    String __surname=null;
    Date __birthDate=null;
    // immutable object: initialize temporary variables for properties with entity propertiy values
    __id=bean.getId();
    __name=bean.getName();
    __surname=bean.getSurname();
    __birthDate=bean.getBirthDate();
    __id=result;
    // immutable object: inizialize object
    bean=new Person(__id,__name,__surname,__birthDate);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithId(result));
    }
    // support for livedata
    registryEvent(result>0?1:0);

    return bean;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth, name, surname FROM person</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectTheOne() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_THE_ONE_SQL1;
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      String __surname=null;
      Date __birthDate=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("surname");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __name=null;
        __surname=null;
        __birthDate=null;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __birthDate=SQLDateUtils.read(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { __name=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { __surname=_cursor.getString(index3); }

        // define immutable POJO
        // immutable object: inizialize object
        resultBean=new Person(__id,__name,__surname,__birthDate);
      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth, name, surname FROM person WHERE birth=${birthDay}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:birthDay</dt><dd>is binded to method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to <code>:birthDay</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<Person> selectByBirthday(Date birthDay) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_BIRTHDAY_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(DateAdapter.class, birthDay));
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

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      String __surname=null;
      Date __birthDate=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("surname");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __name=null;
          __surname=null;
          __birthDate=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __birthDate=SQLDateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { __name=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { __surname=_cursor.getString(index3); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__id,__name,__surname,__birthDate);
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
   * <pre>SELECT id, birth, name, surname FROM person WHERE birth=${birthDay}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:birthDay</dt><dd>is binded to method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to <code>:birthDay</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  protected List<Person> selectForLiveData(Date birthDay) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(DateAdapter.class, birthDay));
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

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      String __surname=null;
      Date __birthDate=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("surname");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __name=null;
          __surname=null;
          __birthDate=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __birthDate=SQLDateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { __name=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { __surname=_cursor.getString(index3); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__id,__name,__surname,__birthDate);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth, name, surname FROM person WHERE birth=${birthDay}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:birthDay</dt><dd>is binded to method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to <code>:birthDay</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public LiveData<List<Person>> select(final Date birthDay) {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonXLiveDataHandlerImpl<List<Person>> builder=new KriptonXLiveDataHandlerImpl<List<Person>>() {
      @Override
      protected List<Person> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Person>>() {
          @Override
          public List<Person> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getPersonDAO().selectForLiveData(birthDay);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth, name, surname FROM person WHERE birth=${birthDay}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:birthDay</dt><dd>is binded to method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to <code>:birthDay</code>
   * @param listener
   * 	is the Person listener
   */
  @Override
  public void selectByBirthday(Date birthDay, OnReadBeanListener<Person> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_BIRTHDAY_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(DateAdapter.class, birthDay));
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
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;
      String __surname=null;
      Date __birthDate=null;
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("surname");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          __birthDate=null;
          __name=null;
          __surname=null;

          // generate mapping
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __birthDate=SQLDateUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { __name=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { __surname=_cursor.getString(index3); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__id,__name,__surname,__birthDate);
          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindAppDataSource.PERSON_D_A_O_UID);
    } else {
      invalidateLiveData();
    }
  }

  /**
   * <p>Allows to registry change on this DAO in a transaction, in an batch operation or in a standalone operation.</p>
   *
   */
  public void registryChange() {
    registryEvent(1);
  }

  protected void registryLiveData(LiveDataHandler value) {
    liveDatas.add(new WeakReference<LiveDataHandler>(value));
  }

  /**
   * <p>Invalidate livedata.</p>
   *
   */
  public void invalidateLiveData() {
    for (WeakReference<LiveDataHandler> item: liveDatas) {
      if (item.get()!=null) {
        item.get().invalidate();
      }
    }
  }

  public PublishSubject<SQLiteEvent> getSubject() {
    return subject;
  }

  public static void clearCompiledStatements() {
    try {
      if (insertPreparedStatement0!=null) {
        insertPreparedStatement0.close();
        insertPreparedStatement0=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
