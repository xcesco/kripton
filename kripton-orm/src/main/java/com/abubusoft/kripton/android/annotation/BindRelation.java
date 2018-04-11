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
 * This annotation allows to specify that marked field is external to table. In
 * case of one-2-many relationship this flag is used to mark a collection or a
 * single bean as rapresentation of relation between two table.
 * </p>
 * 
 * <p>
 * Image the following model:
 * </p>
 * 
 * <pre>
 * public class Album {
 * 	public long id;
 * 
 * 	&#064;BindRelation
 * 	public List&lt;Song&gt; songs;
 * }
 * 
 * public class Song {
 * 	public long id;
 * 	public long albumId;
 * }
 * </pre>
 * 
 * <p>
 * Field <code>songs</code> will not be inserted in table <code>album</code>. If
 * you remove <code>BindRelation</code> it will be inserted as BLOB.
 * 
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindRelation {

	/**
	 * Indicates which field is used in the other table to link this table. If
	 * there is only one field used as foreign key to this table, this attribute
	 * can be omitted.
	 * 
	 * @return
	 */
	String field() default "";
}
