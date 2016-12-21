package sqlite.foreignKey;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanA_1</code>, based on interface <code>DaoBeanA_1</code>
 * </p>
 *
 *  @see BeanA_1
 *  @see DaoBeanA_1
 *  @see BeanA_1Table
 */
public class DaoBeanA_1Impl extends AbstractDao implements DaoBeanA_1 {
  public DaoBeanA_1Impl(BindDummyDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, bean_a2_id FROM bean_a_1 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_1> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, bean_a2_id FROM bean_a_1 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, bean_a2_id FROM bean_a_1 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_1> resultList=new LinkedList<BeanA_1>();
    BeanA_1 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("bean_a2_id");

      do
       {
        resultBean=new BeanA_1();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.beanA2Id=cursor.getLong(index1); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }
}
