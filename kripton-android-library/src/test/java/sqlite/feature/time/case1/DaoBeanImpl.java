package sqlite.feature.time.case1;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.common.time.DurationUtils;
import com.abubusoft.kripton.common.time.InstantUtils;
import com.abubusoft.kripton.common.time.LocalDateTimeUtils;
import com.abubusoft.kripton.common.time.LocalDateUtils;
import com.abubusoft.kripton.common.time.LocalTimeUtils;
import com.abubusoft.kripton.common.time.MonthDayUtils;
import com.abubusoft.kripton.common.time.OffsetDateTimeUtils;
import com.abubusoft.kripton.common.time.OffsetTimeUtils;
import com.abubusoft.kripton.common.time.PeriodUtils;
import com.abubusoft.kripton.common.time.YearMonthUtils;
import com.abubusoft.kripton.common.time.YearUtils;
import com.abubusoft.kripton.common.time.ZoneIdUtils;
import com.abubusoft.kripton.common.time.ZoneOffsetUtils;
import com.abubusoft.kripton.common.time.ZonedDateTimeUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean</code>, based on interface <code>DaoBean</code>
 * </p>
 *
 *  @see Bean
 *  @see DaoBean
 *  @see BeanTable
 */
public class DaoBeanImpl extends Dao implements DaoBean {
  private static SupportSQLiteStatement insertPreparedStatement0;

  private static SupportSQLiteStatement updatePreparedStatement1;

  /**
   * SQL definition for method select
   */
  private static final String SELECT_SQL1 = "SELECT id, duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time FROM bean WHERE year=?";

  private static SupportSQLiteStatement deletePreparedStatement2;

