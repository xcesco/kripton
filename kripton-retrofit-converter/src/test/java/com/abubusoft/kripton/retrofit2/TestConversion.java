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

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * The Class TestConversion.
 */
public class TestConversion extends AbstractBaseTest {

  @Before
  public void setup() {

  }

  /**
   * Log.
   *
   * @param msg the msg
   */
  public void log(String msg) {
    System.out.println(msg);
  }

  /**
   * Uncomment @Test to run
   */
  @Test
  public void test2() {
    String input = "\t[\n\t{\"userId\": 1,\"id\": 1, \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"}]";

    KriptonJsonContext context = KriptonBinder.jsonBind();
    List a = context.parseCollection(input, new ArrayList<>(), Post.class);
    log("" + a.size());
  }

  /**
   * Test.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Test
  public void testGet() throws IOException {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(KriptonBinderConverterFactory.create())
            .client(new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .callTimeout(240, TimeUnit.SECONDS)
                    .readTimeout(240, TimeUnit.SECONDS)
                    .build())
            .build();

    JsonPlaceHolderService service = retrofit.create(JsonPlaceHolderService.class);
    Response<List<Post>> response = service.getAllPost().execute();
    log(response.toString());
  }

  @Test
  public void testPost() throws IOException {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(KriptonBinderConverterFactory.create())
            .client(new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .callTimeout(240, TimeUnit.SECONDS)
                    .readTimeout(240, TimeUnit.SECONDS)
                    .build())
            .build();

    JsonPlaceHolderService service = retrofit.create(JsonPlaceHolderService.class);

    Post post=Post.create(0L, 0L, "sample_title", "sample_body");

    service.insert(post).execute();
    //log(response.toString());
  }

  @Test
  public void testWrongPost() throws IOException {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(KriptonBinderConverterFactory.Builder.create()
                    .setDefaultCharset(StandardCharsets.UTF_8)
                    .setErrorAction(CodingErrorAction.IGNORE)
                    .build())
            .client(new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .callTimeout(240, TimeUnit.SECONDS)
                    .readTimeout(240, TimeUnit.SECONDS)
                    .build())
            .build();

    JsonPlaceHolderService service = retrofit.create(JsonPlaceHolderService.class);

    Post post=Post.create(0L, 0L, "sample_title", "sample_body");

    service.insert(post).execute();
  }

}
