package com.abubusoft.kripton.processor.sqlite.transform.util;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.google.common.collect.Lists;

import java.util.*;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class UtilsTransformations {
  public static final List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations = Lists.newArrayList(
          of(Date.class, DateSQLTransform.class),
          of(Locale.class, LocaleSQLTransform.class),
          of(Currency.class, CurrencySQLTransform.class),
          of(Calendar.class, CalendarSQLTransform.class),
          of(TimeZone.class, TimeZoneSQLTransform.class)
  );
}
