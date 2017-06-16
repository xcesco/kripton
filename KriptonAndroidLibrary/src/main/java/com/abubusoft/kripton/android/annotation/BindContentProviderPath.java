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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Used to generate a content provider path for annotated data source. This annotation can be used only on a DAO definition.
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * 
 * @see <a href=
 *      "https://developer.android.com/guide/topics/providers/content-provider-basics.html">content-provider-basics</a>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindContentProviderPath {

	/**
	 * Define the segment path associated to DAO.
	 * 
	 * @return content provider authority
	 */
	public String path();
	

	/**
	 * Name of type that content provider returns. If not specified, class name with authority
	 * will be used.
	 * 
	 * @return
	 * 		type name
	 */
	public String typeName() default "";
	
}
