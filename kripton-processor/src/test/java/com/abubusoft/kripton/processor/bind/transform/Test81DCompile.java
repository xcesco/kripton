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

import com.abubusoft.kripton.processor.bind.transform.lang.IntegerBindTransform;
import com.abubusoft.kripton.processor.bind.transform.lang.LangTransformations;
import com.abubusoft.kripton.processor.bind.transform.lang.StringBindTransform;
import com.abubusoft.kripton.processor.bind.transform.math.BigDecimalBindTransform;
import com.abubusoft.kripton.processor.bind.transform.math.MathTransformations;
import com.abubusoft.kripton.processor.bind.transform.net.UrlBindTransform;
import com.abubusoft.kripton.processor.bind.transform.sql.SQLDateBindTransform;
import com.abubusoft.kripton.processor.bind.transform.sql.SQLTimeBindTransform;
import com.abubusoft.kripton.processor.bind.transform.sql.SQLTransformations;
import com.abubusoft.kripton.processor.bind.transform.util.DateBindTransform;
import com.abubusoft.kripton.processor.bind.transform.util.UtilsTransformations;
import com.abubusoft.kripton.processor.bind.transform.net.NetTransformations;
import org.junit.Test;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;


/**
 * The Class Test81DCompile.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class Test81DCompile {

  /**
   * Test bind transformer.
   */
  @Test
  public void testBindTransformer() {
    assertEquals(StringBindTransform.class.getName(), BindTransformer.getSupportedTransformations(typeName("java.lang.String"), LangTransformations.transformations).getClass().getName());
    assertEquals(BigDecimalBindTransform.class.getName(), BindTransformer.getSupportedTransformations(typeName("java.math.BigDecimal"), MathTransformations.transformations).getClass().getName());
    assertEquals(UrlBindTransform.class.getName(), BindTransformer.getSupportedTransformations(typeName("java.net.URL"), NetTransformations.transformations).getClass().getName());
    assertEquals(IntegerBindTransform.class.getName(), BindTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
    assertEquals(SQLTimeBindTransform.class.getName(), BindTransformer.getSupportedTransformations(typeName("java.sql.Time"), SQLTransformations.transformations).getClass().getName());
    assertEquals(SQLDateBindTransform.class.getName(), BindTransformer.getSupportedTransformations(typeName("java.sql.Date"), SQLTransformations.transformations).getClass().getName());
    assertEquals(DateBindTransform.class.getName(), BindTransformer.getSupportedTransformations(typeName("java.util.Date"), UtilsTransformations.transformations).getClass().getName());
  }
}
