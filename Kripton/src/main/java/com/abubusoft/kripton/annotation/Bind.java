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

/**
 * 
 * Annotation for bind associated field to xml/json/properties/cbor/yaml format.
 * 
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	/**
	 * default order of
	 */
	public static final int DEFAULT_ORDER = 1000;

	/**
	 * default name of map value entry
	 */
	public static final String MAP_VALUE_DEFAULT = "value";
	/**
	 * default name of map key entry
	 */
	public static final String MAP_KEY_DEFAULT = "key";

	/**
	 * true if this field must be persisted. Note that {@link BindDisabled} annotation wins about this value.
	 * 
	 * @return true if field is enabled
	 */
	public boolean enabled() default true;

	/**
	 * The name of the XML/JSON element
	 * 
	 * @return name
	 */
	public String value() default "";

	/**
	 * name of element rapresents key of a map. <b>Used only by map type.</b>
	 * 
	 * @return name of map key. Default is {@link #MAP_KEY_DEFAULT}
	 */
	public String mapKeyName() default MAP_KEY_DEFAULT;

	/**
	 * name of element rapresents value of a map. <b>Used only by map type.</b>
	 * 
	 * @return name of map. Default is {@link #MAP_VALUE_DEFAULT}
	 */
	public String mapValueName() default MAP_VALUE_DEFAULT;

	/**
	 * specifies the order of pojo fields during mapping. Order is specified entire schema.
	 * 
	 * @return order of field
	 */
	public int order() default DEFAULT_ORDER;

}
