package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public abstract class AbstractCollectionTransform extends AbstractSPTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	protected Class<?> utilClazz;
	
	private ParameterizedTypeName collectionTypeName;
	
	private TypeName itemTypeName;

	public AbstractCollectionTransform(ParameterizedTypeName clazz) {
		this.utilClazz = ProcessorHelper.class;

		this.collectionTypeName = clazz;
		this.itemTypeName = collectionTypeName.typeArguments.get(0);
	}

	protected abstract Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName);

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean add) {
		Class<?> listClazz = defineCollectionClass(collectionTypeName);

		methodBuilder.beginControlFlow("");
		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getName());		
		
		if (add) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicOrPackageField() ? "(" : "=") + "", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		methodBuilder.addCode("$T.hasText(temp) ? ", StringUtility.class);
		methodBuilder.addCode("$T.asCollection(new $T<$T>(), $T.class, temp)", utilClazz, listClazz, itemTypeName, itemTypeName);
		methodBuilder.addCode(": null");

		if (add) {
			methodBuilder.addCode((!property.isPublicOrPackageField() ? ")" : ""));
		}

		methodBuilder.addCode(";\n");
		methodBuilder.endControlFlow();
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass != null) {
			methodBuilder.addCode("if ($L!=null) ", getter(beanName, beanClass, property));
			methodBuilder.addCode("$L.putString($S,$T.asString($L))", editorName, property.getName(), utilClazz, getter(beanName, beanClass, property));
			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
		} else {
			methodBuilder.addCode("if ($L!=null) ", beanName);
			methodBuilder.addCode("$L.putString($S,$T.asString($L))", editorName, property.getName(), utilClazz, beanName);
			methodBuilder.addCode(";");
			methodBuilder.addCode(" else ");
			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
		}

	}
}
