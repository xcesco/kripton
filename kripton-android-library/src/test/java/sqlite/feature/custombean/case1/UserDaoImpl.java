package sqlite.feature.custombean.case1;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>UserDao</code>
 * </p>
 *
 *  @see User
 *  @see UserDao
 *  @see UserTable
 */
public class UserDaoImpl extends Dao implements UserDao {
  /**
   * SQL definition for method loadAllUsers
   */
  private static final String LOAD_ALL_USERS_SQL13 = "SELECT id, age, last_name, name FROM user";

  /**
   * SQL definition for method loadUserById
   */
  private static final String LOAD_USER_BY_ID_SQL14 = "SELECT id, age, last_name, name FROM user WHERE id = ?";

  /**
   * SQL definition for method findUserByNameAndLastName
   */
  private static final String FIND_USER_BY_NAME_AND_LAST_NAME_SQL15 = "SELECT id, age, last_name, name FROM user WHERE name = ? and last_name = ?";

  private static SupportSQLiteStatement insertUserPreparedStatement0;

  private static SupportSQLiteStatement deleteUserPreparedStatement1;

  private static SupportSQLiteStatement deleteUsersByNamePreparedStatement2;

  private static SupportSQLiteStatement insertOrReplaceUsersPreparedStatement3;

  private static SupportSQLiteStatement deleteUsersPreparedStatement4;

  /**
   * SQL definition for method findUsersYoungerThan
   */
  private static final String FIND_USERS_YOUNGER_THAN_SQL16 = "SELECT id, age, last_name, name FROM user WHERE age = ?";

  /**
   * SQL definition for method findUsersYoungerThanSolution
   */
  private static final String FIND_USERS_YOUNGER_THAN_SOLUTION_SQL17 = "SELECT id, age, last_name, name FROM user WHERE age < ?";

  private static SupportSQLiteStatement deleteAllPreparedStatement5;

  public UserDaoImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, age, last_name, name FROM user</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>age</dt><dd>is associated to bean's property <strong>age</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> loadAllUsers() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=LOAD_ALL_USERS_SQL13;
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("age");
        int index2=_cursor.getColumnIndex("last_name");
        int index3=_cursor.getColumnIndex("name");

