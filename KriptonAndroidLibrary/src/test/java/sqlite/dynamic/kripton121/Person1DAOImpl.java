package sqlite.dynamic.kripton121;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
import sqlite.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>Person1DAO</code>
 * </p>
 *
 *  @see Person
 *  @see Person1DAO
 *  @see sqlite.dynamic.PersonTable
 */
public class Person1DAOImpl extends AbstractDao implements Person1DAO {
  public Person1DAOImpl(BindPerson1DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE #{where} ORDER BY #{orderBy}</pre>
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
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String where, String orderBy) {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person WHERE "+SqlUtils.appendForLog(where)+" ORDER BY "+SqlUtils.appendForLog(orderBy),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person WHERE "+SqlUtils.appendForSQL(where)+" ORDER BY "+SqlUtils.appendForSQL(orderBy), _args)) {
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
}
