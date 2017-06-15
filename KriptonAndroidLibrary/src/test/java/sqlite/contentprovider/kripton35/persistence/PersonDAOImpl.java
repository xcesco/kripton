package sqlite.contentprovider.kripton35.persistence;

import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import sqlite.contentprovider.kripton35.entities.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see sqlite.contentprovider.kripton35.entities.PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  private static final Set<String> selectOne0ColumnSet = CollectionUtils.asSet(String.class, "id", "alias_parent_id", "birth_city", "birth_day", "value", "name", "surname");

  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person WHERE name like ${nameTemp} || \'%\' HAVING id=2 GROUP BY id ORDER BY id#{orderBy}#{pageSize}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * <dt>#{orderBy}</dt>is part of order statement resolved at runtime.</dd><dt>#{pageSize}</dt>is part of limit statement resolved at runtime.</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${nameTemp}</dt><dd>is binded to method's parameter <strong>nameValue</strong></dd>
   * </dl>
   *
   * @param nameValue
   * 	is binded to <code>${nameTemp}</code>
   * @param pageSize
   * 	is used as <strong>dynamic LIMIT statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Person> selectOne(String nameValue, int pageSize, String orderBy) {
    // build where condition
    String[] _args={(nameValue==null?"":nameValue)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person WHERE name like '%s' || \'%%' HAVING id=2 GROUP BY id ORDER BY id"+SqlUtils.appendForLog(orderBy)+SqlUtils.printIf(pageSize>0, " LIMIT "+pageSize),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person WHERE name like ? || \'%\' HAVING id=2 GROUP BY id ORDER BY id"+SqlUtils.appendForSQL(orderBy)+SqlUtils.printIf(pageSize>0, " LIMIT "+pageSize), _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Person> resultList=new LinkedList<Person>();
      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("parent_id");
        int index2=cursor.getColumnIndex("birth_city");
        int index3=cursor.getColumnIndex("birth_day");
        int index4=cursor.getColumnIndex("value");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.parentId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthCity=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthDay=DateUtils.read(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.value=cursor.getLong(index4); }
          if (!cursor.isNull(index5)) { resultBean.setName(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.setSurname(cursor.getString(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons/[*]/test</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, birthCity, birthDay, value, name, surname FROM Person WHERE name like ${nameTemp} || '%' GROUP BY id HAVING id=2 ORDER BY id,  #{DYNAMIC_ORDER_BY} LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, birth_city, birth_day, value, name, surname FROM person WHERE name like ${nameTemp} || '%' GROUP BY id HAVING id=2 ORDER BY id,  #{DYNAMIC_ORDER_BY} LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>${nameTemp}</strong> at path segment 1</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/[*]/test"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectOne0(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    StringBuilder _sqlBuilder=new StringBuilder();
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM person      ");

    // manage WHERE arguments -- BEGIN
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage GROUP BY statement
    String _sqlGroupByStatement=" GROUP BY id";
    _sqlBuilder.append(_sqlGroupByStatement);

    // manage HAVING statement
    String _sqlHavingStatement=" HAVING id=2";
    _sqlBuilder.append(_sqlHavingStatement);

    // manage order by statement
    String _sqlOrderByStatement=" ORDER BY id"+StringUtils.ifNotEmptyAppend(sortOrder, ", ");
    _sqlBuilder.append(_sqlOrderByStatement);
    String _sqlLimitStatement=" LIMIT #{DYNAMIC_PAGE_SIZE}";
    String _sqlOffsetStatement=" OFFSET #{DYNAMIC_PAGE_OFFSET}";

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectOne0ColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons/*/test', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectOne0ColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter nameTemp at path segment 1
    _sqlWhereParams.add(uri.getPathSegments().get(1));

    // manage log
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().rawQuery(_sql, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return _result;
  }
}
