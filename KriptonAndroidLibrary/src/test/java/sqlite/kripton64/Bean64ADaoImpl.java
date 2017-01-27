package sqlite.kripton64;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean64A</code>, based on interface <code>Bean64ADao</code>
 * </p>
 *
 *  @see Bean64A
 *  @see Bean64ADao
 *  @see Bean64ATable
 */
public class Bean64ADaoImpl extends AbstractDao implements Bean64ADao {
  public Bean64ADaoImpl(BindBean64ADataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64A> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean64A> resultList=new LinkedList<Bean64A>();
    Bean64A resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_map_string_bean");
      int index1=cursor.getColumnIndex("value_set_string");
      int index2=cursor.getColumnIndex("value_string");
      int index3=cursor.getColumnIndex("id");

      do
       {
        resultBean=new Bean64A();

        if (!cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64ATable.parseValueMapStringBean(cursor.getBlob(index0)); }
        if (!cursor.isNull(index1)) { resultBean.valueSetString=Bean64ATable.parseValueSetString(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.id=cursor.getLong(index3); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean64A> selectList(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT value_map_string_bean, value_set_string, value_string, id FROM bean64_a WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean64A> resultList=new LinkedList<Bean64A>();
    Bean64A resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("value_map_string_bean");
      int index1=cursor.getColumnIndex("value_set_string");
      int index2=cursor.getColumnIndex("value_string");
      int index3=cursor.getColumnIndex("id");

      do
       {
        resultBean=new Bean64A();

        if (!cursor.isNull(index0)) { resultBean.valueMapStringBean=Bean64ATable.parseValueMapStringBean(cursor.getBlob(index0)); }
        if (!cursor.isNull(index1)) { resultBean.valueSetString=Bean64ATable.parseValueSetString(cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.id=cursor.getLong(index3); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean64_a (value_map_string_bean, value_set_string, value_string) VALUES (${bean.valueMapStringBean}, ${bean.valueSetString}, ${bean.valueString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_map_string_bean</dt><dd>is mapped to <strong>${bean.valueMapStringBean}</strong></dd>
   * 	<dt>value_set_string</dt><dd>is mapped to <strong>${bean.valueSetString}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean64A bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.valueMapStringBean!=null) {
      contentValues.put("value_map_string_bean", Bean64ATable.serializeValueMapStringBean(bean.valueMapStringBean));
    } else {
      contentValues.putNull("value_map_string_bean");
    }

    if (bean.valueSetString!=null) {
      contentValues.put("value_set_string", Bean64ATable.serializeValueSetString(bean.valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO bean64_a (value_map_string_bean, value_set_string, value_string) VALUES ('"+StringUtils.checkSize(contentValues.get("value_map_string_bean"))+"', '"+StringUtils.checkSize(contentValues.get("value_set_string"))+"', '"+StringUtils.checkSize(contentValues.get("value_string"))+"')"));
    long result = database().insert("bean64_a", null, contentValues);
    bean.id=result;

    return result;
  }
}
