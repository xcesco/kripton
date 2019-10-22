package sqlite.feature.typeadapter;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Contact</code>, based on interface <code>ContactDao</code>
 * </p>
 *
 *  @see Contact
 *  @see ContactDao
 *  @see ContactTable
 */
public class ContactDaoImpl extends Dao implements ContactDao {
  /**
   * SQL definition for method selectBySurnameWithAdapter
   */
  private static final String SELECT_BY_SURNAME_WITH_ADAPTER_SQL1 = "SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE surname=?";

  /**
   * SQL definition for method selectBySurname
   */
  private static final String SELECT_BY_SURNAME_SQL2 = "SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE surname=?";

  private static SupportSQLiteStatement deleteCompactBeanPreparedStatement0;

  private static SupportSQLiteStatement deleteCompactRawPreparedStatement1;

  private static SupportSQLiteStatement deleteJQLBeanPreparedStatement2;

  private static SupportSQLiteStatement deleteJQLRawPreparedStatement3;

  /**
   * SQL definition for method selectCompactBean
   */
  private static final String SELECT_COMPACT_BEAN_SQL3 = "SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE id=?  and type=?";

  /**
   * SQL definition for method selectJQLBeanListener
   */
  private static final String SELECT_J_Q_L_BEAN_LISTENER_SQL4 = "SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE id=? and password=? and type=?";

  /**
   * SQL definition for method selecJQLBean
   */
  private static final String SELEC_J_Q_L_BEAN_SQL5 = "SELECT birth_day, password, type FROM contact WHERE id=? and password=? and type=?";

  /**
   * SQL definition for method selectJQLRaw
   */
  private static final String SELECT_J_Q_L_RAW_SQL6 = "SELECT * FROM contact WHERE password=? and type=?";

  /**
   * SQL definition for method selectCompactRaw
   */
  private static final String SELECT_COMPACT_RAW_SQL7 = "SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE password=? and type=?";

  private static SupportSQLiteStatement updateCompactBeanPreparedStatement4;

  private static SupportSQLiteStatement updateCompactRaw1PreparedStatement5;

  private static SupportSQLiteStatement updateCompactRaw2PreparedStatement6;

  private static SupportSQLiteStatement updateJQLBeanPreparedStatement7;

  private static SupportSQLiteStatement updateJQLRawPreparedStatement8;

  private static SupportSQLiteStatement insertCompactRawPreparedStatement9;

  private static SupportSQLiteStatement insertCompactBeanPreparedStatement10;

  private static SupportSQLiteStatement insertJQLBeanPreparedStatement11;

  private static SupportSQLiteStatement insertJQLRawPreparedStatement12;

