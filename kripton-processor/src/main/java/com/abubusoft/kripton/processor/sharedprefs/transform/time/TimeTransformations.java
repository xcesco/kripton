package com.abubusoft.kripton.processor.sharedprefs.transform.time;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.google.common.collect.Lists;

import java.time.*;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class TimeTransformations {
  public static final List<Pair<Class<?>, Class<? extends PrefsTransform>>> transformations = Lists.newArrayList(
          of(Duration.class, DurationBindTransform.class),
          of(Instant.class, InstantBindTransform.class),
          of(LocalDateTime.class, LocalDateTimeBindTransform.class),
          of(LocalDate.class, LocalDateBindTransform.class),
          of(LocalTime.class, LocalTimeBindTransform.class),
          of(MonthDay.class, MonthDayBindTransform.class),
          of(OffsetDateTime.class, OffsetDateTimeBindTransform.class),
          of(OffsetTime.class, OffsetTimeBindTransform.class),
          of(Period.class, PeriodBindTransform.class),
          of(YearMonth.class, YearMonthBindTransform.class),
          of(Year.class, YearBindTransform.class),
          of(ZonedDateTime.class, ZonedDateTimeBindTransform.class),
          of(ZoneId.class, ZoneIdBindTransform.class),
          of(ZoneOffset.class, ZoneOffsetBindTransform.class)
  );
}
