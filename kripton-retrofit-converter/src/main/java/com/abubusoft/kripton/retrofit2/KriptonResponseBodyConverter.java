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
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;

/**
 * The Class KriptonResponseBodyConverter.
 *
 * @param <T> the generic type
 */
final class KriptonResponseBodyConverter<T> extends AbstractConverter implements Converter<ResponseBody, T> {

  /**
   * The clazz.
   */
  private final Class<T> clazz;

  /**
   * Instantiates a new kripton response body converter.
   *
   * @param binderContext the binder context
   * @param clazz         the clazz
   */
  KriptonResponseBodyConverter(BinderContext binderContext, Charset defaultCharset, boolean includeCharsetInContentType, CodingErrorAction codingErrorAction, Class<T> clazz) {
    super(binderContext, defaultCharset, codingErrorAction, includeCharsetInContentType);
    this.clazz = clazz;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    try (InputStreamReader targetStream = new InputStreamReader(value.byteStream(), createCharsetDecoder(value.contentType()))) {
      return binderContext.parse(targetStream, clazz);
    } catch (Exception e) {
      e.printStackTrace();
      throw (new IOException(e));
    }
  }
}
