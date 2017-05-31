package sqlite.adapter.example01;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
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
  public PersonDAOImpl(BindExample01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_date FROM person WHERE birthDay=${birthDay}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_date</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${birthDay}</dt><dd>is binded to method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to <code>${birthDay}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectByBirthday(Date birthDay) {
    // build where condition
    String[] _args={(birthDay==null?"":DateUtils.write(birthDay))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_date FROM person WHERE birthDay='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_date FROM person WHERE birthDay=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_date");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setSurname(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setBirthDate(DateUtils.read(cursor.getString(index3))); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
