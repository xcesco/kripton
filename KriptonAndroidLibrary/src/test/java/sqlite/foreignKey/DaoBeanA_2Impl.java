package sqlite.foreignKey;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
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
   * <p>
   * <pre>SELECT id FROM bean_a_2 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_2> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id FROM bean_a_2 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id FROM bean_a_2 WHERE 1=1", args);
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
}
