package sqlite.contentprovider.kripton35.persistence;

import android.content.ContentValues;
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
  private static final Set<String> insertOne0ColumnSet = CollectionUtils.asSet(String.class, "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> insertChild1ColumnSet = CollectionUtils.asSet(String.class, "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR FAIL INTO person (birth_city, birth_day, value, name, surname) VALUES (${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
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
    Logger.info(SqlUtils.formatSQL("INSERT OR FAIL INTO person (birth_city, birth_day, value, name, surname) VALUES ('"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_FAIL);
    bean.id=result;
  }

  /**
   * <p>Manage the INSERT operation for content provider URI:</p>
   * <pre>content://sqlite.contentprovider.kripton35/persons</pre>
   */
  long insertOne0(Uri uri, ContentValues contentValues) {
    // content://sqlite.contentprovider.kripton35/persons
    // 
    // content://sqlite.contentprovider.kripton35/persons
    // INSERT OR FAIL INTO Person (birthCity, birthDay, value, name, surname) VALUES (${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})
    for (String columnName:contentValues.keySet()) {
      if (!insertOne0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("Column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
      }
    }
    // INSERT OR FAIL INTO person (birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s)
    //SqlUtils and StringUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT OR FAIL INTO person (birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s)", StringUtils.formatParam(contentValues.get("birth_city"),"'"), StringUtils.formatParam(contentValues.get("birth_day"),"'"), StringUtils.formatParam(contentValues.get("value"),""), StringUtils.formatParam(contentValues.get("name"),"'"), StringUtils.formatParam(contentValues.get("surname"),"'")));
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
   */
  long insertChild1(Uri uri, ContentValues contentValues) {
    // content://sqlite.contentprovider.kripton35/persons/#/children
    // /${parentId}/children
    // content://sqlite.contentprovider.kripton35/persons/${parentId}/children
    // INSERT OR ABORT INTO Person (parentId, birthCity, birthDay, value, name, surname) VALUES (${bean.parentId}, ${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})
    contentValues.put("alias_parent_id", Long.valueOf(uri.getPathSegments().get(1)));
    for (String columnName:contentValues.keySet()) {
      if (!insertChild1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("Column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
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
   * <pre>DELETE person WHERE id = ${id}</pre> #{runtimeWhere}</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * 	<dt>#{runtimeWhere}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param runtimeWhere
   * 	is used as dynamic where conditions
   * @param args
   * 	is used as updated field <strong>args</strong>
   */
  @Override
  public void delete(long id, String runtimeWhere, String[] args) {
    String[] whereConditionsArray={String.valueOf(id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s "+SqlUtils.appendForLog(runtimeWhere), (Object[])whereConditionsArray));
    int result = database().delete("person", "id = ? "+SqlUtils.appendForSQL(runtimeWhere)+"", whereConditionsArray);
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
   * <h2>JQL delete for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${id} AND #{runtimeWhere}</pre>
   *
   * <h2>SQL delete for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${id} AND #{runtimeWhere}</pre>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   */
  int delete2(Uri uri, String selection, String[] selectionArgs) {
    //SqlUtils and StringUtils will be used to format SQL
    String whereCondition=" id = ? ";
    if (StringUtils.hasText(selection)) {
      whereCondition+="AND " + selection;
    }
    ArrayList<String> whereParams=new ArrayList<>();
    // Add parameter id at path segment 1
    whereParams.add(uri.getPathSegments().get(1));
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        whereParams.add(arg);
      }
    }
    int result = database().delete("person", whereCondition, whereParams.toArray(new String[whereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE id = ${id} and birthDay=${parentId}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${parentId}</dt><dd>is mapped to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param parentId
   * 	is used as where parameter <strong>${parentId}</strong>
   */
  @Override
  public void delete(long id, long parentId) {
    String[] whereConditionsArray={String.valueOf(id), String.valueOf(parentId)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s and birthDay=%s", (Object[])whereConditionsArray));
    int result = database().delete("person", "id = ? and birth_day=?", whereConditionsArray);
  }

  /**
   * <h1>Content provider URI (DELETE operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons/level1/#/#</pre>
   *
   * <p>Path variables defined:</p>
   * <ul>
   * <li><strong>${id}</strong> at path segment 2</li>
   * <li><strong>${parentId}</strong> at path segment 3</li>
   * </ul>
   *
   * <h2>JQL delete for Content Provider</h2>
   * <pre>DELETE FROM Person WHERE id = ${id} and birthDay=${parentId}</pre>
   *
   * <h2>SQL delete for Content Provider</h2>
   * <pre>DELETE FROM person WHERE id = ${id} and birth_day=${parentId}</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/level1/#/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   */
  int delete3(Uri uri, String selection, String[] selectionArgs) {
    //SqlUtils and StringUtils will be used to format SQL
    String whereCondition=" id = ${id} and birthDay=${parentId}";
    ArrayList<String> whereParams=new ArrayList<>();
    // Add parameter id at path segment 2
    whereParams.add(uri.getPathSegments().get(2));
    // Add parameter parentId at path segment 3
    whereParams.add(uri.getPathSegments().get(3));
    int result = database().delete("person", whereCondition, whereParams.toArray(new String[whereParams.size()]));
    return result;
  }
}
