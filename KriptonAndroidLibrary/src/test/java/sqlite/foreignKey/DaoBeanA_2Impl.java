package sqlite.foreignKey;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanA_2</code>, based on interface <code>DaoBeanA_2</code>
 * </p>
 *
 *  @see BeanA_2
 *  @see DaoBeanA_2
 *  @see BeanA_2Table
 */
public class DaoBeanA_2Impl extends AbstractDao implements DaoBeanA_2 {
  public DaoBeanA_2Impl(BindDummyDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value_string2 FROM bean_a_2</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_2> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value_string2 FROM bean_a_2",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, value_string2 FROM bean_a_2", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_2> resultList=new LinkedList<BeanA_2>();
    BeanA_2 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_string2");

      do
       {
        resultBean=new BeanA_2();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.valueString2=cursor.getString(index1); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value_string2 FROM bean_a_2 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_2> selectById(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, value_string2 FROM bean_a_2 WHERE id='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, value_string2 FROM bean_a_2 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_2> resultList=new LinkedList<BeanA_2>();
    BeanA_2 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_string2");

      do
       {
        resultBean=new BeanA_2();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.valueString2=cursor.getString(index1); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id FROM bean_a_2 WHERE valueString2=${dummy}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${dummy}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_2> selectByString(String value) {
    // build where condition
    String[] args={(value==null?"":value)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id FROM bean_a_2 WHERE value_string2='%s'",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id FROM bean_a_2 WHERE value_string2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_2> resultList=new LinkedList<BeanA_2>();
    BeanA_2 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");

      do
       {
        resultBean=new BeanA_2();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_a_2 (value_string2) VALUES (${bean.valueString2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(BeanA_2 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.valueString2!=null) {
      contentValues.put("value_string2", bean.valueString2);
    } else {
      contentValues.putNull("value_string2");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean_a_2 (value_string2) VALUES ('"+StringUtils.checkSize(contentValues.get("value_string2"))+"')"));
    long result = database().insert("bean_a_2", null, contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean_a_2 SET value_string2=${bean.valueString2} WHERE valueString2=${bean.valueString2}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.valueString2}</dt><dd>is mapped to method's parameter <strong>bean.valueString2</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public int update(BeanA_2 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.valueString2!=null) {
      contentValues.put("value_string2", bean.valueString2);
    } else {
      contentValues.putNull("value_string2");
    }

    String[] whereConditionsArray={(bean.valueString2==null?"":bean.valueString2)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean_a_2 SET value_string2='"+StringUtils.checkSize(contentValues.get("value_string2"))+"' WHERE valueString2='%s'", (Object[]) whereConditionsArray));
    int result = database().update("bean_a_2", contentValues, "UPDATE bean_a_2 SET value_string2='"+StringUtils.checkSize(contentValues.get("value_string2"))+"' WHERE valueString2='%s'", whereConditionsArray);
    return result;
  }
}
