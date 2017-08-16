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

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class Test81DCompile {

	@Test
	public void testBindTransformer()
	{
		assertEquals(StringBindTransform.class.getName(), BindTransformer.getLanguageTransform(typeName("java.lang.String")).getClass().getName());
		assertEquals(BigDecimalBindTransform.class.getName(), BindTransformer.getMathTransform(typeName("java.math.BigDecimal")).getClass().getName());
		assertEquals(UrlBindTransform.class.getName(), BindTransformer.getNetTransform(typeName("java.net.URL")).getClass().getName());
		assertEquals(IntegerBindTransform.class.getName(), BindTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
		assertEquals(TimeBindTransform.class.getName(), BindTransformer.getSqlTransform(typeName("java.sql.Time")).getClass().getName());
		assertEquals(DateBindTransform.class.getName(), BindTransformer.getUtilTransform(typeName("java.util.Date")).getClass().getName());
	}
}
