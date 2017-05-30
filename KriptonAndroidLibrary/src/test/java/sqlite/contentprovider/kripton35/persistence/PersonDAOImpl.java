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
import java.util.Date;
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

  private static final Set<String> insertTwo1ColumnSet = CollectionUtils.asSet(String.class, "birth_city", "birth_day", "value", "name", "surname");

  private static final Set<String> insertTwo2ColumnSet = CollectionUtils.asSet(String.class, "name", "surname", "birth_city", "birth_day");

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

  long insertOne0(ContentValues contentValues) {
    // INSERT OR FAIL INTO Person (birthCity, birthDay, value, name, surname) VALUES (${bean.birthCity}, ${bean.birthDay}, ${bean.value}, ${bean.name}, ${bean.surname})
    for (String columnName:contentValues.keySet()) {
      if (!insertOne0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("Column '%s' does not exists in table '%s'", columnName, "person" ));
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
   * <pre>INSERT OR ABORT INTO person (birth_day) VALUES (${birthDay})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>birth_day</dt><dd>is binded to query's parameter <strong>${birthDay}</strong> and method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to column <strong>birth_day</strong>
   *
   *
   */
  @Override
  public void insertTwo(Date birthDay) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (birthDay!=null) {
      contentValues.put("birth_day", DateUtils.write(birthDay));
    } else {
      contentValues.putNull("birth_day");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT OR ABORT INTO person (birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_ABORT);
  }

  long insertTwo1(ContentValues contentValues) {
    // INSERT OR ABORT INTO Person (birthCity, birthDay, value, name, surname) VALUES (${birthCity}, ${birthDay}, ${value}, ${name}, ${surname})
    for (String columnName:contentValues.keySet()) {
      if (!insertTwo1ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("Column '%s' does not exists in table '%s'", columnName, "person" ));
      }
    }
    // INSERT OR ABORT INTO person (birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s)
    //SqlUtils and StringUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT OR ABORT INTO person (birth_city, birth_day, value, name, surname) VALUES (%s, %s, %s, %s, %s)", StringUtils.formatParam(contentValues.get("birth_city"),"'"), StringUtils.formatParam(contentValues.get("birth_day"),"'"), StringUtils.formatParam(contentValues.get("value"),""), StringUtils.formatParam(contentValues.get("name"),"'"), StringUtils.formatParam(contentValues.get("surname"),"'")));
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_ABORT);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>${surname}</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is binded to query's parameter <strong>${birthCity}</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is binded to query's parameter <strong>${birthDay}</strong> and method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column <strong>name</strong>
   * @param surname
   * 	is binded to column <strong>surname</strong>
   * @param birthCity
   * 	is binded to column <strong>birth_city</strong>
   * @param birthDay
   * 	is binded to column <strong>birth_day</strong>
   *
   *
   */
  @Override
  public void insertTwo(String name, String surname, String birthCity, Date birthDay) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
    }

    if (surname!=null) {
      contentValues.put("surname", surname);
    } else {
      contentValues.putNull("surname");
    }

    if (birthCity!=null) {
      contentValues.put("birth_city", birthCity);
    } else {
      contentValues.putNull("birth_city");
    }

    if (birthDay!=null) {
      contentValues.put("birth_day", DateUtils.write(birthDay));
    } else {
      contentValues.putNull("birth_day");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (name, surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    database().insert("person", null, contentValues);
  }

  long insertTwo2(ContentValues contentValues) {
    // INSERT INTO Person (name, surname, birthCity, birthDay) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})
    for (String columnName:contentValues.keySet()) {
      if (!insertTwo2ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("Column '%s' does not exists in table '%s'", columnName, "person" ));
      }
    }
    // INSERT INTO person (name, surname, birth_city, birth_day) VALUES (%s, %s, %s, %s)
    //SqlUtils and StringUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (name, surname, birth_city, birth_day) VALUES (%s, %s, %s, %s)", StringUtils.formatParam(contentValues.get("name"),"'"), StringUtils.formatParam(contentValues.get("surname"),"'"), StringUtils.formatParam(contentValues.get("birth_city"),"'"), StringUtils.formatParam(contentValues.get("birth_day"),"'")));
    long result = database().insert("person", null, contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE id = ${id} and birthCity= ${birthCity}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${birthCity}</dt><dd>is mapped to method's parameter <strong>birthCity</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param birthCity
   * 	is used as where parameter <strong>${birthCity}</strong>
   */
  @Override
  public void delete(long id, Date birthCity) {
    String[] whereConditionsArray={String.valueOf(id), (birthCity==null?"":DateUtils.write(birthCity))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s and birthCity= %s", (Object[])whereConditionsArray));
    int result = database().delete("person", "id = ? and birth_city= ?", whereConditionsArray);
  }

  long delete3(Uri uri, String selection, String selectionArgs) {
    // DELETE FROM Person WHERE id = ${id} and birthCity= ${birthCity}
    // DELETE FROM person WHERE id = %s and birth_city= %s
    //SqlUtils and StringUtils will be used to format SQL
    long result = database().delete("person", null, null);
    return result;
  }
}
