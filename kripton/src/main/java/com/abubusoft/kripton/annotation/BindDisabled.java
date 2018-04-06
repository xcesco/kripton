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
 * This annotation disable completely bind for the associated field. This
 * annotation is usefully only when attribute <strong>allFields</strong> is set
 * to <code>true</code> (default value).
 * </p>
 * 
 * <p>
 * <strong>Don't use with allField = false, otherwise an exception will be
 * thrown during compilation</strong>
 * </p>
 * 
 * <p>
 * This annotation affects bind to xml/json/etc, sharepreference and sqlite
 * conversion.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindDisabled {

}
