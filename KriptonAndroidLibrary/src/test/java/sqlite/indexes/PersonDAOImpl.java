package sqlite.indexes;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
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

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO person (name, surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    database().insert("person", null, contentValues);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name</pre>
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
  public List<Person> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name", args);
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
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ${nameTemp} || \'%\' and birthDay < ${date} #{where} ORDER BY #{orderBy}</pre>
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
   * <h2>Dynamic parts:</h2>
   * <dl>
   * <dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd><dt>#{orderBy}</dt>is part of order statement resolved at runtime.</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${nameTemp}</dt><dd>is binded to method's parameter <strong>nameValue</strong></dd>
   * 	<dt>${date}</dt><dd>is binded to method's parameter <strong>date</strong></dd>
   * </dl>
   *
   * @param nameValue
   * 	is binded to <code>${nameTemp}</code>
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param date
   * 	is binded to <code>${date}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String nameValue, String where, String orderBy, Date date) {
    // build where condition
    String[] args={(nameValue==null?"":nameValue), (date==null?"":DateUtils.write(date))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like '%s' || \'%%' and birth_day < '%s' "+StringUtils.appendForLog(where)+" ORDER BY "+StringUtils.appendForLog(orderBy)+"",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ? || \'%\' and birth_day < ? "+StringUtils.appendForSQL(where)+" ORDER BY "+StringUtils.appendForSQL(orderBy)+"", args);
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
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name#{orderBy}</pre>
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
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name"+StringUtils.appendForLog(orderBy)+"",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name"+StringUtils.appendForSQL(orderBy)+"", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Person resultBean=new Person();
    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.name=null;
          resultBean.surname=null;
          resultBean.birthCity=null;
          resultBean.birthDay=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

          beanListener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }
}
