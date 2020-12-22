/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.retrofit2;

import com.abubusoft.kripton.BinderContext;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.*;

/**
 * The Class KriptonResponseBodyCollectionConverter.
 *
 * @param <T> the generic type
 */
final class KriptonResponseBodyCollectionConverter<T> extends AbstractConverter implements Converter<ResponseBody, T> {

  /**
   * The clazz.
   */
  private final Class<?> clazz;

  /**
   * The bean clazz.
   */
  private final Class<?> beanClazz;

  /**
   * Instantiates a new kripton response body collection converter.
   *
   * @param binderContext  the binder context
   * @param collectionType the collection type
   */
  KriptonResponseBodyCollectionConverter(BinderContext binderContext, Charset defaultCharset, boolean includeCharsetInContentType, CodingErrorAction codingErrorAction, ParameterizedType collectionType) {
    super(binderContext, defaultCharset, codingErrorAction, includeCharsetInContentType);
    this.clazz = replaceInterface((Class<?>) collectionType.getRawType());
    this.beanClazz = (Class<?>) collectionType.getActualTypeArguments()[0];
  }

  /**
   * Replace interface.
   *
   * @param clazz the clazz
   * @return the class
   */
  private Class<?> replaceInterface(Class<?> clazz) {
    if (clazz.equals(List.class)) {
      return ArrayList.class;
    }

    if (clazz.equals(Set.class)) {
      return HashSet.class;
    }

    if (clazz.equals(Map.class)) {
      return HashMap.class;
    }

    return clazz;
  }

  /* (non-Javadoc)
   * @see retrofit2.Converter#convert(java.lang.Object)
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public T convert(ResponseBody value) throws IOException {
    try (InputStreamReader inputStreamReader = new InputStreamReader(value.byteStream(), createCharsetDecoder(value.contentType()))) {
      return (T) binderContext.parseCollection(inputStreamReader, (Collection) clazz.newInstance(), beanClazz);
    } catch (Exception e) {
      e.printStackTrace();
      throw (new IOException(e));
    }
  }
}
