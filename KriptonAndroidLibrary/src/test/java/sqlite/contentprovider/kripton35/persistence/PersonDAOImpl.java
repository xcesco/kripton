package sqlite.contentprovider.kripton35.persistence;

import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.ArrayList;
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
  private static final Set<String> selectBeanListener0ColumnSet = CollectionUtils.asSet(String.class, "parentId", "birthCity", "birthDay", "value", "name", "surname");

  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT parent_id, birth_city, birth_day, value, name, surname FROM person WHERE parentId= ${id} #{where} HAVING parentId = \'a\' GROUP BY parentId ORDER BY name</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
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
   * <dt>#{where}</dt><dd>is part of where conditions resolved at runtime.</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param beanListener
   * 	is the Person listener
   * @param id
   * 	is binded to <code>${id}</code>
   * @param where
   * 	is used as <strong>dynamic WHERE statement</strong> and it is formatted by ({@link StringUtils#format})
   * @param args
   * 	is binded to <code>${args}</code>
   */
  @Override
  public void selectBeanListener(OnReadBeanListener<Person> beanListener, long id, String where, String[] args) {
    // build where condition
    String[] _args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT parent_id, birth_city, birth_day, value, name, surname FROM person WHERE parent_id= '%s' "+SqlUtils.appendForLog(where)+" HAVING parent_id = \'a\' GROUP BY parent_id ORDER BY name",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT parent_id, birth_city, birth_day, value, name, surname FROM person WHERE parent_id= ? "+SqlUtils.appendForSQL(where)+" HAVING parent_id = \'a\' GROUP BY parent_id ORDER BY name", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Person resultBean=new Person();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("parent_id");
        int index1=cursor.getColumnIndex("birth_city");
        int index2=cursor.getColumnIndex("birth_day");
        int index3=cursor.getColumnIndex("value");
        int index4=cursor.getColumnIndex("name");
        int index5=cursor.getColumnIndex("surname");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.parentId=0L;
          resultBean.birthCity=null;
          resultBean.birthDay=null;
          resultBean.value=0L;
          resultBean.setName(null);
          resultBean.setSurname(null);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.parentId=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.birthCity=cursor.getString(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthDay=DateUtils.read(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.value=cursor.getLong(index3); }
          if (!cursor.isNull(index4)) { resultBean.setName(cursor.getString(index4)); }
          if (!cursor.isNull(index5)) { resultBean.setSurname(cursor.getString(index5)); }

          beanListener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons/#</pre>
   *
   * <p>Path variables defined:</p>
   * <ul>
   * <li><strong>${id}</strong> at path segment 1</li>
   * </ul>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT parentId, birthCity, birthDay, value, name, surname FROM Person WHERE parentId= ${id} AND #{DYNAMIC_WHERE} GROUP BY parentId HAVING parentId = 'a' ORDER BY name</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT alias_parent_id, birth_city, birth_day, value, name, surname FROM person WHERE alias_parent_id= ${id} AND #{DYNAMIC_WHERE} GROUP BY alias_parent_id HAVING alias_parent_id = 'a' ORDER BY name</pre>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons/#"
   * @param selection dynamic part of <code>where</code> statement 
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement 
   * @return number of effected rows
   */
  Cursor selectBeanListener0(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    //SqlUtils and StringUtils will be used to format SQL
    StringBuilder sqlBuilder=new StringBuilder();
    sqlBuilder.append("SELECT @{__TEMPORARY_PLACE_HOLDER_FOR_PROJECTED_COLUMNS__} FROM person    ");

    // manage WHERE statement
    String sqlWhereStatement=" WHERE alias_parent_id= ${id}"+(StringUtils.hasText(selection) ? ", "+selection: "");
    sqlBuilder.append(sqlWhereStatement);

    // manage GROUP BY statement
    String sqlGroupByStatement=" GROUP BY alias_parent_id";
    sqlBuilder.append(sqlGroupByStatement);

    // manage HAVING statement
    String sqlHavingStatement=" HAVING alias_parent_id = 'a'";
    sqlBuilder.append(sqlHavingStatement);

    // manage order by statement
    String sqlOrderByStatement=" ORDER BY name";
    sqlBuilder.append(sqlOrderByStatement);

    // check projected fields
    ArrayList<String> whereParams=new ArrayList<>();

    // manage where arguments
    if (StringUtils.hasText(selection) && selectionArgs!=null) {
      for (String arg: selectionArgs) {
        whereParams.add(arg);
      }
    }
    for (String columnName:projection) {
      if (!selectBeanListener0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
      }
    }
    StringBuilder projectionBuffer=new StringBuilder();
    // Add parameter id at path segment 1
    whereParams.add(uri.getPathSegments().get(1));

    // execute query
    String sql=sqlBuilder.toString();

    // SELECT alias_parent_id, birth_city, birth_day, value, name, surname FROM person WHERE alias_parent_id= %s AND "+selection+" GROUP BY alias_parent_id HAVING alias_parent_id = 'a' ORDER BY name
    Logger.info(sql);
    Cursor result = database().rawQuery(sql, whereParams.toArray(new String[whereParams.size()]));
    return result;
  }
}
