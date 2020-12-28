package shared.feature.time.case1;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

import java.time.*;

@BindSharedPreferences
public class Bean {
  public Duration duration;
  public Instant istant;
  public LocalDate localDate;
  public LocalDateTime localDateTime;
  public LocalTime localTime;
  public MonthDay monthDay;
  public OffsetDateTime offsetDateTime;
  public OffsetTime offsetTime;
  public Period period;
  public Year year;
  public YearMonth yearMonth;
  public ZonedDateTime zonedDateTime;
  public ZoneId zoneId;
  public ZoneOffset zoneOffset;
}
