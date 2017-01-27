package sqlite.kripton96;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean96</code>, based on interface <code>Bean96Dao</code>
 * </p>
 *
 *  @see Bean96
 *  @see Bean96Dao
 *  @see Bean96Table
 */
public class Bean96DaoImpl extends AbstractDao implements Bean96Dao {
  public Bean96DaoImpl(BindBean96DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM bean96 WHERE name like ${name} || \'%\'</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${name}</dt><dd>is binded to method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to <code>${name}</code>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean96 selectByBean(String name) {
    // build where condition
    String[] args={(name==null?"":name)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname FROM bean96 WHERE name like '%s' || \'%%'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname FROM bean96 WHERE name like ? || \'%\'", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean96 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("name");
      int index2=cursor.getColumnIndex("surname");

      resultBean=new Bean96();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
      if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean96 (name, surname) VALUES (${bean.name}, ${bean.surname})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(Bean96 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.surname!=null) {
      contentValues.put("surname", bean.surname);
    } else {
      contentValues.putNull("surname");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO bean96 (name, surname) VALUES ('"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("surname"))+"')"));
    long result = database().insert("bean96", null, contentValues);
    bean.id=result;

    return result!=-1;
  }
}
