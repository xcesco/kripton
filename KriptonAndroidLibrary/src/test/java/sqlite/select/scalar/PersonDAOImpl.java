package sqlite.select.scalar;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.Date;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see sqlite.select.Person
 *  @see PersonDAO
 *  @see sqlite.select.PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT name FROM person ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>name</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public String selectAll() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT name FROM person ORDER BY name",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT name FROM person ORDER BY name", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      String result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getString(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT birth_day FROM person ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Date selectAll2() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT birth_day FROM person ORDER BY name",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT birth_day FROM person ORDER BY name", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Date result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=DateUtils.read(cursor.getString(0));
      }
      return result;
    }
  }
}
