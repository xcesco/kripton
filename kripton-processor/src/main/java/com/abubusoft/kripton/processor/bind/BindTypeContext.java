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
package com.abubusoft.kripton.processor.bind;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class BindTypeContext {
	public TypeSpec.Builder builder;
	public Set<String> alreadyGeneratedMethods;
	public Modifier[] modifiers;
	private TypeName beanTypeName;

	public BindTypeContext(TypeSpec.Builder builder, TypeName beanTypeName, Modifier ... modifiers) {
		this.builder=builder;
		this.beanTypeName=beanTypeName;
		this.alreadyGeneratedMethods = new HashSet<>();
		this.modifiers=modifiers;
	}

	public String getBindMapperName(BindTypeContext context, TypeName typeName) {
		Converter<String, String> format = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		TypeName bindMapperName=TypeUtility.mergeTypeNameWithSuffix(typeName,BindTypeBuilder.SUFFIX);
		String simpleName=format.convert(TypeUtility.simpleName(bindMapperName));
	
		if (!alreadyGeneratedMethods.contains(simpleName))
		{
			alreadyGeneratedMethods.add(simpleName);
			if (bindMapperName.equals(beanTypeName))
			{
				context.builder.addField(FieldSpec.builder(bindMapperName, simpleName, modifiers)					
						.addJavadoc("$T", bindMapperName)
						.initializer("this")
						.build());
			} else {				
				context.builder.addField(FieldSpec.builder(bindMapperName, simpleName, modifiers)					
						.addJavadoc("$T", bindMapperName)
						.initializer("$T.mapperFor($T.class)", BinderUtils.class, typeName)
						.build());	
			}		
		}
		
		return simpleName;
	}
}