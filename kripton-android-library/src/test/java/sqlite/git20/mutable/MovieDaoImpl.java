package sqlite.git20.mutable;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Movie</code>, based on interface <code>MovieDao</code>
 * </p>
 *
 *  @see Movie
 *  @see MovieDao
 *  @see MovieTable
 */
public class MovieDaoImpl extends Dao implements MovieDao {
  /**
   * SQL definition for method findCountByTitle
   */
  private static final String FIND_COUNT_BY_TITLE_SQL1 = "select 'title' as title, count(*) as count";

  public MovieDaoImpl(BindMovieDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select 'title' as title, count(*) as count</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Count}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * 	<dt>count</dt><dd>is associated to bean's property <strong>count</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Count> findCountByTitle() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_COUNT_BY_TITLE_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Count> resultList=new ArrayList<Count>(_cursor.getCount());
      Count resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("title");
        int index1=_cursor.getColumnIndex("count");

        do
         {
          resultBean=new Count();

          if (!_cursor.isNull(index0)) { resultBean.setTitle(_cursor.getString(index0)); }
          if (!_cursor.isNull(index1)) { resultBean.setCount(_cursor.getInt(index1)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  public static void clearCompiledStatements() {
  }
}
