package sqlite.feature.jql.persistence;

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
import sqlite.feature.jql.entities.Child;

/**
 * <p>
 * DAO implementation for entity <code>Child</code>, based on interface <code>DaoChild</code>
 * </p>
 *
 *  @see Child
 *  @see DaoChild
 *  @see sqlite.feature.jql.entities.ChildTable
 */
public class DaoChildImpl extends Dao implements DaoChild {
  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL1 = "SELECT _id, name, parent_id FROM child";

  private static SQLiteStatement insertBeanPreparedStatement0;

  /**
   * SQL definition for method selectByParent
   */
  private static final String SELECT_BY_PARENT_SQL2 = "select * from child where parent_id in (select _id from person where _id=?)";

  /**
   * SQL definition for method selectByParent2
   */
  private static final String SELECT_BY_PARENT2_SQL3 = "select count(*) from child where parent_id in (select _id from person where _id=?)";

  /**
   * SQL definition for method selectByParentId
   */
  private static final String SELECT_BY_PARENT_ID_SQL4 = "SELECT _id, name, parent_id FROM child WHERE parent_id=?";

  private static SQLiteStatement insertByCopy3PreparedStatement1;

  private static SQLiteStatement insertByCopyPreparedStatement2;

  private static SQLiteStatement updateJQLPreparedStatement3;

