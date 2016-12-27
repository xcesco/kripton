package sqlite.foreignKey;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanA_6</code>, based on interface <code>DaoBeanA_6</code>
 * </p>
 *
 *  @see BeanA_6
 *  @see DaoBeanA_6
 *  @see BeanA_6Table
 */
public class DaoBeanA_6Impl extends AbstractDao implements DaoBeanA_6 {
  public DaoBeanA_6Impl(BindDummy3DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, bean_a2_id, value_string2 FROM bean_a_6 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_6> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, bean_a2_id, value_string2 FROM bean_a_6 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, bean_a2_id, value_string2 FROM bean_a_6 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_6> resultList=new LinkedList<BeanA_6>();
    BeanA_6 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("bean_a2_id");
      int index2=cursor.getColumnIndex("value_string2");

      do
       {
        resultBean=new BeanA_6();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        resultBean.beanA2Id=cursor.getLong(index1);
        if (!cursor.isNull(index2)) { resultBean.valueString2=cursor.getString(index2); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, bean_a2_id, value_string2 FROM bean_a_6 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
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
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_6> selectById(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("SELECT id, bean_a2_id, value_string2 FROM bean_a_6 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, bean_a2_id, value_string2 FROM bean_a_6 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_6> resultList=new LinkedList<BeanA_6>();
    BeanA_6 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("bean_a2_id");
      int index2=cursor.getColumnIndex("value_string2");

      do
       {
        resultBean=new BeanA_6();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        resultBean.beanA2Id=cursor.getLong(index1);
        if (!cursor.isNull(index2)) { resultBean.valueString2=cursor.getString(index2); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id FROM bean_a_6 WHERE valueString2=${dummy}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to ${dummy}
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_6> selectByString(String value) {
    // build where condition
    String[] args={(value==null?null:value)};

    Logger.info(StringUtils.formatSQL("SELECT id FROM bean_a_6 WHERE value_string2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id FROM bean_a_6 WHERE value_string2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_6> resultList=new LinkedList<BeanA_6>();
    BeanA_6 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");

      do
       {
        resultBean=new BeanA_6();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_a_6 (bean_a2_id, value_string2) VALUES (${bean.beanA2Id}, ${bean.valueString2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>bean_a2_id</dt><dd>is mapped to <strong>${bean.beanA2Id}</strong></dd>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(BeanA_6 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.beanA2Id!=null) {
      contentValues.put("bean_a2_id", bean.beanA2Id);
    } else {
      contentValues.putNull("bean_a2_id");
    }

    if (bean.valueString2!=null) {
      contentValues.put("value_string2", bean.valueString2);
    } else {
      contentValues.putNull("value_string2");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO bean_a_6 (bean_a2_id, value_string2) VALUES ('"+StringUtils.checkSize(contentValues.get("bean_a2_id"))+"', '"+StringUtils.checkSize(contentValues.get("value_string2"))+"')"));
    long result = database().insert("bean_a_6", null, contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE bean_a_6 SET bean_a2_id=${bean.beanA2Id}, value_string2=${bean.valueString2} WHERE valueString2=${bean.valueString2}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>bean_a2_id</dt><dd>is mapped to <strong>${bean.beanA2Id}</strong></dd>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
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
  public int update(BeanA_6 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.beanA2Id!=null) {
      contentValues.put("bean_a2_id", bean.beanA2Id);
    } else {
      contentValues.putNull("bean_a2_id");
    }

    if (bean.valueString2!=null) {
      contentValues.put("value_string2", bean.valueString2);
    } else {
      contentValues.putNull("value_string2");
    }

    String[] whereConditions={(bean.valueString2==null?null:bean.valueString2)};

    Logger.info(StringUtils.formatSQL("UPDATE bean_a_6 SET bean_a2_id='"+StringUtils.checkSize(contentValues.get("bean_a2_id"))+"', value_string2='"+StringUtils.checkSize(contentValues.get("value_string2"))+"' WHERE valueString2='%s'"), (Object[])whereConditions);
    int result = database().update("bean_a_6", contentValues, "value_string2=?", whereConditions);
    return result;
  }
}
