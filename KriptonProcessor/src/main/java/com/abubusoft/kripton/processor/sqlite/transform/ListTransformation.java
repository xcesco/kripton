package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.ArrayList;

import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

public class ListTransformation extends AbstractCompileTimeTransform {

	static Converter<String, String> nc=CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
	
	private TypeName clazz;

	public ListTransformation(TypeName clazz) {
		this.clazz = clazz;
	}


	@Override
	public void generateWriteProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanName != null) {
			methodBuilder.addCode("$T.toByteArray($T.toList($L." + getter(beanClass, property) + ", $T.class))", DaoHelper.class, CollectionUtility.class, beanName, ArrayList.class);
		} else {
			generateWriteProperty(methodBuilder, property.getName());
		}
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String objectName) {
		methodBuilder.addCode("$T.toByteArray($T.toList($L, $T.class))", DaoHelper.class, CollectionUtility.class, objectName, ArrayList.class);
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		if (TypeUtility.isString(clazz)){
			methodBuilder.addCode("$L." + setter(beanClass, property, "$T.toStringArray($T.toList(String.class, $L.getBlob($L)))"), beanName, CollectionUtility.class, DaoHelper.class, cursorName, indexName);
		} else if (TypeUtility.isTypeWrappedPrimitive(clazz)){
			String name=nc.convert(clazz.toString().substring(clazz.toString().lastIndexOf(".")+1));
			methodBuilder.addCode("$L." + setter(beanClass, property, "$T.to$LArray($T.toList($L.class, $L.getBlob($L)))"), beanName, CollectionUtility.class, name, DaoHelper.class, name, cursorName, indexName);
		} else {
			String name=nc.convert(clazz.toString().substring(clazz.toString().lastIndexOf(".")+1));			
			methodBuilder.addCode("$L." + setter(beanClass, property, "$T.toArray($T.toList($L.class, $L.getBlob($L)))"), beanName, CollectionUtility.class, DaoHelper.class, name, cursorName, indexName);
		}
	}

	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$T.toList($T.class, $L.getBlob($L))", CollectionUtility.class, DaoHelper.class, clazz, cursorName, indexName);
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		methodBuilder.addCode("(writeToByteArray($T.class, $L." + setter(beanClass, property, "null") + ")", clazz, beanName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "BLOB";
	}
}
