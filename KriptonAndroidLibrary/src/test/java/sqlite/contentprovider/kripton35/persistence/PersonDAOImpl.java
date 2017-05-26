package sqlite.contentprovider.kripton35.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
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
  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR FAIL INTO person (birth_city, birth_day, name, surname) VALUES (${bean.birthCity}, ${bean.birthDay}, ${bean.name}, ${bean.surname})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
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
    Logger.info(SqlUtils.formatSQL("INSERT OR FAIL INTO person (birth_city, birth_day, name, surname) VALUES ('"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("person", null, contentValues, SQLiteDatabase.CONFLICT_FAIL);
    bean.id=result;
  }

  public long insertOne(ContentValues contentValues) {
    // INSERT OR FAIL INTO Person (birthCity, birthDay, name, surname) VALUES (${bean.birthCity}, ${bean.birthDay}, ${bean.name}, ${bean.surname})
    // INSERT OR FAIL INTO Person (birthCity, birthDay, name, surname) VALUES (arturo, arturo, arturo, arturo)
    return 0L;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET birthCity=${birthCity} WHERE id=${id} #{where}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>birth_city</dt><dd>is binded to query's parameter <strong>${birthCity}</strong> and method's parameter <strong>dummy</strong></dd>
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
   * @param dummy
   * 	is used as updated field <strong>birthCity</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param where
   * 	is used as dynamic where conditions
   *
   * @return number of updated records
   */
  @Override
  public int updateWhereStaticAndDynamic(String dummy, long id, String where) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (dummy!=null) {
      contentValues.put("birth_city", dummy);
    } else {
      contentValues.putNull("birth_city");
    }

    String[] whereConditionsArray={String.valueOf(id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET birthCity='"+StringUtils.checkSize(contentValues.get("birth_city"))+"' WHERE id=%s "+SqlUtils.appendForLog(where), (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "id=? "+SqlUtils.appendForSQL(where)+"", whereConditionsArray);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person WHERE name like ${nameTemp} || \'%\' #{where} HAVING id=2 GROUP BY id ORDER BY id#{orderBy}#{pageSize}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * <dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd><dt>#{orderBy}</dt>is part of order statement resolved at runtime.</dd><dt>#{pageSize}</dt>is part of limit statement resolved at runtime.</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${nameTemp}</dt><dd>is binded to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * @param nameValue
   * 	is binded to <code>${nameTemp}</code>
   * @param pageSize
   * 	is used as <strong>dynamic LIMIT statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String nameValue, int pageSize, String where, String orderBy) {
    // build where condition
    String[] _args={(nameValue==null?"":nameValue)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, birth_city, birth_day, name, surname FROM person WHERE name like '%s' || \'%%' "+SqlUtils.appendForLog(where)+" HAVING id=2 GROUP BY id ORDER BY id"+SqlUtils.appendForLog(orderBy)+SqlUtils.printIf(pageSize>0, " LIMIT "+pageSize),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, birth_city, birth_day, name, surname FROM person WHERE name like ? || \'%\' "+SqlUtils.appendForSQL(where)+" HAVING id=2 GROUP BY id ORDER BY id"+SqlUtils.appendForSQL(orderBy)+SqlUtils.printIf(pageSize>0, " LIMIT "+pageSize), _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("birth_city");
        int index2=cursor.getColumnIndex("birth_day");
        int index3=cursor.getColumnIndex("name");
        int index4=cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.birthCity=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.setSurname(cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name#{orderBy}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * <dt>#{orderBy}</dt>is part of order statement resolved at runtime.</dd>
   * </dl>
   *
   * @param beanListener
   * 	is the Person listener
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   */
  @Override
  public void selectBeanListener(OnReadBeanListener<Person> beanListener, String orderBy) {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name"+SqlUtils.appendForLog(orderBy),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, birth_city, birth_day, name, surname FROM person ORDER BY name"+SqlUtils.appendForSQL(orderBy), _args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Person resultBean=new Person();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("birth_city");
        int index2=cursor.getColumnIndex("birth_day");
        int index3=cursor.getColumnIndex("name");
        int index4=cursor.getColumnIndex("surname");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.birthCity=null;
          resultBean.birthDay=null;
          resultBean.setName(null);
          resultBean.setSurname(null);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.birthCity=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setName(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.setSurname(cursor.getString(index4)); }

          beanListener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
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
}
