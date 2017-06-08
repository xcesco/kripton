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
  private static final Set<String> selectBeanListener0ColumnSet = CollectionUtils.asSet(String.class, "id", "parentId", "birthCity", "birthDay", "value", "name", "surname");

  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person ORDER BY name#{orderBy}</pre>
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
   * <dt>#{orderBy}</dt>is part of order statement resolved at runtime.</dd>
   * </dl>
   *
   * @param beanListener
   * 	is the Person listener
   * @param orderBy
   * 	is used as <strong>dynamic ORDER BY statement</strong> and it is formatted by ({@link StringUtils#format})
   */
  @Override
  public void selectBeanListener(OnReadBeanListener<Person> beanListener, String orderBy) {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person ORDER BY name"+SqlUtils.appendForLog(orderBy),(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, parent_id, birth_city, birth_day, value, name, surname FROM person ORDER BY name"+SqlUtils.appendForSQL(orderBy), _args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Person resultBean=new Person();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("parent_id");
        int index2=cursor.getColumnIndex("birth_city");
        int index3=cursor.getColumnIndex("birth_day");
        int index4=cursor.getColumnIndex("value");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("surname");

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
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.parentId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.birthCity=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.birthDay=DateUtils.read(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.value=cursor.getLong(index4); }
          if (!cursor.isNull(index5)) { resultBean.setName(cursor.getString(index5)); }
          if (!cursor.isNull(index6)) { resultBean.setSurname(cursor.getString(index6)); }

          beanListener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.contentprovider.kripton35/persons</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, parentId, birthCity, birthDay, value, name, surname FROM Person ORDER BY name,  #{orderBy}</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, alias_parent_id, birth_city, birth_day, value, name, surname FROM person ORDER BY name,  #{orderBy}</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.contentprovider.kripton35/persons"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectBeanListener0(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    //SqlUtils and StringUtils will be used to format SQL
    String sqlWhereStatement=null;
    String sqlGroupStatement=null;
    String sqlHavingStatement=null;
    String sqlOrderByStatement=" ORDER BY name,  #{orderBy}";
    String sqlLimitStatement=null;
    String sqlOffsetStatement=null;
    ArrayList<String> whereParams=new ArrayList<>();
    for (String columnName:projection) {
      if (!selectBeanListener0ColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.contentprovider.kripton35/persons', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "person" ));
      }
    }

    String sql="SELECT id, alias_parent_id, birth_city, birth_day, value, name, surname FROM person ORDER BY name,  #{orderBy}";
    Cursor result = database().rawQuery(sql, whereParams.toArray(new String[whereParams.size()]));
    return result;
  }
}
