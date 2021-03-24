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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelEntity;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * The Class BindTypeContext.
 */
public class BindTypeContext {

	/** The builder. */
	public TypeSpec.Builder builder;

	public MethodSpec.Builder initBuilder;

	/** The already generated methods. */
	public Set<String> alreadyGeneratedMethods;

	/** The modifiers. */
	public Modifier[] modifiers;

	/** The bean type name. */
	private TypeName beanTypeName;

	/**
	 * Instantiates a new bind type context.
	 *
	 * @param builder
	 *            the builder
	 * @param beanTypeName
	 *            the bean type name
	 * @param modifiers
	 *            the modifiers
	 */
	public BindTypeContext(TypeSpec.Builder builder, TypeName beanTypeName, Modifier... modifiers) {
		this.builder = builder;
		// XXX
		this.initBuilder = MethodSpec.methodBuilder("init").addModifiers(Modifier.PUBLIC);
		this.beanTypeName = beanTypeName;
		this.alreadyGeneratedMethods = new HashSet<>();
		this.modifiers = modifiers;
	}

	/**
	 * Gets the bind mapper name.
	 *
	 * @param context
	 *            the context
	 * @param typeName
	 *            the type name
	 * @return the bind mapper name
	 */
	public String getBindMapperName(BindTypeContext context, TypeName typeName, ModelEntity<?> entity) {
		Pair<String, TypeName> names = BinderMapRegistry.getInstance().getMapperNames(typeName);
		String simpleName = names.value0;
		TypeName bindMapperName = names.value1;

		if (!alreadyGeneratedMethods.contains(simpleName)) {
			alreadyGeneratedMethods.add(simpleName);
			if (bindMapperName.equals(beanTypeName)) {
				context.builder.addField(FieldSpec.builder(bindMapperName, simpleName, modifiers)
						.addJavadoc("$T", bindMapperName).initializer("this").build());
			} else {
				List<Modifier> finalModifiers=Arrays.asList(modifiers);
				boolean needToBeInitialized=finalModifiers.contains(Modifier.STATIC);
				
				if (needToBeInitialized) {
					context.builder.addField(FieldSpec.builder(bindMapperName, simpleName, finalModifiers.toArray(new Modifier[finalModifiers.size()]))
							.addJavadoc("binder for type $T\n", typeName)
							.initializer("$T.mapperFor($T.class)", BinderUtils.class, typeName)
							.build());

					// store initialization info for mapper to use in init method (or constructor for shared preference or dao)
					// BinderMapRegistry.getInstance().registry(context.beanTypeName, typeName);
				} else {
					context.builder.addField(FieldSpec.builder(bindMapperName, simpleName, modifiers)
							.addJavadoc("binder for type $T\n", typeName)
							// .initializer("$T.mapperFor($T.class)", BinderUtils.class, typeName)
							.build());

					// store initialization info for mapper to use in init method (or constructor for shared preference or dao)
					BinderMapRegistry.getInstance().registry(context.beanTypeName, typeName);
				}

			}
		}

		return simpleName;
	}
}