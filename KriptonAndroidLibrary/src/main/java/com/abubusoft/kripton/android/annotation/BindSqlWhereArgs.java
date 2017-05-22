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
 * Define dynamic where condition's arguments. These arguments are append to existing ones. 
 * 
 * This annotation can be used: 
 * 
 * <ul>
 *    <li>on SELECT, DELETE, UPDATE query type</li>
 *    <li>if {@link BindSqlWhere} marked argument is defined is the same method</li>
 *    <li>on type <code>String[]</code> parameter</li>
 *    <li>only one annotation for method</li>
 * </ul>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 * 
 * @see BindSqlWhere
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BindSqlWhereArgs {

}
