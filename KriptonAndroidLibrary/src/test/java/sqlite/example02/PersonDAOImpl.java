package sqlite.example02;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET surname=${surname}, birthCity=${birthCity}, birthDay=${birthDay} WHERE name=${name}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>${surname}</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is binded to query's parameter <strong>${birthCity}</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is binded to query's parameter <strong>${birthDay}</strong> and method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${name}</dt><dd>is mapped to method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as where parameter <strong>${name}</strong>
   * @param surname
   * 	is used as updated field <strong>surname</strong>
   * @param birthCity
   * 	is used as updated field <strong>birthCity</strong>
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   */
  @Override
  public void updateOne(String name, String surname, String birthCity, Date birthDay) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
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

    String[] whereConditionsArray={(name==null?"":name)};

    Logger.info(StringUtils.formatSQL("UPDATE person SET surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birthCity='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birthDay='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE name=%s", (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "name=?", whereConditionsArray);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${name}, surname=${surname}, birthCity=${birthCity}, birthDay=${birthDay} WHERE 1=1</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>${surname}</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is binded to query's parameter <strong>${birthCity}</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is binded to query's parameter <strong>${birthDay}</strong> and method's parameter <strong>date</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   * @param name
   * 	is used as updated field <strong>name</strong>
   * @param surname
   * 	is used as updated field <strong>surname</strong>
   * @param birthCity
   * 	is used as updated field <strong>birthCity</strong>
   * @param date
   * 	is used as updated field <strong>birthDay</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateTwo(String name, String surname, String birthCity, Date date) {
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
    if (date!=null) {
      contentValues.put("birth_day", DateUtils.write(date));
    } else {
      contentValues.putNull("birth_day");
    }

    String[] whereConditionsArray={};

    Logger.info(StringUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birthCity='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birthDay='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE 1=1", (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "1=1", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${bean.name}, surname=${bean.surname}, birth_city=${bean.birthCity}, birth_day=${bean.birthDay} WHERE id=${bean.id} and name=${bean.name}</pre>
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
   * 	<dt>${bean.name}</dt><dd>is mapped to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void updateThree(Person bean) {
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

    String[] whereConditionsArray={String.valueOf(bean.id), (bean.name==null?"":bean.name)};

    Logger.info(StringUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id='%s' and name='%s'", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id='%s' and name='%s'", whereConditionsArray);
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
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is binded to query's parameter <strong>${surname}</strong> and method's parameter <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is binded to query's parameter <strong>${birthCity}</strong> and method's parameter <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is binded to query's parameter <strong>${birthDay}</strong> and method's parameter <strong>date</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column <strong>name</strong>
   * @param surname
   * 	is binded to column <strong>surname</strong>
   * @param birthCity
   * 	is binded to column <strong>birth_city</strong>
   * @param date
   * 	is binded to column <strong>birth_day</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertTwo(String name, String surname, String birthCity, Date date) {
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

    if (date!=null) {
      contentValues.put("birth_day", DateUtils.write(date));
    } else {
      contentValues.putNull("birth_day");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO person (name, surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    long result = database().insert("person", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (birth_city, birth_day) VALUES (${bean.birthCity}, ${bean.birthDay})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insertThree(Person bean) {
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

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO person (birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    long result = database().insert("person", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE name=${name} and surname=${surname}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${name}</dt><dd>is mapped to method's parameter <strong>name</strong></dd>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>temp</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as where parameter <strong>${name}</strong>
   * @param temp
   * 	is used as where parameter <strong>${surname}</strong>
   */
  @Override
  public void deleteOne(String name, String temp) {
    String[] whereConditionsArray={(name==null?"":name), (temp==null?"":temp)};

    Logger.info(StringUtils.formatSQL("DELETE person WHERE name=%s and surname=%s", (Object[])whereConditionsArray));
    int result = database().delete("person", "name=? and surname=?", whereConditionsArray);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE name=${name} and surname=${surname}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${name}</dt><dd>is mapped to method's parameter <strong>name</strong></dd>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>temp</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as where parameter <strong>${name}</strong>
   * @param temp
   * 	is used as where parameter <strong>${surname}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteTwo(String name, String temp) {
    String[] whereConditionsArray={(name==null?"":name), (temp==null?"":temp)};

    Logger.info(StringUtils.formatSQL("DELETE person WHERE name=%s and surname=%s", (Object[])whereConditionsArray));
    int result = database().delete("person", "name=? and surname=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE id = ${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteThree(Person bean) {
    String[] whereConditionsArray={String.valueOf(bean.id)};

    Logger.info(StringUtils.formatSQL("DELETE person WHERE id = %s ", (Object[]) whereConditionsArray));
    int result = database().delete("person", "id = ?", whereConditionsArray);
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
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ${name} || \'%%\' ORDER BY name</pre>
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
   * 	<dt>${name}</dt><dd>is binded to method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to <code>${name}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Person> selectAll(String name) {
    // build where condition
    String[] args={(name==null?"":name)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like '%s' || \'%%\' ORDER BY name",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ? || \'%%\' ORDER BY name", args);
    Logger.info("Rows found: %s",cursor.getCount());

    HashSet<Person> resultList=new HashSet<Person>();
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
   * @param beanListener
   * 	is the Person listener
   */
  @Override
  public void selectBeanListener(OnReadBeanListener<Person> beanListener) {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name", args);
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

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>name</dt><dd>no bean's property is associated</dd>
   * 	<dt>surname</dt><dd>no bean's property is associated</dd>
   * 	<dt>birth_city</dt><dd>no bean's property is associated</dd>
   * 	<dt>birth_day</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @param cursorListener
   * 	is the cursor listener
   */
  @Override
  public void selectCursorListener(OnReadCursorListener cursorListener) {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          cursorListener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }
}
