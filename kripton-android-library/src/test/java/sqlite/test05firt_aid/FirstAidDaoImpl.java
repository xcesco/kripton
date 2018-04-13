package sqlite.test05firt_aid;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>FirstAid</code>, based on interface <code>FirstAidDao</code>
 * </p>
 *
 *  @see FirstAid
 *  @see FirstAidDao
 *  @see FirstAidTable
 */
public class FirstAidDaoImpl extends Dao implements FirstAidDao {
  private static final String SELECT_ALL_SQL1 = "SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid ORDER BY description";

  private static SQLiteStatement deleteAllPreparedStatement0;

  private static SQLiteStatement insertPreparedStatement1;

  public FirstAidDaoImpl(BindFirstAidDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid ORDER BY description</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>description</dt><dd>is associated to bean's property <strong>description</strong></dd>
   * 	<dt>info</dt><dd>is associated to bean's property <strong>info</strong></dd>
   * 	<dt>longitude</dt><dd>is associated to bean's property <strong>longitude</strong></dd>
   * 	<dt>latitude</dt><dd>is associated to bean's property <strong>latitude</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>address2</dt><dd>is associated to bean's property <strong>address2</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>total_patient_count</dt><dd>is associated to bean's property <strong>totalPatientCount</strong></dd>
   * 	<dt>white_waiting_patients</dt><dd>is associated to bean's property <strong>whiteWaitingPatients</strong></dd>
   * 	<dt>white_visiting_patients</dt><dd>is associated to bean's property <strong>whiteVisitingPatients</strong></dd>
   * 	<dt>white_average_waiting_time</dt><dd>is associated to bean's property <strong>whiteAverageWaitingTime</strong></dd>
   * 	<dt>green_waiting_patients</dt><dd>is associated to bean's property <strong>greenWaitingPatients</strong></dd>
   * 	<dt>green_visiting_patients</dt><dd>is associated to bean's property <strong>greenVisitingPatients</strong></dd>
   * 	<dt>green_average_waiting_time</dt><dd>is associated to bean's property <strong>greenAverageWaitingTime</strong></dd>
   * 	<dt>yellow_waiting_patients</dt><dd>is associated to bean's property <strong>yellowWaitingPatients</strong></dd>
   * 	<dt>yellow_visiting_patients</dt><dd>is associated to bean's property <strong>yellowVisitingPatients</strong></dd>
   * 	<dt>yellow_average_waiting_time</dt><dd>is associated to bean's property <strong>yellowAverageWaitingTime</strong></dd>
   * 	<dt>red_waiting_patients</dt><dd>is associated to bean's property <strong>redWaitingPatients</strong></dd>
   * 	<dt>red_average_waiting_time</dt><dd>is associated to bean's property <strong>redAverageWaitingTime</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<FirstAid> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
    // add where arguments
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<FirstAid> resultList=new ArrayList<FirstAid>(_cursor.getCount());
      FirstAid resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("uid");
        int index2=_cursor.getColumnIndex("description");
        int index3=_cursor.getColumnIndex("info");
        int index4=_cursor.getColumnIndex("longitude");
        int index5=_cursor.getColumnIndex("latitude");
        int index6=_cursor.getColumnIndex("address");
        int index7=_cursor.getColumnIndex("address2");
        int index8=_cursor.getColumnIndex("city");
        int index9=_cursor.getColumnIndex("phone");
        int index10=_cursor.getColumnIndex("total_patient_count");
        int index11=_cursor.getColumnIndex("white_waiting_patients");
        int index12=_cursor.getColumnIndex("white_visiting_patients");
        int index13=_cursor.getColumnIndex("white_average_waiting_time");
        int index14=_cursor.getColumnIndex("green_waiting_patients");
        int index15=_cursor.getColumnIndex("green_visiting_patients");
        int index16=_cursor.getColumnIndex("green_average_waiting_time");
        int index17=_cursor.getColumnIndex("yellow_waiting_patients");
        int index18=_cursor.getColumnIndex("yellow_visiting_patients");
        int index19=_cursor.getColumnIndex("yellow_average_waiting_time");
        int index20=_cursor.getColumnIndex("red_waiting_patients");
        int index21=_cursor.getColumnIndex("red_average_waiting_time");

        do
         {
          resultBean=new FirstAid();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.uid=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.description=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.info=_cursor.getString(index3); }
          if (!_cursor.isNull(index4)) { resultBean.longitude=_cursor.getFloat(index4); }
          if (!_cursor.isNull(index5)) { resultBean.latitude=_cursor.getFloat(index5); }
          if (!_cursor.isNull(index6)) { resultBean.address=_cursor.getString(index6); }
          if (!_cursor.isNull(index7)) { resultBean.address2=_cursor.getString(index7); }
          if (!_cursor.isNull(index8)) { resultBean.city=_cursor.getString(index8); }
          if (!_cursor.isNull(index9)) { resultBean.phone=_cursor.getString(index9); }
          if (!_cursor.isNull(index10)) { resultBean.totalPatientCount=_cursor.getInt(index10); }
          if (!_cursor.isNull(index11)) { resultBean.whiteWaitingPatients=_cursor.getInt(index11); }
          if (!_cursor.isNull(index12)) { resultBean.whiteVisitingPatients=_cursor.getInt(index12); }
          if (!_cursor.isNull(index13)) { resultBean.whiteAverageWaitingTime=_cursor.getString(index13); }
          if (!_cursor.isNull(index14)) { resultBean.greenWaitingPatients=_cursor.getInt(index14); }
          if (!_cursor.isNull(index15)) { resultBean.greenVisitingPatients=_cursor.getInt(index15); }
          if (!_cursor.isNull(index16)) { resultBean.greenAverageWaitingTime=_cursor.getString(index16); }
          if (!_cursor.isNull(index17)) { resultBean.yellowWaitingPatients=_cursor.getInt(index17); }
          if (!_cursor.isNull(index18)) { resultBean.yellowVisitingPatients=_cursor.getInt(index18); }
          if (!_cursor.isNull(index19)) { resultBean.yellowAverageWaitingTime=_cursor.getString(index19); }
          if (!_cursor.isNull(index20)) { resultBean.redWaitingPatients=_cursor.getInt(index20); }
          if (!_cursor.isNull(index21)) { resultBean.redAverageWaitingTime=_cursor.getString(index21); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM first_aid WHERE 1=1</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   *
   * @return number of deleted records
   */
  @Override
  public int deleteAll() {
    if (deleteAllPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM first_aid WHERE 1=1";
      deleteAllPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement0);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM first_aid WHERE 1=1");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO first_aid (uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time) VALUES (${bean.uid}, ${bean.description}, ${bean.info}, ${bean.longitude}, ${bean.latitude}, ${bean.address}, ${bean.address2}, ${bean.city}, ${bean.phone}, ${bean.totalPatientCount}, ${bean.whiteWaitingPatients}, ${bean.whiteVisitingPatients}, ${bean.whiteAverageWaitingTime}, ${bean.greenWaitingPatients}, ${bean.greenVisitingPatients}, ${bean.greenAverageWaitingTime}, ${bean.yellowWaitingPatients}, ${bean.yellowVisitingPatients}, ${bean.yellowAverageWaitingTime}, ${bean.redWaitingPatients}, ${bean.redAverageWaitingTime})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>description</dt><dd>is mapped to <strong>${bean.description}</strong></dd>
   * 	<dt>info</dt><dd>is mapped to <strong>${bean.info}</strong></dd>
   * 	<dt>longitude</dt><dd>is mapped to <strong>${bean.longitude}</strong></dd>
   * 	<dt>latitude</dt><dd>is mapped to <strong>${bean.latitude}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * 	<dt>address2</dt><dd>is mapped to <strong>${bean.address2}</strong></dd>
   * 	<dt>city</dt><dd>is mapped to <strong>${bean.city}</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>${bean.phone}</strong></dd>
   * 	<dt>total_patient_count</dt><dd>is mapped to <strong>${bean.totalPatientCount}</strong></dd>
   * 	<dt>white_waiting_patients</dt><dd>is mapped to <strong>${bean.whiteWaitingPatients}</strong></dd>
   * 	<dt>white_visiting_patients</dt><dd>is mapped to <strong>${bean.whiteVisitingPatients}</strong></dd>
   * 	<dt>white_average_waiting_time</dt><dd>is mapped to <strong>${bean.whiteAverageWaitingTime}</strong></dd>
   * 	<dt>green_waiting_patients</dt><dd>is mapped to <strong>${bean.greenWaitingPatients}</strong></dd>
   * 	<dt>green_visiting_patients</dt><dd>is mapped to <strong>${bean.greenVisitingPatients}</strong></dd>
   * 	<dt>green_average_waiting_time</dt><dd>is mapped to <strong>${bean.greenAverageWaitingTime}</strong></dd>
   * 	<dt>yellow_waiting_patients</dt><dd>is mapped to <strong>${bean.yellowWaitingPatients}</strong></dd>
   * 	<dt>yellow_visiting_patients</dt><dd>is mapped to <strong>${bean.yellowVisitingPatients}</strong></dd>
   * 	<dt>yellow_average_waiting_time</dt><dd>is mapped to <strong>${bean.yellowAverageWaitingTime}</strong></dd>
   * 	<dt>red_waiting_patients</dt><dd>is mapped to <strong>${bean.redWaitingPatients}</strong></dd>
   * 	<dt>red_average_waiting_time</dt><dd>is mapped to <strong>${bean.redAverageWaitingTime}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(FirstAid bean) {
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO first_aid (uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put("uid", bean.uid);
    _contentValues.put("description", bean.description);
    _contentValues.put("info", bean.info);
    _contentValues.put("longitude", bean.longitude);
    _contentValues.put("latitude", bean.latitude);
    _contentValues.put("address", bean.address);
    _contentValues.put("address2", bean.address2);
    _contentValues.put("city", bean.city);
    _contentValues.put("phone", bean.phone);
    _contentValues.put("total_patient_count", bean.totalPatientCount);
    _contentValues.put("white_waiting_patients", bean.whiteWaitingPatients);
    _contentValues.put("white_visiting_patients", bean.whiteVisitingPatients);
    _contentValues.put("white_average_waiting_time", bean.whiteAverageWaitingTime);
    _contentValues.put("green_waiting_patients", bean.greenWaitingPatients);
    _contentValues.put("green_visiting_patients", bean.greenVisitingPatients);
    _contentValues.put("green_average_waiting_time", bean.greenAverageWaitingTime);
    _contentValues.put("yellow_waiting_patients", bean.yellowWaitingPatients);
    _contentValues.put("yellow_visiting_patients", bean.yellowVisitingPatients);
    _contentValues.put("yellow_average_waiting_time", bean.yellowAverageWaitingTime);
    _contentValues.put("red_waiting_patients", bean.redWaitingPatients);
    _contentValues.put("red_average_waiting_time", bean.redAverageWaitingTime);

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
      Logger.info("INSERT INTO first_aid (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
    bean.id=result;

    return (int)result;
  }

  public static void clearCompiledStatements() {
    if (deleteAllPreparedStatement0!=null) {
      deleteAllPreparedStatement0.close();
      deleteAllPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
  }
}
