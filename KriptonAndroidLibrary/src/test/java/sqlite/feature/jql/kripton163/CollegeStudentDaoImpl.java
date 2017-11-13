package sqlite.feature.jql.kripton163;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
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
  private static final String GET_STUDENTS_SQL1 = "select * from students where first_name like ? || '%' ";

  private static final String GET_STUDENTS_RAW_SQL2 = "SELECT first_name, surname, id FROM students WHERE first_name like ? || '%' ";

  public CollegeStudentDaoImpl(SQLContext context) {
    super(context);
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
    // query SQL is statically defined
    String _sql=GET_STUDENTS_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((firstName==null?"":firstName));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<CollegeStudent> resultList=new ArrayList<CollegeStudent>(cursor.getCount());
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
    // query SQL is statically defined
    String _sql=GET_STUDENTS_RAW_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((firstName==null?"":firstName));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<CollegeStudent> resultList=new ArrayList<CollegeStudent>(cursor.getCount());
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

  public static void clearCompiledStatements() {
  }
}
