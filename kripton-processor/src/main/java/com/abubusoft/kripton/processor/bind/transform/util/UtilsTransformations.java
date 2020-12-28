package com.abubusoft.kripton.processor.bind.transform.util;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.google.common.collect.Lists;

import java.util.*;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class UtilsTransformations {
  public static final List<Pair<Class<?>, Class<? extends BindTransform>>> transformations = Lists.newArrayList(
          of(Date.class, DateBindTransform.class),
          of(Locale.class, LocaleBindTransform.class),
          of(Currency.class, CurrencyBindTransform.class),
          of(Calendar.class, CalendarBindTransform.class),
          of(TimeZone.class, TimeZoneBindTransform.class)
  );
}
