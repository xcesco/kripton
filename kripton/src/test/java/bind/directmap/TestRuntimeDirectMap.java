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
package bind.directmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.map.BindMapListener;
import com.abubusoft.kripton.map.BindMapVisitor;
import com.abubusoft.kripton.map.BindMapVisitor.VisitorStatusType;

import bind.AbstractBaseTest;


/**
 * The Class TestRuntimeDirectMap.
 */
public class TestRuntimeDirectMap extends AbstractBaseTest {

	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception {
		KriptonJsonContext context = KriptonBinder.jsonBind();

		Person bean = new Person();
		bean.name = "name";
		bean.surname = "sunrame";
		bean.birthday = new Date();
		bean.tags = new ArrayList<>();
		bean.tags.add("hello");
		bean.tags.add(null);
		bean.tags.add("hello2");

		String buffer = context.serialize(bean);
		System.out.println(buffer);

		Map<String, Object> map = context.parseMap(buffer);

		Assert.assertTrue("name", bean.name.equals(map.get("name")));
		Assert.assertTrue("surname", bean.surname.equals(map.get("surname")));

		BindMapVisitor.execute(map, new BindMapListener() {

			@Override
			public void onField(String name, String value, VisitorStatusType status) {
				System.out.println(String.format("%s = %s", name, value));

			}
		});
	}

}
