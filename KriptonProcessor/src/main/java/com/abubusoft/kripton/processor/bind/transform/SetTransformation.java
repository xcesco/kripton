package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class SetTransformation extends AbstractBindTransform {

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
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		/*
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
		}*/
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String editorName, TypeName beanClass, String beanName, BindProperty property) {
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

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

}
