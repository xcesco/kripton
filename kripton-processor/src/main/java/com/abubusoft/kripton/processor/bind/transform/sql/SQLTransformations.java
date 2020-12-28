package com.abubusoft.kripton.processor.bind.transform.sql;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.google.common.collect.Lists;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class SQLTransformations {
  public static final List<Pair<Class<?>, Class<? extends BindTransform>>> transformations = Lists.newArrayList(
          of(Date.class, SQLDateBindTransform.class),
          of(Time.class, SQLTimeBindTransform.class)
  );
}
