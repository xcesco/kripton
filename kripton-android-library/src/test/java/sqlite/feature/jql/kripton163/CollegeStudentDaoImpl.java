package sqlite.feature.jql.kripton163;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
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
public class CollegeStudentDaoImpl extends Dao implements CollegeStudentDao {
  /**
   * SQL definition for method getStudents
   */
  private static final String GET_STUDENTS_SQL1 = "select * from students where first_name like ? || '%' ";

  /**
   * SQL definition for method getStudentsRaw
   */
  private static final String GET_STUDENTS_RAW_SQL2 = "SELECT id, first_name, surname FROM students WHERE first_name like ? || '%' ";

  public CollegeStudentDaoImpl(BindCollegeStudentsDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from students where first_name like ${firstName} || '%' </pre>
   *
   * <h2>Mapped class:</h2>
   * {@link CollegeStudent}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:firstName</dt><dd>is binded to method's parameter <strong>firstName</strong></dd>
   * </dl>
   *
   * @param firstName
   * 	is binded to <code>:firstName</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<CollegeStudent> getStudents(String firstName) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_STUDENTS_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((firstName==null?"":firstName));
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

      ArrayList<CollegeStudent> resultList=new ArrayList<CollegeStudent>(_cursor.getCount());
      CollegeStudent resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("first_name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new CollegeStudent();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.firstName=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, first_name, surname FROM students WHERE first_name like ${firstName} || '%' </pre>
   *
   * <h2>Mapped class:</h2>
   * {@link CollegeStudent}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>first_name</dt><dd>is associated to bean's property <strong>firstName</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:firstName</dt><dd>is binded to method's parameter <strong>firstName</strong></dd>
   * </dl>
   *
   * @param firstName
   * 	is binded to <code>:firstName</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<CollegeStudent> getStudentsRaw(String firstName) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_STUDENTS_RAW_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((firstName==null?"":firstName));
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

      ArrayList<CollegeStudent> resultList=new ArrayList<CollegeStudent>(_cursor.getCount());
      CollegeStudent resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("first_name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new CollegeStudent();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.firstName=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

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
