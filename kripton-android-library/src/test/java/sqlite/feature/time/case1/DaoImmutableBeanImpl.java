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
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanImmutable</code>, based on interface <code>DaoImmutableBean</code>
 * </p>
 *
 *  @see BeanImmutable
 *  @see DaoImmutableBean
 *  @see BeanImmutableTable
 */
public class DaoImmutableBeanImpl extends Dao implements DaoImmutableBean {
  private static SupportSQLiteStatement insertPreparedStatement0;

  private static SupportSQLiteStatement updatePreparedStatement1;

  /**
   * SQL definition for method select
   */
  private static final String SELECT_SQL2 = "SELECT id, duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time FROM bean_immutable WHERE year=?";

  private static SupportSQLiteStatement deletePreparedStatement2;

  public DaoImmutableBeanImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean_immutable (duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time) VALUES (:duration, :istant, :localDate, :localDateTime, :localTime, :monthDay, :offsetDateTime, :offsetTime, :period, :year, :yearMonth, :zoneId, :zoneOffset, :zonedDateTime)</pre>
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
  public int insert(BeanImmutable value) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean_immutable (duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("duration", DurationUtils.write(value.getDuration()));
    _contentValues.put("istant", InstantUtils.write(value.getIstant()));
    _contentValues.put("local_date", LocalDateUtils.write(value.getLocalDate()));
    _contentValues.put("local_date_time", LocalDateTimeUtils.write(value.getLocalDateTime()));
    _contentValues.put("local_time", LocalTimeUtils.write(value.getLocalTime()));
    _contentValues.put("month_day", MonthDayUtils.write(value.getMonthDay()));
    _contentValues.put("offset_date_time", OffsetDateTimeUtils.write(value.getOffsetDateTime()));
    _contentValues.put("offset_time", OffsetTimeUtils.write(value.getOffsetTime()));
    _contentValues.put("period", PeriodUtils.write(value.getPeriod()));
    _contentValues.put("year", YearUtils.write(value.getYear()));
    _contentValues.put("year_month", YearMonthUtils.write(value.getYearMonth()));
    _contentValues.put("zone_id", ZoneIdUtils.write(value.getZoneId()));
    _contentValues.put("zone_offset", ZoneOffsetUtils.write(value.getZoneOffset()));
    _contentValues.put("zoned_date_time", ZonedDateTimeUtils.write(value.getZonedDateTime()));

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
      Logger.info("INSERT INTO bean_immutable (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean_immutable SET duration=:duration, istant=:istant, local_date=:localDate, local_date_time=:localDateTime, local_time=:localTime, month_day=:monthDay, offset_date_time=:offsetDateTime, offset_time=:offsetTime, period=:period, year=:year, year_month=:yearMonth, zone_id=:zoneId, zone_offset=:zoneOffset, zoned_date_time=:zonedDateTime WHERE year=:value.year</pre>
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
  public int update(BeanImmutable value) {
    if (updatePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean_immutable SET duration=?, istant=?, local_date=?, local_date_time=?, local_time=?, month_day=?, offset_date_time=?, offset_time=?, period=?, year=?, year_month=?, zone_id=?, zone_offset=?, zoned_date_time=? WHERE year=?";
      updatePreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    _contentValues.put("duration", DurationUtils.write(value.getDuration()));
    _contentValues.put("istant", InstantUtils.write(value.getIstant()));
    _contentValues.put("local_date", LocalDateUtils.write(value.getLocalDate()));
    _contentValues.put("local_date_time", LocalDateTimeUtils.write(value.getLocalDateTime()));
    _contentValues.put("local_time", LocalTimeUtils.write(value.getLocalTime()));
    _contentValues.put("month_day", MonthDayUtils.write(value.getMonthDay()));
    _contentValues.put("offset_date_time", OffsetDateTimeUtils.write(value.getOffsetDateTime()));
    _contentValues.put("offset_time", OffsetTimeUtils.write(value.getOffsetTime()));
    _contentValues.put("period", PeriodUtils.write(value.getPeriod()));
    _contentValues.put("year", YearUtils.write(value.getYear()));
    _contentValues.put("year_month", YearMonthUtils.write(value.getYearMonth()));
    _contentValues.put("zone_id", ZoneIdUtils.write(value.getZoneId()));
    _contentValues.put("zone_offset", ZoneOffsetUtils.write(value.getZoneOffset()));
    _contentValues.put("zoned_date_time", ZonedDateTimeUtils.write(value.getZonedDateTime()));

    _contentValues.addWhereArgs((value.getYear()==null?"":YearUtils.write(value.getYear())));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean_immutable SET duration=:duration, istant=:istant, local_date=:local_date, local_date_time=:local_date_time, local_time=:local_time, month_day=:month_day, offset_date_time=:offset_date_time, offset_time=:offset_time, period=:period, year=:year, year_month=:year_month, zone_id=:zone_id, zone_offset=:zone_offset, zoned_date_time=:zoned_date_time WHERE year=?");

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
   * <pre>SELECT id, duration, istant, local_date, local_date_time, local_time, month_day, offset_date_time, offset_time, period, year, year_month, zone_id, zone_offset, zoned_date_time FROM bean_immutable WHERE year=:value.year</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link BeanImmutable}
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
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<BeanImmutable> select(BeanImmutable value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(YearUtils.write(value.getYear()));
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

      ArrayList<BeanImmutable> resultList=new ArrayList<BeanImmutable>(_cursor.getCount());
      BeanImmutable resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      long __id=0;
      Duration __duration=null;
      Instant __istant=null;
      LocalDate __localDate=null;
      LocalDateTime __localDateTime=null;
      LocalTime __localTime=null;
      MonthDay __monthDay=null;
      OffsetDateTime __offsetDateTime=null;
      OffsetTime __offsetTime=null;
      Period __period=null;
      Year __year=null;
      YearMonth __yearMonth=null;
      ZonedDateTime __zonedDateTime=null;
      ZoneId __zoneId=null;
      ZoneOffset __zoneOffset=null;

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
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __id=0;
          __duration=null;
          __istant=null;
          __localDate=null;
          __localDateTime=null;
          __localTime=null;
          __monthDay=null;
          __offsetDateTime=null;
          __offsetTime=null;
          __period=null;
          __year=null;
          __yearMonth=null;
          __zonedDateTime=null;
          __zoneId=null;
          __zoneOffset=null;
          __id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { __duration=DurationUtils.read(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { __istant=InstantUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { __localDate=LocalDateUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { __localDateTime=LocalDateTimeUtils.read(_cursor.getString(index4)); }
          if (!_cursor.isNull(index5)) { __localTime=LocalTimeUtils.read(_cursor.getString(index5)); }
          if (!_cursor.isNull(index6)) { __monthDay=MonthDayUtils.read(_cursor.getString(index6)); }
          if (!_cursor.isNull(index7)) { __offsetDateTime=OffsetDateTimeUtils.read(_cursor.getString(index7)); }
          if (!_cursor.isNull(index8)) { __offsetTime=OffsetTimeUtils.read(_cursor.getString(index8)); }
          if (!_cursor.isNull(index9)) { __period=PeriodUtils.read(_cursor.getString(index9)); }
          if (!_cursor.isNull(index10)) { __year=YearUtils.read(_cursor.getString(index10)); }
          if (!_cursor.isNull(index11)) { __yearMonth=YearMonthUtils.read(_cursor.getString(index11)); }
          if (!_cursor.isNull(index12)) { __zoneId=ZoneIdUtils.read(_cursor.getString(index12)); }
          if (!_cursor.isNull(index13)) { __zoneOffset=ZoneOffsetUtils.read(_cursor.getString(index13)); }
          if (!_cursor.isNull(index14)) { __zonedDateTime=ZonedDateTimeUtils.read(_cursor.getString(index14)); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new BeanImmutable(__id,__duration,__istant,__localDate,__localDateTime,__localTime,__monthDay,__offsetDateTime,__offsetTime,__period,__year,__yearMonth,__zonedDateTime,__zoneId,__zoneOffset);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM bean_immutable WHERE year=:value.year</pre>
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
  public int delete(BeanImmutable value) {
    if (deletePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM bean_immutable WHERE year=?";
      deletePreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement2);
    _contentValues.addWhereArgs((value.getYear()==null?"":YearUtils.write(value.getYear())));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM bean_immutable WHERE year=?");

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
