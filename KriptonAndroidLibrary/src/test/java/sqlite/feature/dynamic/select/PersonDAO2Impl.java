package sqlite.feature.dynamic.select;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO2</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO2
 *  @see sqlite.feature.dynamic.PersonTable
 */
public class PersonDAO2Impl extends Dao implements PersonDAO2 {
  public PersonDAO2Impl(SQLContext context) {
    super(context);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from person where id=${id} and #{ DYNAMIC_WHERE }</pre>
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
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> select(long id, String where) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("select * from person");
    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id=? and #{ DYNAMIC_WHERE }";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