        do
         {
          resultBean=new User();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.age=_cursor.getInt(index1); }
          if (!_cursor.isNull(index2)) { resultBean.lastName=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }

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
   * <pre>SELECT id, age, last_name, name FROM user WHERE id = :id</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>age</dt><dd>is associated to bean's property <strong>age</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
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
  public User loadUserById(int id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=LOAD_USER_BY_ID_SQL14;
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
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("age");
        int index2=_cursor.getColumnIndex("last_name");
        int index3=_cursor.getColumnIndex("name");

        resultBean=new User();

        resultBean.id=_cursor.getString(index0);
        if (!_cursor.isNull(index1)) { resultBean.age=_cursor.getInt(index1); }
        if (!_cursor.isNull(index2)) { resultBean.lastName=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, age, last_name, name FROM user WHERE name = :firstName and last_name = :lastName</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>age</dt><dd>is associated to bean's property <strong>age</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:firstName</dt><dd>is binded to method's parameter <strong>firstName</strong></dd>
   * 	<dt>:lastName</dt><dd>is binded to method's parameter <strong>lastName</strong></dd>
   * </dl>
   *
   * @param firstName
   * 	is binded to <code>:firstName</code>
   * @param lastName
   * 	is binded to <code>:lastName</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> findUserByNameAndLastName(String firstName, String lastName) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_USER_BY_NAME_AND_LAST_NAME_SQL15;
    // add where arguments
    _contentValues.addWhereArgs((firstName==null?"":firstName));
    _contentValues.addWhereArgs((lastName==null?"":lastName));
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

      ArrayList<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("age");
        int index2=_cursor.getColumnIndex("last_name");
        int index3=_cursor.getColumnIndex("name");

        do
         {
          resultBean=new User();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.age=_cursor.getInt(index1); }
          if (!_cursor.isNull(index2)) { resultBean.lastName=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO user (id, age, last_name, name) VALUES (:user.id, :user.age, :user.lastName, :user.name)</pre>
   *
   * <p><code>user.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:user.id</strong></dd>
   * 	<dt>age</dt><dd>is mapped to <strong>:user.age</strong></dd>
   * 	<dt>last_name</dt><dd>is mapped to <strong>:user.lastName</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:user.name</strong></dd>
   * </dl>
   *
   * @param user
   * 	is mapped to parameter <strong>user</strong>
   *
   */
  @Override
  public void insertUser(User user) {
    // Specialized Insert - InsertType - BEGIN
    if (insertUserPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO user (id, age, last_name, name) VALUES (?, ?, ?, ?)";
      insertUserPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertUserPreparedStatement0);
    _contentValues.put("id", user.id);
    _contentValues.put("age", user.age);
    _contentValues.put("last_name", user.lastName);
    _contentValues.put("name", user.name);

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
      Logger.info("INSERT OR IGNORE INTO user (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertUserPreparedStatement0, _contentValues);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM user</pre>
   *
   * @param user
   * 	is used as <code>:user</code>
   */
  @Override
  public void deleteUser(User user) {
    if (deleteUserPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM user";
      deleteUserPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteUserPreparedStatement1);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM user");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteUserPreparedStatement1, _contentValues);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM user WHERE name like :badName OR last_name like :badName</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:badName</dt><dd>is mapped to method's parameter <strong>badName</strong></dd>
   * 	<dt>:badName</dt><dd>is mapped to method's parameter <strong>badName</strong></dd>
   * </dl>
   *
   * @param badName
   * 	is used as where parameter <strong>:badName</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteUsersByName(String badName) {
    if (deleteUsersByNamePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM user WHERE name like ? OR last_name like ?";
      deleteUsersByNamePreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteUsersByNamePreparedStatement2);
    _contentValues.addWhereArgs((badName==null?"":badName));
    _contentValues.addWhereArgs((badName==null?"":badName));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM user WHERE name like ? OR last_name like ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteUsersByNamePreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR IGNORE INTO user (id, age, last_name, name) VALUES (:users.id, :users.age, :users.lastName, :users.name)</pre>
   *
   * <p><code>users.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:users.id</strong></dd>
   * 	<dt>age</dt><dd>is mapped to <strong>:users.age</strong></dd>
   * 	<dt>last_name</dt><dd>is mapped to <strong>:users.lastName</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:users.name</strong></dd>
   * </dl>
   *
   * @param users
   * 	is mapped to parameter <strong>users</strong>
   *
   */
  @Override
  public void insertOrReplaceUsers(User users) {
    // Specialized Insert - InsertType - BEGIN
    if (insertOrReplaceUsersPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="INSERT OR IGNORE INTO user (id, age, last_name, name) VALUES (?, ?, ?, ?)";
      insertOrReplaceUsersPreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOrReplaceUsersPreparedStatement3);
    _contentValues.put("id", users.id);
    _contentValues.put("age", users.age);
    _contentValues.put("last_name", users.lastName);
    _contentValues.put("name", users.name);

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
      Logger.info("INSERT OR IGNORE INTO user (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertOrReplaceUsersPreparedStatement3, _contentValues);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM user WHERE id=:user.id</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:user.id</dt><dd>is mapped to method's parameter <strong>user.id</strong></dd>
   * </dl>
   *
   * @param user
   * 	is used as <code>:user</code>
   */
  @Override
  public void deleteUsers(User user) {
    if (deleteUsersPreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM user WHERE id=?";
      deleteUsersPreparedStatement4 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteUsersPreparedStatement4);
    _contentValues.addWhereArgs((user.id==null?"":user.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM user WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteUsersPreparedStatement4, _contentValues);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, age, last_name, name FROM user WHERE age = :age</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>age</dt><dd>is associated to bean's property <strong>age</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:age</dt><dd>is binded to method's parameter <strong>age</strong></dd>
   * </dl>
   *
   * @param age
   * 	is binded to <code>:age</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> findUsersYoungerThan(int age) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_USERS_YOUNGER_THAN_SQL16;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(age));
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

      ArrayList<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("age");
        int index2=_cursor.getColumnIndex("last_name");
        int index3=_cursor.getColumnIndex("name");

        do
         {
          resultBean=new User();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.age=_cursor.getInt(index1); }
          if (!_cursor.isNull(index2)) { resultBean.lastName=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }

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
   * <pre>SELECT id, age, last_name, name FROM user WHERE age < :age</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>age</dt><dd>is associated to bean's property <strong>age</strong></dd>
   * 	<dt>last_name</dt><dd>is associated to bean's property <strong>lastName</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:age</dt><dd>is binded to method's parameter <strong>age</strong></dd>
   * </dl>
   *
   * @param age
   * 	is binded to <code>:age</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> findUsersYoungerThanSolution(int age) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_USERS_YOUNGER_THAN_SOLUTION_SQL17;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(age));
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

      ArrayList<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("age");
        int index2=_cursor.getColumnIndex("last_name");
        int index3=_cursor.getColumnIndex("name");

        do
         {
          resultBean=new User();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.age=_cursor.getInt(index1); }
          if (!_cursor.isNull(index2)) { resultBean.lastName=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.name=_cursor.getString(index3); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM user</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM user";
      deleteAllPreparedStatement5 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement5);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM user");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deleteAllPreparedStatement5, _contentValues);
  }

  public static void clearCompiledStatements() {
    try {
      if (insertUserPreparedStatement0!=null) {
        insertUserPreparedStatement0.close();
        insertUserPreparedStatement0=null;
      }
      if (deleteUserPreparedStatement1!=null) {
        deleteUserPreparedStatement1.close();
        deleteUserPreparedStatement1=null;
      }
      if (deleteUsersByNamePreparedStatement2!=null) {
        deleteUsersByNamePreparedStatement2.close();
        deleteUsersByNamePreparedStatement2=null;
      }
      if (insertOrReplaceUsersPreparedStatement3!=null) {
        insertOrReplaceUsersPreparedStatement3.close();
        insertOrReplaceUsersPreparedStatement3=null;
      }
      if (deleteUsersPreparedStatement4!=null) {
        deleteUsersPreparedStatement4.close();
        deleteUsersPreparedStatement4=null;
      }
      if (deleteAllPreparedStatement5!=null) {
        deleteAllPreparedStatement5.close();
        deleteAllPreparedStatement5=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
