/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sharedprefs.transform;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;
import com.abubusoft.kripton.processor.sharedprefs.model.PrefsProperty;
import com.abubusoft.kripton.processor.sharedprefs.transform.lang.*;
import com.abubusoft.kripton.processor.sharedprefs.transform.math.MathTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.net.NetTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.sql.SQLTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.time.TimeTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.util.UtilsTransformations;
import com.google.common.collect.Lists;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.abubusoft.kripton.common.Pair.of;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

/**
 * Transformer for java primitive types and frequently used java types.
 *
 * @author xcesco
 */
public abstract class PrefsTransformer {

  private static final List<Pair<String, List<Pair<Class<?>, Class<? extends PrefsTransform>>>>> transformations = Lists.newArrayList(
          of("java.lang", LangTransformations.transformations),
          of("java.util", UtilsTransformations.transformations),
          of("java.math", MathTransformations.transformations),
          of("java.net", NetTransformations.transformations),
          of("java.sql", SQLTransformations.transformations),
          of("java.time", TimeTransformations.transformations)
  );

  /**
   * cache for transform.
   */
  private static final Map<TypeName, PrefsTransform> cache = new ConcurrentHashMap<TypeName, PrefsTransform>();

  /**
   * Register custom transformable for a Java primitive type or a frequently
   * used Java type.
   *
   * @param type      a Java primitive type or a frequently used Java type.
   * @param transform a class implementing @see
   *                  org.abubu.elio.binder.transform.Transformable interface.
   */
  public static void register(TypeName type, PrefsTransform transform) {
    cache.put(type, transform);
  }

  /**
   * Get transformer for type.
   *
   * @param property the property
   * @return transform
   */
  public static PrefsTransform lookup(PrefsProperty property) {
    TypeMirror typeMirror = property.getElement().asType();

    TypeName typeName = typeName(typeMirror);
    return lookup(typeName);
  }

  static PrefsTransform getSupportedTransformations(TypeName typeName, List<Pair<Class<?>, Class<? extends PrefsTransform>>> transformations) {
    return transformations.stream()
            .filter(item -> item.value0.getName().equals(typeName.toString()))
            .findFirst().map(item -> {
              PrefsTransform tranformation = null;
              try {
                tranformation = item.value1.newInstance();
              } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
              }
              return tranformation;
            }).orElse(null);
  }

  /**
   * Get transformer for type.
   *
   * @param typeName the type name
   * @return transform
   */
  public static PrefsTransform lookup(TypeName typeName) {
    PrefsTransform transform = cache.get(typeName);

    if (transform != null) {
      return transform;
    }

    transform = getTransform(typeName);
    AssertKripton.assertNotNull(transform, new UnsupportedFieldTypeException(typeName));
    cache.put(typeName, transform);

    return transform;
  }

  /**
   * Gets the transform.
   *
   * @param typeName the type name
   * @return the transform
   */
  private static PrefsTransform getTransform(TypeName typeName) {
    if (typeName.isPrimitive()) {
      return getPrimitiveTransform(typeName);
    }

    if (typeName instanceof ArrayTypeName) {
      ArrayTypeName typeNameArray = (ArrayTypeName) typeName;
      TypeName componentTypeName = typeNameArray.componentType;

      if (TypeUtility.isEquals(componentTypeName, Byte.TYPE.toString())) {
        return new ByteArrayPrefsTransform();
      } else {
        return new ArrayPrefsTransform();
      }
    } else if (typeName instanceof ParameterizedTypeName) {
      ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
      if (TypeUtility.isList(parameterizedTypeName)) {
        return new ListPrefsTransformation();
      } else if (TypeUtility.isSet(parameterizedTypeName)) {
        return new SetPrefsTransformation();
      } else if (TypeUtility.isMap(parameterizedTypeName)) {
        return new MapPrefsTransformation();
      }
    }

    if (TypeUtility.isEnum(typeName)) {
      return new EnumPrefsTransform(typeName);
    }

    // for default is treated as object
    String name = typeName.toString();
    List<Pair<Class<?>, Class<? extends PrefsTransform>>> values = transformations.stream()
            .filter(item -> name.startsWith(item.value0))
            .findFirst().map(item -> item.value1).orElse(null);

    if (values != null) {
      return getSupportedTransformations(typeName, values);
    } else {
      return new ObjectPrefsTransform();
    }
  }


  static PrefsTransform getPrimitiveTransform(TypeName type) {

    if (Integer.TYPE.toString().equals(type.toString())) {
      return new IntegerPrefsTransform(false);
    }
    if (Boolean.TYPE.toString().equals(type.toString())) {
      return new BooleanPrefsTransform(false);
    }
    if (Long.TYPE.toString().equals(type.toString())) {
      return new LongPrefsTransform(false);
    }
    if (Double.TYPE.toString().equals(type.toString())) {
      return new DoublePrefsTransform(false);
    }
    if (Float.TYPE.toString().equals(type.toString())) {
      return new FloatPrefsTransform(false);
    }
    if (Short.TYPE.toString().equals(type.toString())) {
      return new ShortPrefsTransform(false);
    }
    if (Byte.TYPE.toString().equals(type.toString())) {
      return new BytePrefsTransform(false);
    }
    if (Character.TYPE.toString().equals(type.toString())) {
      return new CharacterPrefsTransform(false);
    }
    return null;
  }
}
