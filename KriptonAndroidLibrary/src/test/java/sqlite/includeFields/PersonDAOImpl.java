package sqlite.includeFields;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
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
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name FROM person WHERE name=${bean.name} ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.name}</dt><dd>is binded to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectIncludeOne(Person bean) {
    // build where condition
    String[] args={(bean.name==null?"":bean.name)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name FROM person WHERE name='%s' ORDER BY name",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name FROM person WHERE name=? ORDER BY name", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Person> resultList=new LinkedList<Person>();
    Person resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("name");

      do
       {
        resultBean=new Person();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT surname, birth_city, birth_day FROM person ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectExcludeOne() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT surname, birth_city, birth_day FROM person ORDER BY name",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT surname, birth_city, birth_day FROM person ORDER BY name", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Person> resultList=new LinkedList<Person>();
    Person resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("surname");
      int index1=cursor.getColumnIndex("birth_city");
      int index2=cursor.getColumnIndex("birth_day");

      do
       {
        resultBean=new Person();

        if (!cursor.isNull(index0)) { resultBean.surname=cursor.getString(index0); }
        if (!cursor.isNull(index1)) { resultBean.birthCity=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(cursor.getString(index2)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name) VALUES (${bean.name})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insertIncludeOne(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (name) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"')"));
    long result = database().insert("person", null, contentValues);
    bean.id=result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (surname, birth_city, birth_day) VALUES (${bean.surname}, ${bean.birthCity}, ${bean.birthDay})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
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
  public void insertExcludeOne(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO person (surname, birth_city, birth_day) VALUES ('"+StringUtils.checkSize(contentValues.get("surname"))+"', '"+StringUtils.checkSize(contentValues.get("birth_city"))+"', '"+StringUtils.checkSize(contentValues.get("birth_day"))+"')"));
    long result = database().insert("person", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=${bean.name} WHERE 1=1</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void updateIncludeOne(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    String[] whereConditionsArray={};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE 1=1", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"' WHERE 1=1", whereConditionsArray);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET surname=${bean.surname}, birth_city=${bean.birthCity}, birth_day=${bean.birthDay} WHERE 1=1</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void updateExcludeOne(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

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

    String[] whereConditionsArray={};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE person SET surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE 1=1", (Object[]) whereConditionsArray));
    int result = database().update("person", contentValues, "UPDATE person SET surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE 1=1", whereConditionsArray);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE 1=1</pre>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteIncludeOne(Person bean) {
    String[] whereConditionsArray={};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE 1=1 ", (Object[]) whereConditionsArray));
    int result = database().delete("person", "1=1", whereConditionsArray);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person WHERE 1=1</pre>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteExcludeOne(Person bean) {
    String[] whereConditionsArray={};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE person WHERE 1=1 ", (Object[]) whereConditionsArray));
    int result = database().delete("person", "1=1", whereConditionsArray);
  }
}