  public DaoChildImpl(BindFamilyDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, name, parent_id FROM child</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Child}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectAll() {
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

      ArrayList<Child> resultList=new ArrayList<Child>(_cursor.getCount());
      Child resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("_id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=_cursor.getLong(index0);
          resultBean.name=_cursor.getString(index1);
          if (!_cursor.isNull(index2)) { resultBean.parentId=_cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO child (name, parent_id) VALUES (:name, :parentId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>:bean.parentId</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public Child insertBean(Child bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO child (name, parent_id) VALUES (?, ?)";
      insertBeanPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBeanPreparedStatement0);
    _contentValues.put("name", bean.name);
    _contentValues.put("parent_id", bean.parentId);

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
      Logger.info("INSERT INTO child (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertBeanPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return bean;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from child where parent_id in (select _id from person where _id=${parentId})</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Child}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:parentId</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>:parentId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectByParent(long parentId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_PARENT_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(parentId));
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

      ArrayList<Child> resultList=new ArrayList<Child>(_cursor.getCount());
      Child resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("_id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=_cursor.getLong(index0);
          resultBean.name=_cursor.getString(index1);
          if (!_cursor.isNull(index2)) { resultBean.parentId=_cursor.getLong(index2); }

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
   * <pre>select count(*) from child where parent_id in (select _id from person where _id=${parentId})</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Child}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:parentId</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>:parentId</code>
   * @return single value extracted by query.
   */
  @Override
  public int selectByParent2(long parentId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_PARENT2_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(parentId));
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
      // Specialized part - SelectScalarHelper - BEGIN
      int result=0;

      if (_cursor.moveToFirst()) {

        if (_cursor.isNull(0)) { return 0; }
        result=_cursor.getInt(0);
      }
      return result;
    }
    // Specialized part - SelectScalarHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, name, parent_id FROM child WHERE parent_id=${parentId}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Child}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:parentId</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>:parentId</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectByParentId(long parentId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_PARENT_ID_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(parentId));
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

      ArrayList<Child> resultList=new ArrayList<Child>(_cursor.getCount());
      Child resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("_id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=_cursor.getLong(index0);
          resultBean.name=_cursor.getString(index1);
          if (!_cursor.isNull(index2)) { resultBean.parentId=_cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>insert into child (name, parent_id) select name, parent_id from child where _id=:parentId or _id=:parent or _id=:aliasParentId</pre>
   *
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>parentId</dt><dd>is binded to query's parameter <strong>:parentId</strong></dd>
   * 	<dt>parent</dt><dd>is binded to query's parameter <strong>:parent</strong></dd>
   * 	<dt>aliasParentId</dt><dd>is binded to query's parameter <strong>:aliasParentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is used as parameter
   * @param aliasParentId
   * 	is used as parameter
   * @param parent
   * 	is used as parameter
   *
   */
  @Override
  public void insertByCopy(long parentId, long aliasParentId, long parent) {
    // Specialized Insert - InsertType - BEGIN
    KriptonContentValues _contentValues=contentValuesForUpdate();
    // build where condition
    _contentValues.addWhereArgs(String.valueOf(parentId));
    _contentValues.addWhereArgs(String.valueOf(parent));
    _contentValues.addWhereArgs(String.valueOf(aliasParentId));
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 

      Logger.info("insert into child (name, parent_id) select name, parent_id from child where _id=? or _id=? or _id=?");

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

    database().execSQL("insert into child (name, parent_id) select name, parent_id from child where _id=? or _id=? or _id=?", _contentValues.whereArgsAsArray());
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>insert into child (name, parent_id) values (:bean.name, :bean.parentId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>:bean.parentId</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertByCopy3(Child bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertByCopy3PreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="insert into child (name, parent_id) values (?, ?)";
      insertByCopy3PreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertByCopy3PreparedStatement1);
    _contentValues.put("name", bean.name);
    _contentValues.put("parent_id", bean.parentId);

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
      Logger.info("insert into child (%s) values (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertByCopy3PreparedStatement1, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO child (parent_id, name) VALUES (:parentId, :name)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>parentId</dt><dd>is binded to query's parameter <strong>:parentId</strong> and method's parameter <strong>parentId</strong></dd>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to column value <strong>parent_id</strong>
   * @param name
   * 	is binded to column value <strong>name</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertByCopy(long parentId, String name) {
    // Specialized Insert - InsertType - BEGIN
    if (insertByCopyPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO child (parent_id, name) VALUES (?, ?)";
      insertByCopyPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertByCopyPreparedStatement2);

    _contentValues.put("parent_id", parentId);
    _contentValues.put("name", name);

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
      Logger.info("INSERT INTO child (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertByCopyPreparedStatement2, _contentValues);
    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>update or replace child set name=:name where parent_id=:a</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:a</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is used as where parameter <strong>:a</strong>
   * @param name
   * 	is used as updated field <strong>name</strong>
   */
  @Override
  public void updateJQL(long parentId, String name) {
    if (updateJQLPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="update or replace child set name=? where parent_id=?";
      updateJQLPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateJQLPreparedStatement3);
    _contentValues.put("name", name);

    _contentValues.addWhereArgs(String.valueOf(parentId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("update or replace child set name=:name where parent_id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updateJQLPreparedStatement3, _contentValues);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>update or replace child set parent_id=:parentId, name=(select _id from person where _id=:parentId )  where parent_id=:parentId</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>parent_id</li>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Parameters:</h2>
   * <dl>
   * 	<dt>:parentId</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * 	<dt>:parentId</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * 	<dt>:parentId</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is used as for parameter <strong>parentId</strong>
   */
  @Override
  public void updateJQL2(long parentId) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("parent_id", parentId);
    // build where condition
    _contentValues.addWhereArgs(String.valueOf(parentId));
    _contentValues.addWhereArgs(String.valueOf(parentId));
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 

      Logger.info("update or replace child set parent_id=${parentId}, name=(select _id from person where _id=? )  where parent_id=?");

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

    database().execSQL("update or replace child set parent_id=?, name=(select _id from person where _id=? )  where parent_id=?", _contentValues.whereArgsAsArray());
  }

  public static void clearCompiledStatements() {
    if (insertBeanPreparedStatement0!=null) {
      insertBeanPreparedStatement0.close();
      insertBeanPreparedStatement0=null;
    }
    if (insertByCopy3PreparedStatement1!=null) {
      insertByCopy3PreparedStatement1.close();
      insertByCopy3PreparedStatement1=null;
    }
    if (insertByCopyPreparedStatement2!=null) {
      insertByCopyPreparedStatement2.close();
      insertByCopyPreparedStatement2=null;
    }
    if (updateJQLPreparedStatement3!=null) {
      updateJQLPreparedStatement3.close();
      updateJQLPreparedStatement3=null;
    }
  }
}
