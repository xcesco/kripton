package com.abubusoft.kripton.processor.sharedprefs.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class SetTransformation extends AbstractSPTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	protected Class<?> utilClazz;
	private ParameterizedTypeName listTypeName;
	private TypeName rawTypeName;

	public SetTransformation(ParameterizedTypeName clazz) {
		this.utilClazz = ProcessorHelper.class;

		this.listTypeName = clazz;
		this.rawTypeName = listTypeName.typeArguments.get(0);

		this.nullable = true;
	}

	protected boolean nullable;

	private Class<?> defineListClass(ParameterizedTypeName setTypeName) {
		if (setTypeName.toString().startsWith(Set.class.getCanonicalName())) {
			// it's a list
			return HashSet.class;
		}
		try {
			return Class.forName(setTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, String preferenceName, TypeName beanClass, String beanName, ModelProperty property, boolean add) {
		String name = nc.convert(rawTypeName.toString().substring(rawTypeName.toString().lastIndexOf(".") + 1));
		Class<?> listClazz = defineListClass(listTypeName);

		if (add) {

			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter() ? "(" : "=") + "", beanName);
		}

		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		methodBuilder.addCode("$T.asCollection(new $T<$L>(), $L.class, $L.getString($S, null))", utilClazz, listClazz, name, name, preferenceName, property.getName());
		methodBuilder.addCode(": null");

		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter() ? ")" : ""));
		}
	}

	@Override
	public void generateWriteProperty(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, ModelProperty property) {
		if (beanClass != null) {
			methodBuilder.addCode("if ($L." + getter(beanClass, property) + "!=null) ", beanName);
			methodBuilder.addCode("$L.putString($S,$T.asString($L." + getter(beanClass, property) + "))", editorName, property.getName(), utilClazz, beanName);
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
