package sqlite.select.scalarlist;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
   * @return collection of single value extracted by query.
   */
  @Override
  public Set<String> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT name FROM person ORDER BY name",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT name FROM person ORDER BY name", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      HashSet<String> resultList=new HashSet<String>();


      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(cursor.getString(0));
          } else {
            resultList.add(null);
          }
        } while (cursor.moveToNext());
      }
      return resultList;
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
   * @return collection of single value extracted by query.
   */
  @Override
  public ArrayList<Date> selectAll2() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT birth_day FROM person ORDER BY name",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT birth_day FROM person ORDER BY name", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      ArrayList<Date> resultList=new ArrayList<Date>();


      if (cursor.moveToFirst()) {

        do
         {
          if (!cursor.isNull(0)) {
            resultList.add(DateUtils.read(cursor.getString(0)));
          } else {
            resultList.add(null);
          }
        } while (cursor.moveToNext());
      }
      return resultList;
    }
  }
}
