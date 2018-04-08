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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Used to generate a content provider path for the annotated data source. This
 * annotation can be used only on a DAO definition.
 * </p>
 * 
 * <h2>Attributes</h2>
 * <ul>
 * <li><strong>path</strong>: define the segment path associated with DAO.</li>
 * <li><strong>typeName</strong>: name of type that content provider returns. If
 * not specified, a class name with authority</li>
 * </ul>
 * 
 * <h2>Usage</h2>
 * <p>
 * As stated before, this annotation can be used only on a DAO method's
 * interface. A DAO interface that uses this annotation:
 * </p>
 * 
 * <pre>
 * &#64;BindContentProviderPath(path = "cheese")
 * &#64;BindDao(Cheese.class)
 * public interface CheeseDao {
 * 
 * 	&#64;BindSqlSelect(fields = "count(*)")
 * 	int count();
 * 
 * 	&#64;BindContentProviderEntry
 * 	&#64;BindSqlInsert
 * 	long insert(Cheese cheese);
 * 
 * 	&#64;BindContentProviderEntry()
 * 	&#64;BindSqlSelect
 * 	List&lt;Cheese&gt; selectAll();
 * 
 * 	&#64;BindContentProviderEntry(path = "${id}")
 * 	&#64;BindSqlSelect(where = "id=${id}")
 * 	Cheese selectById(long id);
 * 
 * 	&#64;BindContentProviderEntry(path = "${id}")
 * 	&#64;BindSqlDelete(where = "id=${id}")
 * 	int deleteById(long id);
 * 
 * 	&#64;BindContentProviderEntry(path = "${cheese.id}")
 * 	&#64;BindSqlUpdate(where = "id=${cheese.id}")
 * 	int update(Cheese cheese);
 * }
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
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
	 * Name of type that content provider returns. If not specified, class name
	 * with authority will be used.
	 * 
	 * @return type name
	 */
	public String typeName() default "";

}
