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
 * <p>
 * Annotation for customize binding operation.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	/**
	 * default order of persistence. The order is ascendent (lower number will
	 * be used at first)
	 */
	public static final int DEFAULT_ORDER = 1000;

	/**
	 * default value for values of map's entry.
	 */
	public static final String MAP_VALUE_DEFAULT = "value";

	/**
	 * default value for keys of map's entry.
	 */
	public static final String MAP_KEY_DEFAULT = "key";

	/**
	 * true if this field must be persisted. Note that **@BindDisabled**
	 * annotation overrides this value.
	 * 
	 * @return true if field is enabled
	 */
	public boolean enabled() default true;

	/**
	 * The name of the element.
	 * 
	 * @return name
	 */
	public String value() default "";

	/**
	 * name of the element represents key of a map's entry. <b>Used only by map
	 * type</b>.
	 * 
	 * @return name of map key. Default is {@link #MAP_KEY_DEFAULT}
	 */
	public String mapKeyName() default MAP_KEY_DEFAULT;

	/**
	 * name of the element represents the value of a map's entry. <b>Used only
	 * by map type</b>.
	 * 
	 * @return name of map. Default is {@link #MAP_VALUE_DEFAULT}
	 */
	public String mapValueName() default MAP_VALUE_DEFAULT;

	/**
	 * specifies the order of POJO fields during mapping. The order is specified
	 * the entire schema.
	 * 
	 * @return order of field
	 */
	public int order() default DEFAULT_ORDER;

}
