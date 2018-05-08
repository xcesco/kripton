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

/**
 * This annotation allow to customize xml persistence of java beans.
 * 
 * <h3>Attributes</h3> Defined attributes are:
 * 
 * <ul>
 * <li>namespaces: set of namespace defined and used for the element. Note that
 * it is used only if annotated bean is used as root of persistence.</li>
 * </ul>
 * <h3>How it is used</h3> An example:
 * 
 * <pre>
 * &#64;BindTypeXml(namespaces={&#64;BindXmlNamespace(name="tool", value="http://www.android.com")}
 * public class Image {
 * 	public String id;
 * 	public String format;
 * 
 *  &#64;BindXml(namespace="tool")
 * 	public String url;
 * 	public String description;
 * }
 * </pre>
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindXmlType {

	/**
	 * xml namespace set
	 * 
	 * @return namespace set
	 */
	public BindXmlNamespace[] namespaces() default {};

}
