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
}
