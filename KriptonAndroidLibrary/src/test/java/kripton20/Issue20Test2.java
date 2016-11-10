/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton20;

import org.junit.Test;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;

public class Issue20Test2 {

	@BindType
	public class Bean1
	{
		@Bind
		public String value;
	}
	
	@BindType
	@BindTypeXml
	public class Bean2
	{
		@Bind(elementName="test")
		public String value;
	}
	
	@Test
	public void test01() throws MappingException {
		MappingSchema.fromClass(Bean1.class);
	}
	
	@Test(expected=MappingException.class)
	public void test02() throws MappingException {
		MappingSchema.fromClass(Bean2.class);
	}
}
