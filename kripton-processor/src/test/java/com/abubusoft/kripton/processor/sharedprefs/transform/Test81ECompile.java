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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class Test81ECompile.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class Test81ECompile {

	/**
	 * Test bind transformer.
	 */
	@Test
	public void testBindTransformer()
	{
		assertEquals(StringPrefsTransform.class.getName(), PrefsTransformer.getLanguageTransform(typeName("java.lang.String")).getClass().getName());
		assertEquals(BigDecimalPrefsTransform.class.getName(), PrefsTransformer.getMathTransform(typeName("java.math.BigDecimal")).getClass().getName());
		assertEquals(UrlPrefsTransform.class.getName(), PrefsTransformer.getNetTransform(typeName("java.net.URL")).getClass().getName());
		assertEquals(IntegerPrefsTransform.class.getName(), PrefsTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
		assertEquals(SQLTimePrefsTransform.class.getName(), PrefsTransformer.getSqlTransform(typeName("java.sql.Time")).getClass().getName());
		assertEquals(DatePrefsTransform.class.getName(), PrefsTransformer.getUtilTransform(typeName("java.util.Date")).getClass().getName());
	}
}
