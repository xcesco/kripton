package com.abubusoft.kripton.processor.sqlite.transform.math;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class MathTransformations {
  public static final List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations = Lists.newArrayList(
          of(BigDecimal.class, BigDecimalSQLTransform.class),
          of(BigInteger.class, BigIntegerSQLTransform.class)
  );
}
