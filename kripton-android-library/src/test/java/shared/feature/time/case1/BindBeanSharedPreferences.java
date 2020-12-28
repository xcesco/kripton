package shared.feature.time.case1;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.StringUtils;
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

/**
 * This class is the shared preference binder defined for Bean
 *
 * @see Bean
 */
public class BindBeanSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindBeanSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Bean defaultBean;

  /**
   * constructor
   */
  private BindBeanSharedPreferences() {
    createPrefs();
    defaultBean=new Bean();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * create prefs
   */
  private void createPrefs() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
  }

  /**
   * force to refresh values
   */
  public BindBeanSharedPreferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    Bean bean=new Bean();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Bean read() {
    Bean bean=new Bean();
     {
      String temp=prefs.getString("local_date_time", null);
      bean.localDateTime=(StringUtils.hasText(temp)) ? LocalDateTimeUtils.read(temp): defaultBean.localDateTime;}

     {
      String temp=prefs.getString("zone_offset", null);
      bean.zoneOffset=(StringUtils.hasText(temp)) ? ZoneOffsetUtils.read(temp): defaultBean.zoneOffset;}

     {
      String temp=prefs.getString("period", null);
      bean.period=(StringUtils.hasText(temp)) ? PeriodUtils.read(temp): defaultBean.period;}

     {
      String temp=prefs.getString("offset_time", null);
      bean.offsetTime=(StringUtils.hasText(temp)) ? OffsetTimeUtils.read(temp): defaultBean.offsetTime;}

     {
      String temp=prefs.getString("year", null);
      bean.year=(StringUtils.hasText(temp)) ? YearUtils.read(temp): defaultBean.year;}

     {
      String temp=prefs.getString("year_month", null);
      bean.yearMonth=(StringUtils.hasText(temp)) ? YearMonthUtils.read(temp): defaultBean.yearMonth;}

     {
      String temp=prefs.getString("month_day", null);
      bean.monthDay=(StringUtils.hasText(temp)) ? MonthDayUtils.read(temp): defaultBean.monthDay;}

     {
      String temp=prefs.getString("istant", null);
      bean.istant=(StringUtils.hasText(temp)) ? InstantUtils.read(temp): defaultBean.istant;}

     {
      String temp=prefs.getString("duration", null);
      bean.duration=(StringUtils.hasText(temp)) ? DurationUtils.read(temp): defaultBean.duration;}

     {
      String temp=prefs.getString("local_time", null);
      bean.localTime=(StringUtils.hasText(temp)) ? LocalTimeUtils.read(temp): defaultBean.localTime;}

     {
      String temp=prefs.getString("zoned_date_time", null);
      bean.zonedDateTime=(StringUtils.hasText(temp)) ? ZonedDateTimeUtils.read(temp): defaultBean.zonedDateTime;}

     {
      String temp=prefs.getString("zone_id", null);
      bean.zoneId=(StringUtils.hasText(temp)) ? ZoneIdUtils.read(temp): defaultBean.zoneId;}

     {
      String temp=prefs.getString("offset_date_time", null);
      bean.offsetDateTime=(StringUtils.hasText(temp)) ? OffsetDateTimeUtils.read(temp): defaultBean.offsetDateTime;}

     {
      String temp=prefs.getString("local_date", null);
      bean.localDate=(StringUtils.hasText(temp)) ? LocalDateUtils.read(temp): defaultBean.localDate;}


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    if (bean.localDateTime!=null)  {
      editor.putString("local_date_time",LocalDateTimeUtils.write(bean.localDateTime));
    } else {
      editor.remove("localDateTime");
    }

    if (bean.zoneOffset!=null)  {
      editor.putString("zone_offset",ZoneOffsetUtils.write(bean.zoneOffset));
    } else {
      editor.remove("zoneOffset");
    }

    if (bean.period!=null)  {
      editor.putString("period",PeriodUtils.write(bean.period));
    } else {
      editor.remove("period");
    }

    if (bean.offsetTime!=null)  {
      editor.putString("offset_time",OffsetTimeUtils.write(bean.offsetTime));
    } else {
      editor.remove("offsetTime");
    }

    if (bean.year!=null)  {
      editor.putString("year",YearUtils.write(bean.year));
    } else {
      editor.remove("year");
    }

    if (bean.yearMonth!=null)  {
      editor.putString("year_month",YearMonthUtils.write(bean.yearMonth));
    } else {
      editor.remove("yearMonth");
    }

    if (bean.monthDay!=null)  {
      editor.putString("month_day",MonthDayUtils.write(bean.monthDay));
    } else {
      editor.remove("monthDay");
    }

    if (bean.istant!=null)  {
      editor.putString("istant",InstantUtils.write(bean.istant));
    } else {
      editor.remove("istant");
    }

    if (bean.duration!=null)  {
      editor.putString("duration",DurationUtils.write(bean.duration));
    } else {
      editor.remove("duration");
    }

    if (bean.localTime!=null)  {
      editor.putString("local_time",LocalTimeUtils.write(bean.localTime));
    } else {
      editor.remove("localTime");
    }

    if (bean.zonedDateTime!=null)  {
      editor.putString("zoned_date_time",ZonedDateTimeUtils.write(bean.zonedDateTime));
    } else {
      editor.remove("zonedDateTime");
    }

    if (bean.zoneId!=null)  {
      editor.putString("zone_id",ZoneIdUtils.write(bean.zoneId));
    } else {
      editor.remove("zoneId");
    }

    if (bean.offsetDateTime!=null)  {
      editor.putString("offset_date_time",OffsetDateTimeUtils.write(bean.offsetDateTime));
    } else {
      editor.remove("offsetDateTime");
    }

    if (bean.localDate!=null)  {
      editor.putString("local_date",LocalDateUtils.write(bean.localDate));
    } else {
      editor.remove("localDate");
    }


    editor.commit();
  }

  /**
   * reads property <code>localDateTime</code> from shared pref with key <code>local_date_time</code>
   *
   * @return property localDateTime value
   */
  public LocalDateTime getLocalDateTime() {
    String temp=prefs.getString("local_date_time", null);
    return (StringUtils.hasText(temp)) ? LocalDateTimeUtils.read(temp): defaultBean.localDateTime;}

  /**
   * reads property <code>zoneOffset</code> from shared pref with key <code>zone_offset</code>
   *
   * @return property zoneOffset value
   */
  public ZoneOffset getZoneOffset() {
    String temp=prefs.getString("zone_offset", null);
    return (StringUtils.hasText(temp)) ? ZoneOffsetUtils.read(temp): defaultBean.zoneOffset;}

  /**
   * reads property <code>period</code> from shared pref with key <code>period</code>
   *
   * @return property period value
   */
  public Period getPeriod() {
    String temp=prefs.getString("period", null);
    return (StringUtils.hasText(temp)) ? PeriodUtils.read(temp): defaultBean.period;}

  /**
   * reads property <code>offsetTime</code> from shared pref with key <code>offset_time</code>
   *
   * @return property offsetTime value
   */
  public OffsetTime getOffsetTime() {
    String temp=prefs.getString("offset_time", null);
    return (StringUtils.hasText(temp)) ? OffsetTimeUtils.read(temp): defaultBean.offsetTime;}

  /**
   * reads property <code>year</code> from shared pref with key <code>year</code>
   *
   * @return property year value
   */
  public Year getYear() {
    String temp=prefs.getString("year", null);
    return (StringUtils.hasText(temp)) ? YearUtils.read(temp): defaultBean.year;}

  /**
   * reads property <code>yearMonth</code> from shared pref with key <code>year_month</code>
   *
   * @return property yearMonth value
   */
  public YearMonth getYearMonth() {
    String temp=prefs.getString("year_month", null);
    return (StringUtils.hasText(temp)) ? YearMonthUtils.read(temp): defaultBean.yearMonth;}

  /**
   * reads property <code>monthDay</code> from shared pref with key <code>month_day</code>
   *
   * @return property monthDay value
   */
  public MonthDay getMonthDay() {
    String temp=prefs.getString("month_day", null);
    return (StringUtils.hasText(temp)) ? MonthDayUtils.read(temp): defaultBean.monthDay;}

  /**
   * reads property <code>istant</code> from shared pref with key <code>istant</code>
   *
   * @return property istant value
   */
  public Instant getIstant() {
    String temp=prefs.getString("istant", null);
    return (StringUtils.hasText(temp)) ? InstantUtils.read(temp): defaultBean.istant;}

  /**
   * reads property <code>duration</code> from shared pref with key <code>duration</code>
   *
   * @return property duration value
   */
  public Duration getDuration() {
    String temp=prefs.getString("duration", null);
    return (StringUtils.hasText(temp)) ? DurationUtils.read(temp): defaultBean.duration;}

  /**
   * reads property <code>localTime</code> from shared pref with key <code>local_time</code>
   *
   * @return property localTime value
   */
  public LocalTime getLocalTime() {
    String temp=prefs.getString("local_time", null);
    return (StringUtils.hasText(temp)) ? LocalTimeUtils.read(temp): defaultBean.localTime;}

  /**
   * reads property <code>zonedDateTime</code> from shared pref with key <code>zoned_date_time</code>
   *
   * @return property zonedDateTime value
   */
  public ZonedDateTime getZonedDateTime() {
    String temp=prefs.getString("zoned_date_time", null);
    return (StringUtils.hasText(temp)) ? ZonedDateTimeUtils.read(temp): defaultBean.zonedDateTime;}

  /**
   * reads property <code>zoneId</code> from shared pref with key <code>zone_id</code>
   *
   * @return property zoneId value
   */
  public ZoneId getZoneId() {
    String temp=prefs.getString("zone_id", null);
    return (StringUtils.hasText(temp)) ? ZoneIdUtils.read(temp): defaultBean.zoneId;}

  /**
   * reads property <code>offsetDateTime</code> from shared pref with key <code>offset_date_time</code>
   *
   * @return property offsetDateTime value
   */
  public OffsetDateTime getOffsetDateTime() {
    String temp=prefs.getString("offset_date_time", null);
    return (StringUtils.hasText(temp)) ? OffsetDateTimeUtils.read(temp): defaultBean.offsetDateTime;}

  /**
   * reads property <code>localDate</code> from shared pref with key <code>local_date</code>
   *
   * @return property localDate value
   */
  public LocalDate getLocalDate() {
    String temp=prefs.getString("local_date", null);
    return (StringUtils.hasText(temp)) ? LocalDateUtils.read(temp): defaultBean.localDate;}

  /**
   * get instance of shared preferences
   */
  public static synchronized BindBeanSharedPreferences getInstance() {
    if (instance==null) {
      instance=new BindBeanSharedPreferences();
      // read and write instance to sync with default values
      instance.write(instance.read());
    }
    return instance;
  }

  /**
   * editor class for shared preferences
   */
  public class BindEditor extends AbstractEditor {
    private BindEditor() {
    }

    /**
     * modifier for property localDateTime
     */
    public BindEditor putLocalDateTime(LocalDateTime value) {
      if (value!=null)  {
        editor.putString("local_date_time",LocalDateTimeUtils.write(value));
      } else {
        editor.remove("localDateTime");
      }

      return this;
    }

    /**
     * remove property localDateTime
     */
    public BindEditor removeLocalDateTime() {
      editor.remove("local_date_time");
      return this;
    }

    /**
     * modifier for property zoneOffset
     */
    public BindEditor putZoneOffset(ZoneOffset value) {
      if (value!=null)  {
        editor.putString("zone_offset",ZoneOffsetUtils.write(value));
      } else {
        editor.remove("zoneOffset");
      }

      return this;
    }

    /**
     * remove property zoneOffset
     */
    public BindEditor removeZoneOffset() {
      editor.remove("zone_offset");
      return this;
    }

    /**
     * modifier for property period
     */
    public BindEditor putPeriod(Period value) {
      if (value!=null)  {
        editor.putString("period",PeriodUtils.write(value));
      } else {
        editor.remove("period");
      }

      return this;
    }

    /**
     * remove property period
     */
    public BindEditor removePeriod() {
      editor.remove("period");
      return this;
    }

    /**
     * modifier for property offsetTime
     */
    public BindEditor putOffsetTime(OffsetTime value) {
      if (value!=null)  {
        editor.putString("offset_time",OffsetTimeUtils.write(value));
      } else {
        editor.remove("offsetTime");
      }

      return this;
    }

    /**
     * remove property offsetTime
     */
    public BindEditor removeOffsetTime() {
      editor.remove("offset_time");
      return this;
    }

    /**
     * modifier for property year
     */
    public BindEditor putYear(Year value) {
      if (value!=null)  {
        editor.putString("year",YearUtils.write(value));
      } else {
        editor.remove("year");
      }

      return this;
    }

    /**
     * remove property year
     */
    public BindEditor removeYear() {
      editor.remove("year");
      return this;
    }

    /**
     * modifier for property yearMonth
     */
    public BindEditor putYearMonth(YearMonth value) {
      if (value!=null)  {
        editor.putString("year_month",YearMonthUtils.write(value));
      } else {
        editor.remove("yearMonth");
      }

      return this;
    }

    /**
     * remove property yearMonth
     */
    public BindEditor removeYearMonth() {
      editor.remove("year_month");
      return this;
    }

    /**
     * modifier for property monthDay
     */
    public BindEditor putMonthDay(MonthDay value) {
      if (value!=null)  {
        editor.putString("month_day",MonthDayUtils.write(value));
      } else {
        editor.remove("monthDay");
      }

      return this;
    }

    /**
     * remove property monthDay
     */
    public BindEditor removeMonthDay() {
      editor.remove("month_day");
      return this;
    }

    /**
     * modifier for property istant
     */
    public BindEditor putIstant(Instant value) {
      if (value!=null)  {
        editor.putString("istant",InstantUtils.write(value));
      } else {
        editor.remove("istant");
      }

      return this;
    }

    /**
     * remove property istant
     */
    public BindEditor removeIstant() {
      editor.remove("istant");
      return this;
    }

    /**
     * modifier for property duration
     */
    public BindEditor putDuration(Duration value) {
      if (value!=null)  {
        editor.putString("duration",DurationUtils.write(value));
      } else {
        editor.remove("duration");
      }

      return this;
    }

    /**
     * remove property duration
     */
    public BindEditor removeDuration() {
      editor.remove("duration");
      return this;
    }

    /**
     * modifier for property localTime
     */
    public BindEditor putLocalTime(LocalTime value) {
      if (value!=null)  {
        editor.putString("local_time",LocalTimeUtils.write(value));
      } else {
        editor.remove("localTime");
      }

      return this;
    }

    /**
     * remove property localTime
     */
    public BindEditor removeLocalTime() {
      editor.remove("local_time");
      return this;
    }

    /**
     * modifier for property zonedDateTime
     */
    public BindEditor putZonedDateTime(ZonedDateTime value) {
      if (value!=null)  {
        editor.putString("zoned_date_time",ZonedDateTimeUtils.write(value));
      } else {
        editor.remove("zonedDateTime");
      }

      return this;
    }

    /**
     * remove property zonedDateTime
     */
    public BindEditor removeZonedDateTime() {
      editor.remove("zoned_date_time");
      return this;
    }

    /**
     * modifier for property zoneId
     */
    public BindEditor putZoneId(ZoneId value) {
      if (value!=null)  {
        editor.putString("zone_id",ZoneIdUtils.write(value));
      } else {
        editor.remove("zoneId");
      }

      return this;
    }

    /**
     * remove property zoneId
     */
    public BindEditor removeZoneId() {
      editor.remove("zone_id");
      return this;
    }

    /**
     * modifier for property offsetDateTime
     */
    public BindEditor putOffsetDateTime(OffsetDateTime value) {
      if (value!=null)  {
        editor.putString("offset_date_time",OffsetDateTimeUtils.write(value));
      } else {
        editor.remove("offsetDateTime");
      }

      return this;
    }

    /**
     * remove property offsetDateTime
     */
    public BindEditor removeOffsetDateTime() {
      editor.remove("offset_date_time");
      return this;
    }

    /**
     * modifier for property localDate
     */
    public BindEditor putLocalDate(LocalDate value) {
      if (value!=null)  {
        editor.putString("local_date",LocalDateUtils.write(value));
      } else {
        editor.remove("localDate");
      }

      return this;
    }

    /**
     * remove property localDate
     */
    public BindEditor removeLocalDate() {
      editor.remove("local_date");
      return this;
    }

    /**
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}
