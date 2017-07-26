package bind.kripton81ExceptionCoverage;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Error10Bean</code>, based on interface <code>Error10Dao</code>
 * </p>
 *
 *  @see Error10Bean
 *  @see Error10Dao
 *  @see Error10BeanTable
 */
public class Error10DaoImpl extends AbstractDao implements Error10Dao {
  public Error10DaoImpl(BindError10DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT date FROM error10_bean WHERE date=${date}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>date</dt><dd>is associated to bean's property <strong>date</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${date}</dt><dd>is binded to method's parameter <strong>date</strong></dd>
   * </dl>
   *
   * @param date
   * 	is binded to <code>${date}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Error10Bean> selectAll(Date date) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT date FROM error10_bean ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE date=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add((date==null?"":DateUtils.write(date)));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Error10Bean> resultList=new LinkedList<Error10Bean>();
      Error10Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("date");

        do
         {
          resultBean=new Error10Bean();

          if (!cursor.isNull(index0)) { resultBean.date=DateUtils.read(cursor.getString(index0)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
