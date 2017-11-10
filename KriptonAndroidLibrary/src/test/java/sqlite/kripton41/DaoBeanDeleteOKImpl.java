package sqlite.kripton41;

import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanDeleteOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanDeleteOK
 *  @see Bean01Table
 */
public class DaoBeanDeleteOKImpl extends AbstractDao implements DaoBeanDeleteOK {
  private SQLiteStatement deleteDistancePreparedStatement0;

  public DaoBeanDeleteOKImpl(BindDummy08DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean01 WHERE id=${value}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteDistance(double value) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(value));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteDistancePreparedStatement0==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM bean01 WHERE id=?";
      deleteDistancePreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }

    // display log
    Logger.info("DELETE FROM bean01 WHERE id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteDistancePreparedStatement0, _contentValues);
    return result!=0;
  }

  public void clearCompiledStatements() {
    if (deleteDistancePreparedStatement0!=null) {
      deleteDistancePreparedStatement0.close();
      deleteDistancePreparedStatement0=null;
    }
  }
}
