package sqlite.quickstart.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.quickstart.model.User;
import sqlite.quickstart.model.UserTable;

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
  private static SQLiteStatement insertPreparedStatement0;

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL1 = "SELECT id, address, company, email, name, phone, username, website FROM user ORDER BY username asc";

  /**
   * SQL definition for method selectById
   */
  private static final String SELECT_BY_ID_SQL2 = "SELECT id, address, company, email, name, phone, username, website FROM user WHERE id = ?";

  public UserDaoImpl(BindQuickStartDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO user (id, address, company, email, name, phone, username, website) VALUES (:bean.id, :bean.address, :bean.company, :bean.email, :bean.name, :bean.phone, :bean.username, :bean.website)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:bean.id</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>:bean.address</strong></dd>
   * 	<dt>company</dt><dd>is mapped to <strong>:bean.company</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>:bean.email</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>:bean.phone</strong></dd>
   * 	<dt>username</dt><dd>is mapped to <strong>:bean.username</strong></dd>
   * 	<dt>website</dt><dd>is mapped to <strong>:bean.website</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(User bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO user (id, address, company, email, name, phone, username, website) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("id", bean.id);
    _contentValues.put("address", UserTable.serializeAddress(bean.address));
    _contentValues.put("company", UserTable.serializeCompany(bean.company));
    _contentValues.put("email", bean.email);
    _contentValues.put("name", bean.name);
    _contentValues.put("phone", bean.phone);
    _contentValues.put("username", bean.username);
    _contentValues.put("website", bean.website);

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
      Logger.info("INSERT INTO user (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, address, company, email, name, phone, username, website FROM user ORDER BY username asc</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * 	<dt>website</dt><dd>is associated to bean's property <strong>website</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
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

      ArrayList<User> resultList=new ArrayList<User>(_cursor.getCount());
      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("address");
        int index2=_cursor.getColumnIndex("company");
        int index3=_cursor.getColumnIndex("email");
        int index4=_cursor.getColumnIndex("name");
        int index5=_cursor.getColumnIndex("phone");
        int index6=_cursor.getColumnIndex("username");
        int index7=_cursor.getColumnIndex("website");

        do
         {
          resultBean=new User();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.address=UserTable.parseAddress(_cursor.getBlob(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.company=UserTable.parseCompany(_cursor.getBlob(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.email=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.name=_cursor.getString(index4); }
          if (!_cursor.isNull(index5)) { resultBean.phone=_cursor.getString(index5); }
          if (!_cursor.isNull(index6)) { resultBean.username=_cursor.getString(index6); }
          if (!_cursor.isNull(index7)) { resultBean.website=_cursor.getString(index7); }

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
   * <pre>SELECT id, address, company, email, name, phone, username, website FROM user WHERE id = ${value}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link User}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * 	<dt>website</dt><dd>is associated to bean's property <strong>website</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:value</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public User selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL2;
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

      User resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("address");
        int index2=_cursor.getColumnIndex("company");
        int index3=_cursor.getColumnIndex("email");
        int index4=_cursor.getColumnIndex("name");
        int index5=_cursor.getColumnIndex("phone");
        int index6=_cursor.getColumnIndex("username");
        int index7=_cursor.getColumnIndex("website");

        resultBean=new User();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.address=UserTable.parseAddress(_cursor.getBlob(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.company=UserTable.parseCompany(_cursor.getBlob(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.email=_cursor.getString(index3); }
        if (!_cursor.isNull(index4)) { resultBean.name=_cursor.getString(index4); }
        if (!_cursor.isNull(index5)) { resultBean.phone=_cursor.getString(index5); }
        if (!_cursor.isNull(index6)) { resultBean.username=_cursor.getString(index6); }
        if (!_cursor.isNull(index7)) { resultBean.website=_cursor.getString(index7); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
