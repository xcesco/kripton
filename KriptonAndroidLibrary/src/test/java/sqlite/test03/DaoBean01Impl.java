package sqlite.test03;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

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
   * <pre>SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>lista</dt><dd>is associated to bean's property <strong>lista</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>message_date</dt><dd>is associated to bean's property <strong>messageDate</strong></dd>
   * 	<dt>message_text</dt><dd>is associated to bean's property <strong>messageText</strong></dd>
   * 	<dt>bean_list</dt><dd>is associated to bean's property <strong>beanList</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean01> listAll() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean01> resultList=new LinkedList<Bean01>();
    Bean01 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("lista");
      int index1=cursor.getColumnIndex("id");
      int index2=cursor.getColumnIndex("message_date");
      int index3=cursor.getColumnIndex("message_text");
      int index4=cursor.getColumnIndex("bean_list");
      int index5=cursor.getColumnIndex("value");

      do
       {
        resultBean=new Bean01();

        if (!cursor.isNull(index0)) { resultBean.setLista(Bean01Table.parseLista(cursor.getBlob(index0))); }
        if (!cursor.isNull(index1)) { resultBean.setId(cursor.getLong(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setMessageDate(cursor.getLong(index2)); }
        resultBean.setMessageText(cursor.getString(index3));
        if (!cursor.isNull(index4)) { resultBean.setBeanList(Bean01Table.parseBeanList(cursor.getBlob(index4))); }
        if (!cursor.isNull(index5)) { resultBean.setValue(cursor.getLong(index5)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }
}
