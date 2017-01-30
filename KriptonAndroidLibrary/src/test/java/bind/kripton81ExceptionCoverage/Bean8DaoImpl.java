package bind.kripton81ExceptionCoverage;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean8</code>, based on interface <code>Bean8Dao</code>
 * </p>
 *
 *  @see Bean8
 *  @see Bean8Dao
 *  @see Bean8Table
 */
public class Bean8DaoImpl extends AbstractDao implements Bean8Dao {
  public Bean8DaoImpl(BindBean8DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, ignore2 FROM bean8</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>ignore2</dt><dd>is associated to bean's property <strong>ignore2</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean8> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, ignore2 FROM bean8",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, ignore2 FROM bean8", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean8> resultList=new LinkedList<Bean8>();
    Bean8 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("ignore2");

      do
       {
        resultBean=new Bean8();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.ignore2=cursor.getString(index1); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }
}
