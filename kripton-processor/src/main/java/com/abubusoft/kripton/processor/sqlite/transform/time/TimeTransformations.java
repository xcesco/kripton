package com.abubusoft.kripton.processor.sqlite.transform.time;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.google.common.collect.Lists;

import java.time.*;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class TimeTransformations {
  public static final List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations = Lists.newArrayList(
          of(Duration.class, DurationSQLTransform.class),
          of(Instant.class, InstantSQLTransform.class),
          of(LocalDateTime.class, LocalDateTimeSQLTransform.class),
          of(LocalDate.class, LocalDateSQLTransform.class),
          of(LocalTime.class, LocalTimeSQLTransform.class),
          of(MonthDay.class, MonthDaySQLTransform.class),
          of(OffsetDateTime.class, OffsetDateTimeSQLTransform.class),
          of(OffsetTime.class, OffsetTimeSQLTransform.class),
          of(Period.class, PeriodSQLTransform.class),
          of(YearMonth.class, YearMonthBindTransform.class),
          of(Year.class, YearSQLTransform.class),
          of(ZonedDateTime.class, ZonedDateTimeSQLTransform.class),
          of(ZoneId.class, ZoneIdSQLTransform.class),
          of(ZoneOffset.class, ZoneOffsetSQLTransform.class)
  );
}
