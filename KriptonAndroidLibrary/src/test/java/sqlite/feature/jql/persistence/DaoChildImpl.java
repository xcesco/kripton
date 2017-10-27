package sqlite.feature.jql.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class DaoChildImpl extends AbstractDao implements DaoChild {
  public DaoChildImpl(BindFamilyDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, name, parent_id FROM child</pre>
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT _id, name, parent_id FROM child");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=cursor.getLong(index0);
          resultBean.name=cursor.getString(index1);
          if (!cursor.isNull(index2)) { resultBean.parentId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO child (name, parent_id) VALUES (${name}, ${parentId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>${bean.parentId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public Child insertBean(Child bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }
    contentValues.put("parent_id", bean.parentId);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO child (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("child", null, contentValues);
    bean.id=result;

    return bean;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from child where parent_id in (select _id from person where _id=${parentId})</pre>
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
   * 	<dt>${parentId}</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>${parentId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectByParent(long parentId) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("select * from child");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where parent_id in (select _id from person where _id=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=cursor.getLong(index0);
          resultBean.name=cursor.getString(index1);
          if (!cursor.isNull(index2)) { resultBean.parentId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select count(*) from child where parent_id in (select _id from person where _id=${parentId})</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>count(*)</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${parentId}</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>${parentId}</code>
   * @return single value extracted by query.
   */
  @Override
  public int selectByParent2(long parentId) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("select count(*) from child");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where parent_id in (select _id from person where _id=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());
      int result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, name, parent_id FROM child WHERE parent_id=${parentId}</pre>
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
   * 	<dt>${parentId}</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>${parentId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectByParentId(long parentId) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT _id, name, parent_id FROM child");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE parent_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=cursor.getLong(index0);
          resultBean.name=cursor.getString(index1);
          if (!cursor.isNull(index2)) { resultBean.parentId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>insert into child (name, parent_id) select name, parent_id from child where _id=${parentId} or _id=${parent} or _id=${aliasParentId}</pre>
   *
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>parentId</dt><dd>is binded to query's parameter <strong>${parentId}</strong></dd>
   * 	<dt>parent</dt><dd>is binded to query's parameter <strong>${parent}</strong></dd>
   * 	<dt>aliasParentId</dt><dd>is binded to query's parameter <strong>${aliasParentId}</strong></dd>
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
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    _sqlWhereParams.add(String.valueOf(parent));
    _sqlWhereParams.add(String.valueOf(aliasParentId));

    Logger.info("insert into child (name, parent_id) select name, parent_id from child where _id=${param0} or _id=${param1} or _id=${param2}");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    database().execSQL("insert into child (name, parent_id) select name, parent_id from child where _id=? or _id=? or _id=?", _sqlWhereParams.toArray(new Object[_sqlWhereParams.size()]));
  }

  /**
   * <p>SQL insert:</p>
   * <pre>insert into child (name, parent_id) values (${bean.name}, ${bean.parentId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>${bean.parentId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertByCopy3(Child bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }
    contentValues.put("parent_id", bean.parentId);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("insert into child (%s) values (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("child", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO child (parent_id, name) VALUES (${parentId}, ${name})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>parent_id</dt><dd>is binded to query's parameter <strong>${parentId}</strong> and method's parameter <strong>parentId</strong></dd>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
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
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("parent_id", parentId);
    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
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
    Logger.info("INSERT INTO child (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    int result = (int)database().insert("child", null, contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>update or replace child set name=:name where parent_id=${a}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${a}</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is used as where parameter <strong>${a}</strong>
   * @param name
   * 	is used as updated field <strong>name</strong>
   */
  @Override
  public void updateJQL(long parentId, String name) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(parentId));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where parent_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("update or replace child set name=:name where parent_id=?");

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

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    // conflict algorithm REPLACE
    int result = database().updateWithOnConflict("child", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]),5);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>update or replace child set parent_id=${parentId}, name=(select _id from person where _id=${parentId} )  where parent_id=${parentId}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>parent_id</li>
   * 	<li>name</li>
   * </ul>
   *
   * <h2>Parameters:</h2>
   * <dl>
   * 	<dt>${parentId}</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * 	<dt>${parentId}</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * 	<dt>${parentId}</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is used as for parameter <strong>parentId</strong>
   */
  @Override
  public void updateJQL2(long parentId) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    _sqlWhereParams.add(String.valueOf(parentId));
    _sqlWhereParams.add(String.valueOf(parentId));

    Logger.info("update or replace child set parentId=${param0}, name=(select _id from person where _id=${param1} )  where parent_id=${param2}");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    database().execSQL("update or replace child set parentId=?, name=(select _id from person where _id=? )  where parent_id=?", _sqlWhereParams.toArray(new Object[_sqlWhereParams.size()]));
  }
}
