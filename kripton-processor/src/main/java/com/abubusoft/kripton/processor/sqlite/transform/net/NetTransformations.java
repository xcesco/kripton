package com.abubusoft.kripton.processor.sqlite.transform.net;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransform;
import com.google.common.collect.Lists;

import java.net.URL;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class NetTransformations {
  public static final List<Pair<Class<?>, Class<? extends SQLTransform>>> transformations = Lists.newArrayList(
          of(URL.class, UrlSQLTransform.class)
  );
}
