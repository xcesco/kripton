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
/**
 * 
 */
package commons.benchmark;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import commons.benchmark.model.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class TestBenchmark.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestBenchmark {

	/**
	 * Test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test() throws Exception {
		URL base = getClass().getClassLoader().getResource("benchmark/largesample.json");

		String input = IOUtils.toString(base, Charset.forName("UTF-8"));
		System.out.println(base.getPath());

		long start = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++) {
			Response output = KriptonBinder.jsonBind().parse(input, Response.class);

			if (!"success".equals(output.status)) {
				throw new KriptonRuntimeException();
			}
		}
		long end = System.currentTimeMillis();

		System.out.println("Time to elaborate " + (end - start));
	}
}
