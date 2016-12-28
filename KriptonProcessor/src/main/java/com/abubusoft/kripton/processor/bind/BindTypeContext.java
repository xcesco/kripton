package com.abubusoft.kripton.processor.bind;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
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

	public String getBindMapperName(BindTypeContext context, BindProperty property) {
		Converter<String, String> format = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		TypeName bindMapperName=TypeUtility.mergeTypeName(property.getPropertyType().getName(),BindTypeBuilder.SUFFIX);
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
						.initializer("$T.mapperFor($T.class)", AbstractContext.class, property.getPropertyType().getName())
						.build());	
			}
			
			
			
			/*MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(simpleName)					
					.addModifiers(modifiers)
					.returns(bindMapperName);
			methodBuilder.beginControlFlow("if ($L==null)", simpleName);
				methodBuilder.addStatement("$L=$T.mapperFor($T.class)", simpleName, AbstractContext.class, property.getPropertyType().getName());
			methodBuilder.endControlFlow();
			
			methodBuilder.addStatement("return $L", simpleName);
			context.builder.addMethod(methodBuilder.build());*/
		}
		
		return simpleName;
	}
}