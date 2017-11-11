package sqlite.kripton48.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import sqlite.kripton48.entities.Bean02;

/**
 * <p>
 * DAO implementation for entity <code>Bean02</code>, based on interface <code>DaoBean02</code>
 * </p>
 *
 *  @see Bean02
 *  @see DaoBean02
 *  @see sqlite.kripton48.entities.Bean02Table
 */
public class DaoBean02Impl extends AbstractDao implements DaoBean02 {
  protected String SELECT_ONE_SQL1 = "SELECT id, text FROM bean02 WHERE id=?";

  private SQLiteStatement deleteOnePreparedStatement0;

  public DaoBean02Impl(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, text FROM bean02 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean02 selectOne(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Bean02 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("text");

        resultBean=new Bean02();

        resultBean.setId(cursor.getLong(index0));
        if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean02 WHERE id=${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteOnePreparedStatement0==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM bean02 WHERE id=?";
      deleteOnePreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM bean02 WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteOnePreparedStatement0, _contentValues);
    return result;
  }

  public void clearCompiledStatements() {
    if (deleteOnePreparedStatement0!=null) {
      deleteOnePreparedStatement0.close();
      deleteOnePreparedStatement0=null;
    }
  }
}
