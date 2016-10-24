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

import com.abubusoft.kripton.binder.transform.CustomTransform;
import com.abubusoft.kripton.binder.transform.DefaultCustomTransform;

/**
 * 
 * Define a custom trasformation for a field
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindTransform {

	/**
	 * <p>Custom trasformation for field or for key value of map entry.</p>
	 * 
	 * @return
	 * 		custom trasformation or null.
	 */
	public Class<? extends CustomTransform<?>> value() default DefaultCustomTransform.class;
	
	/**
	 * <p>Custom trasformation for value of map entry.</p>
	 * 
	 * @return
	 * 		custom trasformation or null.
	 */
	public Class<? extends CustomTransform<?>> mapValue() default DefaultCustomTransform.class;

}
