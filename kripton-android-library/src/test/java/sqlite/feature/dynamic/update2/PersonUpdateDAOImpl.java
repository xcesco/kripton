package sqlite.feature.dynamic.update2;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import sqlite.feature.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonUpdateDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonUpdateDAO
 *  @see sqlite.feature.dynamic.PersonTable
 */
public class PersonUpdateDAOImpl extends Dao implements PersonUpdateDAO {
  public PersonUpdateDAOImpl(BindPersonUpdateDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name, surname=:surname, birth_city=:birthCity, birth_day=:birthDay WHERE id = ${bean.id} #{DYNAMIC_WHERE}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>:bean.birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>:bean.birthDay</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * <h2>Method's parameters and associated dynamic parts:</h2>
   * <dl>
   * <dt>where</dt><dd>is part of where conditions resolved at runtime. In above SQL it is displayed as #{DYNAMIC_WHERE}</dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   * @param where
   * 	is used as dynamic where conditions
   */
  @Override
  public void updateBean(Person bean, String where) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.put("name", bean.name);
    _contentValues.put("surname", bean.surname);
    _contentValues.put("birth_city", bean.birthCity);
    _contentValues.put("birth_day", DateUtils.write(bean.birthDay));

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // initialize dynamic where
    String _sqlDynamicWhere=where;
    // generation CODE_001 -- END
    StringBuilder _sqlBuilder=sqlBuilder();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ? "+StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND ");
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql=String.format("UPDATE person SET name=?, surname=?, birth_city=?, birth_day=? WHERE id = ? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name, surname=:surname, birth_city=:birth_city, birth_day=:birth_day WHERE id = ? %s", StringUtils.ifNotEmptyAppend(_sqlDynamicWhere," AND "));

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(_context, _sql, _contentValues);
  }

  public static void clearCompiledStatements() {
  }
}
