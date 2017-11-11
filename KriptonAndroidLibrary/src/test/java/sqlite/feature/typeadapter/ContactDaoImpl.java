package sqlite.feature.typeadapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
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
public class ContactDaoImpl extends AbstractDao implements ContactDao {
  protected String SELECT_BY_SURNAME_WITH_ADAPTER_SQL1 = "SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE surname=?";

  protected String SELECT_BY_SURNAME_SQL2 = "SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE surname=?";

  private SQLiteStatement deleteCompactBeanPreparedStatement0;

  private SQLiteStatement deleteCompactRawPreparedStatement1;

  private SQLiteStatement deleteJQLBeanPreparedStatement2;

  private SQLiteStatement deleteJQLRawPreparedStatement3;

  protected String SELECT_COMPACT_BEAN_SQL3 = "SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE id=?  and type=?";

  protected String SELECT_J_Q_L_BEAN_LISTENER_SQL4 = "SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE id=? and password=? and type=?";

  protected String SELEC_J_Q_L_BEAN_SQL5 = "SELECT birth_day, password, type FROM contact WHERE id=? and password=? and type=?";

  protected String SELECT_J_Q_L_RAW_SQL6 = "SELECT * FROM contact WHERE password=? and type=?";

  protected String SELECT_COMPACT_RAW_SQL7 = "SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE password=? and type=?";

  private SQLiteStatement updateCompactBeanPreparedStatement4;

  private SQLiteStatement updateCompactRaw1PreparedStatement5;

  private SQLiteStatement updateCompactRaw2PreparedStatement6;

  private SQLiteStatement updateJQLBeanPreparedStatement7;

  private SQLiteStatement updateJQLRawPreparedStatement8;

  private SQLiteStatement insertCompactRawPreparedStatement9;

  private SQLiteStatement insertCompactBeanPreparedStatement10;

  private SQLiteStatement insertJQLBeanPreparedStatement11;

  private SQLiteStatement insertJQLRawPreparedStatement12;

