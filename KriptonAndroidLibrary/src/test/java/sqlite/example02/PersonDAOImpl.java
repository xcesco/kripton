package sqlite.example02;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birthCity='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birthDay='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE name=%s", (Object[])whereConditionsArray));
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birthCity='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birthDay='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE 1=1", (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "1=1", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${bean.name}, birth_city=${bean.birthCity}, birth_day=${bean.birthDay} WHERE id=${bean.id} and name=${bean.name}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
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
  public void updateThreeExclude(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id='%s' and name='%s'", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id='%s' and name='%s'", whereConditionsArray);
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
  public void updateThreeInclude(Person bean) {
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id='%s' and name='%s'", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE id='%s' and name='%s'", whereConditionsArray);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET surname=${bean.surname} WHERE id=${bean.id} and name=${bean.name}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
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
  public void updateThreeIncludeOK(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.surname!=null) {
      contentValues.put("surname", bean.surname);
    } else {
      contentValues.putNull("surname");
    }

    String[] whereConditionsArray={String.valueOf(bean.id), (bean.name==null?"":bean.name)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET surname='"+StringUtils.checkSize(contentValues.get("surname"))+"' WHERE id='%s' and name='%s'", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET surname='"+StringUtils.checkSize(contentValues.get("surname"))+"' WHERE id='%s' and name='%s'", whereConditionsArray);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT name FROM person WHERE id=${bean.id} and name=${bean.name}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.name}</dt><dd>is binded to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectThreeIncludeERR(Person bean) {
    // build where condition
    String[] _args={String.valueOf(bean.id), (bean.name==null?"":bean.name)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT name FROM person WHERE id='%s' and name='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT name FROM person WHERE id=? and name=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("name");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.name=cursor.getString(index0); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${bean.name} WHERE id=${bean.id} and name=${bean.name}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
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
  public void updateThreeIncludeERR(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditionsArray={String.valueOf(bean.id), (bean.name==null?"":bean.name)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s' and name='%s'", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE id='%s' and name='%s'", whereConditionsArray);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${name} WHERE surname=${surname} and name=${nameValue}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>${name}</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${surname}</dt><dd>is mapped to method's parameter <strong>surname</strong></dd>
   * 	<dt>${nameValue}</dt><dd>is mapped to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as updated field <strong>name</strong>
   * @param surname
   * 	is used as where parameter <strong>${surname}</strong>
   * @param nameValue
   * 	is used as where parameter <strong>${nameValue}</strong>
   */
  @Override
  public void updateThreeIncludeERR(String name, String surname, String nameValue) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (name!=null) {
      contentValues.put("name", name);
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditionsArray={(surname==null?"":surname), (nameValue==null?"":nameValue)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE surname=%s and name=%s", (Object[])whereConditionsArray));
    int result = database().update("person", contentValues, "surname=? and name=?", whereConditionsArray);
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (name, surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (name, surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE name=%s and surname=%s", (Object[])whereConditionsArray));
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE name=%s and surname=%s", (Object[])whereConditionsArray));
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

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE id = %s ", (Object[]) whereConditionsArray));
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
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAll() {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name", _args)) {
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
   * @return collection of bean or empty collection.
   */
  @Override
  public Set<Person> selectAll(String name) {
    // build where condition
    String[] _args={(name==null?"":name)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like '%s' || \'%%\' ORDER BY name",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person WHERE name like ? || \'%%\' ORDER BY name", _args)) {
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

      return resultList;
    }
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
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Person resultBean=new Person();
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
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      if (cursor.moveToFirst()) {

        do
         {
          cursorListener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }
}