  public DaoBeanImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean (duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time) VALUES (:duration, :istant, :localDate, :localDateTime, :localTime, :monthDay, :offsetDateTime, :offsetTime, :period, :year, :yearMonth, :zoneId, :zoneOffset, :zonedDateTime)</pre>
   *
   * <p><code>value.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>duration</dt><dd>is mapped to <strong>:value.duration</strong></dd>
   * 	<dt>istant</dt><dd>is mapped to <strong>:value.istant</strong></dd>
   * 	<dt>local_date</dt><dd>is mapped to <strong>:value.localDate</strong></dd>
   * 	<dt>local_date_time</dt><dd>is mapped to <strong>:value.localDateTime</strong></dd>
   * 	<dt>local_time</dt><dd>is mapped to <strong>:value.localTime</strong></dd>
   * 	<dt>month_day</dt><dd>is mapped to <strong>:value.monthDay</strong></dd>
   * 	<dt>offset_date_time</dt><dd>is mapped to <strong>:value.offsetDateTime</strong></dd>
   * 	<dt>offset_time</dt><dd>is mapped to <strong>:value.offsetTime</strong></dd>
   * 	<dt>period</dt><dd>is mapped to <strong>:value.period</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:value.year</strong></dd>
   * 	<dt>year_month</dt><dd>is mapped to <strong>:value.yearMonth</strong></dd>
   * 	<dt>zone_id</dt><dd>is mapped to <strong>:value.zoneId</strong></dd>
   * 	<dt>zone_offset</dt><dd>is mapped to <strong>:value.zoneOffset</strong></dd>
   * 	<dt>zoned_date_time</dt><dd>is mapped to <strong>:value.zonedDateTime</strong></dd>
   * </dl>
   *
   * @param value
   * 	is mapped to parameter <strong>value</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(Bean value) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean (duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("duration", DurationUtils.write(value.duration));
    _contentValues.put("istant", InstantUtils.write(value.istant));
    _contentValues.put("local_date", LocalDateUtils.write(value.localDate));
    _contentValues.put("local_date_time", LocalDateTimeUtils.write(value.localDateTime));
    _contentValues.put("local_time", LocalTimeUtils.write(value.localTime));
    _contentValues.put("month_day", MonthDayUtils.write(value.monthDay));
    _contentValues.put("offset_date_time", OffsetDateTimeUtils.write(value.offsetDateTime));
    _contentValues.put("offset_time", OffsetTimeUtils.write(value.offsetTime));
    _contentValues.put("period", PeriodUtils.write(value.period));
    _contentValues.put("year", YearUtils.write(value.year));
    _contentValues.put("year_month", YearMonthUtils.write(value.yearMonth));
    _contentValues.put("zone_id", ZoneIdUtils.write(value.zoneId));
    _contentValues.put("zone_offset", ZoneOffsetUtils.write(value.zoneOffset));
    _contentValues.put("zoned_date_time", ZonedDateTimeUtils.write(value.zonedDateTime));

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
      Logger.info("INSERT INTO bean (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    value.id=result;

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean SET duration=:duration, istant=:istant, local_date=:localDate, local_date_time=:localDateTime, local_time=:localTime, month_day=:monthDay, offset_date_time=:offsetDateTime, offset_time=:offsetTime, period=:period, year=:year, year_month=:yearMonth, zone_id=:zoneId, zone_offset=:zoneOffset, zoned_date_time=:zonedDateTime WHERE year=:value.year</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>duration</dt><dd>is mapped to <strong>:value.duration</strong></dd>
   * 	<dt>istant</dt><dd>is mapped to <strong>:value.istant</strong></dd>
   * 	<dt>local_date</dt><dd>is mapped to <strong>:value.localDate</strong></dd>
   * 	<dt>local_date_time</dt><dd>is mapped to <strong>:value.localDateTime</strong></dd>
   * 	<dt>local_time</dt><dd>is mapped to <strong>:value.localTime</strong></dd>
   * 	<dt>month_day</dt><dd>is mapped to <strong>:value.monthDay</strong></dd>
   * 	<dt>offset_date_time</dt><dd>is mapped to <strong>:value.offsetDateTime</strong></dd>
   * 	<dt>offset_time</dt><dd>is mapped to <strong>:value.offsetTime</strong></dd>
   * 	<dt>period</dt><dd>is mapped to <strong>:value.period</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:value.year</strong></dd>
   * 	<dt>year_month</dt><dd>is mapped to <strong>:value.yearMonth</strong></dd>
   * 	<dt>zone_id</dt><dd>is mapped to <strong>:value.zoneId</strong></dd>
   * 	<dt>zone_offset</dt><dd>is mapped to <strong>:value.zoneOffset</strong></dd>
   * 	<dt>zoned_date_time</dt><dd>is mapped to <strong>:value.zonedDateTime</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:value.year</dt><dd>is mapped to method's parameter <strong>value.year</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:value</code>
   *
   * @return number of updated records
   */
  @Override
  public int update(Bean value) {
    if (updatePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean SET duration=?, istant=?, local_date=?, local_date_time=?, local_time=?, month_day=?, offset_date_time=?, offset_time=?, period=?, year=?, year_month=?, zone_id=?, zone_offset=?, zoned_date_time=? WHERE year=?";
      updatePreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    _contentValues.put("duration", DurationUtils.write(value.duration));
    _contentValues.put("istant", InstantUtils.write(value.istant));
    _contentValues.put("local_date", LocalDateUtils.write(value.localDate));
    _contentValues.put("local_date_time", LocalDateTimeUtils.write(value.localDateTime));
    _contentValues.put("local_time", LocalTimeUtils.write(value.localTime));
    _contentValues.put("month_day", MonthDayUtils.write(value.monthDay));
    _contentValues.put("offset_date_time", OffsetDateTimeUtils.write(value.offsetDateTime));
    _contentValues.put("offset_time", OffsetTimeUtils.write(value.offsetTime));
    _contentValues.put("period", PeriodUtils.write(value.period));
    _contentValues.put("year", YearUtils.write(value.year));
    _contentValues.put("year_month", YearMonthUtils.write(value.yearMonth));
    _contentValues.put("zone_id", ZoneIdUtils.write(value.zoneId));
    _contentValues.put("zone_offset", ZoneOffsetUtils.write(value.zoneOffset));
    _contentValues.put("zoned_date_time", ZonedDateTimeUtils.write(value.zonedDateTime));

    _contentValues.addWhereArgs((value.year==null?"":YearUtils.write(value.year)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean SET duration=:duration, istant=:istant, local_date=:local_date, local_date_time=:local_date_time, local_time=:local_time, month_day=:month_day, offset_date_time=:offset_date_time, offset_time=:offset_time, period=:period, year=:year, year_month=:year_month, zone_id=:zone_id, zone_offset=:zone_offset, zoned_date_time=:zoned_date_time WHERE year=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updatePreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time FROM bean WHERE year=:value.year</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>duration</dt><dd>is associated to bean's property <strong>duration</strong></dd>
   * 	<dt>istant</dt><dd>is associated to bean's property <strong>istant</strong></dd>
   * 	<dt>local_date</dt><dd>is associated to bean's property <strong>localDate</strong></dd>
   * 	<dt>local_date_time</dt><dd>is associated to bean's property <strong>localDateTime</strong></dd>
   * 	<dt>local_time</dt><dd>is associated to bean's property <strong>localTime</strong></dd>
   * 	<dt>month_day</dt><dd>is associated to bean's property <strong>monthDay</strong></dd>
   * 	<dt>offset_date_time</dt><dd>is associated to bean's property <strong>offsetDateTime</strong></dd>
   * 	<dt>offset_time</dt><dd>is associated to bean's property <strong>offsetTime</strong></dd>
   * 	<dt>period</dt><dd>is associated to bean's property <strong>period</strong></dd>
   * 	<dt>year</dt><dd>is associated to bean's property <strong>year</strong></dd>
   * 	<dt>year_month</dt><dd>is associated to bean's property <strong>yearMonth</strong></dd>
   * 	<dt>zone_id</dt><dd>is associated to bean's property <strong>zoneId</strong></dd>
   * 	<dt>zone_offset</dt><dd>is associated to bean's property <strong>zoneOffset</strong></dd>
   * 	<dt>zoned_date_time</dt><dd>is associated to bean's property <strong>zonedDateTime</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:value.year</dt><dd>is binded to method's parameter <strong>value.year</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:value</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean> select(Bean value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(YearUtils.write(value.year));
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
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean> resultList=new ArrayList<Bean>(_cursor.getCount());
      Bean resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("duration");
        int index2=_cursor.getColumnIndex("istant");
        int index3=_cursor.getColumnIndex("local_date");
        int index4=_cursor.getColumnIndex("local_date_time");
        int index5=_cursor.getColumnIndex("local_time");
        int index6=_cursor.getColumnIndex("month_day");
        int index7=_cursor.getColumnIndex("offset_date_time");
        int index8=_cursor.getColumnIndex("offset_time");
        int index9=_cursor.getColumnIndex("period");
        int index10=_cursor.getColumnIndex("year");
        int index11=_cursor.getColumnIndex("year_month");
        int index12=_cursor.getColumnIndex("zone_id");
        int index13=_cursor.getColumnIndex("zone_offset");
        int index14=_cursor.getColumnIndex("zoned_date_time");

        do
         {
          resultBean=new Bean();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.duration=DurationUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.istant=InstantUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.localDate=LocalDateUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.localDateTime=LocalDateTimeUtils.read(_cursor.getString(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.localTime=LocalTimeUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { resultBean.monthDay=MonthDayUtils.read(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { resultBean.offsetDateTime=OffsetDateTimeUtils.read(_cursor.getString(index7)); }
          if (!_cursor.isNull(index8)) { resultBean.offsetTime=OffsetTimeUtils.read(_cursor.getString(index8)); }
          if (!_cursor.isNull(index9)) { resultBean.period=PeriodUtils.read(_cursor.getString(index9)); }
          if (!_cursor.isNull(index10)) { resultBean.year=YearUtils.read(_cursor.getString(index10)); }
          if (!_cursor.isNull(index11)) { resultBean.yearMonth=YearMonthUtils.read(_cursor.getString(index11)); }
          if (!_cursor.isNull(index12)) { resultBean.zoneId=ZoneIdUtils.read(_cursor.getString(index12)); }
          if (!_cursor.isNull(index13)) { resultBean.zoneOffset=ZoneOffsetUtils.read(_cursor.getString(index13)); }
          if (!_cursor.isNull(index14)) { resultBean.zonedDateTime=ZonedDateTimeUtils.read(_cursor.getString(index14)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM bean WHERE year=:value.year</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:value.year</dt><dd>is mapped to method's parameter <strong>value.year</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:value</code>
   *
   * @return number of deleted records
   */
  @Override
  public int delete(Bean value) {
    if (deletePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM bean WHERE year=?";
      deletePreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement2);
    _contentValues.addWhereArgs((value.year==null?"":YearUtils.write(value.year)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM bean WHERE year=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deletePreparedStatement2, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    try {
      if (insertPreparedStatement0!=null) {
        insertPreparedStatement0.close();
        insertPreparedStatement0=null;
      }
      if (updatePreparedStatement1!=null) {
        updatePreparedStatement1.close();
        updatePreparedStatement1=null;
      }
      if (deletePreparedStatement2!=null) {
        deletePreparedStatement2.close();
        deletePreparedStatement2=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
