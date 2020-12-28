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
package com.abubusoft.kripton.processor.bind.transform;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.bind.transform.lang.*;
import com.abubusoft.kripton.processor.bind.transform.math.MathTransformations;
import com.abubusoft.kripton.processor.bind.transform.net.NetTransformations;
import com.abubusoft.kripton.processor.bind.transform.sql.SQLTransformations;
import com.abubusoft.kripton.processor.bind.transform.time.TimeTransformations;
import com.abubusoft.kripton.processor.bind.transform.util.UtilsTransformations;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.UnsupportedFieldTypeException;
import com.google.common.collect.Lists;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.abubusoft.kripton.common.Pair.of;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;


/**
 * Transformer for java primitive types and frequently used java types.
 */
public abstract class BindTransformer {

  private static final List<Pair<String, List<Pair<Class<?>, Class<? extends BindTransform>>>>> transformations = Lists.newArrayList(
          of("java.lang", LangTransformations.transformations),
          of("java.util", UtilsTransformations.transformations),
          of("java.math", MathTransformations.transformations),
          of("java.net", NetTransformations.transformations),
          of("java.sql", SQLTransformations.transformations),
          of("java.time", TimeTransformations.transformations)
  );

  static final Set<String> unsupportedPackage = new HashSet<>(
          Arrays.asList("java.", "javax.", "android.", "androidx."));
  /**
   * cache for transform.
   */
  private static final Map<TypeName, BindTransform> cache = new ConcurrentHashMap<TypeName, BindTransform>();

  /**
   * Get transformer for type.
   *
   * @param property the property
   * @return transform
   */
  public static BindTransform lookup(BindProperty property) {
    try {
      TypeName typeName = property.getPropertyType().getTypeName();

      if (property.hasTypeAdapter()) {
        typeName = typeName(property.typeAdapter.dataType);
      }

      return lookup(typeName);
    } catch (UnsupportedFieldTypeException e) {
      throw (UnsupportedFieldTypeException.merge(e, property));
    }
  }

  /**
   * Checks if is binded object.
   *
   * @param typeName the type name
   * @return true, if is binded object
   */
  public static boolean isBindedObject(TypeName typeName) {
    BindTransform t = lookup(typeName);

    if (t instanceof ObjectBindTransform) {
      return true;
    }
    return false;
  }

  /**
   * Checks if is binded object.
   *
   * @param property the property
   * @return true, if is binded object
   */
  public static boolean isBindedObject(BindProperty property) {
    BindTransform t = lookup(property);

    if (t instanceof ObjectBindTransform) {
      return true;
    }
    return false;
  }

  static boolean isInUnsupportedPackage(TypeName typeName) {
    for (String item : unsupportedPackage) {
      if (typeName.toString().startsWith(item)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Get transformer for type.
   *
   * @param typeName the type name
   * @return transform
   */
  public static BindTransform lookup(TypeName typeName) {
    BindTransform transform = cache.get(typeName);

    if (transform != null) {
      return transform;
    }

    transform = getTransform(typeName);

    AssertKripton.assertTrueOrUnsupportedFieldTypeException(transform != null, typeName);

    // transform will be always valorized
    cache.put(typeName, transform);

    return transform;
  }

  public static void checkIfIsInUnsupportedPackage(TypeName typeName) {
    BindTransform transform = lookup(typeName);

    AssertKripton.assertTrueOrUnsupportedFieldTypeException(
            !(transform.getClass().getName().equals(ObjectBindTransform.class.getName())
                    && isInUnsupportedPackage(typeName)),
            typeName);
  }

  /**
   * Gets the transform.
   *
   * @param typeName the type name
   * @return the transform
   */
  static BindTransform getTransform(TypeName typeName) {
    if (typeName.isPrimitive()) {
      return getPrimitiveTransform(typeName);
    }

    if (typeName instanceof ArrayTypeName) {
      ArrayTypeName typeNameArray = (ArrayTypeName) typeName;

      if (TypeUtility.isEquals(typeNameArray.componentType, Byte.TYPE.toString())) {
        return new ByteArrayBindTransform();
      } else {
        return new ArrayBindTransform(typeNameArray.componentType, typeNameArray.componentType.isPrimitive());
      }
    } else if (typeName instanceof ParameterizedTypeName) {
      ParameterizedTypeName parameterizedTypeName = (ParameterizedTypeName) typeName;
      if (TypeUtility.isList(parameterizedTypeName.rawType)) {
        return new ListBindTransformation(parameterizedTypeName);
      } else if (TypeUtility.isSet(parameterizedTypeName.rawType)) {
        return new SetBindTransformation(parameterizedTypeName);
      } else if (TypeUtility.isMap(parameterizedTypeName.rawType)) {
        return new MapBindTransformation(parameterizedTypeName);
      }
    }

    if (TypeUtility.isEnum(typeName)) {
      return new EnumBindTransform(typeName);
    }

    // for default is treated as object
    String name = typeName.toString();
    List<Pair<Class<?>, Class<? extends BindTransform>>> values = transformations.stream()
            .filter(item -> name.startsWith(item.value0))
            .findFirst().map(item -> item.value1).orElse(null);

    if (values != null) {
      return getSupportedTransformations(typeName, values);
    } else {
      return new ObjectBindTransform();
    }
  }

  static BindTransform getSupportedTransformations(TypeName typeName, List<Pair<Class<?>, Class<? extends BindTransform>>> transformations) {
    return transformations.stream().filter(item -> item.value0.getName().equals(typeName.toString())).findFirst().map(item -> {
      BindTransform tranformation = null;
      try {
        tranformation = item.value1.newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
      return tranformation;
    }).orElse(null);
  }

  /**
   * Get Java primitive type Transformable.
   *
   * @param type the type
   * @return the primitive transform
   */
  static BindTransform getPrimitiveTransform(TypeName type) {
    if (Integer.TYPE.toString().equals(type.toString())) {
      return new IntegerBindTransform(false);
    }
    if (Boolean.TYPE.toString().equals(type.toString())) {
      return new BooleanBindTransform(false);
    }
    if (Long.TYPE.toString().equals(type.toString())) {
      return new LongBindTransform(false);
    }
    if (Double.TYPE.toString().equals(type.toString())) {
      return new DoubleBindTransform(false);
    }
    if (Float.TYPE.toString().equals(type.toString())) {
      return new FloatBindTransform(false);
    }
    if (Short.TYPE.toString().equals(type.toString())) {
      return new ShortBindTransform(false);
    }
    if (Byte.TYPE.toString().equals(type.toString())) {
      return new ByteBindTransform(false);
    }
    if (Character.TYPE.toString().equals(type.toString())) {
      return new CharacterBindTransform(false);
    }
    return null;
  }

}
