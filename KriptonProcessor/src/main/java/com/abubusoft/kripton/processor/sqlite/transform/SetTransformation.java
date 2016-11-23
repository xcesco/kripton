package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class SetTransformation extends AbstractCompileTimeTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	private ParameterizedTypeName listTypeName;

	private TypeName rawTypeName;

	public SetTransformation(ParameterizedTypeName clazz) {
		this.listTypeName = clazz;
		this.rawTypeName = listTypeName.typeArguments.get(0);
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
			methodBuilder.addCode("$T.asByteArray($L)", ProcessorHelper.class, getter(beanName, beanClass, property));
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$T.asByteArray($L)", ProcessorHelper.class, objectName);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		Class<?> setClazz = defineSetClass(listTypeName);

		if (TypeUtility.isTypeWrappedPrimitive(rawTypeName)) {
			methodBuilder.addCode("$L." + setter(beanClass, property, "$T.asCollection(new $T<$L>(), $T.class, $L.getBlob($L))"), beanName, ProcessorHelper.class, setClazz, rawTypeName, rawTypeName, cursorName, indexName);
		} else {
			methodBuilder.addCode("$L." + setter(beanClass, property, "$T.asCollection(new $T<$T>(), $T.class, $L.getBlob($L))"), beanName, ProcessorHelper.class, setClazz, rawTypeName, rawTypeName, cursorName, indexName);
		}
	}

	private Class<?> defineSetClass(ParameterizedTypeName setTypeName) {
		if (setTypeName.toString().startsWith(Set.class.getCanonicalName())) {
			return HashSet.class;
		}
		try {
			return Class.forName(setTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		Class<?> setClazz = defineSetClass(listTypeName);

		methodBuilder.addCode("$T.asCollection(new $T<$T>(),$T.class, $L.getBlob($L))", CollectionUtility.class, ProcessorHelper.class, setClazz, rawTypeName, rawTypeName, cursorName, indexName);
	}*/

	/*@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}*/

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "null"), beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "BLOB";
	}
}