  public ContactDaoImpl(BindContactDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE surname=${dummyTest}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:dummyTest</dt><dd>is binded to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param dummy
   * 	is binded to <code>:dummyTest</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectBySurnameWithAdapter(String dummy) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_SURNAME_WITH_ADAPTER_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, dummy));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Contact> resultList=new ArrayList<Contact>(_cursor.getCount());
      Contact resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index2=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index3=_cursor.getColumnIndex("surname");
        int index4=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=_cursor.getColumnIndex("update_date");
        int index6=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.surname=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(_cursor.getString(index6)); }

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
   * <pre>SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE surname=${dummy}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:dummy</dt><dd>is binded to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param dummy
   * 	is binded to <code>:dummy</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectBySurname(String dummy) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_SURNAME_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((dummy==null?"":dummy));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Contact> resultList=new ArrayList<Contact>(_cursor.getCount());
      Contact resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index2=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index3=_cursor.getColumnIndex("surname");
        int index4=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=_cursor.getColumnIndex("update_date");
        int index6=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.surname=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(_cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>:bean.type</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void deleteCompactBean(Contact bean) {
    if (deleteCompactBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM contact WHERE id=? and type=?";
      deleteCompactBeanPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteCompactBeanPreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM contact WHERE id=? and type=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteCompactBeanPreparedStatement0, _contentValues);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM contact WHERE password=:password and type=:type</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:password</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>:type</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>:password</strong>
   * @param type
   * 	is used as where parameter <strong>:type</strong>
   */
  @Override
  public void deleteCompactRaw(String password, ContactType type) {
    if (deleteCompactRawPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM contact WHERE password=? and type=?";
      deleteCompactRawPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteCompactRawPreparedStatement1);
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM contact WHERE password=? and type=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteCompactRawPreparedStatement1, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>:bean.type</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void deleteJQLBean(Contact bean) {
    if (deleteJQLBeanPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM contact WHERE id=? and type=?";
      deleteJQLBeanPreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteJQLBeanPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM contact WHERE id=? and type=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteJQLBeanPreparedStatement2, _contentValues);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM contact WHERE id=:id and type=:type</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>:type</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   * @param type
   * 	is used as where parameter <strong>:type</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteJQLRaw(long id, ContactType type) {
    if (deleteJQLRawPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM contact WHERE id=? and type=?";
      deleteJQLRawPreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteJQLRawPreparedStatement3);
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM contact WHERE id=? and type=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteJQLRawPreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE id=${bean.id}  and type=${bean.type}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>:bean.type</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectCompactBean(Contact bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_COMPACT_BEAN_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Contact> resultList=new ArrayList<Contact>(_cursor.getCount());
      Contact resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index2=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index3=_cursor.getColumnIndex("surname");
        int index4=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=_cursor.getColumnIndex("update_date");
        int index6=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.surname=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(_cursor.getString(index6)); }

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
   * <pre>SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>:bean.password</dt><dd>is binded to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>:bean.type</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param listener
   * 	is the Contact listener
   */
  @Override
  public void selectJQLBeanListener(Contact bean, OnReadBeanListener<Contact> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_J_Q_L_BEAN_LISTENER_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Contact resultBean=new Contact();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index2=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index3=_cursor.getColumnIndex("surname");
        int index4=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=_cursor.getColumnIndex("update_date");
        int index6=_cursor.getColumnIndex("update_time");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.birthDay=null;
          resultBean.setPassword(null);
          resultBean.surname=null;
          resultBean.type=null;
          resultBean.updateDate=null;
          resultBean.updateTime=null;

          // generate mapping
          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.surname=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(_cursor.getString(index6)); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT birth_day, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>:bean.password</dt><dd>is binded to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>:bean.type</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selecJQLBean(Contact bean) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELEC_J_Q_L_BEAN_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Contact> resultList=new ArrayList<Contact>(_cursor.getCount());
      Contact resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index1=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index2=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);

        do
         {
          resultBean=new Contact();

          if (!_cursor.isNull(index0)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index0)); }
          if (!_cursor.isNull(index1)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index1))); }
          if (!_cursor.isNull(index2)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index2)); }

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
   * <pre>SELECT * FROM contact WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:password</dt><dd>is binded to method's parameter <strong>password</strong></dd>
   * 	<dt>:type</dt><dd>is binded to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to <code>:password</code>
   * @param type
   * 	is binded to <code>:type</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectJQLRaw(String password, ContactType type) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_J_Q_L_RAW_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Contact> resultList=new ArrayList<Contact>(_cursor.getCount());
      Contact resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index2=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index3=_cursor.getColumnIndex("surname");
        int index4=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=_cursor.getColumnIndex("update_date");
        int index6=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.surname=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(_cursor.getString(index6)); }

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
   * <pre>SELECT id, birth_day, password, surname, type, update_date, update_time FROM contact WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Contact}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:password</dt><dd>is binded to method's parameter <strong>password</strong></dd>
   * 	<dt>:type</dt><dd>is binded to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to <code>:password</code>
   * @param type
   * 	is binded to <code>:type</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectCompactRaw(String password, ContactType type) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_COMPACT_RAW_SQL7;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Contact> resultList=new ArrayList<Contact>(_cursor.getCount());
      Contact resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index2=_cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index3=_cursor.getColumnIndex("surname");
        int index4=_cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=_cursor.getColumnIndex("update_date");
        int index6=_cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.birthDay=birthDayAdapter.toJava(_cursor.getLong(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setPassword(passwordAdapter.toJava(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.surname=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(_cursor.getInt(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(_cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET id=:id, type=:type WHERE id=${bean.id}  and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>:bean.type</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>:bean.password</dt><dd>is mapped to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>:bean.type</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactBean(Contact bean) {
    if (updateCompactBeanPreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="UPDATE contact SET id=?, type=? WHERE id=?  and password=? and type=?";
      updateCompactBeanPreparedStatement4 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateCompactBeanPreparedStatement4);
    _contentValues.put("id", bean.getId());
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE contact SET id=:id, type=:type WHERE id=?  and password=? and type=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateCompactBeanPreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET password=:password, type=:type WHERE id=:id</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>password</li>
   * 	<li>type</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as updated field <strong>password</strong>
   * @param type
   * 	is used as updated field <strong>type</strong>
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactRaw1(String password, ContactType type, long id) {
    if (updateCompactRaw1PreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="UPDATE contact SET password=?, type=? WHERE id=?";
      updateCompactRaw1PreparedStatement5 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateCompactRaw1PreparedStatement5);
    _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE contact SET password=:password, type=:type WHERE id=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateCompactRaw1PreparedStatement5, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=:password and type=:type</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:password</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>:type</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param password
   * 	is used as where parameter <strong>:password</strong>
   * @param type
   * 	is used as where parameter <strong>:type</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactRaw2(Date birthDay, String password, ContactType type, long id) {
    if (updateCompactRaw2PreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="UPDATE contact SET birth_day=?, id=? WHERE password=? and type=?";
      updateCompactRaw2PreparedStatement6 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateCompactRaw2PreparedStatement6);
    _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    _contentValues.put("id", id);

    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE contact SET birth_day=:birth_day, id=:id WHERE password=? and type=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateCompactRaw2PreparedStatement6, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE type=${bean.type}  and type=${bean.password}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>:bean.birthDay</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>:bean.password</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>:bean.type</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.type</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * 	<dt>:bean.password</dt><dd>is mapped to method's parameter <strong>bean.password</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLBean(Contact bean) {
    if (updateJQLBeanPreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="UPDATE contact SET birth_day=?, password=?, type=? WHERE type=?  and type=?";
      updateJQLBeanPreparedStatement7 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateJQLBeanPreparedStatement7);
    _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, bean.birthDay));
    _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));

    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE contact SET birth_day=:birth_day, password=:password, type=:type WHERE type=?  and type=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateJQLBeanPreparedStatement7, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=:password and type=:type</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:password</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>:type</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>:password</strong>
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param type
   * 	is used as where parameter <strong>:type</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLRaw(String password, Date birthDay, ContactType type, long id) {
    if (updateJQLRawPreparedStatement8==null) {
      // generate static SQL for statement
      String _sql="UPDATE contact SET birth_day=?, id=? WHERE password=? and type=?";
      updateJQLRawPreparedStatement8 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateJQLRawPreparedStatement8);
    _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    _contentValues.put("id", id);

    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE contact SET birth_day=:birth_day, id=:id WHERE password=? and type=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateJQLRawPreparedStatement8, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (:password, :type, :id)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>password</dt><dd>is binded to query's parameter <strong>:password</strong> and method's parameter <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is binded to query's parameter <strong>:type</strong> and method's parameter <strong>type</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>:id</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to column value <strong>password</strong>
   * @param type
   * 	is binded to column value <strong>type</strong>
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCompactRaw(String password, ContactType type, long id) {
    // Specialized Insert - InsertType - BEGIN
    if (insertCompactRawPreparedStatement9==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO contact (password, type, id) VALUES (?, ?, ?)";
      insertCompactRawPreparedStatement9 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertCompactRawPreparedStatement9);

    _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    _contentValues.put("id", id);

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
      Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertCompactRawPreparedStatement9, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (id, type) VALUES (:bean.id, :bean.type)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>:bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCompactBean(Contact bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertCompactBeanPreparedStatement10==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO contact (id, type) VALUES (?, ?)";
      insertCompactBeanPreparedStatement10 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertCompactBeanPreparedStatement10);
    _contentValues.put("id", bean.getId());
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));

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
      Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertCompactBeanPreparedStatement10, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.setId(result);

    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (:bean.password, :bean.type, :bean.id)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>:bean.password</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>:bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertJQLBean(Contact bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertJQLBeanPreparedStatement11==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO contact (password, type, id) VALUES (?, ?, ?)";
      insertJQLBeanPreparedStatement11 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertJQLBeanPreparedStatement11);
    _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    _contentValues.put("id", bean.getId());

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
      Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertJQLBeanPreparedStatement11, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.setId(result);

    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (:password, :type, :id)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>password</dt><dd>is binded to query's parameter <strong>:password</strong> and method's parameter <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is binded to query's parameter <strong>:type</strong> and method's parameter <strong>type</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>:id</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to column value <strong>password</strong>
   * @param type
   * 	is binded to column value <strong>type</strong>
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertJQLRaw(String password, Date birthDay, ContactType type, long id) {
    // Specialized Insert - InsertType - BEGIN
    if (insertJQLRawPreparedStatement12==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO contact (password, type, id) VALUES (?, ?, ?)";
      insertJQLRawPreparedStatement12 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertJQLRawPreparedStatement12);

    _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    _contentValues.put("id", id);

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
      Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertJQLRawPreparedStatement12, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    try {
      if (deleteCompactBeanPreparedStatement0!=null) {
        deleteCompactBeanPreparedStatement0.close();
        deleteCompactBeanPreparedStatement0=null;
      }
      if (deleteCompactRawPreparedStatement1!=null) {
        deleteCompactRawPreparedStatement1.close();
        deleteCompactRawPreparedStatement1=null;
      }
      if (deleteJQLBeanPreparedStatement2!=null) {
        deleteJQLBeanPreparedStatement2.close();
        deleteJQLBeanPreparedStatement2=null;
      }
      if (deleteJQLRawPreparedStatement3!=null) {
        deleteJQLRawPreparedStatement3.close();
        deleteJQLRawPreparedStatement3=null;
      }
      if (updateCompactBeanPreparedStatement4!=null) {
        updateCompactBeanPreparedStatement4.close();
        updateCompactBeanPreparedStatement4=null;
      }
      if (updateCompactRaw1PreparedStatement5!=null) {
        updateCompactRaw1PreparedStatement5.close();
        updateCompactRaw1PreparedStatement5=null;
      }
      if (updateCompactRaw2PreparedStatement6!=null) {
        updateCompactRaw2PreparedStatement6.close();
        updateCompactRaw2PreparedStatement6=null;
      }
      if (updateJQLBeanPreparedStatement7!=null) {
        updateJQLBeanPreparedStatement7.close();
        updateJQLBeanPreparedStatement7=null;
      }
      if (updateJQLRawPreparedStatement8!=null) {
        updateJQLRawPreparedStatement8.close();
        updateJQLRawPreparedStatement8=null;
      }
      if (insertCompactRawPreparedStatement9!=null) {
        insertCompactRawPreparedStatement9.close();
        insertCompactRawPreparedStatement9=null;
      }
      if (insertCompactBeanPreparedStatement10!=null) {
        insertCompactBeanPreparedStatement10.close();
        insertCompactBeanPreparedStatement10=null;
      }
      if (insertJQLBeanPreparedStatement11!=null) {
        insertJQLBeanPreparedStatement11.close();
        insertJQLBeanPreparedStatement11=null;
      }
      if (insertJQLRawPreparedStatement12!=null) {
        insertJQLRawPreparedStatement12.close();
        insertJQLRawPreparedStatement12=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
