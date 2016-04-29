package com.abubusoft.kripton.processor.core;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.reflect.MethodAnalyzer;

public class KriptonPropertyAnalyzer {

	private static final String SET_PREFIX = "set";
	private static final String IS_PREFIX = "is";
	private static final String GET_PREFIX = "get";

	public static void analyze(KriptonClass currentKriptonClass, List<? extends Element> list) {
		// Recupera prima di tutto i field
		for (Element item : list) {
			if (item.getKind() == ElementKind.FIELD && modifierIsAcceptable(item)) {
				KriptonProperty field = new KriptonProperty(item);
				field.setType(new KriptonType(item.asType()));
				field.setPublicField(item.getModifiers().contains(Modifier.PUBLIC));
				currentKriptonClass.add(field);
			}
		}

		// Analizziamo i metodi
		//
		String methodName;
		String propertyName = "";
		KriptonProperty currentKriptonField;

		Converter<String, String> converter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		int status = 0;

		for (Element item : list) {
			methodName = item.getSimpleName().toString();
			if (item.getKind() == ElementKind.METHOD && item.getModifiers().contains(Modifier.PUBLIC)) {
				status = -1;
				if (methodName.startsWith(GET_PREFIX)) {
					status = 0;
					propertyName = methodName.substring(GET_PREFIX.length());
				} else if (methodName.startsWith(IS_PREFIX)) {
					status = 1;
					propertyName = methodName.substring(IS_PREFIX.length());
				} else if (methodName.startsWith(SET_PREFIX)) {
					status = 2;
					propertyName = methodName.substring(SET_PREFIX.length());
				}

				propertyName = converter.convert(propertyName);
				if (currentKriptonClass.contains(propertyName)) {
					currentKriptonField = currentKriptonClass.get(propertyName);
					Pair<String, String> result = MethodAnalyzer.extractResultAndArguments(item.asType().toString());

					if (currentKriptonField.getType().isSameType(result.value1)) {
						switch (status) {
						case 0:
							currentKriptonField.setFieldWithGetter(true);
							break;
						case 1:
							currentKriptonField.setFieldWithIs(true);
							break;
						case 2:
							currentKriptonField.setFieldWithSetter(true);
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}

	static boolean modifierIsAcceptable(Element item) {
		Object[] values = { Modifier.NATIVE, Modifier.STATIC, Modifier.FINAL, Modifier.ABSTRACT };

		for (Object i : values) {
			if (item.getModifiers().contains(i))
				return false;
		}

		return true;
	}

}
