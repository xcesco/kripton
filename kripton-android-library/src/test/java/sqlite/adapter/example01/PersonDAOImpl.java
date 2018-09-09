package sqlite.adapter.example01;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see PersonTable
 */
public class PersonDAOImpl extends Dao implements PersonDAO {
  /**
   * SQL definition for method selectByBirthday
   */
  private static final String SELECT_BY_BIRTHDAY_SQL2 = "SELECT id, birth, name, surname FROM person WHERE birth=?";

  public PersonDAOImpl(BindExample01DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth, name, surname FROM person WHERE birth=${birthDay}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth</dt><dd>is associated to bean's property <strong>birthDate</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:birthDay</dt><dd>is binded to method's parameter <strong>birthDay</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is binded to <code>:birthDay</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectByBirthday(Date birthDay) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_BIRTHDAY_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((birthDay==null?"":DateUtils.write(birthDay)));
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

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("birth");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setBirthDate(DateUtils.read(_cursor.getString(index1))); }
          if (!_cursor.isNull(index2)) { resultBean.setName(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setSurname(_cursor.getString(index3)); }

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
