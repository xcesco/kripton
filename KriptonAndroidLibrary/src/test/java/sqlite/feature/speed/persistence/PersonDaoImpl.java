package sqlite.feature.speed.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.speed.model.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDao</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDao
 *  @see sqlite.feature.speed.model.PersonTable
 */
public class PersonDaoImpl extends AbstractDao implements PersonDao {
  private static final String SELECT_ALL_SQL1 = "SELECT id, name, surname, data1, data2, data3, data4 FROM person";

  private static final String SELECT_BY_ID_SQL2 = "SELECT id, name, surname, data1, data2, data3, data4 FROM person WHERE id=?";

  private static SQLiteStatement insertPreparedStatement0;

  private static SQLiteStatement updatePreparedStatement1;

  private static SQLiteStatement deletePreparedStatement2;

  public PersonDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, data1, data2, data3, data4 FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>data1</dt><dd>is associated to bean's property <strong>data1</strong></dd>
   * 	<dt>data2</dt><dd>is associated to bean's property <strong>data2</strong></dd>
   * 	<dt>data3</dt><dd>is associated to bean's property <strong>data3</strong></dd>
   * 	<dt>data4</dt><dd>is associated to bean's property <strong>data4</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {

      ArrayList<Person> resultList=new ArrayList<Person>(cursor.getCount());
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("data1");
        int index4=cursor.getColumnIndex("data2");
        int index5=cursor.getColumnIndex("data3");
        int index6=cursor.getColumnIndex("data4");

        do
         {
          resultBean=new Person();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.data1=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.data2=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.data3=cursor.getString(index5); }
          if (!cursor.isNull(index6)) { resultBean.data4=cursor.getString(index6); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, data1, data2, data3, data4 FROM person WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>data1</dt><dd>is associated to bean's property <strong>data1</strong></dd>
   * 	<dt>data2</dt><dd>is associated to bean's property <strong>data2</strong></dd>
   * 	<dt>data3</dt><dd>is associated to bean's property <strong>data3</strong></dd>
   * 	<dt>data4</dt><dd>is associated to bean's property <strong>data4</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("data1");
        int index4=cursor.getColumnIndex("data2");
        int index5=cursor.getColumnIndex("data3");
        int index6=cursor.getColumnIndex("data4");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.data1=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.data2=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.data3=cursor.getString(index5); }
        if (!cursor.isNull(index6)) { resultBean.data4=cursor.getString(index6); }

      }
      return resultBean;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, surname, data1, data2, data3, data4) VALUES (${bean.name}, ${bean.surname}, ${bean.data1}, ${bean.data2}, ${bean.data3}, ${bean.data4})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>data1</dt><dd>is mapped to <strong>${bean.data1}</strong></dd>
   * 	<dt>data2</dt><dd>is mapped to <strong>${bean.data2}</strong></dd>
   * 	<dt>data3</dt><dd>is mapped to <strong>${bean.data3}</strong></dd>
   * 	<dt>data4</dt><dd>is mapped to <strong>${bean.data4}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public Person insert(Person bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    if (bean.name!=null) {
      _contentValues.put(bean.name);
    } else {
      _contentValues.putNull();
    }
    if (bean.surname!=null) {
      _contentValues.put(bean.surname);
    } else {
      _contentValues.putNull();
    }
    if (bean.data1!=null) {
      _contentValues.put(bean.data1);
    } else {
      _contentValues.putNull();
    }
    if (bean.data2!=null) {
      _contentValues.put(bean.data2);
    } else {
      _contentValues.putNull();
    }
    if (bean.data3!=null) {
      _contentValues.put(bean.data3);
    } else {
      _contentValues.putNull();
    }
    if (bean.data4!=null) {
      _contentValues.put(bean.data4);
    } else {
      _contentValues.putNull();
    }

    // insert operation
    if (insertPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(_context, insertPreparedStatement0, _contentValues);
    bean.id=result;

    return bean;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE person SET name=:name, surname=:surname, data1=:data1, data2=:data2, data3=:data3, data4=:data4 WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>data1</dt><dd>is mapped to <strong>${bean.data1}</strong></dd>
   * 	<dt>data2</dt><dd>is mapped to <strong>${bean.data2}</strong></dd>
   * 	<dt>data3</dt><dd>is mapped to <strong>${bean.data3}</strong></dd>
   * 	<dt>data4</dt><dd>is mapped to <strong>${bean.data4}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(Person bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    if (bean.name!=null) {
      _contentValues.put(bean.name);
    } else {
      _contentValues.putNull();
    }
    if (bean.surname!=null) {
      _contentValues.put(bean.surname);
    } else {
      _contentValues.putNull();
    }
    if (bean.data1!=null) {
      _contentValues.put(bean.data1);
    } else {
      _contentValues.putNull();
    }
    if (bean.data2!=null) {
      _contentValues.put(bean.data2);
    } else {
      _contentValues.putNull();
    }
    if (bean.data3!=null) {
      _contentValues.put(bean.data3);
    } else {
      _contentValues.putNull();
    }
    if (bean.data4!=null) {
      _contentValues.put(bean.data4);
    } else {
      _contentValues.putNull();
    }

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updatePreparedStatement1==null) {
      StringBuilder _sqlBuilder=sqlBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE person SET name=?, surname=?, data1=?, data2=?, data3=?, data4=? WHERE id=?";
      updatePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    int result = KriptonDatabaseWrapper.updateDelete(_context, updatePreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM person WHERE id=${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Person bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deletePreparedStatement2==null) {
      StringBuilder _sqlBuilder=sqlBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM person WHERE id=?";
      deletePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    int result = KriptonDatabaseWrapper.updateDelete(_context, deletePreparedStatement2, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (updatePreparedStatement1!=null) {
      updatePreparedStatement1.close();
      updatePreparedStatement1=null;
    }
    if (deletePreparedStatement2!=null) {
      deletePreparedStatement2.close();
      deletePreparedStatement2=null;
    }
  }
}
