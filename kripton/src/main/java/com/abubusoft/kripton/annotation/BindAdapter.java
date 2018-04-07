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
package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.TypeAdapter;

/**
 * <p>
 * Defines TypeAdapter to use to the field.
 * </p>
 * 
 * <p>
 * This kind of adapter is applied from java to data format (JSON/XML/etc) and
 * viceversa. <strong>It is not used in SharedPreference and SQLite
 * generation.</strong>
 * </p>
 * 
 * <p>
 * Every supported java type in Kripton has a specific representation in its
 * persisted state. This annotation allows to persist a field of a specific type
 * in can be applied to a public field or a field with getter and setter. It is
 * not used in SharedPreference and SQLite generation.
 * </p>
 * <p>
 * For example:
 * </p>
 * 
 * <pre>
 * &#64;BindType
 * public class Bean {
 * 
 * 	&#64;BindXml(xmlType = XmlType.ATTRIBUTE)
 * 	public String description;
 * 
 * 	&#64;BindXml(xmlType = XmlType.ATTRIBUTE)
 * 	public int id;
 * 
 * 	public Date date;
 * }
 * </pre>
 * <p>
 * Its rapresentation in JSON:
 * </p>
 * 
 * <pre>
 * {"date":"2017-01-23T00:35:24.728Z","description":"hello","id":0}
 * </pre>
 * 
 * Its rapresentation in XML (indented for readibility reasons):
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;
&lt;bean description="hello" id="0"&gt;
  &lt;date>2017-01-23T00:35:24.728Z&lt;/date&gt;
&lt;/bean&gt;
 * </pre>
 * 
 * As you notice, field date is persisted like a string
 * 2017-01-23T00:35:24.728Z. To persist date as a long, just change annotations
 * and write an adapter class named DateAdapter:
 * 
 * <pre>
 * &#64;BindType
 * public class Bean {
 * 
 * 	&#64;BindXml(xmlType = XmlType.ATTRIBUTE)
 * 	public String description;
 * 
 * 	&#64;BindXml(xmlType = XmlType.ATTRIBUTE)
 * 	public int id;
 * 
 * 	&#64;BindAdapter(adapter = DateAdapter.class, dataType = Long.class)
 * 	public Date date;
 * }
 * </pre>
 * 
 * 
 * <pre>
 * public class DateAdapter implements BindTypeAdapter&lt;Date, Long&gt; {
 * 
 * 	&#64;Override
 * 	public Date toJava(Long dataValue) throws Exception {
 * 		return new Date(dataValue);
 * 	}
 * 
 * 	&#64;Override
 * 	public Long toData(Date javaValue) throws Exception {
 * 		return javaValue.getTime();
 * 	}
 * }
 * </pre>
 * 
 * 
 * Bean's rapresentation in JSON:
 * 
 * <pre>
 * {"date":1485132009409,"description":"hello","id":0}
 * </pre>
 * 
 * Its rapresentation in XML (indented for readibility reasons):
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;
 *  &lt;bean description="hello" id="0"&gt;
 *    &lt;date&gt;1485132009409&lt;/date&gt;
 *  &lt;/bean&gt;
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindAdapter {

	/**
	 * TypeAdapter used to convert data
	 * 
	 * @return instance of class converter
	 */
	public Class<? extends TypeAdapter<?, ?>> adapter();
}
