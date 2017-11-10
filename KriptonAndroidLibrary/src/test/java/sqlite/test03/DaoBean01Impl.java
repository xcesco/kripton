package sqlite.test03;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
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
   *
   * <pre>SELECT lista, id, message_date, message_text, bean_list, value FROM bean01 WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>lista</dt><dd>is associated to bean's property <strong>lista</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>message_date</dt><dd>is associated to bean's property <strong>messageDate</strong></dd>
   * 	<dt>message_text</dt><dd>is associated to bean's property <strong>messageText</strong></dd>
   * 	<dt>bean_list</dt><dd>is associated to bean's property <strong>beanList</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean01> listAll() {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT lista, id, message_date, message_text, bean_list, value FROM bean01");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE 1=1";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
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
          resultBean.setId(cursor.getLong(index1));
          if (!cursor.isNull(index2)) { resultBean.setMessageDate(cursor.getLong(index2)); }
          resultBean.setMessageText(cursor.getString(index3));
          if (!cursor.isNull(index4)) { resultBean.setBeanList(Bean01Table.parseBeanList(cursor.getBlob(index4))); }
          if (!cursor.isNull(index5)) { resultBean.setValue(cursor.getLong(index5)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  public void clearCompiledStatements() {
  }
}
