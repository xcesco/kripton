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
package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;

/**
 * 
 * This annotation specify information to bind with xml format
 * 
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindXml {	
	
	
	/**
	 * Customized name of xml element.
	 * 
	 * @return
	 * 		Name of xml element
	 */
	String value() default "";
	
	/**
	 * 
	 * @return
	 * 		if true, means field must bind persist model
	 */
	boolean enabled() default true;

	/**
	 * Type of binding. Default is by TAG. See {@link XmlType}
	 * 
	 * @return Type of binding. Default is by TAG.
	 */
	XmlType xmlType() default XmlType.TAG;

	/**
	 * Type of mapping of element of a map. <strong>Valid only for
	 * maps</strong>. If used with a field who does not implement map interface,
	 * an runtime exception will be thrown. See {@link MapEntryType}.
	 * 
	 * @return Type of mapping of element of a map
	 */
	MapEntryType mapEntryStrategy() default MapEntryType.ELEMENTS;

}
