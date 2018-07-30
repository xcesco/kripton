package sqlite.feature.pkstring.many2many1;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>PersonCity</code>, based on interface <code>GeneratedPersonCityDao</code>
 * </p>
 *
 *  @see PersonCity
 *  @see GeneratedPersonCityDao
 *  @see PersonCityTable
 */
public class PersonCityDaoImpl extends Dao implements GeneratedPersonCityDao {
  private static final String SELECT_BY_ID_SQL5 = "SELECT id, city_id, person_id FROM person_city WHERE id=?";

  private static final String SELECT_BY_PERSON_ID_SQL6 = "SELECT id, city_id, person_id FROM person_city WHERE person_id=?";

  private static final String SELECT_BY_CITY_ID_SQL7 = "SELECT id, city_id, person_id FROM person_city WHERE city_id=?";

  private static SQLiteStatement deleteByIdPreparedStatement0;

  private static SQLiteStatement deleteByPersonIdPreparedStatement1;

  private static SQLiteStatement deleteByCityIdPreparedStatement2;

  private static SQLiteStatement insertPreparedStatement3;

  public PersonCityDaoImpl(BindPersonCirtyDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, city_id, person_id FROM person_city WHERE id=:id</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>city_id</dt><dd>is associated to bean's property <strong>cityId</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PersonCity selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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
      // Specialized part - SelectBeanHelper - BEGIN

      PersonCity resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      long __personId=0;
      String __cityId=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("city_id");
        int index2=_cursor.getColumnIndex("person_id");

        // reset temporary variable for immutable POJO
        // immutable object: initialize temporary variables for properties
        __id=0;
        __personId=0;
        __cityId=null;
        __id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { __cityId=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { __personId=_cursor.getLong(index2); }

      }
      // define immutable POJO
      // immutable object: inizialize object
      resultBean=new PersonCity(__id,__personId,__cityId);
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, city_id, person_id FROM person_city WHERE person_id=:personId</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>city_id</dt><dd>is associated to bean's property <strong>cityId</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:personId</dt><dd>is binded to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is binded to <code>:personId</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<PersonCity> selectByPersonId(long personId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_PERSON_ID_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(personId));
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

      ArrayList<PersonCity> resultList=new ArrayList<PersonCity>(_cursor.getCount());
      PersonCity resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      long __personId=0;
      String __cityId=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("city_id");
        int index2=_cursor.getColumnIndex("person_id");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __personId=0;
          __cityId=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __cityId=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __personId=_cursor.getLong(index2); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new PersonCity(__id,__personId,__cityId);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, city_id, person_id FROM person_city WHERE city_id=:cityId</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>city_id</dt><dd>is associated to bean's property <strong>cityId</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:cityId</dt><dd>is binded to method's parameter <strong>cityId</strong></dd>
   * </dl>
   *
   * @param cityId
   * 	is binded to <code>:cityId</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<PersonCity> selectByCityId(String cityId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CITY_ID_SQL7;
    // add where arguments
    _contentValues.addWhereArgs((cityId==null?"":cityId));
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

      ArrayList<PersonCity> resultList=new ArrayList<PersonCity>(_cursor.getCount());
      PersonCity resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      long __personId=0;
      String __cityId=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("city_id");
        int index2=_cursor.getColumnIndex("person_id");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __personId=0;
          __cityId=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __cityId=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __personId=_cursor.getLong(index2); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new PersonCity(__id,__personId,__cityId);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_city WHERE id=:id</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteById(long id) {
    if (deleteByIdPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person_city WHERE id=?";
      deleteByIdPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement0);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_city WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_city WHERE person_id=:personId</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:personId</dt><dd>is mapped to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is used as where parameter <strong>:personId</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteByPersonId(long personId) {
    if (deleteByPersonIdPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person_city WHERE person_id=?";
      deleteByPersonIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByPersonIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(personId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_city WHERE person_id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByPersonIdPreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_city WHERE city_id=:cityId</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:cityId</dt><dd>is mapped to method's parameter <strong>cityId</strong></dd>
   * </dl>
   *
   * @param cityId
   * 	is used as where parameter <strong>:cityId</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteByCityId(String cityId) {
    if (deleteByCityIdPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person_city WHERE city_id=?";
      deleteByCityIdPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByCityIdPreparedStatement2);
    _contentValues.addWhereArgs((cityId==null?"":cityId));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person_city WHERE city_id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByCityIdPreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person_city (city_id, person_id) VALUES (:bean.cityId, :bean.personId)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>city_id</dt><dd>is mapped to <strong>:bean.cityId</strong></dd>
   * 	<dt>person_id</dt><dd>is mapped to <strong>:bean.personId</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PersonCity bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person_city (city_id, person_id) VALUES (?, ?)";
      insertPreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement3);
    _contentValues.put("city_id", bean.getCityId());
    _contentValues.put("person_id", bean.getPersonId());

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO person_city (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement3, _contentValues);

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    if (deleteByIdPreparedStatement0!=null) {
      deleteByIdPreparedStatement0.close();
      deleteByIdPreparedStatement0=null;
    }
    if (deleteByPersonIdPreparedStatement1!=null) {
      deleteByPersonIdPreparedStatement1.close();
      deleteByPersonIdPreparedStatement1=null;
    }
    if (deleteByCityIdPreparedStatement2!=null) {
      deleteByCityIdPreparedStatement2.close();
      deleteByCityIdPreparedStatement2=null;
    }
    if (insertPreparedStatement3!=null) {
      insertPreparedStatement3.close();
      insertPreparedStatement3=null;
    }
  }
}
