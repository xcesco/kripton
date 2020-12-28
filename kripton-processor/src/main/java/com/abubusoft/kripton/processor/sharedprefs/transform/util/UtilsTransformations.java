package com.abubusoft.kripton.processor.sharedprefs.transform.util;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.google.common.collect.Lists;

import java.util.*;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class UtilsTransformations {
  public static final List<Pair<Class<?>, Class<? extends PrefsTransform>>> transformations = Lists.newArrayList(
          of(Date.class, DatePrefsTransform.class),
          of(Locale.class, LocalePrefsTransform.class),
          of(Currency.class, CurrencyPrefsTransform.class),
          of(Calendar.class, CalendarPrefsTransform.class),
          of(TimeZone.class, TimeZonePrefsTransform.class)
  );
}
