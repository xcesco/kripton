package sqlite.test03;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
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
public class DaoBean01Impl extends Dao implements DaoBean01 {
  private static final String LIST_ALL_SQL1 = "SELECT id, bean_list, lista, message_date, message_text, value FROM bean01 WHERE 1=1";

  public DaoBean01Impl(BindDummy01DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, bean_list, lista, message_date, message_text, value FROM bean01 WHERE 1=1</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean01}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_list</dt><dd>is associated to bean's property <strong>beanList</strong></dd>
   * 	<dt>lista</dt><dd>is associated to bean's property <strong>lista</strong></dd>
   * 	<dt>message_date</dt><dd>is associated to bean's property <strong>messageDate</strong></dd>
   * 	<dt>message_text</dt><dd>is associated to bean's property <strong>messageText</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean01> listAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=LIST_ALL_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean01> resultList=new ArrayList<Bean01>(_cursor.getCount());
      Bean01 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("bean_list");
        int index2=_cursor.getColumnIndex("lista");
        int index3=_cursor.getColumnIndex("message_date");
        int index4=_cursor.getColumnIndex("message_text");
        int index5=_cursor.getColumnIndex("value");

        do
         {
          resultBean=new Bean01();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setBeanList(Bean01Table.parseBeanList(_cursor.getBlob(index1))); }
          if (!_cursor.isNull(index2)) { resultBean.setLista(Bean01Table.parseLista(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.setMessageDate(_cursor.getLong(index3)); }
          resultBean.setMessageText(_cursor.getString(index4));
          if (!_cursor.isNull(index5)) { resultBean.setValue(_cursor.getLong(index5)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  public static void clearCompiledStatements() {
  }
}
