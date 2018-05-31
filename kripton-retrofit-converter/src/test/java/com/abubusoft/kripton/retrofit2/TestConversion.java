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
package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;

import retrofit2.Retrofit;

// TODO: Auto-generated Javadoc
/**
 * The Class TestConversion.
 */
public class TestConversion extends AbstractBaseTest {

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
	@Test
	public void test2() {
		String input = "\t[\n\t{\"userId\": 1,\"id\": 1, \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"}]";

		KriptonJsonContext context = KriptonBinder.jsonBind();
		List a = context.parseCollection(input, new ArrayList<Post>(), Post.class);
		log("" + a.size());
	}

	/**
	 * Test.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void test() throws IOException {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(KriptonBinderConverterFactory.create()).build();

		JsonPlaceHolderService service = retrofit.create(JsonPlaceHolderService.class);

		// Response<List<Post>> response = service.getAllPost().execute();

		// log(response.toString());

		// log(""+list);
	}

}