  public ContactDaoImpl(BindContactDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE surname=${dummyTest}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${dummyTest}</dt><dd>is binded to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param dummy
   * 	is binded to <code>${dummyTest}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectBySurnameWithAdapter(String dummy) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_SURNAME_WITH_ADAPTER_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, dummy));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Contact> resultList=new ArrayList<Contact>(cursor.getCount());
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index3=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index4=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=cursor.getColumnIndex("update_date");
        int index6=cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index3))); }
          if (!cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index4)); }
          if (!cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE surname=${dummy}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is binded to method's parameter <strong>dummy</strong></dd>
   * </dl>
   *
   * @param dummy
   * 	is binded to <code>${dummy}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectBySurname(String dummy) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_SURNAME_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((dummy==null?"":dummy));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Contact> resultList=new ArrayList<Contact>(cursor.getCount());
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index3=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index4=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=cursor.getColumnIndex("update_date");
        int index6=cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index3))); }
          if (!cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index4)); }
          if (!cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteCompactBean(Contact bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteCompactBeanPreparedStatement0==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM contact WHERE id=? and type=?";
      deleteCompactBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteCompactBeanPreparedStatement0, _contentValues);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM contact WHERE password=${password} and type=${type}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   */
  @Override
  public void deleteCompactRaw(String password, ContactType type) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteCompactRawPreparedStatement1==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" password=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM contact WHERE password=? and type=?";
      deleteCompactRawPreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteCompactRawPreparedStatement1, _contentValues);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteJQLBean(Contact bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteJQLBeanPreparedStatement2==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM contact WHERE id=? and type=?";
      deleteJQLBeanPreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteJQLBeanPreparedStatement2, _contentValues);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM contact WHERE id=${id} and type=${type}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteJQLRaw(long id, ContactType type) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(id));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteJQLRawPreparedStatement3==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM contact WHERE id=? and type=?";
      deleteJQLRawPreparedStatement3 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteJQLRawPreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE id=${bean.id}  and type=${bean.type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectCompactBean(Contact bean) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_COMPACT_BEAN_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Contact> resultList=new ArrayList<Contact>(cursor.getCount());
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index3=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index4=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=cursor.getColumnIndex("update_date");
        int index6=cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index3))); }
          if (!cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index4)); }
          if (!cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is binded to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param listener
   * 	is the Contact listener
   */
  @Override
  public void selectJQLBeanListener(Contact bean, OnReadBeanListener<Contact> listener) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_J_Q_L_BEAN_LISTENER_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END
      Contact resultBean=new Contact();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index3=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index4=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=cursor.getColumnIndex("update_date");
        int index6=cursor.getColumnIndex("update_time");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.surname=null;
          resultBean.birthDay=null;
          resultBean.setPassword(null);
          resultBean.type=null;
          resultBean.updateDate=null;
          resultBean.updateTime=null;

          // generate mapping
          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index3))); }
          if (!cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index4)); }
          if (!cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(cursor.getString(index6)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT birth_day, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}</pre>
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
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is binded to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selecJQLBean(Contact bean) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELEC_J_Q_L_BEAN_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Contact> resultList=new ArrayList<Contact>(cursor.getCount());
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index1=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index2=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);

        do
         {
          resultBean=new Contact();

          if (!cursor.isNull(index0)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index1))); }
          if (!cursor.isNull(index2)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index2)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM contact WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is binded to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is binded to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to <code>${password}</code>
   * @param type
   * 	is binded to <code>${type}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectJQLRaw(String password, ContactType type) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_J_Q_L_RAW_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Contact> resultList=new ArrayList<Contact>(cursor.getCount());
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index3=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index4=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=cursor.getColumnIndex("update_date");
        int index6=cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index3))); }
          if (!cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index4)); }
          if (!cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, surname, birth_day, password, type, update_date, update_time FROM contact WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * 	<dt>update_date</dt><dd>is associated to bean's property <strong>updateDate</strong></dd>
   * 	<dt>update_time</dt><dd>is associated to bean's property <strong>updateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is binded to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is binded to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to <code>${password}</code>
   * @param type
   * 	is binded to <code>${type}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectCompactRaw(String password, ContactType type) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_COMPACT_RAW_SQL7;
    // add where arguments
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Contact> resultList=new ArrayList<Contact>(cursor.getCount());
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("birth_day");
        DateAdapterType birthDayAdapter=SQLTypeAdapterUtils.getAdapter(DateAdapterType.class);
        int index3=cursor.getColumnIndex("password");
        PasswordAdapterType passwordAdapter=SQLTypeAdapterUtils.getAdapter(PasswordAdapterType.class);
        int index4=cursor.getColumnIndex("type");
        EnumAdapterType typeAdapter=SQLTypeAdapterUtils.getAdapter(EnumAdapterType.class);
        int index5=cursor.getColumnIndex("update_date");
        int index6=cursor.getColumnIndex("update_time");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=birthDayAdapter.toJava(cursor.getLong(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setPassword(passwordAdapter.toJava(cursor.getBlob(index3))); }
          if (!cursor.isNull(index4)) { resultBean.type=typeAdapter.toJava(cursor.getInt(index4)); }
          if (!cursor.isNull(index5)) { resultBean.updateDate=SQLDateUtils.read(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.updateTime=SQLTimeUtils.read(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE contact SET id=:id, type=:type WHERE id=${bean.id}  and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is mapped to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactBean(Contact bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("id", bean.getId());
    if (bean.type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      _contentValues.putNull("type");
    }

    _contentValues.addWhereArgs(String.valueOf(bean.getId()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateCompactBeanPreparedStatement4==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?  and password=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE contact SET id=?, type=? WHERE id=?  and password=? and type=?";
      updateCompactBeanPreparedStatement4 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateCompactBeanPreparedStatement4, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET password=:password, type=:type WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>password</li>
   * 	<li>type</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as updated field <strong>password</strong>
   * @param type
   * 	is used as updated field <strong>type</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactRaw1(String password, ContactType type, long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (password!=null) {
      _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    } else {
      _contentValues.putNull("password");
    }
    if (type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    } else {
      _contentValues.putNull("type");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateCompactRaw1PreparedStatement5==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE contact SET password=?, type=? WHERE id=?";
      updateCompactRaw1PreparedStatement5 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateCompactRaw1PreparedStatement5, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactRaw2(Date birthDay, String password, ContactType type, long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (birthDay!=null) {
      _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }
    _contentValues.put("id", id);

    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateCompactRaw2PreparedStatement6==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" password=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE contact SET birth_day=?, id=? WHERE password=? and type=?";
      updateCompactRaw2PreparedStatement6 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=? and type=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateCompactRaw2PreparedStatement6, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE type=${bean.type}  and type=${bean.password}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>${bean.password}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is mapped to method's parameter <strong>bean.password</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLBean(Contact bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.birthDay!=null) {
      _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, bean.birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }
    if (bean.getPassword()!=null) {
      _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, bean.getPassword()));
    } else {
      _contentValues.putNull("password");
    }
    if (bean.type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      _contentValues.putNull("type");
    }

    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateJQLBeanPreparedStatement7==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" type=?  and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE contact SET birth_day=?, password=?, type=? WHERE type=?  and type=?";
      updateJQLBeanPreparedStatement7 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE type=?  and type=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateJQLBeanPreparedStatement7, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLRaw(String password, Date birthDay, ContactType type, long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (birthDay!=null) {
      _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }
    _contentValues.put("id", id);

    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _contentValues.addWhereArgs(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateJQLRawPreparedStatement8==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" password=? and type=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE contact SET birth_day=?, id=? WHERE password=? and type=?";
      updateJQLRawPreparedStatement8 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=? and type=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateJQLRawPreparedStatement8, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (${password}, ${type}, ${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>password</dt><dd>is binded to query's parameter <strong>${password}</strong> and method's parameter <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is binded to query's parameter <strong>${type}</strong> and method's parameter <strong>type</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
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
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (password!=null) {
      _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    } else {
      _contentValues.putNull("password");
    }
    if (type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    } else {
      _contentValues.putNull("type");
    }
    _contentValues.put("id", id);

    // log section BEGIN
    if (this.dataSource.logEnabled) {
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

    }
    // log section END
    // insert operation
    if (insertCompactRawPreparedStatement9==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO contact (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertCompactRawPreparedStatement9 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertCompactRawPreparedStatement9, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO contact (id, type) VALUES (${bean.id}, ${bean.type})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCompactBean(Contact bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("id", bean.getId());
    if (bean.type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      _contentValues.putNull("type");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
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

    }
    // log section END
    // insert operation
    if (insertCompactBeanPreparedStatement10==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO contact (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertCompactBeanPreparedStatement10 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertCompactBeanPreparedStatement10, _contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO contact (password, type, id) VALUES (${bean.password}, ${bean.type}, ${bean.id})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>${bean.password}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertJQLBean(Contact bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.getPassword()!=null) {
      _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, bean.getPassword()));
    } else {
      _contentValues.putNull("password");
    }
    if (bean.type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      _contentValues.putNull("type");
    }
    _contentValues.put("id", bean.getId());

    // log section BEGIN
    if (this.dataSource.logEnabled) {
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

    }
    // log section END
    // insert operation
    if (insertJQLBeanPreparedStatement11==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO contact (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertJQLBeanPreparedStatement11 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertJQLBeanPreparedStatement11, _contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (${password}, ${type}, ${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>password</dt><dd>is binded to query's parameter <strong>${password}</strong> and method's parameter <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is binded to query's parameter <strong>${type}</strong> and method's parameter <strong>type</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
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
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (password!=null) {
      _contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    } else {
      _contentValues.putNull("password");
    }
    if (birthDay!=null) {
      _contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }
    if (type!=null) {
      _contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    } else {
      _contentValues.putNull("type");
    }
    _contentValues.put("id", id);

    // log section BEGIN
    if (this.dataSource.logEnabled) {
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

    }
    // log section END
    // insert operation
    if (insertJQLRawPreparedStatement12==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO contact (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertJQLRawPreparedStatement12 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertJQLRawPreparedStatement12, _contentValues);
    return result;
  }

  public void clearCompiledStatements() {
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
  }
}
