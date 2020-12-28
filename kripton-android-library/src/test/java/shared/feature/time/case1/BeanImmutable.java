package shared.feature.time.case1;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

import java.time.*;

@BindSharedPreferences
public class BeanImmutable {
  public BeanImmutable(Duration duration, Instant istant, LocalDate localDate, LocalDateTime localDateTime, LocalTime localTime, MonthDay monthDay, OffsetDateTime offsetDateTime, OffsetTime offsetTime, Period period, Year year, YearMonth yearMonth, ZonedDateTime zonedDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
    this.duration = duration;
    this.istant = istant;
    this.localDate = localDate;
    this.localDateTime = localDateTime;
    this.localTime = localTime;
    this.monthDay = monthDay;
    this.offsetDateTime = offsetDateTime;
    this.offsetTime = offsetTime;
    this.period = period;
    this.year = year;
    this.yearMonth = yearMonth;
    this.zonedDateTime = zonedDateTime;
    this.zoneId = zoneId;
    this.zoneOffset = zoneOffset;
  }

  public Duration getDuration() {
    return duration;
  }

  public Instant getIstant() {
    return istant;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public LocalTime getLocalTime() {
    return localTime;
  }

  public MonthDay getMonthDay() {
    return monthDay;
  }

  public OffsetDateTime getOffsetDateTime() {
    return offsetDateTime;
  }

  public OffsetTime getOffsetTime() {
    return offsetTime;
  }

  public Period getPeriod() {
    return period;
  }

  public Year getYear() {
    return year;
  }

  public YearMonth getYearMonth() {
    return yearMonth;
  }

  public ZonedDateTime getZonedDateTime() {
    return zonedDateTime;
  }

  public ZoneId getZoneId() {
    return zoneId;
  }

  public ZoneOffset getZoneOffset() {
    return zoneOffset;
  }

  private final Duration duration;
  private final Instant istant;
  private final LocalDate localDate;
  private final LocalDateTime localDateTime;
  private final LocalTime localTime;
  private final MonthDay monthDay;
  private final OffsetDateTime offsetDateTime;
  private final OffsetTime offsetTime;
  private final Period period;
  private final Year year;
  private final YearMonth yearMonth;
  private final ZonedDateTime zonedDateTime;
  private final ZoneId zoneId;
  private final ZoneOffset zoneOffset;

}
