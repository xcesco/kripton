package com.abubusoft.kripton.processor.sharedprefs.transform.sql;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sharedprefs.transform.PrefsTransform;
import com.google.common.collect.Lists;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class SQLTransformations {
  public static final List<Pair<Class<?>, Class<? extends PrefsTransform>>> transformations = Lists.newArrayList(
          of(Date.class, SQLDatePrefsTransform.class),
          of(Time.class, SQLTimePrefsTransform.class)
  );
}
