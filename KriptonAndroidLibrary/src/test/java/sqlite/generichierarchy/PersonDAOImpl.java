package sqlite.generichierarchy;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;

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
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE id=${id}</pre>
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
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>arg0</strong></dd>
   * </dl>
   *
   * @param arg0
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectById(long arg0) {
    // build where condition
    String[] _args={String.valueOf(arg0)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person WHERE id='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person WHERE id=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        resultBean=new Person();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

      }
      return resultBean;
    }
  }
}
