package sqlite.feature.jql.kripton164;

import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

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
  private SQLiteStatement insertPreparedStatement0;

  private SQLiteStatement insertBeanFromSelectPreparedStatement1;

  public CollegeStudentDaoImpl(BindCollegeStudentsDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO students (surname) VALUES (${student.surname})</pre>
   *
   * <p><code>student.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${student.surname}</strong></dd>
   * </dl>
   *
   * @param student
   * 	is mapped to parameter <strong>student</strong>
   *
   */
  @Override
  public void insert(CollegeStudent student) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (student.surname!=null) {
      _contentValues.put("surname", student.surname);
    } else {
      _contentValues.putNull("surname");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO students (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
      // log for insert -- END 

    }
    // log section END
    // insert operation
    if (insertPreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO students (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertPreparedStatement0, _contentValues);
    student.id=result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO students (surname) SELECT surname FROM students WHERE surname=${bean.surname}</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertBeanFromSelect(CollegeStudent bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.surname!=null) {
      _contentValues.put("surname", bean.surname);
    } else {
      _contentValues.putNull("surname");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT OR REPLACE INTO students (%s) SELECT surname FROM students WHERE surname=${bean.surname}", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
      // log for insert -- END 

    }
    // log section END
    // insert operation
    if (insertBeanFromSelectPreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT OR REPLACE INTO students (%s) SELECT surname FROM students WHERE surname=${bean.surname}", _contentValues.keyList(), _contentValues.keyValueList());
      insertBeanFromSelectPreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertBeanFromSelectPreparedStatement1, _contentValues);
    bean.id=result;
  }

  public void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (insertBeanFromSelectPreparedStatement1!=null) {
      insertBeanFromSelectPreparedStatement1.close();
      insertBeanFromSelectPreparedStatement1=null;
    }
  }
}
