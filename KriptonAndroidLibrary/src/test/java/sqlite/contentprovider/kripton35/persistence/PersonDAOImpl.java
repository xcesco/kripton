package sqlite.contentprovider.kripton35.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import sqlite.contentprovider.kripton35.entities.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see sqlite.contentprovider.kripton35.entities.PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  private static final Set<String> insertBean0ColumnSet = CollectionUtils.asSet(String.class, "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> insertBeanChild1ColumnSet = CollectionUtils.asSet(String.class, "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> updateName3ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> selectAll4ColumnSet = CollectionUtils.asSet(String.class, "id", "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR FAIL INTO person (parent_id, birth_city, birth_day, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>${bean.parentId}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insertBean(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("parent_id", bean.parentId);

    if (bean.birthCity!=null) {
      contentValues.put("birth_city", bean.birthCity);
    } else {
      contentValues.putNull("birth_city");
    }

    if (bean.birthDay!=null) {
      contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    } else {
      contentValues.putNull("birth_day");
    }

    contentValues.put("value", bean.value);

    if (bean.getName()!=null) {
      contentValues.put("name", bean.getName());
    } else {
      contentValues.putNull("name");
    }

    if (bean.getSurname()!=null) {
      contentValues.put("surname", bean.getSurname());
    } else {
      contentValues.putNull("surname");
    }

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT OR FAIL INTO person (parent_id, birth_city, birth_day, value, name, surname) VALUES ('"+StringUtils.checkSize(contentValues.get("parent_id"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_FAIL);
    bean.id=result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR FAIL INTO Person (parentId, birthCity, birthDay, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR FAIL INTO person (alias_parent_id, birth_city, birth_day, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons"
   * @param contentValues content values
   * @return new row's id
   */
  long insertBean0(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());

    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      if (!insertBean0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info(SqlUtils.formatSQL("INSERT OR FAIL INTO Person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString()));

    // manage log for content values
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_FAIL);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR ABORT INTO person (parent_id, birth_city, birth_day, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>${bean.parentId}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insertBeanChild(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("parent_id", bean.parentId);

    if (bean.birthCity!=null) {
      contentValues.put("birth_city", bean.birthCity);
    } else {
      contentValues.putNull("birth_city");
    }

    if (bean.birthDay!=null) {
      contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    } else {
      contentValues.putNull("birth_day");
    }

    contentValues.put("value", bean.value);

    if (bean.getName()!=null) {
      contentValues.put("name", bean.getName());
    } else {
      contentValues.putNull("name");
    }

    if (bean.getSurname()!=null) {
      contentValues.put("surname", bean.getSurname());
    } else {
      contentValues.putNull("surname");
    }

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT OR ABORT INTO person (parent_id, birth_city, birth_day, value, name, surname) VALUES ('"+StringUtils.checkSize(contentValues.get("parent_id"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_ABORT);
    bean.id=result;
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons/#/children</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR ABORT INTO Person (parentId, birthCity, birthDay, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR ABORT INTO person (alias_parent_id, birth_city, birth_day, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${parentId}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/#/children"
   * @param contentValues content values
   * @return new row's id
   */
  long insertBeanChild1(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());

    // Add parameter parentId at path segment 1
    contentValues.put("alias_parent_id", Long.valueOf(uri.getPathSegments().get(1)));
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      if (!insertBeanChild1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons/#/children', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info(SqlUtils.formatSQL("INSERT OR ABORT INTO Person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString()));

    // manage log for content values
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_ABORT);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE id = ${id}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   */
  @Override
  public void delete(long id) {
    String[] whereConditionsArray={String.valueOf(id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s", (Object[])whereConditionsArray));
    int result = database().delete("person", "id = ?", whereConditionsArray);
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons/#</pre>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${id}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  int delete2(Uri uri, String selection, String[] selectionArgs) {
    Logger.info("Execute DELETE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=new StringBuilder();

    // manage WHERE arguments
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);
    // Add parameter id at path segment 1
    _sqlWhereParams.add(uri.getPathSegments().get(1));

    // display log
    Logger.info("DELETE FROM Person WHERE id = ?");

    // manage log for where parameters
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }

    // execute SQL
    int result = database().delete("person", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${name} WHERE id=${id} #{where}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * 	<dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * @param name
   * 	is used as updated field <strong>name</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param where
   * 	is used as dynamic where conditions
   *
   * @return number of updated records
   */
  @Override
  public int updateName(String name, long id, String where) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditionsArray={String.valueOf(id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id=%s "+SqlUtils.appendForLog(where), (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "id=? "+SqlUtils.appendForSQL(where)+"", whereConditionsArray);
    return result;
  }

  /**
   * <h1>Content provider URI (UPDATE operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons/#</pre>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET name=${name} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET name=${name} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/#"
   * @param contentValues content values
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  int updateName3(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
    Logger.info("Execute UPDATE for URI %s", uri.toString());
    StringBuilder _sqlBuilder=new StringBuilder();

    // manage WHERE arguments
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE statement
    String _sqlWhereStatement=" id=?"+StringUtils.ifNotEmptyAppend(selection," AND ");
    _sqlBuilder.append(_sqlWhereStatement);
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        _sqlWhereParams.add(arg);
      }
    }
    // Add parameter id at path segment 1
    _sqlWhereParams.add(uri.getPathSegments().get(1));
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        _sqlWhereParams.add(arg);
      }
    }
    for (String columnName:contentValues.keySet()) {
      if (!updateName3ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }

    // display log
    Logger.info("UPDATE Person SET name=:name WHERE id=?%s", StringUtils.ifNotEmptyAppend(selection," AND "));

    // manage log for content values
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("value :%s = <null>", _contentKey);
      } else {
        Logger.info("value :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getName());
      }
    }

    // manage log for where parameters
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }

    // execute SQL
    int result = database().update("person", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person WHERE #{where} ORDER BY name asc#{order}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * <dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd><dt>#{order}</dt>is part of order statement resolved at runtime.</dd>
   * </dl>
   *
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param args
   * 	is binded to <code>${args}</code>
   * @param order
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAll(String where, String[] args, String order) {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person WHERE "+SqlUtils.appendForLog(where)+" ORDER BY name asc"+SqlUtils.appendForLog(order),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person WHERE "+SqlUtils.appendForSQL(where)+" ORDER BY name asc"+SqlUtils.appendForSQL(order), _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("parent_id");
        int index2=cursor.getColumnIndex("birth_city");
        int index3=cursor.getColumnIndex("birth_day");
        int index4=cursor.getColumnIndex("value");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.parentId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthCity=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthDay=DateUtils.read(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.value=cursor.getLong(index4); }
          if (!cursor.isNull(index5)) { resultBean.setName(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.setSurname(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, birthCity, birthDay, value, name, surname FROM Person WHERE #{DYNAMIC_WHERE} ORDER BY name asc,  #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, birth_city, birth_day, value, name, surname FROM person WHERE #{DYNAMIC_WHERE} ORDER BY name asc,  #{DYNAMIC_ORDER_BY}</pre>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectAll4(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=new StringBuilder();
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person  ");

    // manage WHERE arguments
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE statement
    String _sqlWhereStatement=StringUtils.ifNotEmptyAppend(selection, " WHERE ");
    _sqlBuilder.append(_sqlWhereStatement);
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        _sqlWhereParams.add(arg);
      }
    }

    // manage order by statement
    String _sqlOrderByStatement=" ORDER BY name asc"+StringUtils.ifNotEmptyAppend(sortOrder, ", ");
    _sqlBuilder.append(_sqlOrderByStatement);

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAll4ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectAll4ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // manage log for where parameters
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }
}
