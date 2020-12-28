package sqlite.feature.time.case1;

import com.abubusoft.kripton.annotation.BindType;

import java.time.*;

@BindType
public class BeanWithAccessors {
  public long getId() {
    return id;
  }

  public BeanWithAccessors setId(long id) {
    this.id = id;
    return this;
  }

  private long id;
  private Duration duration;
  private Instant istant;
  private LocalDate localDate;
  private LocalDateTime localDateTime;
  private LocalTime localTime;
  private MonthDay monthDay;
  private OffsetDateTime offsetDateTime;
  private OffsetTime offsetTime;
  private Period period;
  private Year year;
  private YearMonth yearMonth;
  private ZonedDateTime zonedDateTime;
  private ZoneId zoneId;
  private ZoneOffset zoneOffset;

  public Duration getDuration() {
    return duration;
  }

  public BeanWithAccessors setDuration(Duration duration) {
    this.duration = duration;
    return this;
  }

  public Instant getIstant() {
    return istant;
  }

  public BeanWithAccessors setIstant(Instant istant) {
    this.istant = istant;
    return this;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public BeanWithAccessors setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
    return this;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public BeanWithAccessors setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
    return this;
  }

  public LocalTime getLocalTime() {
    return localTime;
  }

  public BeanWithAccessors setLocalTime(LocalTime localTime) {
    this.localTime = localTime;
    return this;
  }

  public MonthDay getMonthDay() {
    return monthDay;
  }

  public BeanWithAccessors setMonthDay(MonthDay monthDay) {
    this.monthDay = monthDay;
    return this;
  }

  public OffsetDateTime getOffsetDateTime() {
    return offsetDateTime;
  }

  public BeanWithAccessors setOffsetDateTime(OffsetDateTime offsetDateTime) {
    this.offsetDateTime = offsetDateTime;
    return this;
  }

  public OffsetTime getOffsetTime() {
    return offsetTime;
  }

  public BeanWithAccessors setOffsetTime(OffsetTime offsetTime) {
    this.offsetTime = offsetTime;
    return this;
  }

  public Period getPeriod() {
    return period;
  }

  public BeanWithAccessors setPeriod(Period period) {
    this.period = period;
    return this;
  }

  public Year getYear() {
    return year;
  }

  public BeanWithAccessors setYear(Year year) {
    this.year = year;
    return this;
  }

  public YearMonth getYearMonth() {
    return yearMonth;
  }

  public BeanWithAccessors setYearMonth(YearMonth yearMonth) {
    this.yearMonth = yearMonth;
    return this;
  }

  public ZonedDateTime getZonedDateTime() {
    return zonedDateTime;
  }

  public BeanWithAccessors setZonedDateTime(ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
    return this;
  }

  public ZoneId getZoneId() {
    return zoneId;
  }

  public BeanWithAccessors setZoneId(ZoneId zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  public ZoneOffset getZoneOffset() {
    return zoneOffset;
  }

  public BeanWithAccessors setZoneOffset(ZoneOffset zoneOffset) {
    this.zoneOffset = zoneOffset;
    return this;
  }
}
