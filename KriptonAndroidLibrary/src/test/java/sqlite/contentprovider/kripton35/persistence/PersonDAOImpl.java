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
  private static final Set<String> insertOne0ColumnSet = CollectionUtils.asSet(String.class, "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> insertChild1ColumnSet = CollectionUtils.asSet(String.class, "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> updateName3ColumnSet = CollectionUtils.asSet(String.class, "name");

  private static final Set<String> selectAll4ColumnSet = CollectionUtils.asSet(String.class, "name");

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
  public void insertOne(Person bean) {
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
   * <p>Manage the INSERT operation for content provider URI:</p>
   * <pre>content://sqlite.contentprovider.kripton35/persons</pre>
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   */
  long insertOne0(Uri uri, ContentValues contentValues) {
    // content://sqlite.contentprovider.kripton35/persons
    // 
    // content://sqlite.contentprovider.kripton35/persons
    // INSERT OR FAIL INTO Person (parentId, birthCity, birthDay, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})
    for (String columnName:contentValues.keySet()) {
      if (!insertOne0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }
    // INSERT OR FAIL INTO person (alias_parent_id, birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s, %s)
    //SqlUtils and StringUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT OR FAIL INTO person (alias_parent_id, birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s, %s)", StringUtils.formatParam(contentValues.get("alias_parent_id"),""), StringUtils.formatParam(contentValues.get("birth_city"),"'"), StringUtils.formatParam(contentValues.get("birth_day"),"'"), StringUtils.formatParam(contentValues.get("value"),""), StringUtils.formatParam(contentValues.get("name"),"'"), StringUtils.formatParam(contentValues.get("surname"),"'")));
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
  public void insertChild(Person bean) {
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
   * <p>Manage the INSERT operation for content provider URI:</p>
   * <pre>content://sqlite.contentprovider.kripton35/persons/#/children</pre>
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   */
  long insertChild1(Uri uri, ContentValues contentValues) {
    // content://sqlite.contentprovider.kripton35/persons/#/children
    // /${parentId}/children
    // content://sqlite.contentprovider.kripton35/persons/${parentId}/children
    // INSERT OR ABORT INTO Person (parentId, birthCity, birthDay, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})
    contentValues.put("alias_parent_id", Long.valueOf(uri.getPathSegments().get(1)));
    for (String columnName:contentValues.keySet()) {
      if (!insertChild1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons/#/children', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }
    // INSERT OR ABORT INTO person (alias_parent_id, birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s, %s)
    //SqlUtils and StringUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT OR ABORT INTO person (alias_parent_id, birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s, %s)", StringUtils.formatParam(contentValues.get("alias_parent_id"),""), StringUtils.formatParam(contentValues.get("birth_city"),"'"), StringUtils.formatParam(contentValues.get("birth_day"),"'"), StringUtils.formatParam(contentValues.get("value"),""), StringUtils.formatParam(contentValues.get("name"),"'"), StringUtils.formatParam(contentValues.get("surname"),"'")));
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
   * <p>Path variables defined:</p>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <h2>JQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${id}</pre>
   *
   * <h2>SQL DELETE for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${id}</pre>
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
    //SqlUtils and StringUtils will be used to format SQL
    StringBuilder sqlBuilder=new StringBuilder();

    // manage WHERE statement
    String sqlWhereStatement=" WHERE id = ?";
    sqlBuilder.append(sqlWhereStatement);
    ArrayList<String> whereParams=new ArrayList<>();
    // Add parameter id at path segment 1
    whereParams.add(uri.getPathSegments().get(1));

    // display log
    Logger.info("DELETE FROM Person WHERE id = ?");

    // excute SQL
    int result = database().delete("person", sqlWhereStatement, whereParams.toArray(new String[whereParams.size()]));
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
   * <p>Path variables defined:</p>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <h2>JQL UPDATE for Content Provider</h2>
   * <pre>UPDATE Person SET name=${name} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
   *
   * <h2>SQL UPDATE for Content Provider</h2>
   * <pre>UPDATE person SET name=${name} WHERE id=${id} AND #{DYNAMIC_WHERE}</pre>
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
    //SqlUtils and StringUtils will be used to format SQL
    StringBuilder sqlBuilder=new StringBuilder();

    // manage WHERE statement
    String sqlWhereStatement=" WHERE id=?"+StringUtils.ifNotEmpty(selection," AND "+selection);
    sqlBuilder.append(sqlWhereStatement);
    ArrayList<String> whereParams=new ArrayList<>();
    // Add parameter id at path segment 1
    whereParams.add(uri.getPathSegments().get(1));
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        whereParams.add(arg);
      }
    }
    for (String columnName:contentValues.keySet()) {
      if (!updateName3ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons/#', column '%s' does not exists in table '%s' or can not be defined in this UPDATE operation", columnName, "person" ));
      }
    }

    // display log
    Logger.info("UPDATE Person SET name=? WHERE id=?%s", StringUtils.ifNotEmpty(selection," AND "+selection));

    // excute SQL
    int result = database().update("person", contentValues, sqlWhereStatement, whereParams.toArray(new String[whereParams.size()]));
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT name FROM person WHERE #{where} ORDER BY name#{order}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
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
    Logger.info(SqlUtils.formatSQL("SELECT name FROM person WHERE "+SqlUtils.appendForLog(where)+" ORDER BY name"+SqlUtils.appendForLog(order),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT name FROM person WHERE "+SqlUtils.appendForSQL(where)+" ORDER BY name"+SqlUtils.appendForSQL(order), _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("name");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.setName(cursor.getString(index0)); }

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
   * <pre>SELECT name FROM Person WHERE #{DYNAMIC_WHERE} ORDER BY name,  #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT name FROM person WHERE #{DYNAMIC_WHERE} ORDER BY name,  #{DYNAMIC_ORDER_BY}</pre>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectAll4(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    //SqlUtils and StringUtils will be used to format SQL
    Logger.info("URI %s", uri.toString());
    StringBuilder sqlBuilder=new StringBuilder();
    StringBuilder projectionBuffer=new StringBuilder();
    sqlBuilder.append("SELECT %s FROM person  ");

    // manage WHERE statement
    String sqlWhereStatement=StringUtils.ifNotEmpty(selection, " WHERE "+selection);
    sqlBuilder.append(sqlWhereStatement);

    // manage order by statement
    String sqlOrderByStatement=" ORDER BY name"+StringUtils.ifNotEmpty(sortOrder, ", "+sortOrder);
    sqlBuilder.append(sqlOrderByStatement);

    // manage where arguments
    ArrayList<String> whereParams=new ArrayList<>();
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        whereParams.add(arg);
      }
    }

    // manage projected columns
    String columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectAll4ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        projectionBuffer.append(columnSeparator + columnName);
        columnSeparator=", ";
      }
    } else {
      for (String column: selectAll4ColumnSet) {
        projectionBuffer.append(columnSeparator + column);
        columnSeparator=", ";
      }
    }

    // execute query
    String sql=String.format(sqlBuilder.toString(), projectionBuffer.toString());
    Logger.info(sql);
    Cursor result = database().rawQuery(sql, whereParams.toArray(new String[whereParams.size()]));
    return result;
  }
}
