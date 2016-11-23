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
/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * @author xcesco
 *
 */
public abstract class AbstractCompileTimeTransform implements Transform {
	
	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		methodBuilder.addCode("$L", getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$L", objectName);		
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		// except for supported result type, each transform does not need to implements this method
		throw new KriptonRuntimeException("Something went wrong!");
	}
	
	@Override
	public void generateDefaultValue(Builder methodBuilder) {
		throw new KriptonRuntimeException("Something went wrong!");
	}
	

}
