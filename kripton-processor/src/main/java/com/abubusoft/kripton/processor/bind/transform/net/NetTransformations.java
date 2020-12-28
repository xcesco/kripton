package com.abubusoft.kripton.processor.bind.transform.net;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.transform.BindTransform;
import com.google.common.collect.Lists;

import java.net.URL;
import java.util.List;

import static com.abubusoft.kripton.common.Pair.of;

public abstract class NetTransformations {
  public static final List<Pair<Class<?>, Class<? extends BindTransform>>> transformations = Lists.newArrayList(
          of(URL.class, UrlBindTransform.class)
  );
}
