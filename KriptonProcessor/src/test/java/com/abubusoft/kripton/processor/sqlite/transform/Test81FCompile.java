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
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class Test81FCompile {

	@Test
	public void testBindTransformer()
	{
		assertEquals(StringSQLTransform.class.getName(), SQLTransformer.getLanguageTransform(typeName("java.lang.String")).getClass().getName());
		assertEquals(BigDecimalSQLTransform.class.getName(), SQLTransformer.getMathTransform(typeName("java.math.BigDecimal")).getClass().getName());
		assertEquals(UrlSQLTransform.class.getName(), SQLTransformer.getNetTransform(typeName("java.net.URL")).getClass().getName());
		assertEquals(IntegerSQLTransform.class.getName(), SQLTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
		assertEquals(TimeSQLTransform.class.getName(), SQLTransformer.getSqlTransform(typeName("java.sql.Time")).getClass().getName());
		assertEquals(DateSQLTransform.class.getName(), SQLTransformer.getUtilTransform(typeName("java.util.Date")).getClass().getName());
	}
}
