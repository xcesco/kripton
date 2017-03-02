package sqlite.dynamic.update;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import sqlite.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonUpdateDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonUpdateDAO
 *  @see sqlite.dynamic.PersonTable
 */
public class PersonUpdateDAOImpl extends AbstractDao implements PersonUpdateDAO {
  public PersonUpdateDAOImpl(BindPersonUpdateDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE typeName like ${nameTemp} || \'%\'</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${nameTemp}</dt><dd>is binded to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * @param nameValue
   * 	is binded to <code>${nameTemp}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String nameValue) {
    // build where condition
    String[] _args={(nameValue==null?"":nameValue)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person WHERE typeName like '%s' || \'%%'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person WHERE typeName like ? || \'%\'", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE id = ${nameValue}</pre> #{where}</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${nameValue}</dt><dd>is mapped to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * 	<dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * @param nameValue
   * 	is used as where parameter <strong>${nameValue}</strong>
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void deleteRaw(String nameValue, String where) {
    String[] whereConditionsArray={(nameValue==null?"":nameValue)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s "+SqlUtils.appendForLog(where), (Object[])whereConditionsArray));
    int result = database().delete("person", "id = ? "+SqlUtils.appendForSQL(where)+"", whereConditionsArray);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE id = ${bean.id} #{where}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * 	<dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void deleteBean(Person bean, String where) {
    String[] whereConditionsArray={String.valueOf(bean.id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s  "+SqlUtils.appendForLog(where), (Object[]) whereConditionsArray));
    int result = database().delete("person", "id = ?", whereConditionsArray);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${name} WHERE id = ${nameValue} #{where}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${nameValue}</dt><dd>is mapped to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * 	<dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * @param name
   * 	is used as updated field <strong>name</strong>
   * @param nameValue
   * 	is used as where parameter <strong>${nameValue}</strong>
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void updateRaw(String name, String nameValue, String where) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditionsArray={(nameValue==null?"":nameValue)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id = %s "+SqlUtils.appendForLog(where), (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "id = ? "+SqlUtils.appendForSQL(where)+"", whereConditionsArray);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${bean.name}, surname=${bean.surname}, birth_city=${bean.birthCity}, birth_day=${bean.birthDay} WHERE id = ${bean.id} #{where}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * 	<dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void updateBean(Person bean, String where) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.surname!=null) {
      contentValues.put("surname", bean.surname);
    } else {
      contentValues.putNull("surname");
    }

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

    String[] whereConditionsArray={String.valueOf(bean.id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id = '%s' "+SqlUtils.appendForLog(where), (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "id = ?", whereConditionsArray);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selecAll() {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR IGNORE INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
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
  public void insertOne(String name, String surname, String birthCity, Date birthDay) {
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
    Logger.info(SqlUtils.formatSQL("INSERT OR IGNORE INTO person (name, surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
  }
}
