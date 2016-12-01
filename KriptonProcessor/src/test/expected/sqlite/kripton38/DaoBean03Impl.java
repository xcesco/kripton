package sqlite.kripton38;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean03</code>, based on interface <code>DaoBean03</code>
 * </p>
 *
 *  @see Bean03
 *  @see DaoBean03
 *  @see Bean03Table
 */
public class DaoBean03Impl extends AbstractDao implements DaoBean03 {
  public DaoBean03Impl(BindDummy03DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, text FROM bean03 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to ${id}
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean03 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, text FROM bean03 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, text FROM bean03 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean03 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      resultBean=new Bean03();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE bean03 WHERE id=${id}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
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
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE bean03 WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("bean03", "id=?", whereConditions);
    return result;
  }
}
