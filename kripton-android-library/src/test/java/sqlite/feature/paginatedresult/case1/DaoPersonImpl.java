package sqlite.feature.paginatedresult.case1;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>DaoPerson</code>
 * </p>
 *
 *  @see Person
 *  @see DaoPerson
 *  @see PersonTable
 */
public class DaoPersonImpl extends Dao implements DaoPerson {
  public DaoPersonImpl(BindPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name FROM person ORDER BY name LIMIT 10 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * @return paginated result.
   */
  @Override
  public PaginatedResult<Person> select() {
    PaginatedResult0 paginatedResult=new PaginatedResult0();
    // common part generation - BEGIN
    // common part generation - END
    return paginatedResult;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name FROM person ORDER BY name LIMIT 10 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<Person> select(PaginatedResult0 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sortOrder=null;
    String _sqlWhereStatement="";
    // generation order - BEGIN
    String _sqlOrderByStatement=" ORDER BY name";
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT 10";
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+paginatedResult.firstRow();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
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
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      String __name=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __name=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __name=_cursor.getString(index1); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new Person(__id,__name);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  public static void clearCompiledStatements() {
  }

  public class PaginatedResult0 extends PaginatedResult<Person> {
    PaginatedResult0() {
      this.pageSize=10;
    }

    public List<Person> execute() {
      list=DaoPersonImpl.this.select(this);
      return list;
    }
  }
}
