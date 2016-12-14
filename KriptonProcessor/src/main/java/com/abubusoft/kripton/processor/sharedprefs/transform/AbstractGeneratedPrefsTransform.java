package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

public abstract class AbstractGeneratedPrefsTransform extends AbstractPrefsTransform {

	protected static Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean readAll) {
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}
		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getName());

		if (readAll) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicField() ? "(" : "=") + "", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		methodBuilder.addCode("$T.hasText(temp) ? ", StringUtils.class);
		methodBuilder.addCode("parse$L(temp)", formatter.convert(property.getName()));
		methodBuilder.addCode(": null");

		if (readAll) {
			methodBuilder.addCode((!property.isPublicField() ? ")" : ""));
		}

		methodBuilder.addCode(";\n");

		if (readAll) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		Converter<String, String> formatter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		methodBuilder.addStatement("String temp=serialize$L($L)", formatter.convert(property.getName()), getter(beanName, beanClass, property));
		methodBuilder.addStatement("$L.putString($S,temp)", editorName, property.getName());
		methodBuilder.nextControlFlow(" else ");
		methodBuilder.addStatement("$L.remove($S)", editorName, property.getName());
		methodBuilder.endControlFlow();

	}
}
