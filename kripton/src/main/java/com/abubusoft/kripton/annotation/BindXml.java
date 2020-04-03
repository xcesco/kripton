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

import com.abubusoft.kripton.xml.MapEntryType;
import com.abubusoft.kripton.xml.XmlType;

/**
 * 
 * <p>
 * Specifies information to bind with xml format
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindXml {

	/**
	 * <p>
	 * Allows to specify the namespace of the element
	 * </p>
	 * 
	 * @return namespace of the tag or the attribute that rapresents the element.
	 */
	String namespace() default "";

	/**
	 * <p>
	 * Used with collections and maps. It's the name of elements contained in the
	 * collection or array, except byte array. Thus, name specified in attribute
	 * value will be used for container.
	 * </p>
	 * 
	 * <p>
	 * <strong>To specify tag name, use {@link Bind} annotation.</strong>
	 * </p>
	 * 
	 * <p>
	 * For examples, using <code>BindXml(elementTag="item")</code> and
	 * <code>Bind("list")</code> for an element:
	 * 
	 * <pre>
	...
	&lt;list&gt;
	&lt;item&gt;..&lt;/item&gt;
	&lt;item&gt;..&lt;/item&gt;
	&lt;item&gt;..&lt;/item&gt;
	&lt;/list&gt;
	...
	 * </pre>
	 * 
	 * @return name of elements of collection. default is ""
	 */
	String elementTag() default "";

	/**
	 * Type of binding. Default is TAG. See {@link XmlType}
	 * 
	 * @return Type of binding. Default is TAG.
	 */
	XmlType xmlType() default XmlType.TAG;

	/**
	 * Type of mapping of element of a map. <strong>Valid only for maps</strong>. If
	 * used with a field who does not implement map interface, an runtime exception
	 * will be thrown. See {@link MapEntryType}.
	 * 
	 * @return Type of mapping of element of a map
	 */
	MapEntryType mapEntryType() default MapEntryType.TAG;

}
