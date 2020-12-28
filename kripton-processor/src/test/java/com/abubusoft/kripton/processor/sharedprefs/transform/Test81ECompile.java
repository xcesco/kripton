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

import com.abubusoft.kripton.processor.sharedprefs.transform.lang.IntegerPrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.lang.LangTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.lang.StringPrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.math.BigDecimalPrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.math.MathTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.net.NetTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.net.UrlPrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.sql.SQLTimePrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.sql.SQLTransformations;
import com.abubusoft.kripton.processor.sharedprefs.transform.util.DatePrefsTransform;
import com.abubusoft.kripton.processor.sharedprefs.transform.util.UtilsTransformations;
import org.junit.Test;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;
import static org.junit.Assert.assertEquals;


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
  public void testBindTransformer() {
    assertEquals(StringPrefsTransform.class.getName(), PrefsTransformer.getSupportedTransformations(typeName("java.lang.String"), LangTransformations.transformations).getClass().getName());
    assertEquals(BigDecimalPrefsTransform.class.getName(), PrefsTransformer.getSupportedTransformations(typeName("java.math.BigDecimal"), MathTransformations.transformations).getClass().getName());
    assertEquals(UrlPrefsTransform.class.getName(), PrefsTransformer.getSupportedTransformations(typeName("java.net.URL"), NetTransformations.transformations).getClass().getName());
    assertEquals(IntegerPrefsTransform.class.getName(), PrefsTransformer.getPrimitiveTransform(typeName("int")).getClass().getName());
    assertEquals(SQLTimePrefsTransform.class.getName(), PrefsTransformer.getSupportedTransformations(typeName("java.sql.Time"), SQLTransformations.transformations).getClass().getName());
    assertEquals(DatePrefsTransform.class.getName(), PrefsTransformer.getSupportedTransformations(typeName("java.util.Date"), UtilsTransformations.transformations).getClass().getName());
  }
}
