package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class MapTransformation extends AbstractCompileTimeTransform {

	static Converter<String, String> nc=CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
	
	private ParameterizedTypeName mapTypeName;

	private TypeName keyTypeName;
	private TypeName valueTypeName;

	public MapTransformation(ParameterizedTypeName clazz) {
		this.mapTypeName = clazz;
		this.keyTypeName=mapTypeName.typeArguments.get(0);
		this.valueTypeName=mapTypeName.typeArguments.get(1);
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanName != null) {
			methodBuilder.addCode("$T.asByteArray($L." + getter(beanClass, property) + ")", ProcessorHelper.class, beanName);
		} else {
			generateWriteProperty(methodBuilder, property.getName());
		}
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$T.asByteArray($L)", ProcessorHelper.class, objectName);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		Class<?> mapClazz=defineMapClass(mapTypeName);
		
		methodBuilder.addCode("$L." + setter(beanClass, property, "$T.asMap(new $T<$T,$T>(), $T.class, $T.class, $L.getBlob($L))"), beanName, ProcessorHelper.class, mapClazz, keyTypeName, valueTypeName, keyTypeName, valueTypeName, cursorName, indexName);
	}

	private Class<?> defineMapClass(ParameterizedTypeName typeName) {
		if (typeName.toString().startsWith(Map.class.getCanonicalName()))
		{
			// it's a list
			return HashMap.class;
		}
		try {
			return Class.forName(typeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}


	/*@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		String keyName=nc.convert(keyTypeName.toString().substring(keyTypeName.toString().lastIndexOf(".")+1));
		String valueName=nc.convert(valueTypeName.toString().substring(valueTypeName.toString().lastIndexOf(".")+1));
		
		Class<?> mapClazz=defineMapClass(mapTypeName);
		
		methodBuilder.addCode("$T.asMap(new $T<$L,$L>(), $T.class, $T.class, $L.getBlob($L))", ProcessorHelper.class, mapClazz, keyName, valueName, keyTypeName, valueTypeName, cursorName, indexName);
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
