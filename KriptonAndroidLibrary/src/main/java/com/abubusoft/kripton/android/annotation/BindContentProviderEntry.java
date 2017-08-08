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
 * Used to manage an URI managed by a content provider path for annotated data
 * source. This annotation can be used only on methods of DAO definition.
 * </p>
 * <p>
 * To use this annotation on DAO method, there are some constraints to respect.
 * </p>
 * 
 * <h3>Insert constraints</h3>
 * <ul>
 * <li>return type must be the primary key value (an <code>integer</code> or a
 * <code>long</code> or bean value)</li>
 * </ul>
 * 
 * <h3>Examples</h3>
 * <p>What follows is an examples of UPDATE method with a parameter used in content provider too.</p>
 * <pre>
 * &#64;BindContentProviderEntry(path="${bean.id}")
 * &#64;BindSqlUpdate(where="id=${bean.id}")
 * int updateBean(Person bean);
 * </pre>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * 
 * @see <a href=
 *      "https://developer.android.com/guide/topics/providers/content-provider-basics.html">content-provider-basics</a>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindContentProviderEntry {

	/**
	 * Define the segment path associated to DAO. <strong>Do not insert '/' at beginning of path, it's automatically added.</strong>
	 * 
	 * @return content provider authority
	 */
	public String path() default "";
	
	/**
	 * Define numerosity of result of operation exposed by content provider
	 * @return
	 */
	public MultiplicityResultType multiplicityResult() default MultiplicityResultType.DEFAULT;
	
	public enum MultiplicityResultType {
		/**
		 * default means: select return many rows, other operation only one.
		 */
		DEFAULT,
		ONE,
		MANY
	}

}
