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
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;

/**
 * The Class KriptonRequestBodyConverter.
 *
 * @param <T> the generic type
 */
final class KriptonRequestBodyConverter<T> extends AbstractConverter implements Converter<T, RequestBody> {

  /**
   * Instantiates a new kripton request body converter.
   */
  KriptonRequestBodyConverter(BinderContext binderContext, Charset defaultCharset, boolean includeCharsetInContentType, CodingErrorAction codingErrorAction) {
    super(binderContext, defaultCharset, codingErrorAction, includeCharsetInContentType);
  }

  /* (non-Javadoc)
   * @see retrofit2.Converter#convert(java.lang.Object)
   */
  @Override
  public RequestBody convert(T value) throws IOException {
    try (Buffer buffer = new Buffer(); OutputStreamWriter outputStreamWriter=new OutputStreamWriter(buffer.outputStream(), defaultCharset.newEncoder())) {
      binderContext.serialize(value, outputStreamWriter);
      return RequestBody.create(mediaType, buffer.readByteString());
    } catch (Exception e) {
      e.printStackTrace();
      throw (new IOException(e));
    }

  }
}
