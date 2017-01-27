package sqlite.kripton38;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean01</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBean01
 *  @see Bean01Table
 */
public class DaoBean01Impl extends AbstractDao implements DaoBean01 {
  public DaoBean01Impl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT temp, id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>temp</dt><dd>is associated to bean's property <strong>temp</strong></dd>
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
   * 	is binded to <code>${id}</code>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT temp, id, text FROM bean01 WHERE id='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT temp, id, text FROM bean01 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("temp");
      int index1=cursor.getColumnIndex("id");
      int index2=cursor.getColumnIndex("text");

      resultBean=new Bean01();

      if (!cursor.isNull(index0)) { resultBean.temp=Bean01Table.parseTemp(cursor.getBlob(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setId(cursor.getLong(index1)); }
      if (!cursor.isNull(index2)) { resultBean.setText(cursor.getString(index2)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>${text}</strong> and method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
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

    String[] whereConditionsArray={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("UPDATE bean01 SET text='"+StringUtils.checkSize(contentValues.get("text"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("bean01", contentValues, "id=?", whereConditionsArray);
    return result;
  }
}
