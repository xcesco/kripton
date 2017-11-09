package sqlite.feature.jql.kripton163;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>CollegeStudent</code>, based on interface <code>CollegeStudentDao</code>
 * </p>
 *
 *  @see CollegeStudent
 *  @see CollegeStudentDao
 *  @see CollegeStudentTable
 */
public class CollegeStudentDaoImpl extends AbstractDao implements CollegeStudentDao {
  public CollegeStudentDaoImpl(BindCollegeStudentsDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from students where first_name like ${firstName} || '%' </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${firstName}</dt><dd>is binded to method's parameter <strong>firstName</strong></dd>
   * </dl>
   *
   * @param firstName
   * 	is binded to <code>${firstName}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<CollegeStudent> getStudents(String firstName) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("select * from students");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where first_name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((firstName==null?"":firstName));
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

      LinkedList<CollegeStudent> resultList=new LinkedList<CollegeStudent>();
      CollegeStudent resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("first_name");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("id");

        do
         {
          resultBean=new CollegeStudent();

          if (!cursor.isNull(index0)) { resultBean.firstName=cursor.getString(index0); }
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          resultBean.id=cursor.getLong(index2);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT first_name, surname, id FROM students WHERE first_name like ${firstName} || '%' </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${firstName}</dt><dd>is binded to method's parameter <strong>firstName</strong></dd>
   * </dl>
   *
   * @param firstName
   * 	is binded to <code>${firstName}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<CollegeStudent> getStudentsRaw(String firstName) {
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT first_name, surname, id FROM students");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE first_name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _contentValues.addWhereArgs((firstName==null?"":firstName));
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

      LinkedList<CollegeStudent> resultList=new LinkedList<CollegeStudent>();
      CollegeStudent resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("first_name");
        int index1=cursor.getColumnIndex("surname");
        int index2=cursor.getColumnIndex("id");

        do
         {
          resultBean=new CollegeStudent();

          if (!cursor.isNull(index0)) { resultBean.firstName=cursor.getString(index0); }
          if (!cursor.isNull(index1)) { resultBean.surname=cursor.getString(index1); }
          resultBean.id=cursor.getLong(index2);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  public void clearCompiledStatements() {
  }
}
