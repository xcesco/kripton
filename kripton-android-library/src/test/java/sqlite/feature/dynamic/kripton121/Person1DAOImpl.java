package sqlite.feature.dynamic.kripton121;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>Person1DAO</code>
 * </p>
 *
 *  @see Person
 *  @see Person1DAO
 *  @see sqlite.feature.dynamic.PersonTable
 */
public class Person1DAOImpl extends Dao implements Person1DAO {
  public Person1DAOImpl(BindPerson1DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person WHERE #{DYNAMIC_WHERE} ORDER BY #{DYNAMIC_ORDER_BY}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * <dt>orderBy</dt>is part of order statement resolved at runtime. In above SQL it is displayed as #{DYNAMIC_ORDER_BY}</dd>
   * </dl>
   *
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String where, String orderBy) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name, surname, birth_city, birth_day FROM person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    String _sortOrder=orderBy;

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=StringUtils.ifNotEmptyAppend(_sqlDynamicWhere, " WHERE ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation order - BEGIN
    String _sqlOrderByStatement=StringUtils.ifNotEmptyAppend(_sortOrder," ORDER BY ");
    _sqlBuilder.append(_sqlOrderByStatement);
    // generation order - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
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
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");
        int index3=_cursor.getColumnIndex("birth_city");
        int index4=_cursor.getColumnIndex("birth_day");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.birthCity=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(_cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  public static void clearCompiledStatements() {
  }
}
