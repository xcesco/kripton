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
package com.abubusoft.kripton.processor.bind.transform;

import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;


/**
 * Class implementing this interface can be used to generate code to read and
 * write the property.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public interface BindTransform {

	/**
	 * Generate parse on xml.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param parserName the parser name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property);

	/**
	 * Generate parse on jackson.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param parserName the parser name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateParseOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property);

	/**
	 * Generate parse on jackson as string.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param parserName the parser name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property);

	/**
	 * Generate serialize on xml.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param serializerName the serializer name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property);

	/**
	 * Generate serialize on jackson.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param serializerName the serializer name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property);

	/**
	 * Generate serialize on jackson as string.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param serializerName the serializer name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 */
	void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property);

	/**
	 * If true, means bind transformer support type adapter.
	 *
	 * @return true, if is type adapter supported
	 */
	boolean isTypeAdapterSupported();

}
