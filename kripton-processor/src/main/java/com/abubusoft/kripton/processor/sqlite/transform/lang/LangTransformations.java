package com.abubusoft.kripton.processor.sqlite.transform.lang;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.google.common.collect.Lists;

import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class LangTransformations {
  public static final List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations = Lists.newArrayList(
          of(Integer.class, IntegerSQLTransform.class),
          of(Boolean.class, BooleanSQLTransform.class),
          of(Long.class, LongSQLTransform.class),
          of(Double.class, DoubleSQLTransform.class),
          of(Float.class, FloatSQLTransform.class),
          of(Short.class, ShortSQLTransform.class),
          of(Byte.class, ByteSQLTransform.class),
          of(Character.class, CharacterSQLTransform.class),
          of(String.class, StringSQLTransform.class)
  );
}
