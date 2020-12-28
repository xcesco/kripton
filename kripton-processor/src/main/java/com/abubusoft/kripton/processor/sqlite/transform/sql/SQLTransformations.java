package com.abubusoft.kripton.processor.sqlite.transform.sql;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.abubusoft.kripton.processor.bind.transform.sql.SQLDateBindTransform;
import com.abubusoft.kripton.processor.bind.transform.sql.SQLTimeBindTransform;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.google.common.collect.Lists;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class SQLTransformations {
  public static final List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations = Lists.newArrayList(
          of(Date.class, SQLDateSQLTransform.class),
          of(Time.class, SQLTimeSQLTransform.class)
  );
}
