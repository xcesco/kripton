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
 * This annotation is used to annotate static method of a <i>DataSource</i> interface to define a transation. An example:
 * 
 * <pre>
 * &#64;BindDataSource(fileName = "app.db", version = 1, daoSet = { DaoPerson.class }, rx = true)
 * public interface AppDataSource {
 * 
 * 	&#64;BindTransaction
 * 	static void execute(DaoPerson daoPerson) {
 * 		daoPerson.insert(new Person());
 * 	}
 * }
 * </pre>
 * 
 * The return type must be <code>void</code>. Parameters can be of any type, including datasource's dao. These last type of parameter will be remove from the generated method.
 * 
 * @author xcesco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BindTransaction {

}
