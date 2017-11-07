package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;

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
    KriptonContentValues _contentValues=contentValues();
    if (student.surname!=null) {
      _contentValues.put("surname", student.surname);
    } else {
      _contentValues.putNull("surname");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO students (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    // generate SQL for insert
    String _sql=String.format("INSERT INTO students (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
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
    KriptonContentValues _contentValues=contentValues();
    if (bean.surname!=null) {
      _contentValues.put("surname", bean.surname);
    } else {
      _contentValues.putNull("surname");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT OR REPLACE INTO students (%s) SELECT surname FROM students WHERE surname=${bean.surname}", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    // generate SQL for insert
    String _sql=String.format("INSERT OR REPLACE INTO students (%s) SELECT surname FROM students WHERE surname=${bean.surname}", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
    bean.id=result;
  }
}
