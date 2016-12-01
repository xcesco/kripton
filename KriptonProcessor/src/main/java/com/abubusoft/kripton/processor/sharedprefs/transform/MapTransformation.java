package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.abubusoft.kripton.processor.utils.StringUtility;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class MapTransformation extends AbstractSPTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	protected Class<?> utilClazz;
	private ParameterizedTypeName listTypeName;
	private TypeName keyTypeName;
	private TypeName valueTypeName;

	public MapTransformation(ParameterizedTypeName clazz) {
		this.utilClazz = ProcessorHelper.class;

		this.listTypeName = clazz;
		this.keyTypeName = listTypeName.typeArguments.get(0);
		this.valueTypeName = listTypeName.typeArguments.get(1);
	}

	private Class<?> defineMapClass(ParameterizedTypeName mapTypeName) {
		if (mapTypeName.toString().startsWith(Map.class.getCanonicalName())) {
			// it's a list
			return HashMap.class;
		}
		try {
			return Class.forName(mapTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new KriptonClassNotFoundException(e);			
		}
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean readAll) {
		Class<?> mapClazz = defineMapClass(listTypeName);
		
		if (readAll) {
			methodBuilder.beginControlFlow("");
		}
		methodBuilder.addStatement("String temp=$L.getString($S, null)", preferenceName, property.getName());

		if (readAll) {
			methodBuilder.addCode("$L." + setter(beanClass, property) + (!property.isPublicOrPackageField() ? "(" : "=") + "", beanName);
		} else {
			methodBuilder.addCode("return ");
		}

		methodBuilder.addCode("($T.hasText(temp)) ? ", StringUtility.class);
		methodBuilder.addCode("$T.asMap(new $T<$T, $T>(), $T.class, $T.class, temp)", utilClazz, mapClazz, keyTypeName, valueTypeName, keyTypeName, valueTypeName);
		methodBuilder.addCode(": null");

		if (readAll) {
			methodBuilder.addCode((!property.isPublicOrPackageField() ? ")" : ""));
		}
		
		methodBuilder.addCode(";");
		
		if (readAll) {
			methodBuilder.endControlFlow();
		}
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
