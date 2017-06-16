package sqlite.kripton48.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import sqlite.kripton48.entities.Bean01;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean01</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBean01
 *  @see sqlite.kripton48.entities.Bean01Table
 */
public class DaoBean01Impl extends AbstractDao implements DaoBean01 {
  public DaoBean01Impl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
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
  public Bean01 selectOne(long id) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT id, text FROM bean01 ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
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

      Bean01 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("text");

        resultBean=new Bean01();

        if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>${text}</strong> and method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param text
   * 	is used as updated field <strong>text</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(String text, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (text!=null) {
      contentValues.put("text", text);
    } else {
      contentValues.putNull("text");
    }

    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("UPDATE bean01 SET text=:text WHERE id=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().update("bean01", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }
}
