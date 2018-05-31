/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
package com.abubusoft.kripton.retrofit3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.abubusoft.kripton.retrofit2.AbstractBaseTest;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;
import com.abubusoft.kripton.retrofit3.model.Search;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

// TODO: Auto-generated Javadoc
/**
 * The Class TestConversion.
 */
public class TestOmdb extends AbstractBaseTest {

	/**
	 * Log.
	 *
	 * @param msg
	 *            the msg
	 */
	public void log(String msg) {
		System.out.println(msg);
	}

	/**
	 * Uncomment @Test to run
	 */
	// @Test
	public void test() throws IOException {
		String apiKey = "d497e644";

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).addInterceptor(logging); // <--
																																	// this
																																	// is
																																	// the
																																	// important
																																	// line!

		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.omdbapi.com/").addConverterFactory(KriptonBinderConverterFactory.create()).client(httpClient.build()).build();

		OmdbApi service = retrofit.create(OmdbApi.class);

		Response<Search> response = service.search("avengers", apiKey).execute();

		log(response.body().getTotalResults());

	}

}
