package sqlite.featRawJQL.persistence;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sqlite.featRawJQL.entities.Child;

/**
 * <p>
 * DAO implementation for entity <code>Child</code>, based on interface <code>DaoChild</code>
 * </p>
 *
 *  @see Child
 *  @see DaoChild
 *  @see sqlite.featRawJQL.entities.ChildTable
 */
public class DaoChildImpl extends AbstractDao implements DaoChild {
  public DaoChildImpl(BindFamilyDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, parent_id FROM child</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectAll() {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT _id, parent_id FROM child");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();
    String _sqlWhereStatement="";

    // build where condition
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.parentId=cursor.getLong(index1); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
